package kore.botssdk.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import kore.botssdk.R;
import kore.botssdk.models.Users;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private Context context;
    private Users[] authUsers;
    private EditText username , password;
    private ExecutorService executorService;
    private Handler handler;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        context = LoginActivity.this;

        login = (Button) findViewById(R.id.login);
        username = (EditText) findViewById(R.id.ctMail);
        password = (EditText) findViewById(R.id.ctPassword);

        executorService = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
        sharedPreferences = context.getSharedPreferences("userShareData", Context.MODE_PRIVATE);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                authUsers = readJsonFile();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        login.setOnClickListener(launchBotBtnOnClickListener);
                    }
                });
            }
        });
    }

    private Users[] readJsonFile() {
        String json = null;
        Users[] userList = null;
        try {
            InputStream is = context.getAssets().open("users/user.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new JSONObject(new String(buffer, "UTF-8")).getString("users");
            userList =  new ObjectMapper().readValue(json, Users[].class);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }
        return userList;
    }


    View.OnClickListener launchBotBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Users currentUser = checkUserAuthentication();
            if(currentUser != null){
                Intent login = new Intent(getApplicationContext(), DashboardActivity.class);
                login.putExtra("current", (Serializable) currentUser);
                startActivity(login);
                finish();
            }
        }
    };

    private Users checkUserAuthentication(){

        String uname = username.getText().toString();
        String upassword = password.getText().toString();

        for (Users obj: authUsers) {
            if(obj.getUserName().equalsIgnoreCase(uname) && obj.getPassword().equalsIgnoreCase(upassword)){
                return obj;
            }
        }
        return null;
    }
}
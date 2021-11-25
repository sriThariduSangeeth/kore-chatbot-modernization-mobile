package kore.botssdk.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import kore.botssdk.R;
import kore.botssdk.models.Users;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private Context context;
    private Users[] authUsers;
    private EditText username , password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        context = LoginActivity.this;

        login = (Button) findViewById(R.id.login);
        username = (EditText) findViewById(R.id.ctMail);
        password = (EditText) findViewById(R.id.ctPassword);
        login.setOnClickListener(launchBotBtnOnClickListener);
        authUsers = readJsonFile();
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
                login.putExtra("current",  currentUser);
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
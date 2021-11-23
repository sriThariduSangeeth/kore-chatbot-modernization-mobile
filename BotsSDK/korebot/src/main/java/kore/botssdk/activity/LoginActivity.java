package kore.botssdk.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import kore.botssdk.R;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(launchBotBtnOnClickListener);
    }


    View.OnClickListener launchBotBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent login = new Intent(getApplicationContext(), DashboardActivity.class);
            startActivity(login);
            finish();
        }
    };
}
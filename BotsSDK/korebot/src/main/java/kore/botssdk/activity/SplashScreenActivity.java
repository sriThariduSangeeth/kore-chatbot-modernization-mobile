package kore.botssdk.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import kore.botssdk.R;

public class SplashScreenActivity extends AppCompatActivity {

    Handler handler;
    ImageView image;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);

        image = (ImageView) findViewById(R.id.splash_img);
        text = (TextView) findViewById(R.id.splash_text);

        image.animate().alpha(3000).setDuration(0);
        text.animate().alpha(3000).setDuration(0);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(login);
                finish();
            }
        }, 4000);
    }
}
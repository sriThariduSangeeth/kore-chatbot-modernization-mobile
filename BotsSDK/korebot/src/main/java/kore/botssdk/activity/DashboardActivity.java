package kore.botssdk.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.UUID;

import kore.botssdk.R;
import kore.botssdk.fragment.NotifyFragment;
import kore.botssdk.fragment.ProfileFragment;
import kore.botssdk.fragment.SettingsFragment;
import kore.botssdk.fragment.TransactionFragment;
import kore.botssdk.listener.BotSocketConnectionManager;
import kore.botssdk.net.SDKConfiguration;
import kore.botssdk.utils.BundleUtils;
import kore.botssdk.utils.StringUtils;

public class DashboardActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavigationview);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(selectItem);
        floatingActionButton.setOnClickListener(launchBotBtnOnClickListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();
    }

    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnNavigationItemSelectedListener selectItem = item -> {
        Fragment selectFragment = null;

        switch (item.getItemId()){
            case R.id.profile:
                selectFragment = new ProfileFragment();
                break;
            case R.id.transaction:
                selectFragment = new TransactionFragment();
                break;
            case R.id.notifi:
                selectFragment = new NotifyFragment();
                break;
            case R.id.settings:
                selectFragment = new SettingsFragment();
                break;
        }
        assert selectFragment != null;
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectFragment).commit();
        return true;
    };

    @Override
    protected void onStart() {
        super.onStart();
    }



    @Override
    protected void onStop() {
        super.onStop();
    }


    View.OnClickListener launchBotBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isOnline()) {
                if(!SDKConfiguration.Client.isWebHook) {
                    SDKConfiguration.Client.identity = UUID.randomUUID().toString();
                    BotSocketConnectionManager.getInstance().startAndInitiateConnectionWithConfig(getApplicationContext(),null);
                    launchBotChatActivity();
                } else {
                    launchBotChatActivity();
                }

            } else {
                Toast.makeText(DashboardActivity.this, "No internet connectivity", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void launchBotChatActivity(){
        Intent intent = new Intent(getApplicationContext(), BotChatActivity.class);
        Bundle bundle = new Bundle();
        //This should not be null
        bundle.putBoolean(BundleUtils.SHOW_PROFILE_PIC, false);
        bundle.putString(BundleUtils.BOT_NAME, "CT BANK BOT1");
        bundle.putString(BundleUtils.BOT_NAME_INITIALS, "CT BANK BOT");
        intent.putExtras(bundle);

        startActivity(intent);
//        finish();
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}
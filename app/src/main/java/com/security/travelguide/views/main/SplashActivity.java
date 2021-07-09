package com.security.travelguide.views.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.security.travelguide.R;
import com.security.travelguide.helper.AppConstants;
import com.security.travelguide.helper.UserUtils;
import com.security.travelguide.views.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_splash);

            setUpView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpView() {
        try {
            String loginToken = UserUtils.getSharedPrefsString(SplashActivity.this, AppConstants.LOGIN_TOKEN);
            Log.d(TAG, "setUpView: login token: " + loginToken);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (loginToken.isEmpty()) {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    }
                }
            }, SPLASH_TIME_OUT);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
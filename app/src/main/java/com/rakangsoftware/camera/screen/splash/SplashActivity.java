package com.rakangsoftware.camera.screen.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.rakangsoftware.camera.screen.camera.OnboardingActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OnboardingActivity.start(this);
        overridePendingTransition(0,0);
        finish();
    }
}

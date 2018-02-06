package com.rakangsoftware.camera.screen.camera;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.rakangsoftware.camera.R;
import com.rakangsoftware.camera.screen.BaseActivity;
import com.rakangsoftware.camera.screen.splash.SplashActivity;

public class OnboardingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
    }

    @Override
    protected void permissionsGranted() {

    }

    @Override
    protected void permissionsDenied(final boolean b) {

    }

    public static void start(final Context context) {
        context.startActivity(new Intent(context, OnboardingActivity.class));
    }
}

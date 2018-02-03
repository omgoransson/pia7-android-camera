package com.rakangsoftware.camera.screen.denied;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.rakangsoftware.camera.R;
import com.rakangsoftware.camera.screen.onboarding.BaseActivity;
import com.rakangsoftware.camera.screen.onboarding.OnboardingActivity;

public class DeniedActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denied);

        findViewById(R.id.settings_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", v.getContext().getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (hasPermissions(this, PERMISSIONS)) {
            permissionsGranted();
        }
    }

/* Start function */

    public static void start(Context context) {
        context.startActivity(new Intent(context, DeniedActivity.class));
    }

    @Override
    protected void permissionsGranted() {
        OnboardingActivity.start(this);
        overridePendingTransition(0, 0);
        finish();
    }

    @Override
    protected void permissionsDenied(final boolean b) {

    }
}

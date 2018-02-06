package com.rakangsoftware.camera.screen.denied;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.rakangsoftware.camera.R;
import com.rakangsoftware.camera.screen.BaseActivity;
import com.rakangsoftware.camera.screen.onboarding.OnboardingActivity;

public class DeniedActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denied);

        findViewById(R.id.denied_close).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                finish();
            }
        });

        findViewById(R.id.denied_settings).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent settingsIntent = new Intent();
                settingsIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", view.getContext().getPackageName(), null);
                settingsIntent.setData(uri);
                startActivity(settingsIntent);
            }
        });
    }


    public static void start(final Context context) {
        context.startActivity(new Intent(context, DeniedActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (hasPermissions(this, PERMISSIONS)) {
            OnboardingActivity.start(this);
            overridePendingTransition(0,0);
            finish();
        }
    }

    @Override
    protected void permissionsGranted() {

    }

    @Override
    protected void permissionsDenied(final boolean b) {

    }
}

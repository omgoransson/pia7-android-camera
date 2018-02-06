package com.rakangsoftware.camera.screen.onboarding;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.rakangsoftware.camera.R;
import com.rakangsoftware.camera.screen.BaseActivity;
import com.rakangsoftware.camera.screen.denied.DeniedActivity;

public class OnboardingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        if (hasPermissions(this, PERMISSIONS)) {
            permissionsGranted();
        } else {
            findViewById(R.id.onboarding_button)
                    .setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(final View view) {
                            checkPermissions(PERMISSIONS);
                        }
                    });
        }
    }

    @Override
    protected void permissionsGranted() {
        // Will go to camera

    }

    @Override
    protected void permissionsDenied(final boolean show) {
        if (show) {
            showDialog();
        } else {
            // Go to denied
            DeniedActivity.start(this);
            overridePendingTransition(0,0);
            finish();
        }
    }

    private void showDialog() {
        DialogInterface.OnClickListener dialogClickListener = new
                DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialogInterface, final int which) {
                switch (which) {
                    case DialogInterface.BUTTON_NEGATIVE:
                        requestPermissions(PERMISSIONS);
                        break;
                    case DialogInterface.BUTTON_POSITIVE:
                        break;
                }
            }
        };

        new AlertDialog.Builder(this)
                .setTitle(R.string.permission_title)
                .setMessage(R.string.dialog_explanation)
                .setPositiveButton(R.string.dialog_imsure, dialogClickListener)
                .setNegativeButton(R.string.dialog_retry, dialogClickListener)
                .show();
    }

    public static void start(final Context context) {
        context.startActivity(new Intent(context, OnboardingActivity.class));
    }
}

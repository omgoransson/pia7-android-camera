package com.rakangsoftware.camera.screen.onboarding;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.rakangsoftware.camera.R;
import com.rakangsoftware.camera.screen.camera.CameraActivity;
import com.rakangsoftware.camera.screen.denied.DeniedActivity;

public class OnboardingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        if (hasPermissions(this, PERMISSIONS)) {
            permissionsGranted();
        } else {
            findViewById(R.id.permissions_button)
                    .setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(final View v) {
                                    checkPermissions(PERMISSIONS);
                                }
                            }
                    );
        }
    }

    @Override
    protected void permissionsGranted() {
        CameraActivity.start(this);
        overridePendingTransition(0, 0);
        finish();
    }

    @Override
    protected void permissionsDenied(final boolean show) {
        if (show) {
            showDialog();
        } else {
            DeniedActivity.start(this);
            overridePendingTransition(0, 0);
            finish();
        }
    }

    private void showDialog() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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
                .setPositiveButton(R.string.dialog_ok, dialogClickListener)
                .setNegativeButton(R.string.dialog_mok, dialogClickListener)
                .show();
    }

    /* Start function */

    public static void start(Context context) {
        context.startActivity(new Intent(context, OnboardingActivity.class));
    }
}

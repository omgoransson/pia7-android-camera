package com.rakangsoftware.camera.screen;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    public static final String[] PERMISSIONS =
            {
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            };

    public static final int PERMISSIONS_REQUEST = 2341;

    /* Permissions */

    public void checkPermissions(final String[] permissions) {
        if (!hasPermissions(this, permissions)) {
            if (shouldShowRequestPermissionRationale(this, permissions)) {
                permissionsDenied(true);
            } else {
                requestPermissions(permissions);
            }
        } else {
            permissionsGranted();
        }
    }

    protected void requestPermissions(final String[] permissions) {
        ActivityCompat.requestPermissions(
                this,
                permissions,
                PERMISSIONS_REQUEST
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST: {
                if (checkPermissionsResult(grantResults)) {
                    permissionsGranted();
                } else {
                    permissionsDenied((shouldShowRequestPermissionRationale(this, permissions)));
                }
                break;
            }
        }
    }

    protected boolean shouldShowRequestPermissionRationale(Activity context, String... permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, permission)) {
                return true;
            }
        }

        return false;
    }

    protected boolean hasPermissions(Context context, String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }

    private boolean checkPermissionsResult(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }

    protected abstract void permissionsGranted();

    protected abstract void permissionsDenied(final boolean b);

}

package com.rakangsoftware.camera.screen.camera;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.Facing;
import com.otaliastudios.cameraview.Flash;
import com.otaliastudios.cameraview.Grid;
import com.rakangsoftware.camera.R;
import com.rakangsoftware.camera.screen.onboarding.OnboardingActivity;

public class CameraActivity extends AppCompatActivity {

    private CameraView mCameraView;
    private CameraViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mCameraView = findViewById(R.id.camera);

        mViewModel = ViewModelProviders.of(this).get(CameraViewModel.class);

        setupFlash();
        setupFacing();
        setupGrid();
        setupCameraCapture();
    }

    private void setupCameraCapture() {
        findViewById(R.id.captureButton).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                mCameraView.capturePicture();
            }
        });

        mCameraView.addCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(final byte[] picture) {
                mViewModel.saveFile(picture);
            }
        });
    }

    private void setupFlash() {
        final ImageView flashButton = findViewById(R.id.flashButton);

        flashButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                mViewModel.flashTapped();
            }
        });

        mViewModel.getFlash().observe(this, new Observer<Flash>() {
            @Override
            public void onChanged(@Nullable final Flash flash) {
                mCameraView.setFlash(flash);
                switch (flash) {
                    case ON:
                        flashButton.setImageResource(R.drawable.ic_flash_on);
                        break;
                    case OFF:
                        flashButton.setImageResource(R.drawable.ic_flash_off);
                        break;
                    case AUTO:
                        flashButton.setImageResource(R.drawable.ic_flash_auto);
                        break;
                }
            }
        });
    }

    private void setupFacing() {
        final ImageView facingButton = findViewById(R.id.facingButton);

        facingButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                mViewModel.facingTapped();
            }
        });

        mViewModel.getFacing().observe(this, new Observer<Facing>() {
            @Override
            public void onChanged(@Nullable final Facing facing) {
                mCameraView.setFacing(facing);
                switch (facing) {
                    case BACK:
                        facingButton.setImageResource(R.drawable.ic_camera_rear);
                        break;
                    case FRONT:
                        facingButton.setImageResource(R.drawable.ic_camera_front);
                        break;
                }
            }
        });
    }

    private void setupGrid() {
        final ImageView gridButton = findViewById(R.id.gridButton);

        gridButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                mViewModel.gridTapped();
            }
        });

        mViewModel.getGrid().observe(this, new Observer<Grid>() {
            @Override
            public void onChanged(@Nullable final Grid grid) {
                mCameraView.setGrid(grid);
                switch (grid) {
                    case OFF:
                        gridButton.setImageResource(R.drawable.ic_grid_off);
                        break;
                    case DRAW_3X3:
                        gridButton.setImageResource(R.drawable.ic_grid_on);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCameraView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCameraView.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCameraView.destroy();
    }


    public static void start(final Context context) {
        context.startActivity(new Intent(context, CameraActivity.class));
    }
}

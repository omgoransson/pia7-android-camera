package com.rakangsoftware.camera.screen.camera;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.Facing;
import com.otaliastudios.cameraview.Flash;
import com.otaliastudios.cameraview.Grid;
import com.rakangsoftware.camera.R;

public class CameraActivity extends AppCompatActivity {

    private CameraView mCameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        final CameraViewModel viewModel = ViewModelProviders.of(this).get(CameraViewModel.class);

        mCameraView = findViewById(R.id.camera);

        /* Flash */

        final ImageView flashButton = findViewById(R.id.flash_button);
        flashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                viewModel.flashTapped();
            }
        });
        viewModel.getFLash().observe(this, new Observer<Flash>() {
            @Override
            public void onChanged(@Nullable final Flash flash) {
                mCameraView.setFlash(flash);
                if (flash == Flash.ON) {
                    flashButton.setImageResource(R.drawable.ic_flash_on);
                } else if (flash == Flash.OFF) {
                    flashButton.setImageResource(R.drawable.ic_flash_off);
                } else if (flash == Flash.AUTO) {
                    flashButton.setImageResource(R.drawable.ic_flash_auto);
                }
            }
        });

        /* Grid */

        final ImageView gridButton = findViewById(R.id.grid_button);
        gridButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                viewModel.gridTapped();
            }
        });
        viewModel.getGrid().observe(this, new Observer<Grid>() {
            @Override
            public void onChanged(@Nullable final Grid grid) {
                mCameraView.setGrid(grid);
                if (grid == Grid.OFF) {
                    gridButton.setImageResource(R.drawable.ic_grid_off);
                } else if (grid == Grid.DRAW_3X3) {
                    gridButton.setImageResource(R.drawable.ic_grid_on);
                }
            }
        });

        /* Facing */

        final ImageView facingButton = findViewById(R.id.facing_button);
        facingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                viewModel.facingTapped();
            }
        });
        viewModel.getFacing().observe(this, new Observer<Facing>() {
            @Override
            public void onChanged(@Nullable final Facing facing) {
                mCameraView.setFacing(facing);
                if (facing == Facing.FRONT) {
                    facingButton.setImageResource(R.drawable.ic_camera_front);
                } else if (facing == Facing.BACK) {
                    facingButton.setImageResource(R.drawable.ic_camera_rear);
                }
            }
        });

        /* Capture Picture */

        findViewById(R.id.capture_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mCameraView.capturePicture();
            }
        });

        /* Save Picture */

        mCameraView.addCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(byte[] picture) {
                viewModel.saveFile(picture);
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

    /* Start Function */

    public static void start(Context context) {
        context.startActivity(new Intent(context, CameraActivity.class));
    }
}

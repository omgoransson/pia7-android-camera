package com.rakangsoftware.camera.screen.camera;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.otaliastudios.cameraview.Facing;
import com.otaliastudios.cameraview.Flash;
import com.otaliastudios.cameraview.Grid;
import com.rakangsoftware.camera.R;


class CameraViewModel extends ViewModel {

    private PictureRepository mPictureRepository;

    private MutableLiveData<Flash>  mFLash  = new MutableLiveData<>();
    private MutableLiveData<Grid>   mGrid   = new MutableLiveData<>();
    private MutableLiveData<Facing> mFacing = new MutableLiveData<>();

    public CameraViewModel() {
        mPictureRepository = new PictureRepository();

        mFLash.setValue(Flash.AUTO);
        mGrid.setValue(Grid.OFF);
        mFacing.setValue(Facing.BACK);
    }

    MutableLiveData<Flash> getFLash() {
        return mFLash;
    }

    void flashTapped() {
        if (mFLash.getValue() == Flash.ON) {
            mFLash.setValue(Flash.OFF);
        } else if (mFLash.getValue() == Flash.OFF) {
            mFLash.setValue(Flash.AUTO);
        } else if (mFLash.getValue() == Flash.AUTO) {
            mFLash.setValue(Flash.ON);
        }
    }

    MutableLiveData<Grid> getGrid() {
        return mGrid;
    }

    void gridTapped() {
        if (mGrid.getValue() == Grid.OFF) {
            mGrid.setValue(Grid.DRAW_3X3);
        } else if (mGrid.getValue() == Grid.DRAW_3X3) {
            mGrid.setValue(Grid.OFF);
        }
    }

    MutableLiveData<Facing> getFacing() {
        return mFacing;
    }

    void facingTapped() {
        if (mFacing.getValue() == Facing.BACK) {
            mFacing.setValue(Facing.FRONT);
        } else if (mFacing.getValue() == Facing.FRONT) {
            mFacing.setValue(Facing.BACK);
        }
    }

    void saveFile(final byte[] picture) {
        mPictureRepository.create(picture);
    }

}

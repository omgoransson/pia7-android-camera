package com.rakangsoftware.camera.screen.camera;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import com.otaliastudios.cameraview.Facing;
import com.otaliastudios.cameraview.Flash;
import com.otaliastudios.cameraview.Grid;

class CameraViewModel extends AndroidViewModel {

    private PictureRepository mPictureRepository;

    private MutableLiveData<Flash>  mFLash  = new MutableLiveData<>();
    private MutableLiveData<Grid>   mGrid   = new MutableLiveData<>();
    private MutableLiveData<Facing> mFacing = new MutableLiveData<>();

    public CameraViewModel(Application application) {
        super(application);

        mPictureRepository = new PictureRepository(application);

        mFLash.setValue(Flash.AUTO);
        mGrid.setValue(Grid.OFF);
        mFacing.setValue(Facing.BACK);
    }

    /* Flash */

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

    /* Grid */

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

    /* Facing */

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

    /* Save File */

    void saveFile(final byte[] picture) {
        mPictureRepository.create(picture);
    }

}

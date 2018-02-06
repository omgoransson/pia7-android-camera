package com.rakangsoftware.camera.screen.camera;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.otaliastudios.cameraview.Facing;
import com.otaliastudios.cameraview.Flash;
import com.otaliastudios.cameraview.Grid;

/**
 * Created by omgoransson on 2018-02-06.
 */

public class CameraViewModel extends AndroidViewModel {

    private PictureRepository mPictureRepository;

    private MutableLiveData<Flash> mFlash = new MutableLiveData<>();
    private MutableLiveData<Grid> mGrid = new MutableLiveData<>();
    private MutableLiveData<Facing> mFacing = new MutableLiveData<>();

    public CameraViewModel(@NonNull final Application application) {
        super(application);
        mPictureRepository = new PictureRepository(application);
        mFlash.setValue(Flash.AUTO);
        mGrid.setValue(Grid.OFF);
    }

    void saveFile(final byte[] picture) {
        mPictureRepository.create(picture);
    }

    void flashTapped() {
        switch (mFlash.getValue()) {
            case ON:
                mFlash.setValue(Flash.OFF);
                break;
            case OFF:
                mFlash.setValue(Flash.AUTO);
                break;
            case AUTO:
                mFlash.setValue(Flash.ON);
        }
    }

    void gridTapped() {
        switch (mGrid.getValue()) {
            case OFF:
                mGrid.setValue(Grid.DRAW_3X3);
                break;
            case DRAW_3X3:
                mGrid.setValue(Grid.OFF);
                break;
        }
    }

    void facingTapped() {
        switch (mFacing.getValue()) {
            case FRONT:
                mFacing.setValue(Facing.BACK);
                break;
            case BACK:
                mFacing.setValue(Facing.FRONT);
                break;
        }
    }

    public MutableLiveData<Flash> getFlash() {
        return mFlash;
    }

    public MutableLiveData<Grid> getGrid() {
        return mGrid;
    }

    public MutableLiveData<Facing> getFacing() {
        return mFacing;
    }
}

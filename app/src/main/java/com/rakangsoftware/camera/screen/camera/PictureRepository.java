package com.rakangsoftware.camera.screen.camera;

import android.app.Application;

import com.rakangsoftware.camera.R;

import java.io.File;

class PictureRepository {

    private final String mPictureFolder;

    PictureRepository(final Application application) {
        mPictureFolder = application.getResources().getString(R.string.folder_name);
    }

    void create(final byte[] picture) {
        File outputMediaFile = FileUtils.getOutputMediaFile(mPictureFolder);
        FileUtils.writeToFile(outputMediaFile, picture);
    }

}

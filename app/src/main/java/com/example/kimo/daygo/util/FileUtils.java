package com.example.kimo.daygo.util;

import android.os.Environment;

import org.apache.commons.logging.Log;

import java.io.File;

/**
 * Created by Administrator on 2016/1/8 0008.
 */
public class FileUtils  {

    private static String LOG_TAG = "file_utils";

    //External Storage
    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    //get public albumStorageDir
    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            LogUtils.logDebug(LOG_TAG, "Directory not created");
        }
        return file;
    }

    //get public movieStorageDir
    public File getMoviesStorageDir(String moviesName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_MOVIES), moviesName);
        if (!file.mkdirs()) {
            LogUtils.logDebug(LOG_TAG, "Directory not created");
        }
        return file;
    }
}

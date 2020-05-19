package com.vothanhhien.automarkmobile.utils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.vothanhhien.automarkmobile.constants.SC;

import org.opencv.core.Mat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {
    private static final String TAG = FileUtils.class.getSimpleName();

    public static boolean saveBitmap(Bitmap bitmap, String folder, String name) {
        // default args
        return saveBitmap(bitmap, folder, name, 100);
    }
    public static boolean saveMat(Mat mat, String folder, String name) {
        // default args
        Bitmap bitmap = Utils.matToBitmapRotate(mat);
        return saveBitmap(bitmap, folder, name, 100);
    }
    private static File touchFile(String folderpath, String filename) throws IOException {
        File file = new File(folderpath, filename);
        if (!file.exists())
            file.createNewFile();
        return file;
    }

    public static void checkMakeDirs(String folderpath) {
        File folder = new File(folderpath);
        if (!folder.exists()) {
            Log.d("custom" + TAG, "Making new directories for" + folderpath);

        }
        folder.mkdirs();
    }

    public static boolean saveBitmap(Bitmap bitmap, String folderpath, String filename, int mQuality) {
        Log.d("custom" + TAG, "saveBitmap: Saving image: " + folderpath + filename);

        try {
            checkMakeDirs(folderpath);
            File file = touchFile(folderpath, filename);
            FileOutputStream mFileOutputStream = new FileOutputStream(file);
            //Compress method used on the Bitmap object to write  image to output stream
            bitmap.compress(Bitmap.CompressFormat.JPEG, mQuality, mFileOutputStream);
            mFileOutputStream.close();
        } catch (Exception e) {
            Log.e("custom" + TAG, e.getMessage(), e);
            return false;
        }
        return true;
    }
    public static  Bitmap decodeBitmapFromFile(String path, String imageName) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeFile(new File(path, imageName).getAbsolutePath(),options);
    }
}

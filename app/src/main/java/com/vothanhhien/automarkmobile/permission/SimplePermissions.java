package com.vothanhhien.automarkmobile.permission;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class SimplePermissions {
    
    private static final String TAG = "SimplePermissions";
    private static final int MY_PERMISSIONS_REQUEST_TOKEN= 101;
    private String[] PermissionsList = {};
    private AppCompatActivity activity;
    public SimplePermissions(AppCompatActivity activity) {
        this.activity = activity;
    }
    public SimplePermissions(AppCompatActivity activity, String[] PermissionsList) {
        this.activity = activity;
        this.PermissionsList = PermissionsList;
    }
    public void grantPermissions() {
        if(!hasAllPermissions()) {
            Log.d(TAG, "Asking runtime Permissions");
        }
        AlertDialog.Builder mbox = new AlertDialog.Builder(activity)
                .setTitle("THÔNG BÁO")
                .setCancelable(false)
                .setMessage("Ứng dụng cần một số quyền sau để hoạt động\n" +
                        "1. Ghi vào bộ nhớ: Lưu trữ dữ liệu của ứng dụng.\n" +
                        "2. Máy ảnh: dùng để quét đáp án đề thi hoặc bài làm.\n")
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final AlertDialog.Builder mbox2 = new AlertDialog.Builder(activity);
                        // Quyền thứ nhất ghi vào bộ nhớ
                        ActivityCompat.requestPermissions(activity,
                               PermissionsList, MY_PERMISSIONS_REQUEST_TOKEN);
                    }
                })
                .setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Đóng ứng dụng và trở về
                       activity.finish();
                        return;
                    }
                });
        mbox.create();
        mbox.show();
    }

    public boolean hasAllPermissions() {
        for (String permission : this.PermissionsList) {
            if (ActivityCompat.checkSelfPermission(this.activity, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}

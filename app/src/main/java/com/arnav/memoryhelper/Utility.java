package com.arnav.memoryhelper;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

//28 Oct 2021
public class Utility {
    private static final int PERMISSION_REQUEST_CODE = 1240;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean checkAndRequestPermissions(Context context) {

        List<String> listPermissionNeeded = new ArrayList<>();

        String[] permissions = {
                //Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.SEND_SMS,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
//                Manifest.permission.RECEIVE_SMS
        };

        int currentAPIVersion = Build.VERSION.SDK_INT;

        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {

            for (String perm : permissions) {

                if (ContextCompat.checkSelfPermission(context, perm) != PackageManager.PERMISSION_GRANTED) {
                    listPermissionNeeded.add(perm);
                }
            }

            if (!listPermissionNeeded.isEmpty()) {
                ActivityCompat.requestPermissions((Activity) context, listPermissionNeeded.toArray(new String[listPermissionNeeded.size()]), PERMISSION_REQUEST_CODE);
                return false;
            }
        }

        return true;
    }
}

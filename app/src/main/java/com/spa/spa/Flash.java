//package com.spa.spa;
//
//import android.annotation.SuppressLint;
//import android.content.DialogInterface;
//import android.content.pm.PackageManager;
//import android.hardware.camera2.CameraAccessException;
//import android.hardware.camera2.CameraManager;
//import android.os.Build;
//
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//@SuppressLint("Registered")
//@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//
//public class Flash extends AppCompatActivity{
//    private CameraManager mCameraManager;
//    private String mCameraId;
//
//
//    public void flashEnable( ) {
//
//        boolean isFlashAvailable = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
//
//        if(!isFlashAvailable)
//        {
//            showNoFlashError();
//        }
//
//        mCameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
//
//        try {
//            mCameraId = mCameraManager.getCameraIdList()[0];
//        } catch (CameraAccessException ex) {
//            ex.printStackTrace();
//        }
//    }
//    public void showNoFlashError () {
//        AlertDialog alert = new AlertDialog.Builder(this).create();
//        alert.setTitle("oops!");
//        alert.setMessage("FLASH NOT AVAILABLE IN THIS DEVICE");
//        alert.setButton(DialogInterface.BUTTON_POSITIVE, "ok", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                finish();
//            }
//        });
//        alert.show();
//    }
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    public void switchFlashLight ( )
//    {
//        try {
//            mCameraManager.setTorchMode(mCameraId, true);
//        } catch (CameraAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
//}

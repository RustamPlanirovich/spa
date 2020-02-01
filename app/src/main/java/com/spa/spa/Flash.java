package com.spa.spa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import static android.content.Context.CAMERA_SERVICE;

@SuppressLint("Registered")
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

public class Flash  {
    private CameraManager mCameraManager;
    private String mCameraId;


//Проверяет есть ли возможность управляеть фонариком
    public void flashEnable(Context mcontext ) {

        boolean isFlashAvailable = mcontext.getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if(!isFlashAvailable)
        {
            //Если нет вызывает Alert диалог с сообщение об ошибке
            showNoFlashError(mcontext);
        }
//Создаем экземпляр сервиса для работы с фонариком
        mCameraManager = (CameraManager) mcontext.getSystemService(CAMERA_SERVICE);

        try {
            mCameraId = mCameraManager.getCameraIdList()[0];
        } catch (CameraAccessException ex) {
            ex.printStackTrace();
        }
    }

    //Класс при озникновении ошибки из-за отсутствия возможности управлять фонариком
    public void showNoFlashError (Context context) {
        AlertDialog alert = new AlertDialog.Builder(context).create();
        alert.setTitle("oops!");
        alert.setMessage("FLASH NOT AVAILABLE IN THIS DEVICE");
        alert.setButton(DialogInterface.BUTTON_POSITIVE, "ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               // finish();
            }
        });
        alert.show();
    }

    //Метод управления фонариком
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void switchFlashLight(Context mcontext,boolean on)
    {
        CameraManager mmCameraManager = (CameraManager) mcontext.getSystemService(CAMERA_SERVICE);
        try {
            mmCameraManager.setTorchMode(mCameraId, on);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

}

package com.app.w2meter.view;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.app.w2meter.MainActivity;
import com.app.w2meter.R;
import com.app.w2meter.utils.ConstantUtil;
import com.app.w2meter.utils.SharedManagerUtil;


public class SplashActivity extends AppCompatActivity {

    SharedManagerUtil session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        session=new SharedManagerUtil(SplashActivity.this);
        getVersionInfo();
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    if(session.isLogin()){
                        /*startActivity(new Intent(SplashActivity.this,MainActivity.class));
                        finish();*/
                        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                        finish();
                    }else{
                        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                        finish();
                    }
                }
            }
        };
        timerThread.start();
    }

    public void getVersionInfo() {
        String versionName = "";
        int versionCode = -1;
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionName = packageInfo.versionName;
            versionCode = packageInfo.versionCode;
            ConstantUtil.APP_VERSION=String.valueOf(packageInfo.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        ConstantUtil.DEVICE_ID= Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.d("Version name : ",versionName);
        Log.d("Version code : ",String.valueOf(versionCode));
    }
}

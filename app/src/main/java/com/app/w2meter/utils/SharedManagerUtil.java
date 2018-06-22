package com.app.w2meter.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by etrans on 24/4/18.
 */

public class SharedManagerUtil {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    public static final String PREF_NAME = "w2meterPref";

    // All Shared Preferences Keys
    public static final String IS_LOGIN = "isLogin";
    public static final String KEY_SESSION_ID = "sessionId";


    public static final String KEY_USER_NAME = "userName";
    public static final String KEY_VEHICLE_ID = "vehicleId";
    public static final String KEY_DEVICE_ID = "deviceId";
    public static final String KEY_ENTITY_ID = "entityId";




    // Constructor
    public SharedManagerUtil(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String jsession){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_SESSION_ID, jsession);
        editor.commit();
    }

    public void stopLoginSession(String vehicleId){
        editor.putBoolean(IS_LOGIN, false);
        editor.putString(KEY_SESSION_ID, vehicleId);
        editor.commit();
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
    }



    public boolean isLogin(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public String getUserName(){
        return pref.getString(KEY_USER_NAME, null);
    }

    public String getKeyJSessionId(){
        return pref.getString(KEY_SESSION_ID, null);
    }

    public String getKeyVehicleId() {
        return pref.getString(KEY_VEHICLE_ID, null);
    }

    public String getKeyDeviceId() {
        return pref.getString(KEY_DEVICE_ID, null);
    }

    public String getKeyEntityId() {
        return pref.getString(KEY_ENTITY_ID, null);
    }
}

package com.security.travelguide.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.security.travelguide.model.userDetails.UserMain;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class UserUtils {
    private static final String TAG = "UserUtils";

    public static boolean isEmptyField(String value) {
        return value.trim().isEmpty();
    }

    public static String getFieldValue(EditText editTextField) {
        return editTextField.getText().toString().trim();
    }

    public static void saveLoginUserDetails(Context context, UserMain userMain){
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.APP_PREFS, MODE_PRIVATE);
        Gson gson = new Gson();
        String loginUserDetails = gson.toJson(userMain);
        sharedPreferences.edit().putString(AppConstants.LOGIN_USER_DETAILS, loginUserDetails).apply();
    }

    public static UserMain getLoginUserDetails(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.APP_PREFS, MODE_PRIVATE);
        Gson gson = new Gson();
        String userDetailsString = sharedPreferences.getString(AppConstants.LOGIN_USER_DETAILS,"");
        return gson.fromJson(userDetailsString,UserMain.class);
    }

    public static void removeLoginUserDetails(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.APP_PREFS, MODE_PRIVATE);
        sharedPreferences.edit().remove(AppConstants.LOGIN_USER_DETAILS).apply();
    }

    public static String getSharedPrefsString(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.APP_PREFS, MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    public static void saveSharedPrefsString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.APP_PREFS, MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).apply();
    }

    public static void removeSharedPrefsString(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.APP_PREFS, MODE_PRIVATE);
        sharedPreferences.edit().remove(key).apply();
    }

    public static void removeAllDataWhenLogout(Context context){
        removeLoginUserDetails(context);
        removeSharedPrefsString(context,AppConstants.LOGIN_TOKEN);
    }
}

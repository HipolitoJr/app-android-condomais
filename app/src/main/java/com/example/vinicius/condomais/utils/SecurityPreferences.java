package com.example.vinicius.condomais.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SecurityPreferences {

    private Context context;
    private final SharedPreferences sharedPreferences;

    public SecurityPreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("condomais", Context.MODE_PRIVATE);
    }

    public void saveString(String key, String value){
        sharedPreferences.edit().putString(key, value).apply();
    }

    public String getSavedString(String key){
        return sharedPreferences.getString(key, "");
    }

    public void saveInt(String key, int value){
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public int getSavedInt(String key){
        return sharedPreferences.getInt(key, -1);
    }

    public void saveFloat(String key, float value){
        sharedPreferences.edit().putFloat(key, value).apply();
    }

    public float getSavedFloat(String key){
        return sharedPreferences.getFloat(key, -1F);
    }

    public void saveBoolean(String key, boolean value){
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public boolean getSavedBoolean(String key){
        return sharedPreferences.getBoolean(key, false);
    }

    public void saveLong(String key, long value){
        sharedPreferences.edit().putLong(key, value).apply();
    }

    public long getSavedLong(String key){
        return sharedPreferences.getLong(key, -1);
    }

    public void limpar(){
        sharedPreferences.edit().clear().commit();
    }

}

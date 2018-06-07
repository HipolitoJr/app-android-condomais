package com.example.vinicius.condomais.infra;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.vinicius.condomais.utils.Constants;
import com.example.vinicius.condomais.utils.SecurityPreferences;

public class FeaturesUtils {

    public static ProgressDialog initPgDialog(Context context, String mensagem){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(mensagem);
        return progressDialog;
    }

    public static String getToken(Context context){
        SecurityPreferences securityPreferences = new SecurityPreferences(context);
        return securityPreferences.getSavedString(Constants.TOKEN);
    }

}

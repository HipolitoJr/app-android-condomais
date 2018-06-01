package com.example.vinicius.condomais.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.vinicius.condomais.R;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        this.setTitle("Sobre");
    }
}

package com.juandanielmoncayo.veterinaria;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by USUARIO on 27/11/2017.
 */

public class AcercaDe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acerca_de);
    }
    public void salir(View v) {
        finish();
    }
}

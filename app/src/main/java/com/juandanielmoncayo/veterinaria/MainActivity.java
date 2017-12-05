package com.juandanielmoncayo.veterinaria;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_usuarios",null,1);

    }

    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.btnOpcionRegistro:
                miIntent=new Intent(MainActivity.this,RegistroUsuariosActivity.class);
                break;
            case R.id.btnRegistroMascota:
                miIntent=new Intent(MainActivity.this,RegistroMascotaActivity.class);
                break;
            //case R.id.btnConsultaIndividual:
                //miIntent=new Intent(MainActivity.this,ConsultarUsuariosActivity.class);
                //break;


            //case R.id.btnConsultaListaMascota:
                //miIntent=new Intent(MainActivity.this,ListaMascotasActivity.class);
                //break;
           // case R.id.btnConsultaListaPersonasRecycler:
             //   miIntent=new Intent(MainActivity.this,ListaPersonasRecycler.class);
               // break;

        }
        if (miIntent!=null){
            startActivity(miIntent);
        }


    }
    public void registromascota(View view) {
        Intent i = new Intent(this, RegistroMascotaActivity.class );
        startActivity(i);
    }
    public void acerca(View view) {
        Intent i = new Intent(this, AcercaDe.class );
        startActivity(i);
    }

    public void salir(View v) {
        finish();
    }
}

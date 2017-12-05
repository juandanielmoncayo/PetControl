package com.juandanielmoncayo.veterinaria;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.juandanielmoncayo.veterinaria.entidades.Usuario;
import com.juandanielmoncayo.veterinaria.entidades.Veterinaria;

/**
 * Created by USUARIO on 03/12/2017.
 */

public class DetalleVeterinariaActivity extends AppCompatActivity {
    TextView campoIdvet, campoNombrevet, campoTelefonovet, campoBarriovet;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_veterinaria);
        campoIdvet = (TextView) findViewById(R.id.idveterinaria);
        campoNombrevet = (TextView) findViewById(R.id.nombreveterinaria);
        campoTelefonovet = (TextView) findViewById(R.id.telefonoveterinaria);
        campoBarriovet = (TextView) findViewById(R.id.barrioveterinaria);

        Bundle objetoEnviado=getIntent().getExtras();
        Veterinaria user=null;

        if(objetoEnviado!=null){
            user= (Veterinaria) objetoEnviado.getSerializable("veterinaria");
            campoIdvet.setText(user.getIdVeterinaria().toString());
            campoNombrevet.setText(user.getNombreVeterinaria().toString());
            campoTelefonovet.setText(user.getTelefono().toString());
            campoBarriovet.setText(user.getBarrio().toString());

        }

    }
    public void salir(View view){
        finish();
    }
}

package com.juandanielmoncayo.veterinaria;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.juandanielmoncayo.veterinaria.entidades.Usuario;
import com.juandanielmoncayo.veterinaria.utilidades.Utilidades;

import java.util.ArrayList;


public class ConsultaComboActivity extends AppCompatActivity {

    EditText campoId,campoNombre,campoTelefono;

    Spinner comboPersonas;
    TextView txtNombre,txtDocumento,txtTelefono;
    ArrayList<String> listaPersonas;
    ArrayList<Usuario> personasList;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_combo);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        comboPersonas= (Spinner) findViewById(R.id.comboPersonas);

        txtDocumento= (TextView) findViewById(R.id.txtDocumento);
        txtNombre= (TextView) findViewById(R.id.txtNombre);
        txtTelefono= (TextView) findViewById(R.id.txtTelefono);
/**
        campoId= (EditText) findViewById(R.id.documentoId);
        campoNombre= (EditText) findViewById(R.id.campoNombreConsulta);
        campoTelefono= (EditText) findViewById(R.id.campoTelefonoConsulta); */

        consultarListaPersonas();

        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listaPersonas);

        comboPersonas.setAdapter(adaptador);

        comboPersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                if (position!=0){
                    txtDocumento.setText(personasList.get(position-1).getId().toString());
                    txtNombre.setText(personasList.get(position-1).getNombre());
                    txtTelefono.setText(personasList.get(position-1).getTelefono());


                }else{
                    txtDocumento.setText("");
                    txtNombre.setText("");
                    txtTelefono.setText("");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Usuario persona=null;
        personasList =new ArrayList<Usuario>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO,null);

        while (cursor.moveToNext()){
            persona=new Usuario();
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getString(2));

            Log.i("id",persona.getId().toString());
            Log.i("Nombre",persona.getNombre());
            Log.i("Tel",persona.getTelefono());

            personasList.add(persona);

        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaPersonas=new ArrayList<String>();
        listaPersonas.add(getResources().getString(R.string.seleccione));

        for(int i=0;i<personasList.size();i++){
            listaPersonas.add(personasList.get(i).getId()+" - "+personasList.get(i).getNombre());
        }

    }
    public void eliminar2(View view) {
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={txtDocumento.getText().toString()};
        //myNum = Integer.parseInt(et.getText().toString());
        Integer Documento = Integer.parseInt(txtDocumento.getText().toString());
        //Integer[] paramteros2={txtDocumento.getText()};
        //int i= Utilidades.
        //db.execSQL("SELECT CASE WHEN EXISTS (SELECT * FROM" + Utilidades.TABLA_MASCOTA + " WHERE " + Utilidades.CAMPO_ID_DUENIO + "=" + Documento + ") THEN CAST(1 AS BIT)ELSE CAST(0 AS BIT) END");


        try {
            //select nombre,telefono from usuario where codigo=?
            Cursor cursor=db.rawQuery("SELECT * FROM "+Utilidades.TABLA_MASCOTA+" WHERE "+Utilidades.CAMPO_ID_DUENIO+"=?",parametros);
            //db.rawQuery("SELECT * FROM "+Utilidades.TABLA_MASCOTA+" WHERE "+Utilidades.CAMPO_ID_DUENIO+"="+ID,parametros);
            //Cursor cursor=db.execSQL("SELECT * FROM "+Utilidades.TABLA_MASCOTA+" WHERE "+Utilidades.CAMPO_ID_DUENIO+"="+Documento);

            cursor.moveToFirst();
            //campoNombre.setText(cursor.getString(0));
            //campoTelefono.setText(cursor.getString(1));
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.error_eliminar)+cursor.getString(1), Toast.LENGTH_LONG).show();
            //Toast.makeText(getApplicationContext(),"El Usuario tiene mascotas no se puede eliminar"+parametros, Toast.LENGTH_LONG).show();
            //Toast.makeText(getApplicationContext(),
           // getResources().getString(R.string.medias), Toast.LENGTH_LONG).show();
        }catch (Exception e){
            //Toast.makeText(getApplicationContext(),"El documento no tiene mascotas", Toast.LENGTH_LONG).show();
            db.delete(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID+"=?",parametros);
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.eliminar_usuario_correcto), Toast.LENGTH_LONG).show();
            //limpiar();
        }
        db.close();
        Intent i = new Intent(this, Main2Activity.class );
        startActivity(i);
    }
    public void actualizar2(View view) {
        //Intent i = new Intent(this, AcercaDe.class );
        //startActivity(i);
    }

    private void consultar() {
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={campoId.getText().toString()};
        String[] campos={Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_TELEFONO};

        try {
            Cursor cursor =db.query(Utilidades.TABLA_USUARIO,campos,Utilidades.CAMPO_ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            campoNombre.setText(cursor.getString(0));
            campoTelefono.setText(cursor.getString(1));
            cursor.close();
        }catch (Exception e){
           // Toast.makeText(getApplicationContext(),"El documento no existe", Toast.LENGTH_LONG).show();
            limpiar();
        }

    }
    public boolean tienemascota(Integer ID) {
        SQLiteDatabase db = conn.getWritableDatabase();
        //db.execSQL("SELECT CASE WHEN EXISTS (SELECT * FROM" + Utilidades.TABLA_MASCOTA + " WHERE " + Utilidades.CAMPO_ID_DUENIO + "=" + ID + ") THEN CAST(1 AS BIT)ELSE CAST(0 AS BIT) END");

        try {

            String[] parametros={ID.toString()};
            Toast.makeText(getApplicationContext(),"El Usuario tiene mascotas,"+ID, Toast.LENGTH_LONG).show();
            //db.rawQuery("SELECT * FROM "+Utilidades.TABLA_MASCOTA+" WHERE "+Utilidades.CAMPO_ID_DUENIO+"="+ID,parametros);
            db.execSQL("SELECT * FROM "+Utilidades.TABLA_MASCOTA+" WHERE "+Utilidades.CAMPO_ID_DUENIO+"="+ID);
            //Toast.makeText(getApplicationContext(),"El Usuario tiene mascotas,"+parametros, Toast.LENGTH_LONG).show();
            db.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    private void limpiar(){
        campoNombre.setText("");
        campoTelefono.setText("");
    }
    public void salir(View v) {
        finish();
    }


}

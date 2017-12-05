package com.juandanielmoncayo.veterinaria;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.juandanielmoncayo.veterinaria.entidades.Usuario;
import com.juandanielmoncayo.veterinaria.entidades.Veterinaria;
import com.juandanielmoncayo.veterinaria.utilidades.Utilidades;

import java.util.ArrayList;

/**
 * Created by USUARIO on 01/12/2017.
 */

public class ConsultarListaListViewVet extends AppCompatActivity {

    ListView listViewVeterinarias;
    static ArrayList<String> listaInformacion;
    static ArrayList<Veterinaria> listaVeterinarias;

    static ConexionSQLiteHelper conn;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_veterinarias);

        //registrarveterinarias();

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        listViewVeterinarias= (ListView) findViewById(R.id.listViewVeterinarias);

        listViewVeterinarias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                //String informacion="id: "+listaVeterinarias.get(pos).getIdVeterinaria()+"\n";
                //informacion+="Nombre: "+listaVeterinarias.get(pos).getNombreVeterinaria()+"\n";
                //informacion+="Telefono: "+listaVeterinarias.get(pos).getTelefono()+"\n";

                //Toast.makeText(getApplicationContext(),informacion, Toast.LENGTH_LONG).show();

                Veterinaria veterinaria=listaVeterinarias.get(pos);

                Intent intent;
                intent = new Intent(ConsultarListaListViewVet.this,DetalleVeterinariaActivity.class);

                Bundle bundle=new Bundle();
                bundle.putSerializable("veterinaria",veterinaria);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        consultarListaVeterinarias();

        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewVeterinarias.setAdapter(adaptador);
    }


    public static void consultarListaVeterinarias() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Veterinaria veterinaria=null;
        listaVeterinarias=new ArrayList<Veterinaria>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_VETERINARIA,null);

        while (cursor.moveToNext()){
            veterinaria=new Veterinaria();
            veterinaria.setIdVeterinaria(cursor.getInt(0));
            veterinaria.setNombreVeterinaria(cursor.getString(1));
            veterinaria.setTelefono(cursor.getString(2));
            veterinaria.setBarrio(cursor.getString(3));
            veterinaria.setLatitud(cursor.getDouble(4));
            veterinaria.setLongitud(cursor.getDouble(5));


            listaVeterinarias.add(veterinaria);
        }
        obtenerlista();
    }

    public static void obtenerlista() {
        listaInformacion=new ArrayList<String>();

        for (int i=0; i<listaVeterinarias.size();i++){
            listaInformacion.add(listaVeterinarias.get(i).getIdVeterinaria()+" - "
                    +listaVeterinarias.get(i).getNombreVeterinaria());
        }
    }
    public void salir(View v) {
        finish();
    }
}

package com.juandanielmoncayo.veterinaria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.juandanielmoncayo.veterinaria.utilidades.Utilidades;


public class ConexionSQLiteHelper extends SQLiteOpenHelper {



    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_USUARIO);
        db.execSQL(Utilidades.CREAR_TABLA_MASCOTA);
        db.execSQL(Utilidades.CREAR_TABLA_VETERINARIA);
        //db.execSQL("INSERT INTO "+ Utilidades.TABLA_VETERINARIA+" (idVeterinaria,nombreVeterinaria,telefono,barrio,latitud,longitud) VALUES (1,'Emervet','3182333259','Calvario',1.2190311,-77.2696942)");
        //db.execSQL("INSERT INTO "+ Utilidades.TABLA_VETERINARIA+" (idVeterinaria,nombreVeterinaria,telefono,barrio,latitud,longitud) VALUES (2,'SaludCan','7202020','Palermo',1.2225071,-77.27895480000001)");
        //db.execSQL("INSERT INTO "+ Utilidades.TABLA_VETERINARIA+" (idVeterinaria,nombreVeterinaria,telefono,barrio,latitud,longitud) VALUES (3,'Sabuesos','3182333259','Parque Infantil',1.2202082,-77.28122439999999)");
        //db.execSQL("INSERT INTO "+ Utilidades.TABLA_VETERINARIA+" (idVeterinaria,nombreVeterinaria,telefono,barrio,latitud,longitud) VALUES (4,'Centro medico veterinario Sn Francisco','3202726212','Calvario',1.2072351,-77.28766029999997)");
        //db.execSQL("INSERT INTO "+ Utilidades.TABLA_VETERINARIA+" (idVeterinaria,nombreVeterinaria,telefono,barrio,latitud,longitud) VALUES (5,'Sanat','4738473','Maridiaz',1.1974709,-77.27369379999999)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
       // db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_USUARIO);
        //db.rawQuery("DROP TABLE IF EXISTS "+Utilidades.TABLA_VETERINARIA);

    }
    public  void cargarveterinarias(SQLiteDatabase db){
        //db.rawQuery("DELETE * FROM "+Utilidades.TABLA_VETERINARIA,null);
        //db.execSQL("DELETE * FROM "+Utilidades.TABLA_VETERINARIA);
        try{
            db.execSQL("INSERT INTO `"+Utilidades.TABLA_VETERINARIA+"` (`"+Utilidades.CAMPO_ID_VETERINARIA+"`, `"+Utilidades.CAMPO_NOMBRE_VETERINARIA+"`, `"+Utilidades.CAMPO_TELEFONO_VETERINARIA+"`, `"+Utilidades.CAMPO_BARRIO+"`, `"+Utilidades.CAMPO_LATITUD+"`, `"+Utilidades.CAMPO_LONGITUD+"`) VALUES\n" +
                    "(1,'Emervet','3182333259','Calvario',1.2190311,-77.2696942),\n" +
                    "(2,'SaludCan','7202020','Palermo',1.2225071,-77.27895480000001),\n" +
                    "(3,'Sabuesos','3182333259','Parque Infantil',1.2202082,-77.28122439999999),\n" +
                    "(4,'Centro medico veterinario Sn Francisco','3202726212','Calvario',1.2072351,-77.28766029999997),\n" +
                    "(5,'Sanat','4738473','Maridiaz',1.1974709,-77.27369379999999);\n");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

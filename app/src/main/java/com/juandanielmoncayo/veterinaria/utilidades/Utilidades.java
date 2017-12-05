package com.juandanielmoncayo.veterinaria.utilidades;



public class Utilidades {

    //Constantes campos tabla usuario
    public static final String TABLA_USUARIO="usuario";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_TELEFONO="telefono";

    public static final String CREAR_TABLA_USUARIO="CREATE TABLE " +
            ""+TABLA_USUARIO+" ("+CAMPO_ID+" " +
            "INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_NOMBRE+" TEXT,"+CAMPO_TELEFONO+" TEXT)";

    //Constantes campos tabla mascota
    public static final String TABLA_MASCOTA="mascota";
    public static final String CAMPO_ID_MASCOTA="id_mascota";
    public static final String CAMPO_NOMBRE_MASCOTA="nombre_mascota";
    public static final String CAMPO_RAZA_MASCOTA="raza_mascota";
    public static final String CAMPO_ID_DUENIO="id_duenio";

    public static final String CREAR_TABLA_MASCOTA="CREATE TABLE " +
            ""+TABLA_MASCOTA+" ("+CAMPO_ID_MASCOTA+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_NOMBRE_MASCOTA+" TEXT, "+CAMPO_RAZA_MASCOTA+" TEXT,"+CAMPO_ID_DUENIO+" INTEGER)";

    //Constantes campos tabla veterinarias
    public static final String TABLA_VETERINARIA="veterinaria";
    public static final String CAMPO_ID_VETERINARIA="id_veterinaria";
    public static final String CAMPO_NOMBRE_VETERINARIA="nombre";
    public static final String CAMPO_TELEFONO_VETERINARIA="telefono";
    public static final String CAMPO_BARRIO="barrio";
    public static final String CAMPO_LATITUD="latitud";
    public static final String CAMPO_LONGITUD="longitud";

    public static final String CREAR_TABLA_VETERINARIA="CREATE TABLE " +
            ""+TABLA_VETERINARIA+" ("+CAMPO_ID_VETERINARIA+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_NOMBRE_VETERINARIA+" TEXT, "+CAMPO_TELEFONO_VETERINARIA+" TEXT,"+CAMPO_BARRIO+" TEXT, "+CAMPO_LATITUD+" DOUBLE, "+CAMPO_LONGITUD+" DOUBLE)";
     //public static  final String INSERTAR_VETERINARIAS="INSERT INTO "+TABLA_VETERINARIA+""


}

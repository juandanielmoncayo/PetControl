package com.juandanielmoncayo.veterinaria;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.juandanielmoncayo.veterinaria.entidades.Veterinaria;
import com.juandanielmoncayo.veterinaria.utilidades.Utilidades;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ConexionSQLiteHelper conn;
    ListView listViewVeterinarias;
    ArrayList<String> listaInformacion;
    ArrayList<Veterinaria> listaVeterinarias;
    float zoomLevel = 13;
    private Marker mimarcador;
    double milat = 0.0;
    double milong = 0.0;

    // Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        // Add a marker in Sydney and move the camera
        //LatLng cordenada = new LatLng();
        LatLng pasto = new LatLng(1.21361, -77.28111);
        LatLng sabuesos = new LatLng(1.2202082, -77.28122439999999);
        LatLng saludcan = new LatLng(1.2225071, -77.27895480000001);
        LatLng sana = new LatLng(1.1974709, -77.27369379999999);
        //miUbicacion();

        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sabuesos, zoomLevel));
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setAllGesturesEnabled(true);




    }

    public void onClick(View view) {
        Intent miIntent = null;
        // float zoomLevel = 13;
        switch (view.getId()) {
            case R.id.parques:
                mMap.clear();
                ubicacionparques();
                break;
            case R.id.veterinarias:
                mMap.clear();

                //Veterinaria veterinaria = null;
                ubicaionveterinarias();
                //miIntent=new Intent(MainActivity.this,RegistroMascotaActivity.class);
                break;
            case R.id.todos:
                mMap.clear();
                ubicaionveterinarias();
                ubicacionparques();
                //mMap.getCameraPosition();
                //miUbicacion();
                //miIntent=new Intent(MainActivity.this,ConsultarUsuariosActivity.class);
                break;


        }
        if (miIntent != null) {
            startActivity(miIntent);
        }
    }
    public void ubicacionparques(){
        LatLng pinfa = new LatLng(1.2194145, -77.28165460000002);
        LatLng unicentro = new LatLng(1.2163327, -77.28870669999998);
        LatLng sanfelipe = new LatLng(1.2140028, -77.28514129999996);

        //miIntent=new Intent(MainActivity.this,RegistroUsuariosActivity.class);
        //Toast.makeText(getApplicationContext(),"boton veterinarias ", Toast.LENGTH_SHORT).show();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pinfa, zoomLevel));
        mMap.addMarker(new MarkerOptions().position(pinfa).title(getResources().getString(R.string.infantil)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(unicentro, zoomLevel));
        mMap.addMarker(new MarkerOptions().position(unicentro).title(getResources().getString(R.string.unicentro)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sanfelipe, zoomLevel));
        mMap.addMarker(new MarkerOptions().position(sanfelipe).title(getResources().getString(R.string.sanfelipe)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));



    }
    public void ubicaionveterinarias(){
        consultarListaVeterinarias();
        for (int i = 0; i < listaVeterinarias.size(); i++) {

            LatLng coordenada = new LatLng(listaVeterinarias.get(i).getLatitud(), listaVeterinarias.get(i).getLongitud());
            //float zoomLevel = 15;
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenada, zoomLevel));
            //mMap.addMarker(new MarkerOptions().position(cordenada).title(listaVeterinarias.get(i).getNombreVeterinaria()+"\nTelefono: "+listaVeterinarias.get(i).getTelefono()));
            //listaInformacion.add(listaVeterinarias.get(i).getIdVeterinaria()+" - "
            //      +listaVeterinarias.get(i).getNombreVeterinaria());
            mMap.addMarker(new MarkerOptions().position(coordenada).title(listaVeterinarias.get(i).getNombreVeterinaria()).snippet(getResources().getString(R.string.telefono_usuario) + listaVeterinarias.get(i).getTelefono() + getResources().getString(R.string.barrio) + listaVeterinarias.get(i).getBarrio())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        }
    }

    private void consultarListaVeterinarias() {
        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();

        Veterinaria veterinaria = null;
        listaVeterinarias = new ArrayList<Veterinaria>();
        //select * from usuarios
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_VETERINARIA, null);

        while (cursor.moveToNext()) {
            veterinaria = new Veterinaria();
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

    public void obtenerlista() {
        listaInformacion = new ArrayList<String>();

        for (int i = 0; i < listaVeterinarias.size(); i++) {
            listaInformacion.add(listaVeterinarias.get(i).getIdVeterinaria() + " - "
                    + listaVeterinarias.get(i).getNombreVeterinaria());
        }
    }


}

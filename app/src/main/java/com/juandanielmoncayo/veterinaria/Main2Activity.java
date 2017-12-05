package com.juandanielmoncayo.veterinaria;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.juandanielmoncayo.veterinaria.utilidades.Utilidades;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //registrarVeterinarias();
        insertarvet();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // fragment = null;
                /*
                Fragment fragment = null;
                Class fragmentClass= AcercaDe.class;
                try{
                    fragment = (Fragment) fragmentClass.newInstance();
                }catch (Exception e){
                    e.printStackTrace();
                }
                FragmentManager fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
               /*
               Intent i = new Intent(MainActivity, AcercaDe.class );
               startActivity(i);
               */
                finish();
                //System. runFinalization();

            }

        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(),"EL de info", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, AcercaDe.class );
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent i = new Intent(this, MainActivity.class );
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(this, ListaPersonasRecycler.class );
            startActivity(i);

        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(this, ListaMascotasActivity.class );
            startActivity(i);


        } else if (id == R.id.nav_manage) {
            Intent i = new Intent(this, ConsultaComboActivity.class );
            startActivity(i);


        } else if (id == R.id.nav_share) {
            Intent i = new Intent(this, MapsActivity.class );
            startActivity(i);

        } else if (id == R.id.nav_send) {
            //registrarVeterinarias();
            Intent i = new Intent(this,ConsultarListaListViewVet.class );
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void acerca(View view) {
        Intent i = new Intent(this, AcercaDe.class );
        startActivity(i);
    }
    public void registrarVeterinarias() {

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_usuarios",null,1);

        SQLiteDatabase db=conn.getWritableDatabase();

        //insert into usuario (id,nombre,telefono) values (123,'Cristian','85665223')
        //db.execSQL("INSERT INTO "+ Utilidades.TABLA_VETERINARIA+" (idVeterinaria,nombreVeterinaria,telefono,barrio,latitud,longitud) VALUES (1,'Emervet','3182333259','Calvario',1.2190311,-77.2696942)");

        String insert1="INSERT INTO "+ Utilidades.TABLA_VETERINARIA
                +" ( " +Utilidades.CAMPO_ID_VETERINARIA+","+Utilidades.CAMPO_NOMBRE_VETERINARIA+","+Utilidades.CAMPO_TELEFONO_VETERINARIA+","+Utilidades.CAMPO_BARRIO+","+Utilidades.CAMPO_LATITUD+","+Utilidades.CAMPO_LONGITUD+")" +
                " VALUES ( VALUES (1,'Emervet','3182333259','Calvario',1.2190311,-77.2696942))";

        db.execSQL(insert1);


        db.close();

    }
    private void insertarvet(){
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_usuarios",null,1);

        SQLiteDatabase db=conn.getWritableDatabase();
        //SQLHelper db=new SQLHelper(this);
        //db.opendb();
        conn.cargarveterinarias(db);

    }
}

package com.example.teamleaguebagit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Collections;

public class Clasificacion extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView navigationBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasificacion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView titulo = findViewById(R.id.toolbar_title);

        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationBottom  = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        navigationBottom.setSelectedItemId(R.id.clasificacion);
        navigationBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent i;
                switch (menuItem.getItemId()) {
                    case R.id.inicio:
                        i = new Intent(Clasificacion.super.getApplication(), Homepage.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        break;
                    case R.id.ali:
                        i = new Intent(Clasificacion.super.getApplication(), Alineacion.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        break;
                    case R.id.mercado:
                        i = new Intent(Clasificacion.super.getApplication(), Mercado.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        break;
                    case R.id.jornada:
                        i = new Intent(Clasificacion.super.getApplication(), Jornada.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        break;
                    case R.id.clasificacion:
                        break;
                }
                return false;

            }
        });


    }

    //Pulsar para atrás
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Atención");
        alert.setMessage("¿Desea salir de la aplicación?");
        alert.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finishAffinity();
                System.exit(0);
            }
        });
        alert.setPositiveButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alert.show();
    }

    //Crear menu lateral
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    //Opciones para menu lateral
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.salir:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Atención");
                alert.setMessage("¿Desea cerrar sesión?");
                alert.setNegativeButton("Cerrar sesión", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
                alert.setPositiveButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.show();
                break;
            case R.id.config:
                break;
        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

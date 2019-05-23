package com.example.teamleaguebagit;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Button crear,unir;
    BottomNavigationView navigationBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView titulo = findViewById(R.id.toolbar_title);


        crear = findViewById(R.id.crearId);
        unir = findViewById(R.id.unirseId);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationBottom  = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        navigationBottom.setSelectedItemId(R.id.inicio);
        navigationBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent i;
                switch (menuItem.getItemId()) {
                    case R.id.inicio:
//                        i = new Intent(Homepage.super.getApplication(), Homepage.class);
//                        startActivity(i);
                        break;
                    case R.id.ali:
                        i = new Intent(Homepage.super.getApplication(), Alineacion.class);
                        startActivity(i);
                        break;
                    case R.id.mercado:
                        i = new Intent(Homepage.super.getApplication(), Mercado.class);
                        startActivity(i);
                        break;
                    case R.id.jornada:
                        i = new Intent(Homepage.super.getApplication(), Jornada.class);
                        startActivity(i);
                        break;
                    case R.id.clasificacion:
                        i = new Intent(Homepage.super.getApplication(), Clasificacion.class);
                        startActivity(i);
                        break;
                }
                return false;

            }

            private void lanzarActividad() {

            }
        });


    }

    public void unirseLiga(View view){
        System.out.println("unir");
    }

    public  void crearLiga(View view){
        System.out.println("crear");

    }

    //Pulsar para atrás
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
            case R.id.inicio:
                System.out.println("unir");
                break;
            case R.id.ali:
                System.out.println("unir");
                break;
            case R.id.mercado:
                System.out.println("unir");
                break;
            case R.id.jornada:
                System.out.println("unir");
                break;
            case R.id.clasificacion:
                System.out.println("unir");
                break;
        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


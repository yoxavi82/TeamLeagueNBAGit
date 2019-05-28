package com.example.teamleaguebagit;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.teamleaguebagit.pojos.Jugadores;
import com.example.teamleaguebagit.pojos.Plantillas;

import java.util.ArrayList;

public class Clasificacion extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView navigationBottom;
    View formElementsView;
    ArrayList<lista_clasificacion> lista;
    ListView lv, lv_plantilla;
    TextView nombre_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasificacion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView titulo = findViewById(R.id.toolbar_title);
        LayoutInflater inflater = getLayoutInflater();
        formElementsView = inflater.inflate(R.layout.confirmar,  null);
        lista = new ArrayList<lista_clasificacion>();
        lv = (ListView) findViewById(R.id.lista_clasificacion);
        lv_plantilla = (ListView) findViewById(R.id.lv_plantilla);

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
                        startActivity(i);
                        break;
                    case R.id.ali:
                        i = new Intent(Clasificacion.super.getApplication(), Alineacion.class);
                        startActivity(i);
                        break;
                    case R.id.mercado:
                        i = new Intent(Clasificacion.super.getApplication(), Mercado.class);
                        startActivity(i);
                        break;
                    case R.id.jornada:
                        i = new Intent(Clasificacion.super.getApplication(), Jornada.class);
                        startActivity(i);
                        break;
                    case R.id.clasificacion:
                        i = new Intent(Clasificacion.super.getApplication(), Clasificacion.class);
                        startActivity(i);
                        break;
                }
                return false;

            }
        });


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

    public void initLista(){
        ArrayList <pojos.EquiposUsuarios> li = null;
        ArrayList<lista_clasificacion> lis = new ArrayList<lista_clasificacion>();
        for (pojos.EquiposUsuarios e: li){
            lista_clasificacion l = new lista_clasificacion(e.getUsuarios().getIdUsuario(), e.getPuntosTotales(), e.getUsuarios());
            lista.add(l);
        }
        //Falta ordenarlos
        AdapterListaClasificacionGeneral adapter = new AdapterListaClasificacionGeneral(this, lista);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LayoutInflater inflater = getLayoutInflater();
                final View view1 = inflater.inflate(R.layout.vista_usuario, null);
                final AlertDialog dialogo = new AlertDialog.Builder(Clasificacion.this).setView(view1).create();
                nombre_usuario = view1.findViewById(R.id.vista_usuario_nombre);
                nombre_usuario.setText(lista.get(position).user.getIdUsuario());
                ArrayList<Jugadores> plantilla = null; //consulta a base de datos sobre plantilla entera sobre usuario
                AdapterListaPlantillaPorUsuario lista_p = new AdapterListaPlantillaPorUsuario(Clasificacion.this,plantilla);
                lv_plantilla.setAdapter(lista_p);
                //Hasta aqui esta rellenada el arrayList del dialogo abierto
                lv_plantilla.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        LayoutInflater inflater = getLayoutInflater();
                        final View view1 = inflater.inflate(R.layout.vista_jugador, null);
                        final AlertDialog dialogo = new AlertDialog.Builder(Clasificacion.this).setView(view1).create();
                        TextView nombre = view1.findViewById(R.id.vista_jugador_nombre);
                        TextView precio = view1.findViewById(R.id.vista_jugador_precio_compra);
                        TextView puntos = view1.findViewById(R.id.vista_jugador_puntos);
                        TextView equipo = view1.findViewById(R.id.vista_jugador_equipo);
                        TextView posicion = view1.findViewById(R.id.vista_jugador_pos);
                        TextView fecha = view1.findViewById(R.id.vista_jugador_fecha);
                        TextView precio_compra = view1.findViewById(R.id.vista_jugador_precio_compra);
                        Jugadores jug = null; //consulta datos jugador
                        Plantillas datos = null; //consulta por id de jugador + id liga para saber fecha y precio de compra
                        //añadir datos a los text view

                    }
                });
            }
        });
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

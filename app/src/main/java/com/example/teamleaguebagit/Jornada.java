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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.teamleaguebagit.Conexiones.EquipoUsuarioConexiones;
import com.example.teamleaguebagit.Conexiones.JugadorConexiones;
import com.example.teamleaguebagit.Conexiones.LigaConexiones;
import com.example.teamleaguebagit.Conexiones.PartidosConexiones;
import com.example.teamleaguebagit.Conexiones.PlantillaConexiones;
import com.example.teamleaguebagit.Conexiones.PuntuacionConexiones;
import com.example.teamleaguebagit.pojos.EquiposUsuarios;
import com.example.teamleaguebagit.pojos.Jugadores;
import com.example.teamleaguebagit.pojos.Partidos;
import com.example.teamleaguebagit.pojos.Plantillas;

import java.util.ArrayList;
import java.util.Date;

public class Jornada extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView navigationBottom;
    ListView lv;
    ArrayList<Jugadores> lista_puntucion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jornada);
        Toolbar toolbar = findViewById(R.id.toolbar);
        lv = findViewById(R.id.lv_jornada);


        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headView = navigationView.getHeaderView(0);
        TextView nombre = headView.findViewById(R.id.NombreUser);
        nombre.setText(Actual.getUsuarioActual().getNombre()+" "+Actual.getUsuarioActual().getApellidos());

        navigationBottom  = findViewById(R.id.bottom_navigation_view);
        navigationBottom.setSelectedItemId(R.id.jornada);
        navigationBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent i;
                switch (menuItem.getItemId()) {
                    case R.id.inicio:
                        i = new Intent(Jornada.super.getApplication(), Homepage.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        break;
                    case R.id.ali:
                        i = new Intent(Jornada.super.getApplication(), Alineacion.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        break;
                    case R.id.mercado:
                        i = new Intent(Jornada.super.getApplication(), Mercado.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        break;
                    case R.id.jornada:
                        break;
                    case R.id.clasificacion:
                        i = new Intent(Jornada.super.getApplication(), Clasificacion.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.Atencion);
        alert.setMessage(R.string.MensajeSalirApp);
        alert.setNegativeButton(R.string.Salir, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finishAffinity();
                System.exit(0);
            }
        });
        alert.setPositiveButton(R.string.Cancelar, new DialogInterface.OnClickListener() {
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
                alert.setTitle(R.string.Atencion);
                alert.setMessage(R.string.CerrarSesionPregunta);
                alert.setNegativeButton(R.string.CerrarSesion, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Actual.setIniciarSesion();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
                alert.setPositiveButton(R.string.Cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.show();
                break;
            case R.id.config:
                break;
        }
        return true;
    }

    public void initJornada(){
        PartidosConexiones Cpartido = new PartidosConexiones();
        final PlantillaConexiones Cplantilla = new PlantillaConexiones();
        JugadorConexiones Cjugadores = new JugadorConexiones();
        final PuntuacionConexiones Cpunt = new PuntuacionConexiones();
        final ArrayList<Partidos> partidos = Cpartido.getBySemana(new java.sql.Date(new Date().getDay()));
        final ArrayList<lista_jornada> lista_jornada = new ArrayList<lista_jornada>();
        final ArrayList <EquiposUsuarios> lista_usuarios = new EquipoUsuarioConexiones().getByLiga(Actual.getLigaActual().getIdLiga());
        for (EquiposUsuarios e : lista_usuarios){
            ArrayList<Plantillas> plantillas = Cplantilla.getTitulares(Actual.getLigaActual().getIdLiga(), e.getNombreEquipo());
            lista_jornada j = new lista_jornada(e.getUsuarios().getIdUsuario(), 0);
            int puntuacion = 0;
            for (Partidos p : partidos){
                for (Plantillas plan : plantillas){
                    if (e.getNombreEquipo().equals(p.getEquiposByIdLocal().getNombre()) | e.getNombreEquipo().equals(p.getEquiposByIdVisitante().getNombre())){
                        int pIndividual = Cpunt.getPuntuacionJugador(plan.getJugadores().getIdJugador(), p.getIdPartido());
                        puntuacion += pIndividual;
                    }
                }
            }
            j.setPuntuacion(puntuacion);
            lista_jornada.add(j);
        }
        AdapterListaJornada adapter = new AdapterListaJornada(this, lista_jornada);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LayoutInflater inflater = getLayoutInflater();
                final View view1 = inflater.inflate(R.layout.vista_jornada_jugadores_puntuaciones, null);
                ListView lv = view1.findViewById(R.id.lv_jornada);
                final AlertDialog dialogo = new AlertDialog.Builder(Jornada.this).setView(view1).create();
                ArrayList<lista_jornada> lista_jugadores = new ArrayList<lista_jornada>();
                ArrayList<Plantillas> plantillas = Cplantilla.getTitulares(Actual.getLigaActual().getIdLiga(), lista_usuarios.get(position).getNombreEquipo());
                for (Partidos p : partidos){
                    lista_jornada j = null;
                    for (Plantillas plan : plantillas){
                        if (lista_usuarios.get(position).getNombreEquipo().equals(p.getEquiposByIdLocal().getNombre()) | lista_usuarios.get(position).getNombreEquipo().equals(p.getEquiposByIdVisitante().getNombre())){
                            j.setNombre_usuario(plan.getJugadores().getNombre() + " " + plan.getJugadores().getApellido());
                            j.setPuntuacion(Cpunt.getPuntuacionJugador(plan.getJugadores().getIdJugador(), p.getIdPartido()));
                        }
                    }
                    lista_jugadores.add(j);
                }
                AdapterListaJornada adapter = new AdapterListaJornada(Jornada.this, lista_jugadores);
                lv.setAdapter(adapter);
            }
        });












    }
}

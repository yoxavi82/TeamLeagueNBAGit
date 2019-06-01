package com.example.teamleaguebagit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
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
import android.widget.Toast;

import com.example.teamleaguebagit.Conexiones.EquipoUsuarioConexiones;
import com.example.teamleaguebagit.Conexiones.JugadorConexiones;
import com.example.teamleaguebagit.Conexiones.PartidosConexiones;
import com.example.teamleaguebagit.Conexiones.PlantillaConexiones;
import com.example.teamleaguebagit.Conexiones.PuntuacionConexiones;
import com.example.teamleaguebagit.pojos.EquiposUsuarios;
import com.example.teamleaguebagit.pojos.Ligas;
import com.example.teamleaguebagit.pojos.Partidos;
import com.example.teamleaguebagit.pojos.Plantillas;

import java.util.ArrayList;
import java.util.Date;

import org.jetbrains.annotations.NotNull;

import static com.example.teamleaguebagit.Actual.ligasUsuarioActual;

public class Jornada extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView navigationBottom;
    NavigationView navView;
    ListView lv,lv2;
    TextView titulo,tituloJornada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jornada);
        Toolbar toolbar = findViewById(R.id.toolbar);
        lv = findViewById(R.id.lv_jornada);
        lv2 = findViewById(R.id.lv_jugadores);
        tituloJornada = findViewById(R.id.tituloJornada);
        tituloJornada.setText("Clasificación liga: "+Actual.getLigaActual().getIdLiga());
         navView = (NavigationView) findViewById(R.id.nav_view);

         titulo = findViewById(R.id.tituloJugadores);
        initMenu();
        initJornada();


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
                Actual.disconect();
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
                        Actual.disconect();
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
                Intent i = new Intent(this, Configuracion.class);
                startActivity(i);
                break;


            default:
                for(Ligas liga: ligasUsuarioActual){
                    if(item.getTitle().equals(liga.getIdLiga()))Actual.setLigaActual(liga);
                }
                Toast toast= Toast.makeText(this,"Liga "+item.getTitle()+" seleccionada", Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
        return true;
    }

    public void initJornada(){
        final PlantillaConexiones Cplantilla = new PlantillaConexiones();
        final ArrayList<Lista_jornada> lista_jornada = new ArrayList<Lista_jornada>();
        final ArrayList <EquiposUsuarios> lista_usuarios = new EquipoUsuarioConexiones().getByLiga(Actual.getLigaActual().getIdLiga());
        for (EquiposUsuarios e : lista_usuarios){
            Lista_jornada j = new Lista_jornada(e.getUsuarios().getIdUsuario(), 0);
            j.setPuntuacion(e.getPuntosTotales());
            lista_jornada.add(j);
        }

        AdapterListaJornada adapter = new AdapterListaJornada(this, lista_jornada);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                titulo.setText("Jugadores de "+lista_jornada.get(position).nombre_usuario);
                titulo.setVisibility(View.VISIBLE);
                ArrayList<Lista_jornada> lista_jugadores = new ArrayList<>();
                ArrayList<Plantillas> plantillas = Cplantilla.getByIdEquipo(Actual.getEquiposUsuariosSesion().get(position).getIdEquipo());
                for (Plantillas p : plantillas){
                    Lista_jornada j = new Lista_jornada();
                    j.setNombre_usuario(p.getJugadores().getNombre() + " " + p.getJugadores().getApellido());
                    j.setPuntuacion(p.getJugadores().getPuntosTotales());
                    lista_jugadores.add(j);
                }
                AdapterListaJornada adapter2 = new AdapterListaJornada(Jornada.this, lista_jugadores);
                lv2.setAdapter(adapter2);
            }
        });
    }

    @NotNull
    private Menu initMenu() {
        Menu m = navView.getMenu();
        m.findItem(R.id.ligas).getSubMenu().clear();
        for(int i = 0; i< Actual.getLigaSesion().size(); i++) {
            m.findItem(R.id.ligas).getSubMenu().add(Actual.getLigaSesion().get(i).getIdLiga());
        }
        return m;
    }
}

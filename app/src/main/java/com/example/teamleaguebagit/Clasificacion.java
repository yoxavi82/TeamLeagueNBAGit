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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.teamleaguebagit.Conexiones.EquipoConexiones;
import com.example.teamleaguebagit.Conexiones.EquipoUsuarioConexiones;
import com.example.teamleaguebagit.Conexiones.LigaConexiones;
import com.example.teamleaguebagit.Conexiones.PlantillaConexiones;
import com.example.teamleaguebagit.pojos.EquiposUsuarios;
import com.example.teamleaguebagit.pojos.Jugadores;
import com.example.teamleaguebagit.pojos.Plantillas;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import static com.example.teamleaguebagit.Actual.ligasUsuarioActual;

public class Clasificacion extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView navigationBottom;
    View formElementsView;
    ArrayList<lista_clasificacion> lista;
    ListView lv, lv_plantilla;
    TextView nombre_usuario;
    ArrayList<Jugadores> plantilla;
    ArrayList<Date> fechasCompra;
    ArrayList<Integer> precioCompras;
    NavigationView navView;


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

        navView = (NavigationView) findViewById(R.id.nav_view);

        initMenu();

        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headView = navigationView.getHeaderView(0);
        TextView nombre = headView.findViewById(R.id.NombreUser);
        nombre.setText(Actual.getUsuarioActual().getNombre()+" "+Actual.getUsuarioActual().getApellidos());
        initLista();

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

    public void initLista(){
        ArrayList <EquiposUsuarios> li = new EquipoUsuarioConexiones().getByLiga(Actual.getLigaActual().getIdLiga());
        ArrayList<lista_clasificacion> lis;
        for (EquiposUsuarios e: li){
            lista_clasificacion l = new lista_clasificacion(e.getUsuarios().getIdUsuario(), e.getPuntosTotales(), e.getUsuarios());
            lista.add(l);
        }
        lis = ordenar(lista);
        AdapterListaClasificacionGeneral adapter = new AdapterListaClasificacionGeneral(this, lis);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LayoutInflater inflater = getLayoutInflater();
                final View view1 = inflater.inflate(R.layout.vista_usuario, null);
                final AlertDialog dialogo = new AlertDialog.Builder(Clasificacion.this).setView(view1).create();
                nombre_usuario = view1.findViewById(R.id.vista_usuario_nombre);
                nombre_usuario.setText(lista.get(position).user.getIdUsuario());
                PlantillaConexiones lista_jugadores = new PlantillaConexiones();
                ArrayList <Plantillas> plan = lista_jugadores.getByIdLiga(Actual.getLigaActual().getIdLiga());
                plantilla = new ArrayList<Jugadores>();
                fechasCompra = new ArrayList<Date>();
                precioCompras = new ArrayList<Integer>();
                for (Plantillas a : plan){
                    if (a.getId().getIdJugador().equals(nombre_usuario.getText().toString())){
                        plantilla.add(a.getJugadores());
                        fechasCompra.add(a.getFechaCompra());
                        precioCompras.add(a.getPrecio());
                    }
                }
                AdapterListaPlantillaPorUsuario lista_p = new AdapterListaPlantillaPorUsuario(Clasificacion.this,plantilla);
                lv_plantilla.setAdapter(lista_p);
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
                        Jugadores jug = plantilla.get(position);
                        EquipoUsuarioConexiones e = new EquipoUsuarioConexiones();
                        nombre.setText(jug.getNombre() + " " + jug.getApellido());
                        precio.setText(jug.getPrecioMercado());
                        puntos.setText(jug.getPuntosTotales());
                        equipo.setText(e.getEquipo(jug.getIdJugador()).getEquipos().getNombre());
                        posicion.setText(jug.getPosicion());
                        fecha.setText(fechasCompra.get(position).getDate());
                        precio_compra.setText(precioCompras.get(position));

                    }
                });
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

    @NotNull
    private Menu initMenu() {
        Menu m = navView.getMenu();
        m.findItem(R.id.ligas).getSubMenu().clear();
        for(int i = 0; i< ligasUsuarioActual.size(); i++) {
            m.findItem(R.id.ligas).getSubMenu().add(ligasUsuarioActual.get(i).getIdLiga());
        }
        return m;

    }

    public ArrayList<lista_clasificacion> ordenar(ArrayList<lista_clasificacion> lista){
        Collections.reverse(lista);
        return lista;
    }
}

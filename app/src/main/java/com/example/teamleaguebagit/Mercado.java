package com.example.teamleaguebagit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
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
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teamleaguebagit.Conexiones.EquipoUsuarioConexiones;
import com.example.teamleaguebagit.Conexiones.JugadorConexiones;
import com.example.teamleaguebagit.Conexiones.LigaConexiones;
import com.example.teamleaguebagit.Conexiones.PlantillaConexiones;
import com.example.teamleaguebagit.pojos.EquiposUsuarios;
import com.example.teamleaguebagit.pojos.Jugadores;
import com.example.teamleaguebagit.pojos.Ligas;
import com.example.teamleaguebagit.pojos.Plantillas;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.example.teamleaguebagit.Actual.equiposUsuarios;
import static com.example.teamleaguebagit.Actual.ligasUsuarioActual;

public class Mercado extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView navigationBottom;
    View formElementsView;
    ArrayList<Jugadores> lista;
    ArrayList<Integer> fotos;
    ArrayList<Plantillas> plantillasMercado;
    ListView lv, lv_jugadoresPropios;
    EditText precio ;
    TextView  alerta_puja, saldo, saldo_proximo,nombreEquipo;
    EquiposUsuarios equiposUsuarios;
    ConstraintLayout container;

    NavigationView navView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mercado);
        equiposUsuarios = new EquipoUsuarioConexiones().getByLigaAndUser(
                Actual.getUsuarioActual().getIdUsuario(),Actual.getLigaActual().getIdLiga());
        saldo = findViewById(R.id.SaldoMercado);
        saldo.setText(equiposUsuarios.getDinero()+"€");
        nombreEquipo = findViewById(R.id.NombreEquipo);
        nombreEquipo.setText(equiposUsuarios.getNombreEquipo()+"");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView titulo = findViewById(R.id.toolbar_title);
        container = findViewById(R.id.constraintLayout2);
        navView = (NavigationView) findViewById(R.id.nav_view);

        Menu m = initMenu();
        MenuItem mi = m.getItem(m.size()-1);
        mi.setTitle(mi.getTitle());
        LayoutInflater inflater = getLayoutInflater();
        formElementsView = inflater.inflate(R.layout.confirmar,  null);
        lista = new ArrayList<Jugadores>();
        fotos = new ArrayList<>();
        lv = (ListView) findViewById(R.id.lista_mercado);
        try {
            initMercado();
        } catch (ParseException | SQLException eee) {
        }

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

        navigationBottom  = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        navigationBottom.setSelectedItemId(R.id.mercado);
        navigationBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent i;
                switch (menuItem.getItemId()) {
                    case R.id.inicio:
                        i = new Intent(Mercado.super.getApplication(), Homepage.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        break;
                    case R.id.ali:
                        i = new Intent(Mercado.super.getApplication(), Alineacion.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        break;
                    case R.id.mercado:
                        break;
                    case R.id.jornada:
                        i = new Intent(Mercado.super.getApplication(), Jornada.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        break;
                    case R.id.clasificacion:
                        i = new Intent(Mercado.super.getApplication(), Clasificacion.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        break;
                }
                return false;

            }
        });
        navigationBottom.setBackgroundColor(Color.parseColor(Colores.mapa.get(Actual.getEquipoActual().getEquipos().getIdEquipo())));
        container.setBackgroundColor(Color.parseColor("#"+Actual.getEquipoActual().getEquipos().getColor()));
        toolbar.setBackgroundColor(Color.parseColor(Colores.mapa.get(Actual.getEquipoActual().getEquipos().getIdEquipo())));

    }



    public void initMercado() throws ParseException, SQLException {
        PlantillaConexiones conexionesPlantilla = new PlantillaConexiones();
        plantillasMercado = conexionesPlantilla.getByIdLiga("ADMIN");
        JugadorConexiones conexionesJugadores = new JugadorConexiones();
        for(int i =0;i<plantillasMercado.size();i++){
            if (conexionesPlantilla.getNumberPlayer(Actual.getLigaActual().getIdLiga(),plantillasMercado.get(i).getJugadores().getIdJugador())==0){
                if(plantillasMercado.get(i).getPuja()==0){
                    Jugadores jugador = conexionesJugadores.getById(plantillasMercado.get(i).getJugadores().getIdJugador());
                    jugador.setPrecioMercado(jugador.getEstrellas()*1000000);
                    fotos.add(getResources().getIdentifier(jugador.getIdJugador().toLowerCase(),"drawable",getPackageName()));
                    lista.add(jugador);
                }
            }
        }
        AdapterListaMercado adapter = new AdapterListaMercado(this, lista,fotos);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                LayoutInflater inflater = getLayoutInflater();
                final View view1 = inflater.inflate(R.layout.confirmar_puja, null);
                final AlertDialog dialogo = new AlertDialog.Builder(Mercado.this).setView(view1).setCancelable(false).setPositiveButton("Confirmar", null).setNegativeButton("Cancelar", null).create();
                precio = view1.findViewById(R.id.preciopuja);
                alerta_puja = view1.findViewById(R.id.alerta_puja);
                alerta_puja.setVisibility(View.GONE);
                final int precioInicial = lista.get(position).getPrecioMercado();
                precio.setText(precioInicial + "");
                dialogo.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(final DialogInterface dialog) {
                        Button button = ((AlertDialog) dialogo).getButton(AlertDialog.BUTTON_POSITIVE);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int precioActual = Integer.parseInt(precio.getText().toString());
                                if (precioActual< precioInicial){
                                    if (alerta_puja.getVisibility() == View.GONE){
                                        alerta_puja.setVisibility(View.VISIBLE);
                                    }
                                }
                                else{
                                    PlantillaConexiones conexiones = new PlantillaConexiones();
                                    Plantillas p = new Plantillas();
                                    p.setJugadores(lista.get(position));
                                    p.setPrecio(precioActual);
                                    p.setLigas(Actual.getLigaActual());
                                    p.setPuja(1);
                                    p.setTitular(3);
                                    p.setFechaCompra(new java.sql.Date(new Date().getTime()));
                                    p.setEquiposUsuarios(equiposUsuarios);
                                    equiposUsuarios.setDinero(equiposUsuarios.getDinero()-precioActual);

                                    new EquipoUsuarioConexiones().updateEquipoUsuario(equiposUsuarios);
                                    conexiones.addPlantilla(p);
                                    dialogo.dismiss();
                                    Intent i = new Intent(getApplication(), Mercado.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(i);
                                }
                            }
                        });
                        Button button2 = ((AlertDialog) dialogo).getButton(AlertDialog.BUTTON_NEGATIVE);
                        button2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogo.dismiss();
                            }
                        });
                    }
                });
                dialogo.show();
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
                    if(item.getTitle().equals(liga.getIdLiga())){
                        Actual.setLigaActual(liga);
                        Actual.setEquipoActual(null);
                        i = new Intent(Mercado.super.getApplication(), Homepage.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }
                }
                Toast toast= Toast.makeText(this,"Liga "+item.getTitle()+" seleccionada", Toast.LENGTH_SHORT);
                toast.show();
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
}

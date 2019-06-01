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
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teamleaguebagit.Conexiones.JugadorConexiones;
import com.example.teamleaguebagit.Conexiones.LigaConexiones;
import com.example.teamleaguebagit.Conexiones.PlantillaConexiones;
import com.example.teamleaguebagit.pojos.EquiposUsuarios;
import com.example.teamleaguebagit.pojos.Jugadores;
import com.example.teamleaguebagit.pojos.Ligas;
import com.example.teamleaguebagit.pojos.Plantillas;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.example.teamleaguebagit.Actual.ligasUsuarioActual;

public class Mercado extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView navigationBottom;
    View formElementsView;
    ArrayList<Jugadores> lista;
    ArrayList<Plantillas> plantillasMercado;
    ListView lv, lv_jugadoresPropios;
    EditText precio ;
    TextView  alerta_puja, saldo, saldo_proximo;

    NavigationView navView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mercado);
        saldo = findViewById(R.id.SaldoMercado);
        ArrayList<EquiposUsuarios> e = Actual.getEquiposUsuariosSesion();
        for (EquiposUsuarios u : e){
            if (u.getUsuarios().equals(Actual.getUsuarioActual())){
                saldo.setText(u.getDinero());
            }
        }
        saldo_proximo = findViewById(R.id.SaldoMercadoFuturo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView titulo = findViewById(R.id.toolbar_title);
        navView = (NavigationView) findViewById(R.id.nav_view);

        Menu m = initMenu();
        MenuItem mi = m.getItem(m.size()-1);
        mi.setTitle(mi.getTitle());
        LayoutInflater inflater = getLayoutInflater();
        formElementsView = inflater.inflate(R.layout.confirmar,  null);
        lista = new ArrayList<Jugadores>();
        lv = (ListView) findViewById(R.id.lista_mercado);
        try {
            initMercado();
        } catch (ParseException eee) {
            eee.printStackTrace();
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
    }



    public void initMercado() throws ParseException {
        PlantillaConexiones conexionesPlantilla = new PlantillaConexiones();
        plantillasMercado = conexionesPlantilla.getByDate(new java.sql.Date( new Date().getTime()));
        JugadorConexiones conexionesJugadores = new JugadorConexiones();
        for(int i =0;i<plantillasMercado.size();i++){
            lista.add(conexionesJugadores.getById(plantillasMercado.get(i).getJugadores().getIdJugador()));
        }
        AdapterListaMercado adapter = new AdapterListaMercado(this, lista);
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
                                    Date myDate = new Date();
                                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                                    String d = df.format(myDate);
                                    Plantillas p = new Plantillas();
                                    p.setJugadores(lista.get(position));
                                    p.setPrecio(precioActual);
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

    public void anadirjugador(View v){
        LayoutInflater inflater = getLayoutInflater();
        final View view1 = inflater.inflate(R.layout.lista_jugadores_enviar_mercado, null);
        AlertDialog dialog = new AlertDialog.Builder(this).setView(view1).create();
        lv_jugadoresPropios = view1.findViewById(R.id.lv_jugadoresPropios_mercado);
        ArrayList<Plantillas> plan = Actual.getPlantillaActual();
        final ArrayList<Jugadores> listaJ = new ArrayList<Jugadores>();
        ArrayList<Integer> indices = new ArrayList<Integer>();
        for (int i = 0 ; i< plan.size(); i++){
            for (int e = 0; e< lista.size(); e++){
                if (plan.get(i).getJugadores().equals(lista.get(e))){
                    indices.add(i);
                }
            }
            listaJ.add(plan.get(i).getJugadores());
        }

        for (int i = 0; i < indices.size(); i++){
            listaJ.remove(indices.get(i));
        }
        AdapterListaMercado adapterListaMercado = new AdapterListaMercado(this, listaJ);
        lv_jugadoresPropios.setAdapter(adapterListaMercado);
        lv_jugadoresPropios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LayoutInflater inflater = getLayoutInflater();
                final View view1 = inflater.inflate(R.layout.confirmar_venta, null);
                final AlertDialog dialogo = new AlertDialog.Builder(Mercado.this).setView(view1).setCancelable(false).setPositiveButton("Confirmar", null).setNegativeButton("Cancelar", null).create();
                precio = view1.findViewById(R.id.preciopuja);
                alerta_puja = view1.findViewById(R.id.alerta_puja);
                alerta_puja.setVisibility(View.GONE);
                final int precioInicial = listaJ.get(position).getPrecioMercado();
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

    public void aceptarPuja(){
        Date horaDespertar = new Date(System.currentTimeMillis());
        Calendar c = Calendar.getInstance();
        c.setTime(horaDespertar);
        if (c.get(Calendar.HOUR_OF_DAY) >= 22) {
            c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 1);
        }

        c.set(Calendar.HOUR_OF_DAY, 8);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        horaDespertar = c.getTime();
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

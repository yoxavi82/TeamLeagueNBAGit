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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.teamleaguebagit.R;
import com.example.teamleaguebagit.pojos.Jugadores;

import java.util.ArrayList;

public class Mercado extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView navigationBottom;
    View formElementsView;
    ArrayList<Jugadores> lista;
    ListView lv;
    EditText precio ;
    TextView  alerta_puja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mercado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView titulo = findViewById(R.id.toolbar_title);
        LayoutInflater inflater = getLayoutInflater();
        formElementsView = inflater.inflate(R.layout.confirmar,  null);
        lista = new ArrayList<Jugadores>();
        lv = (ListView) findViewById(R.id.lista_mercado);
        initMercado();


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

    public void initMercado(){
        Jugadores j1 = new Jugadores("1", "Alvaro", "Vazquez", "5", 1, 20000000, "Interior", 5, 250, 97 );
        Jugadores j2 = new Jugadores("2", "Xavi", "Vazquez", "7", 1, 100000, "Exterior", 5, 25, 97 );
        Jugadores j3 = new Jugadores("3", "Alberto", "Vazquez", "10", 1, 20000, "Base", 5, 200, 97 );
        Jugadores j4 = new Jugadores("4", "LeBron", "James", "23", 1, 40000000, "Base", 5, 900, 97 );
        lista.add(j1);
        lista.add(j2);
        lista.add(j3);
        lista.add(j4);
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
                                    dialogo.dismiss();
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
}

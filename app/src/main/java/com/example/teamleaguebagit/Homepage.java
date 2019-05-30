package com.example.teamleaguebagit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import static com.example.teamleaguebagit.Actual.ligasList;

public class Homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Button crear,unir;
    BottomNavigationView navigationBottom;
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        navView = (NavigationView) findViewById(R.id.nav_view);

        if(ligasList==null)
            Actual.initArrayMenu();

        initMenu();
        Toolbar toolbar =findViewById(R.id.toolbar);
        TextView titulo = findViewById(R.id.toolbar_title);

        crear = findViewById(R.id.crearId);
        unir = findViewById(R.id.unirseId);
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
        navigationBottom.setSelectedItemId(R.id.inicio);
        navigationBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent i;
                switch (menuItem.getItemId()) {
                    case R.id.inicio:
                        break;
                    case R.id.ali:
                        if(isInLeague()) {
                            i = new Intent(Homepage.super.getApplication(), Alineacion.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }else{
                            errorLiga();
                        }
                        break;
                    case R.id.mercado:
                        if(isInLeague()) {
                            i = new Intent(Homepage.super.getApplication(), Mercado.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }else{
                            errorLiga();
                        }
                        break;
                    case R.id.jornada:
                        if(isInLeague()) {
                            i = new Intent(Homepage.super.getApplication(), Jornada.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }else{
                            errorLiga();
                        }
                        break;
                    case R.id.clasificacion:
                        if(isInLeague()) {
                            i = new Intent(Homepage.super.getApplication(), Clasificacion.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }else{
                            errorLiga();
                        }
                        break;
                }
                return false;
            }
        });
    }

    private boolean isInLeague() {
        //return Actual.ligaActual != null;
        return true;
    }

    private void errorLiga() {
        Toast.makeText(this,getString(R.string.SelectLeague),Toast.LENGTH_SHORT).show();
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

    public void unirseLiga(View view){
        Intent intent = new Intent(this, BuscarLigaActivity.class);
        startActivity(intent);
    }

    public void crearLiga(View view){
        Intent intent = new Intent(this, CrearLigaActivity.class);
        startActivity(intent);
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


                default:
                    Toast toast= Toast.makeText(this,item.getTitle()+"", Toast.LENGTH_LONG);

        }
        return true;
    }

    @NotNull
    private Menu initMenu() {
        Menu m = navView.getMenu();
        m.findItem(R.id.ligas).getSubMenu().clear();
        for(int i = 0; i< ligasList.size(); i++) {
            m.findItem(R.id.ligas).getSubMenu().add(ligasList.get(i).getIdLiga());
        }
        return m;

    }
}


package com.example.teamleaguebagit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

import com.example.teamleaguebagit.Conexiones.JugadorConexiones;
import com.example.teamleaguebagit.Conexiones.PlantillaConexiones;
import com.example.teamleaguebagit.pojos.Jugadores;
import com.example.teamleaguebagit.pojos.Plantillas;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Configuracion extends AppCompatActivity{
    EditText configuracion_nombre, configuracion_apellido, configuracion_contraseña;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuracion);
        configuracion_nombre = findViewById(R.id.configuracion_nombre);
        configuracion_apellido = findViewById(R.id.configuracion_apellido);
        configuracion_contraseña = findViewById(R.id.configuracion_contraseña);
        lv = findViewById(R.id.lv_misligas);
        configuracion_nombre.setText(Actual.getUsuarioActual().getNombre());
        configuracion_apellido.setText(Actual.getUsuarioActual().getApellidos());
    }

    public void initMisLigas() {

    }

}

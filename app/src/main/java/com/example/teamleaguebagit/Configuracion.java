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

import com.example.teamleaguebagit.Conexiones.EquipoUsuarioConexiones;
import com.example.teamleaguebagit.Conexiones.JugadorConexiones;
import com.example.teamleaguebagit.Conexiones.PlantillaConexiones;
import com.example.teamleaguebagit.Conexiones.UsuarioConexiones;
import com.example.teamleaguebagit.pojos.Jugadores;
import com.example.teamleaguebagit.pojos.Ligas;
import com.example.teamleaguebagit.pojos.Plantillas;
import com.example.teamleaguebagit.pojos.Usuarios;

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
        initMisLigas();
    }

    public void initMisLigas() {
        final ArrayList<Ligas> misLigas = Actual.getLigaSesion();
        AdapterListaConfiguracion adapter = new AdapterListaConfiguracion(this, misLigas);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                LayoutInflater inflater = getLayoutInflater();
                final View view1 = inflater.inflate(R.layout.confirmar_abandonar_liga, null);
                AlertDialog.Builder dialogo = new AlertDialog.Builder(Configuracion.this).setView(view1).setCancelable(false);
                dialogo.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        EquipoUsuarioConexiones con = new EquipoUsuarioConexiones();
                        int i = con.getByLigaAndUser(Actual.getUsuarioActual().getIdUsuario(),misLigas.get(position).getIdLiga()).getIdEquipo();
                        new PlantillaConexiones().deleteByTeamUser(i);
                        con.abandonar(i);
                        misLigas.remove(position);
                        Actual.setLigaActual(null);
                        Actual.setLigasUsuarioActual(null);
                        Intent intent = new Intent(getApplication(), Homepage.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
                dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                    }
                });

                dialogo.show();
            }
        });
    }

    public void update(View v){
        Usuarios u = Actual.getUsuarioActual();
        u.setApellidos(configuracion_apellido.getText().toString());
        u.setNombre(configuracion_nombre.getText().toString());
        UsuarioConexiones usuarioConexiones = new UsuarioConexiones();
        usuarioConexiones.update(u);
        Intent i = new Intent(this, Homepage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

}

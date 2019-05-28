package com.example.teamleaguebagit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.teamleaguebagit.Conexiones.LigaConexiones;
import com.example.teamleaguebagit.Conexiones.UsuarioConexiones;
import com.example.teamleaguebagit.pojos.Ligas;
import com.example.teamleaguebagit.pojos.PasswordLigas;
import com.example.teamleaguebagit.pojos.Usuarios;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class CrearLigaActivity extends AppCompatActivity {
    private EditText nombre_liga, contraseña;
    View v;
    ArrayList<lista_ligas> lista, lista2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_liga);
        nombre_liga = findViewById(R.id.id_liga);
        contraseña = findViewById(R.id.Contraseña);


    /*
        ArrayList<lista_ligas> lista = new ArrayList<lista_ligas>();

        ListView lv = (ListView) findViewById(R.id.listView);

        lista_ligas l = new lista_ligas("Los mejores pechos", "3/17");

        lista.add(l);

        AdapterListaBuscarLiga adapter = new AdapterListaBuscarLiga(this, lista);

        lv.setAdapter(adapter); */



    }
    public void registrarLiga(View v){
        crearLiga(nombre_liga.getText().toString(), contraseña.getText().toString());
    }

    public void crearLiga(String idLiga, String contra){
        LigaConexiones crearLiga = new LigaConexiones();
        UsuarioConexiones crearUsu = new UsuarioConexiones();
        if (crearLiga.get(idLiga) == null){
            Usuarios user = new Usuarios("Alvatros0000005", "Alvaro", "s", "aaa@gail.com",new java.sql.Date(new Date().getTime()) , 1);
            crearUsu.register(user);
            crearLiga.register(idLiga, user);
            Ligas liga = new Ligas(idLiga, user);
            PasswordLigas pass = new PasswordLigas(liga, contra);
            crearLiga.registerPass(pass);
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "El nombre de la liga ya esta en uso, utiliza otro", Toast.LENGTH_SHORT);
            toast.show();
        }

    }




}

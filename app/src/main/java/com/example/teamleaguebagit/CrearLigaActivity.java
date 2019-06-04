package com.example.teamleaguebagit;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teamleaguebagit.Conexiones.LigaConexiones;
import com.example.teamleaguebagit.Conexiones.UsuarioConexiones;
import com.example.teamleaguebagit.pojos.Ligas;
import com.example.teamleaguebagit.pojos.PasswordLigas;

public class CrearLigaActivity extends AppCompatActivity {
    private EditText nombre_liga, contraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_liga);
        nombre_liga = findViewById(R.id.id_liga);
        contraseña = findViewById(R.id.Contraseña);

    }
    public void registrarLiga(View v){
        crearLiga(nombre_liga.getText().toString(), contraseña.getText().toString());
    }

    public void crearLiga(String idLiga, String contra){
        Button boton = findViewById(R.id.registrar);
        LigaConexiones crearLiga = new LigaConexiones();
        UsuarioConexiones crearUsu = new UsuarioConexiones();
        if (crearLiga.get(idLiga) == null){
            crearLiga.register(idLiga, Actual.getUsuarioActual());
            Ligas liga = new Ligas(idLiga, Actual.getUsuarioActual());
            PasswordLigas pass = new PasswordLigas(liga, contra);
            if(crearLiga.registerPass(pass)){
                Intent i =new Intent(this, Creacion_equipo.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("liga",liga);
                startActivity(i);
            }
        }else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "El nombre de la liga ya esta en uso, utiliza otro", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}

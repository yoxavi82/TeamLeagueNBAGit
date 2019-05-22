package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RegisterView extends AppCompatActivity {
    TextView usuario,nombre,apellidos,correo;
    String fecha;
    CalendarView calendario;
    Button accionRegistrar;
    ArrayList<String> usuarios =null;
    boolean existe = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);
        getSupportActionBar().hide();
        usuario = findViewById(R.id.username);
        nombre = findViewById(R.id.Nombre);
        apellidos = findViewById(R.id.Apellidos);
        correo = findViewById(R.id.Correo);
        calendario = findViewById(R.id.Calendario);
        accionRegistrar = findViewById( R.id.registrar);
        sqlThread.start();

        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                fecha = dayOfMonth+"/"+month+"/"+year;
            }
        });

        usuario.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    for(int i =0;i<usuarios.size();i++){
                        if(usuarios.get(i)==usuario.getText().toString()){
                            existe=true;
                        }
                    }
                }
            }
        });
    }

    Thread sqlThread = new Thread() {
        public void run() {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://remotemysql.com/k0zCh3gTgb", "k0zCh3gTgb", "HD5V4w6oyv");
                String stsql = "Select Id_Usuario from Usuarios";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(stsql);
                while (rs.next()){
                    usuarios.add(rs.getString(0));
                    System.out.println( rs.getString(0) );
                }
                conn.close();
            } catch (SQLException se) {
                System.out.println("oops! No se puede conectar. Error: " + se.toString());
            } catch (ClassNotFoundException e) {
                System.out.println("oops! No se encuentra la clase. Error: " + e.getMessage());
            }
        }
    };


    public void registrar(View view)throws ParseException {
        if(usuario.getText().toString()==""||nombre.getText().toString()==""|apellidos.getText().toString()==""|
                correo.getText().toString()==""||fecha==null){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Introduce todos los campos", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
            Usuarios registrado = new Usuarios(usuario.getText().toString(),nombre.getText().toString(),
                    apellidos.getText().toString(),correo.getText().toString(),date1,0);
        }

    }
}

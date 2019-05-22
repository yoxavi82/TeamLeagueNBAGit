package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.pojos.Usuarios;

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
    String fechaDia,fechaMes,fechaAño;
    String fechaNacimiento;
//    CalendarView calendario;
    Spinner dia,mes,año;
    Button accionRegistrar;
    ArrayList<String> usuarios =new ArrayList<>();
    ArrayList<String> dias = new ArrayList<>();
    ArrayList<String> meses = new ArrayList<>();
    ArrayList<String> años = new ArrayList<>();

    boolean existe = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);
        usuario = findViewById(R.id.username);
        nombre = findViewById(R.id.Nombre);
        apellidos = findViewById(R.id.Apellidos);
        correo = findViewById(R.id.Correo);
        dia = findViewById(R.id.dia);
        mes = findViewById(R.id.mes);
        año = findViewById(R.id.año);
//        calendario = findViewById(R.id.Calendario);
        accionRegistrar = findViewById( R.id.registrar);
//        sqlThread.start();

        for(int i =1 ;i<=31;i++){
            dias.add(i+"");
            if(i<=12)meses.add(i+"");
        }
        for(int i =1900 ; i<2019;i++)años.add(i+"");


        final ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dias);
        dia.setAdapter(adaptador);
        dia.setOnItemSelectedListener(new  AdapterView.OnItemSelectedListener() {
            @Override
            public void  onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fechaDia= adaptador.getItem(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final ArrayAdapter<String>adaptador2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, meses);
        mes.setAdapter(adaptador2);
        mes.setOnItemSelectedListener(new  AdapterView.OnItemSelectedListener() {
            @Override
            public void  onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fechaMes = adaptador2.getItem(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final ArrayAdapter<String>adaptador3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, años);
        año.setAdapter(adaptador3);
        año.setOnItemSelectedListener(new  AdapterView.OnItemSelectedListener() {
            @Override
            public void  onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fechaAño = adaptador3.getItem(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });





//        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                fecha = dayOfMonth+"/"+month+"/"+year;
//            }
//        });

//        usuario.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(!hasFocus){
//                    for(int i =0;i<usuarios.size();i++){
//                        if(usuarios.get(i)==usuario.getText().toString()){
//                            existe=true;
//                        }
//                    }
//                }
//            }
//        });
    }

//    public Connection connection(){
//        Connection conexion = null;
//        try{
//
//
//        }catch (Exception e){
//
//        }
//        return  conexion;
//    }
//
//
//    Thread sqlThread = new Thread() {
//        public void run() {
//            try {
//                Class.forName("com.mysql.jdbc.Driver");
//                Connection conn = DriverManager.getConnection(
//                        "jdbc:mysql://remotemysql.com/k0zCh3gTgb", "k0zCh3gTgb", "HD5V4w6oyv");
//                String stsql = "Select Id_Usuario from Usuarios";
//                Statement st = conn.createStatement();
//                ResultSet rs = st.executeQuery(stsql);
//                while (rs.next()){
//                    usuarios.add(rs.getString(0));
//                    System.out.println( rs.getString(0) );
//                }
//                conn.close();
//            } catch (SQLException se) {
//                System.out.println("oops! No se puede conectar. Error: " + se.toString());
//            } catch (ClassNotFoundException e) {
//                System.out.println("oops! No se encuentra la clase. Error: " + e.getMessage());
//            }
//        }
//    };

    public  void crearFecha(){
        if(fechaAño!=null&&fechaDia!=null&&fechaMes!=null){
            int ayuda =0;
            switch (Integer.parseInt(fechaMes)){
                case 2:if(Integer.parseInt(fechaDia)>28) ayuda =1;
                        break;
                case 4:if(Integer.parseInt(fechaDia)>30) ayuda =1;
                    break;
                case 6:if(Integer.parseInt(fechaDia)>30) ayuda =1;
                    break;
                case 9: if(Integer.parseInt(fechaDia)>30) ayuda =1;
                    break;
                case 11:if(Integer.parseInt(fechaDia)>30) ayuda =1;
                    break;
                default:break;
            }
            if(ayuda==0){
                fechaNacimiento = fechaDia+"/"+fechaMes+"/"+fechaAño;
            }else{
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Fecha no valida", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }


    public void registrar(View view)throws ParseException {
        crearFecha();
        if(usuario.getText().toString().isEmpty()||nombre.getText().toString().isEmpty()|apellidos.getText().toString().isEmpty()|
                correo.getText().toString().isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Introduce todos los campos", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(fechaNacimiento);
            System.out.println(date1);
            Usuarios registrado = new Usuarios(usuario.getText().toString(),nombre.getText().toString(),
                    apellidos.getText().toString(),correo.getText().toString(),date1,0);
        }
    }
}

package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.pojos.Usuarios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RegisterView extends AppCompatActivity {
    TextView usuario,nombre,apellidos,correo;
    String fechaDia,fechaMes,fechaAño;
    String fechaNacimiento;
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
        accionRegistrar = findViewById( R.id.registrar);

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
    }


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

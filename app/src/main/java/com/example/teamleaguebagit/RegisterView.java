package com.example.teamleaguebagit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teamleaguebagit.pojos.Equipos;
import com.example.teamleaguebagit.pojos.Usuarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RegisterView extends AppCompatActivity {
    private final String user="k0zCh3gTgb";
    private final String password ="HD5V4w6oyv";
    private final String server ="remotemysql.com";
    private final String port="3306";
    private Statement st;
    private Connection con=null;
    private ResultSet rs;
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
                        getString(R.string.NoValidDate), Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }


    public void registrar(View view)throws ParseException {
        generarConexion(accionRegistrar);
        crearFecha();
        if(usuario.getText().toString().isEmpty()||nombre.getText().toString().isEmpty()|apellidos.getText().toString().isEmpty()|
                correo.getText().toString().isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(),
                    getString(R.string.ErrorEmptyFields), Toast.LENGTH_SHORT);
            toast.show();
        }else{
            Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(fechaNacimiento);
            System.out.println(date1);
            Usuarios registrado = new Usuarios(usuario.getText().toString(),nombre.getText().toString(),
                    apellidos.getText().toString(),correo.getText().toString(),date1,0);
        }
    }

    public ArrayList getEquipos(){
        /*ArrayList donde se almacenarán los objetos Agenda por cada iteración.*/
        ArrayList columnas = new ArrayList();
        try{
            String driver = "com.mysql.jdbc.Driver";
            String urlMySQL = "jdbc:mysql://remotemysql.com/"+user;
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(urlMySQL,user,password);
            if(con!=null){
                Toast toast = Toast.makeText(this,"Error",Toast.LENGTH_SHORT);
                toast.show();
                System.out.println(con.isValid(0)+"zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
            }
            String sql="Select Nombre,Id_Equipo,Color from Equipos";
            PreparedStatement prest = con.prepareStatement(sql);
            ResultSet res =prest.executeQuery();
            while (res.next()){
                Equipos equipo = new Equipos(rs.getString("Id_Equipo"),rs.getString("Nombre"),rs.getString("Color"));
                columnas.add(equipo);
            }
//            st = con.createStatement();
//            rs = st.executeQuery("Select ");
//            if(rs.first()){
//                do{
//                    Equipos equipo = new Equipos(rs.getString("Id_Equipo"),rs.getString("Nombre"),rs.getString("Color"));
//                    columnas.add(equipo);
//                }while(rs.next());
//                rs.last();
//            }
//            else{
//                Toast.makeText(this, "La base de datos consultada está vacía.", Toast.LENGTH_LONG).show();
//            }
        }catch(Exception ex){
            Toast.makeText(this, "Error al obtener resultados de la consulta Transact-SQL: "
                    + ex.getMessage(), Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
        finally{
            try {
//                rs.close();
//                st.close();
                con.close();
            } catch (SQLException e) {
//                e.printStackTrace();
            }
        }
        return columnas;
    }

    public void generarConexion(View view){
        String url = "jdbc:mysql://remotemysql.com/k0zCh3gTgb";
        String user = "k0zCh3gTgb";
        String pwd = "HD5V4w6oyv";
        Connection conexio = null;
        try {
            conexio = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                System.out.println(conexio.isValid(0));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

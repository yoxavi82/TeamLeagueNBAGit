package com.example.teamleaguebagit;

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

import com.example.teamleaguebagit.pojos.Usuarios;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class RegisterView extends AppCompatActivity {
    TextView usuario, nombre, apellidos, correo, Password;
    String fechaDia, fechaMes, fechaAño;
    String fechaNacimiento;
    Spinner dia, mes, año;
    String fecha;
    CalendarView calendario;
    Button accionRegistrar;
    ArrayList<String> usuarios = new ArrayList<>();
    ArrayList<String> dias = new ArrayList<>();
    ArrayList<String> meses = new ArrayList<>();
    ArrayList<String> años = new ArrayList<>();

    boolean existe = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);
        usuario = findViewById(R.id.id_liga);
        nombre = findViewById(R.id.Contraseña);
        apellidos = findViewById(R.id.Apellidos);
        correo = findViewById(R.id.Correo);
        dia = findViewById(R.id.dia);
        mes = findViewById(R.id.mes);
        año = findViewById(R.id.año);
        accionRegistrar = findViewById(R.id.registrar);

        for (int i = 1; i <= 31; i++) {
            dias.add(i + "");
            if (i <= 12) meses.add(i + "");
        }
        for (int i = 1900; i < 2019; i++) años.add(i + "");
        Password = findViewById(R.id.Password);
        accionRegistrar = findViewById(R.id.registrar);


        final ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dias);
        dia.setAdapter(adaptador);
        dia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fechaDia = adaptador.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, meses);
        mes.setAdapter(adaptador2);
        mes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fechaMes = adaptador2.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final ArrayAdapter<String> adaptador3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, años);
        año.setAdapter(adaptador3);
        año.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fechaAño = adaptador3.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    public void crearFecha() {
        if (fechaAño != null && fechaDia != null && fechaMes != null) {
            int ayuda = 0;
            switch (Integer.parseInt(fechaMes)) {
                case 2:
                    if (Integer.parseInt(fechaDia) > 28) ayuda = 1;
                    break;
                case 4:
                    if (Integer.parseInt(fechaDia) > 30) ayuda = 1;
                    break;
                case 6:
                    if (Integer.parseInt(fechaDia) > 30) ayuda = 1;
                    break;
                case 9:
                    if (Integer.parseInt(fechaDia) > 30) ayuda = 1;
                    break;
                case 11:
                    if (Integer.parseInt(fechaDia) > 30) ayuda = 1;
                    break;
                default:
                    break;
            }
            if (ayuda == 0) {
                fechaNacimiento = fechaDia + "/" + fechaMes + "/" + fechaAño;
            } else {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Fecha no valida", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }


            public void registrar (View view) throws ParseException {
                crearFecha();
                if (usuario.getText().toString().isEmpty() || nombre.getText().toString().isEmpty() | apellidos.getText().toString().isEmpty() |
                        correo.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Introduce todos los campos", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaNacimiento);
                    System.out.println(date1);
                    Usuarios registrado = new Usuarios(usuario.getText().toString(), nombre.getText().toString(),
                            apellidos.getText().toString(), correo.getText().toString(), date1, 0);
                }
            }

            public void registerUser (View view){
                String user = usuario.getText().toString();
                // Get NAme ET control value
                String name = nombre.getText().toString();
                // Get Email ET control value
                String email = correo.getText().toString();
                // Get Password ET control value
                String password = Password.getText().toString();

                Long edad = calendario.getDate();
                // Instantiate Http Request Param Object
                RequestParams params = new RequestParams();
                // When Name Edit View, Email Edit View and Password Edit View have values other than Null
                if (Utility.isNotNull(name) && Utility.isNotNull(email) && Utility.isNotNull(password)) {
                    // When Email entered is Valid
                    if (Utility.validate(email)) {
                        params.put("user", user);
                        // Put Http parameter name with value of Name Edit View control
                        params.put("nombre", name);
                        // Put Http parameter username with value of Email Edit View control
                        params.put("correo", email);

                        params.put("edad", edad);
                        // Put Http parameter password with value of Password Edit View control
                        params.put("password", password);
                        // Invoke RESTful Web Service with Http parameters
                        invokeWS(params);
                    }
                    // When Email is invalid
                    else {
                        Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_LONG).show();
                    }
                }
                // When any of the Edit View control left blank
                else {
                    Toast.makeText(getApplicationContext(), "Please fill the form, don't leave any field blank", Toast.LENGTH_LONG).show();
                }

            }

            public void invokeWS (RequestParams params){
                // Make RESTful webservice call using AsyncHttpClient object
                AsyncHttpClient client = new AsyncHttpClient();
                client.get("http://192.168.43.17:9999/useraccount/register/doregister", params, new AsyncHttpResponseHandler() {
                    // When the response returned by REST has Http response code '200'
                    public void onSuccess(String response) {
                        try {
                            // JSON Object
                            JSONObject obj = new JSONObject(response);
                            // When the JSON response has status boolean value assigned with true
                            if (obj.getBoolean("status")) {
                                valoresPredefinidos();
                                // Display successfully registered message using Toast
                                Toast.makeText(getApplicationContext(), "You are successfully registered!", Toast.LENGTH_LONG).show();
                            }
                            // Else display error message
                            else {
                                Toast.makeText(getApplicationContext(), obj.getString("error_msg"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                            e.printStackTrace();

                        }
                    }

                    public void onFailure(int statusCode, Throwable error, String content) {
                        // When Http response code is '404'
                        if (statusCode == 404) {
                            Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                        }
                        // When Http response code is '500'
                        else if (statusCode == 500) {
                            Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                        }
                        // When Http response code other than 404, 500
                        else {
                            Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

            public void valoresPredefinidos(){
                usuario.setText("");
                nombre.setText("");
                apellidos.setText("");
                Password.setText("");
                correo.setText("");
            }

        }
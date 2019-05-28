package com.example.teamleaguebagit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button entrar,resgitrar;

    TextView usuario,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entrar = findViewById(R.id.Entrar);
        resgitrar = findViewById(R.id.Registrar);
        usuario = findViewById(R.id.id_liga);
        password = findViewById(R.id.Password);
    }

    public  void login(View view){
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
    }

    public void registro(View view){
        Intent intent = new Intent(this, RegisterView.class);
        startActivity(intent);
    }

    public void loginUser(View view){
        String user = usuario.getText().toString();
        // Get NAme ET control value
        String pass = password.getText().toString();

        RequestParams params = new RequestParams();
        // When Name Edit View, Email Edit View and Password Edit View have values other than Null
        if(Utility.isNotNull(user) && Utility.isNotNull(pass)){
                params.put("user", user);
                params.put("pass", pass);
                invokeWS(params);

        }
        else{
            Toast.makeText(getApplicationContext(), "Introduce un usuario y contraseña", Toast.LENGTH_LONG).show();
        }

    }

    public void invokeWS(RequestParams params){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://192.168.43.17:9999/useraccount/register/doregister",params ,new AsyncHttpResponseHandler() {
            // When the response returned by REST has Http response code '200'
            public void onSuccess(String response) {
                try {
                    // JSON Object
                    JSONObject obj = new JSONObject(response);
                    // When the JSON response has status boolean value assigned with true
                    if (obj.getBoolean("status")) {
                        valoresPredefinidos();
                        // Display successfully registered message using Toast
                        Toast.makeText(getApplicationContext(), "You are successfully login!", Toast.LENGTH_LONG).show();
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
                if(statusCode == 404){
                    Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                }
                // When Http response code is '500'
                else if(statusCode == 500){
                    Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                }
                // When Http response code other than 404, 500
                else{
                    Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void valoresPredefinidos() {
        usuario.setText("");
        password.setText("");
    }

    public void ir(View view){
        Intent intent = new Intent(this, BuscarLigaActivity.class);
        startActivity(intent);
    }
}

package com.example.teamleaguebagit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button entrar,resgitrar;
    ImageView info;

    TextView usuario,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info = findViewById(R.id.informacion);
        entrar = findViewById(R.id.Entrar);
        resgitrar = findViewById(R.id.Registrar);
        usuario = findViewById(R.id.username);
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

    public void showInfo(View view){
        Toast toast = Toast.makeText(getApplicationContext(),"Aplicación creada por:\nXavier Cañada, Alvaro Vázquez y Alberto Tiffon\n" +
                "Versión 1.0",Toast.LENGTH_SHORT);
        toast.show();
    }
}

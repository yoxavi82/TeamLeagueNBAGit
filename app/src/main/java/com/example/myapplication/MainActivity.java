package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button entrar,resgitrar;

    TextView usuario,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}

package com.example.teamleaguebagit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button entrar,resgitrar;
    CheckBox recordarUser;
    ImageView info;
    TextView usuario,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = getSharedPreferences(R.string.NameBbdd+"", Context.MODE_PRIVATE);
        String username = prefs.getString(getString(R.string.KeyBbddUser),"");
        String contra = prefs.getString(getString(R.string.KeyBbddPass),"");
        info = findViewById(R.id.informacion);
        entrar = findViewById(R.id.Entrar);
        recordarUser = findViewById(R.id.Recordar);
        resgitrar = findViewById(R.id.RegistrarMain);
        usuario = findViewById(R.id.Usuario);
        password = findViewById(R.id.Password);
        if(contra!=null&&!contra.isEmpty()&&username!=null&&!contra.isEmpty()){
            password.setText(contra+"");
            usuario.setText(username+"");
            login(entrar);
        }
    }

    public  void login(View view){
        if(usuario.getText().toString().isEmpty()||password.getText().toString().isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.ToastErrorMain),Toast.LENGTH_SHORT);
            toast.show();
        }else {
            if (recordarUser.isChecked()) {
                SharedPreferences prefs = getSharedPreferences(getString(R.string.NameBbdd), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(R.string.KeyBbddUser+"", usuario.getText() + "");
                editor.putString(R.string.KeyBbddPass+"", password.getText() + "");
                editor.commit();
            }
            Intent intent = new Intent(this, Homepage.class);
            startActivity(intent);
        }
    }

    public void registro(View view){
        Intent intent = new Intent(this, RegisterView.class);
        startActivity(intent);
    }

    public void showInfo(View view){
        Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.Programadores) +
                getString(R.string.Version),Toast.LENGTH_SHORT);
        toast.show();
    }
}

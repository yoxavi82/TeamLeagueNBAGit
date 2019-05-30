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

import com.example.teamleaguebagit.Conexiones.UsuarioConexiones;
import com.example.teamleaguebagit.pojos.PasswordUsuarios;

public class MainActivity extends AppCompatActivity {

    Button entrar,resgitrar;
    CheckBox recordarUser;
    ImageView info;
    TextView usuario,password;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        String username = prefs.getString("Username","");
        String contra = prefs.getString("Password","");
        info = findViewById(R.id.informacion);
        entrar = findViewById(R.id.Entrar);
        recordarUser = findViewById(R.id.Recordar);
        resgitrar = findViewById(R.id.RegistrarMain);
        usuario = findViewById(R.id.Usuario);
        password = findViewById(R.id.Password);
        if(Actual.getIniciarSesion()){
            if(contra!=null&&!contra.isEmpty()&&username!=null&&!contra.isEmpty()){
                password.setText(contra+"");
                usuario.setText(username+"");
                login(entrar);
            }
        }
    }

    public  void login(View view){
        if(usuario.getText().toString().isEmpty()||password.getText().toString().isEmpty()){
            errorLogin(R.string.ToastErrorMain);
        }else {
           UsuarioConexiones user = new UsuarioConexiones();
            PasswordUsuarios pass = user.login(usuario.getText().toString());
            if(pass.getPassword()!=null){
                if(pass.getPassword().equals(password.getText().toString())){
                    Actual.setUsuarioActual(user.get(usuario.getText().toString()));
                    if (recordarUser.isChecked()) {
                        SharedPreferences prefs = getSharedPreferences("Usuario", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("Username", usuario.getText() + "");
                        editor.putString("Password", password.getText() + "");
                        editor.commit();
                    }
                    Intent intent = new Intent(this, Homepage.class);
                    startActivity(intent);
                }else{
                    errorLogin(R.string.ErrorLogin);
                }
            }else{
                errorLogin(R.string.ErrorLogin);
            }

        }
    }

    private void errorLogin(int p) {
        Toast toast = Toast.makeText(getApplicationContext(), getString(p), Toast.LENGTH_SHORT);
        toast.show();
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

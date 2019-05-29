package com.example.teamleaguebagit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teamleaguebagit.Conexiones.LigaConexiones;
import com.example.teamleaguebagit.pojos.Ligas;
import com.example.teamleaguebagit.pojos.PasswordLigas;

import java.util.ArrayList;

public class BuscarLigaActivity extends AppCompatActivity {
    private EditText nombre_liga;
    View formElementsView;
    ArrayList<lista_ligas> lista, lista2;
    ArrayList<PasswordLigas> passwordLigas,ligas2;
    TextView contraLiga,error;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar_liga);
        nombre_liga = findViewById(R.id.nombre_liga);
        LayoutInflater inflater = getLayoutInflater();
        formElementsView = inflater.inflate(R.layout.confirmar,  null);
        lista = new ArrayList<lista_ligas>();
        lista2 = new ArrayList<lista_ligas>();
        ligas2 = new ArrayList<>();
        lv = (ListView) findViewById(R.id.listView);
        passwordLigas=new LigaConexiones().getAll();
        for(PasswordLigas pass : passwordLigas){
            lista_ligas listaLiga = new lista_ligas();
            listaLiga.setNombre_liga(pass.getIdLiga());
            lista.add(listaLiga);
        }
        nombre_liga.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String nuevoTexto = s.toString();
                initLiga(nuevoTexto);
            }
        });
    }

    public void initLiga(String palabra){
        lista2.clear();
        if(ligas2!=null)ligas2.clear();

        for(int i = 0; i < lista.size(); i++){
            if (lista.get(i).nombre_liga.contains(palabra)){
                lista2.add(lista.get(i));
                ligas2.add(passwordLigas.get(i));
            }
        }

        AdapterListaBuscarLiga adapter = new AdapterListaBuscarLiga(this, lista2);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                LayoutInflater inflater = getLayoutInflater();
                Boolean yaEnLiga = false;
                Ligas actual = new LigaConexiones().get(ligas2.get(position).getIdLiga());
                for(Ligas liga:Actual.ligasUsuarioActual){
                    if(liga==actual)yaEnLiga=true;
                }
                if(yaEnLiga){
                    Toast.makeText(getApplicationContext(),"Ya estas en esta liga",Toast.LENGTH_SHORT).show();
                }else{
                final View view1 = inflater.inflate(R.layout.confirmar, null);
                final android.support.v7.app.AlertDialog dialogo = new android.support.v7.app.AlertDialog.Builder(BuscarLigaActivity.this).setView(view1).setCancelable(false).setPositiveButton("Confirmar", null).setNegativeButton("Cancelar", null).create();
                contraLiga = view1.findViewById(R.id.PasswordLigaUnirse);
                error = view1.findViewById(R.id.errorPasswordLiga);
                contraLiga.setText("");
                dialogo.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(final DialogInterface dialog) {
                        Button button = ((android.support.v7.app.AlertDialog) dialogo).getButton(android.support.v7.app.AlertDialog.BUTTON_POSITIVE);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(contraLiga.getText().toString().equals(ligas2.get(position).getPassword())){
                                    Intent i =new Intent(BuscarLigaActivity.super.getApplication(), creacion_equipo.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    i.putExtra("liga",new LigaConexiones().get(ligas2.get(position).getIdLiga()));
                                    startActivity(i);
                                    dialogo.dismiss();
                                }else{
                                    error.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                        Button button2 = ((android.support.v7.app.AlertDialog) dialogo).getButton(android.support.v7.app.AlertDialog.BUTTON_NEGATIVE);
                        button2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogo.dismiss();
                            }
                        });
                    }
                });
                dialogo.show();
            }
            }
        });
    }
}

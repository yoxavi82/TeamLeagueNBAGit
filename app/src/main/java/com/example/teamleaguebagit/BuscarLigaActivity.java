package com.example.teamleaguebagit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class BuscarLigaActivity extends AppCompatActivity {
    private EditText nombre_liga;
    View formElementsView;
    ArrayList<lista_ligas> lista, lista2;
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
        lv = (ListView) findViewById(R.id.listView);

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
    /*
        ArrayList<lista_ligas> lista = new ArrayList<lista_ligas>();

        ListView lv = (ListView) findViewById(R.id.listView);

        lista_ligas l = new lista_ligas("Los mejores pechos", "3/17");

        lista.add(l);

        AdapterListaBuscarLiga adapter = new AdapterListaBuscarLiga(this, lista);

        lv.setAdapter(adapter); */



    }

    public void initLiga(String palabra){
        lista2.clear();
        lista.clear();
        lista_ligas l = new lista_ligas("Los mejores pechos", "3/17");

        lista_ligas e = new lista_ligas("Los mejores porrros", "3/17");

        lista_ligas d = new lista_ligas("jojojojo", "3/17");

        lista_ligas g = new lista_ligas("mejores jejejee", "3/17");

        lista_ligas h = new lista_ligas("Los mejores jejejee", "3/17");

        lista_ligas j = new lista_ligas("jijiijij", "3/17");

        lista_ligas k = new lista_ligas("super", "3/17");

        lista_ligas t = new lista_ligas("lest go", "3/17");


        lista_ligas vv = new lista_ligas("Los mejores churros", "3/17");

        lista_ligas tt = new lista_ligas("josefa", "3/17");

        lista.add(l);
        lista.add(e);
        lista.add(d);
        lista.add(g);
        lista.add(h);
        lista.add(j);
        lista.add(k);
        lista.add(t);

        lista.add(vv);

        lista.add(tt);

        for(int i = 0; i < lista.size(); i++){
            if (lista.get(i).nombre_liga.contains(palabra)){
                lista2.add(lista.get(i));
            }
        }

        AdapterListaBuscarLiga adapter = new AdapterListaBuscarLiga(this, lista2);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final AlertDialog.Builder dialogo = new AlertDialog.Builder(BuscarLigaActivity.this).setView(R.layout.confirmar);
                dialogo.setCancelable(false);
                dialogo.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo, int id) {

                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo, int id) {

                    }
                });

                dialogo.show();
            }
        });
    }

    private void crearEquipo(String idLiga){
        String sql = "SELECT IdJugador FROM Jugadores WHERE IdJugador NOT IN (SELECT IdJugador FROM Plantillas WHERE IdLiga = " + idLiga + ")";
    }


}

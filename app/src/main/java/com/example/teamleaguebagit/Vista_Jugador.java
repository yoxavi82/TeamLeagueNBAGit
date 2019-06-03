package com.example.teamleaguebagit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.teamleaguebagit.Conexiones.EquipoUsuarioConexiones;
import com.example.teamleaguebagit.Conexiones.PlantillaConexiones;
import com.example.teamleaguebagit.pojos.Plantillas;
import com.example.teamleaguebagit.pojos.Usuarios;

import java.util.ArrayList;

public class Vista_Jugador extends AppCompatActivity {
    TextView nombre,puntos,precio,equipo,posicion,fecha,precio_compra;
    Spinner spinner;
    ImageView foto;
    Usuarios user;
    ArrayList<Plantillas> plantillaJugadores= new ArrayList<>();
    ArrayList<String> nombreJugadores= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_jugador);
        Bundle b = getIntent().getExtras();
        user = (Usuarios) b.get("Usuario");
        nombre = findViewById(R.id.vista_jugador_nombre);
        precio = findViewById(R.id.vista_jugador_precio_compra);
        puntos = findViewById(R.id.vista_jugador_puntos);
        equipo = findViewById(R.id.vista_jugador_equipo);
        posicion = findViewById(R.id.vista_jugador_pos);
        fecha = findViewById(R.id.vista_jugador_fecha);
        precio_compra = findViewById(R.id.vista_jugador_precio_compra);
        spinner = findViewById(R.id.spinner);
        foto=findViewById(R.id.fotoJugador);
        plantillaJugadores = new PlantillaConexiones().getByIdEquipo(
                new EquipoUsuarioConexiones().getByLigaAndUser(user.getIdUsuario(),Actual.getLigaActual().getIdLiga()).getIdEquipo());
        for (Plantillas plant: plantillaJugadores){
            nombreJugadores.add(plant.getJugadores().getNombre()+" "+plant.getJugadores().getApellido());
        }
        spinner.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,nombreJugadores));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nombre.setText(plantillaJugadores.get(position).getJugadores().getNombre()+" "
                        +plantillaJugadores.get(position).getJugadores().getApellido());
                precio.setText(plantillaJugadores.get(position).getPrecio()+"");
                puntos.setText(plantillaJugadores.get(position).getJugadores().getPuntosTotales()+"");
                equipo.setText(plantillaJugadores.get(position).getEquiposUsuarios().getNombreEquipo());
                posicion.setText(plantillaJugadores.get(position).getJugadores().getPosicion());
                fecha.setText(plantillaJugadores.get(position).getFechaCompra().toString());
//                foto.setImageResource();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}

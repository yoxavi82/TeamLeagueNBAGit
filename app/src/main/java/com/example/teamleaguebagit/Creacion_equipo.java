package com.example.teamleaguebagit;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teamleaguebagit.Conexiones.EquipoConexiones;
import com.example.teamleaguebagit.Conexiones.EquipoUsuarioConexiones;
import com.example.teamleaguebagit.Conexiones.JugadorConexiones;
import com.example.teamleaguebagit.Conexiones.PlantillaConexiones;
import com.example.teamleaguebagit.Conexiones.PuntuacionConexiones;
import com.example.teamleaguebagit.pojos.EquiposUsuarios;
import com.example.teamleaguebagit.pojos.Jugadores;
import com.example.teamleaguebagit.pojos.Ligas;
import com.example.teamleaguebagit.pojos.Plantillas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Creacion_equipo extends AppCompatActivity {
    ListView lv;
    static final String[] siglasEquipos={"ATL","BKN","BOS","CLE","LAC","DAL","GS","CHA","HOU","NY","LAL","CHI","MEM","MIA","MIL","MIN","OKC"
            ,"ORL","IND","NO","PHI","DET","POR","SAC","PHX","DEN","TOR","SA","UTA","WSH"};
    String siglasActuales;
    TextView nombreEquipoUsuario,ayuda;
    Ligas liga;
    EquiposUsuarios equipo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_equipos_nba);
        lv = findViewById(R.id.lv_imagenes);
        nombreEquipoUsuario = findViewById(R.id.nombreEquipoUsuario);
        ayuda = findViewById(R.id.ayuda);
        añadirFotos();
    }

    public void añadirFotos(){
        Drawable ATL  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.atlanta);
        Drawable BKN  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.brooklyn);
        Drawable BOS  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.celtic);
        Drawable CLE  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.cle);
        Drawable LAC  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.clippers);
        Drawable DAL  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.dalas);
        Drawable GS  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.gs);
        Drawable CHA  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.hornets);
        Drawable HOU  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.houston);
        Drawable NY  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.knicks);
        Drawable LAL  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.lakers);
        Drawable CHI  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.logo_bull);
        Drawable MEM  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.mem);
        Drawable MIA  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.miami);
        Drawable MIL  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.milwa);
        Drawable MIN  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.minesota);
        Drawable OKC  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.okc);
        Drawable ORL  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.orlando);
        Drawable IND  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.pacers);
        Drawable NO  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.pelicans);
        Drawable PHI  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.phi);
        Drawable DET  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.pistons);
        Drawable POR  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.portland);
        Drawable SAC  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.sacramento);
        Drawable PHX  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.suns);
        Drawable DEN  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.denver);
        Drawable TOR  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.toronto);
        Drawable SA  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.spurs);
        Drawable UTA  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.uta);
        Drawable WSH  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.whas);
        final ArrayList<Drawable> fotos = new ArrayList<Drawable>();
        final ArrayList<String> nombres = new ArrayList<>();
        fotos.add(ATL);
        fotos.add(BOS);
        fotos.add(BKN);
        fotos.add(CLE);
        fotos.add(CHA);
        fotos.add(CHI);
        fotos.add(DEN);
        fotos.add(DET);
        fotos.add(DAL);
        fotos.add(PHX);
        fotos.add(GS);
        fotos.add(HOU);
        fotos.add(IND);
        fotos.add(LAC);
        fotos.add(LAL);
        fotos.add(MEM);
        fotos.add(MIA);
        fotos.add(MIL);
        fotos.add(MIN);
        fotos.add(NO);
        fotos.add(NY);
        fotos.add(OKC);
        fotos.add(ORL);
        fotos.add(PHI);
        fotos.add(POR);
        fotos.add(SA);
        fotos.add(SAC);
        fotos.add(TOR);
        fotos.add(UTA);
        fotos.add(WSH);
        final AdapterListaSeleccionarFoto adapter = new AdapterListaSeleccionarFoto(this, fotos);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ayuda.setVisibility(View.INVISIBLE);
                siglasActuales=siglasEquipos[position];
            }
        });
    }
    public void crearEquipoUsuario(View view){
        if(siglasActuales!=null&&!nombreEquipoUsuario.getText().toString().isEmpty()){
            Intent i = getIntent();
            Bundle extras = i.getExtras();
            liga = (Ligas) extras.get("liga");
            EquipoUsuarioConexiones con = new EquipoUsuarioConexiones();
            equipo = new EquiposUsuarios();
            equipo.setUsuarios(Actual.getUsuarioActual());
            equipo.setPuntosTotales(0);
            equipo.setLigas(liga);
            equipo.setDinero(10000000);
            equipo.setNombreEquipo(nombreEquipoUsuario.getText().toString());
            equipo.setEquipos(new EquipoConexiones().get(siglasActuales));
            if(con.register(equipo)){
                Actual.getLigaSesion().add(liga);
                Actual.getEquiposUsuariosSesion().add(equipo);
                Actual.setLigaActual(liga);
                generarEquipo();
                i = new Intent(this, Homepage.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        }else{
            Toast toast = Toast.makeText(this,"Escribe el nombre de tu equipo y selecciona un equipo",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void generarEquipo() {
        ArrayList<Plantillas> jugadores= new ArrayList<>();
        for(int i=1;i<5;i++){
            ArrayList<Jugadores> jugadores1Estrellas = new JugadorConexiones().getByStarsRandom(i);
            int ayuda=0;
            switch (i){
                case 1:
                    ayuda=4;
                    break;
                case 2:
                    ayuda= 5;
                    break;
                case 3:
                    ayuda=3;
                    break;
                case 4:
                    ayuda=1;
                    break;
            }
            for (int j=0;j<ayuda;j++){
                Plantillas plantilla = new Plantillas();
                plantilla.setLigas(Actual.getLigaActual());
                plantilla.setTitular(0);
                plantilla.setPuja(0);
                plantilla.setJugadores(jugadores1Estrellas.get(j));
                plantilla.setEquiposUsuarios(new EquipoUsuarioConexiones().getByLigaAndUser(Actual.getUsuarioActual().getIdUsuario(),
                        Actual.getLigaActual().getIdLiga()));
                plantilla.setFechaCompra(new java.sql.Date(new Date().getTime()));
                plantilla.setPrecio(i*1000000);
                new PlantillaConexiones().addPlantilla(plantilla);
//        Plantillas la = new Plantillas();
//        la.setEquiposUsuarios(Actual.getEquiposUsuariosSesion().get(0));
//        la.setFechaCompra(new java.sql.Date(new Date().getTime()));
//        la.setJugadores(new JugadorConexiones().getById("ATL_11"));
//        la.setLigas(Actual.getLigaActual());
//        la.setPrecio(0);
//        la.setTitular(0);
//        la.setPuja(0);
//        plant.addPlantilla(la);
            }
        }
    }
}

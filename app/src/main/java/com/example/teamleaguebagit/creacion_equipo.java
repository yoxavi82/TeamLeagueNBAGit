package com.example.teamleaguebagit;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.net.URI;
import java.util.ArrayList;

public class creacion_equipo extends AppCompatActivity {
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_equipos_nba);
        lv = findViewById(R.id.lv_imagenes);
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
        Drawable FHX  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.suns);
        Drawable DEN  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.denver);
        Drawable TOR  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.toronto);
        Drawable SA  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.spurs);
        Drawable UTA  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.uta);
        Drawable WSH  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.whas);
        ArrayList<Drawable> fotos = new ArrayList<Drawable>();
        fotos.add(ATL);
        fotos.add(BOS);
        fotos.add(BKN);
        fotos.add(CLE);
        fotos.add(CHA);
        fotos.add(CHI);
        fotos.add(DEN);
        fotos.add(DET);
        fotos.add(DAL);
        fotos.add(FHX);
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
        AdapterListaSeleccionarFoto adapter = new AdapterListaSeleccionarFoto(this, fotos);
        lv.setAdapter(adapter);

    }
}

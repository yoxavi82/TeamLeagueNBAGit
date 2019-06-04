package com.example.teamleaguebagit;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.teamleaguebagit.pojos.Jugadores;

import java.util.ArrayList;

public class AdapterListaMercado extends BaseAdapter {
    protected Activity activity;
    protected ArrayList<Jugadores> items;

    public AdapterListaMercado(Activity activity, ArrayList<Jugadores> items) {
        this.activity = activity;
        this.items = items;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<Jugadores> lista) {
        for (int i = 0; i < lista.size(); i++) {
            items.add(lista.get(i));
        }
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.lista_mercado, null);
        }

        Jugadores dir = items.get(position);

        /*byte[] b = dir.getImagen();
        ByteArrayInputStream is = new ByteArrayInputStream(b);
        Drawable drw = Drawable.createFromStream(is, "articleImage");*/

        ImageView imagen = v.findViewById(R.id.imagen_jugador);
        imagen.setImageResource(R.drawable.pelota);


        TextView nombre = v.findViewById(R.id.lista_nombre_jugador);
        nombre.setText(dir.getNombre() + " " + dir.getApellido());

        TextView precio = (TextView) v.findViewById(R.id.lista_precio_jugador);
        precio.setText(dir.getPrecioMercado() + "");

        TextView puntos = (TextView) v.findViewById(R.id.lista_puntos_jugador);
        puntos.setText(dir.getPuntosTotales() + "");

        TextView pos = (TextView) v.findViewById(R.id.lista_pos_jugador);
        if (dir.getPosicion().equals("Exterior")) pos.setText("Ext");
        else pos.setText("Int");


        return v;

    }

}

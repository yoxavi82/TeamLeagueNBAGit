package com.example.teamleaguebagit;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class AdapterListaClasificacionGeneral extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Lista_clasificacion> items;

    public AdapterListaClasificacionGeneral(Activity activity, ArrayList<Lista_clasificacion> items) {
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

    public void addAll(ArrayList<Lista_clasificacion> lista) {
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
            v = inf.inflate(R.layout.list_item_layout, null);
        }

        Lista_clasificacion dir = items.get(position);

        TextView title = v.findViewById(R.id.lista_nombre_jugador);
        title.setText(dir.getNombre());

        TextView pos = v.findViewById(R.id.Posicion);
        pos.setText(dir.getClasificacion() + "");




        return v;
    }
}

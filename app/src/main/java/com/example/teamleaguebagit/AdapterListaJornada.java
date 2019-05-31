package com.example.teamleaguebagit;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class AdapterListaJornada extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<lista_jornada> items;

    public AdapterListaJornada(Activity activity, ArrayList<lista_jornada> items) {
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

    public void addAll(ArrayList<lista_jornada> lista) {
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
            v = inf.inflate(R.layout.list_jornada_item, null);
        }

        lista_jornada dir = items.get(position);

        TextView title = v.findViewById(R.id.lista_nombre_misligas);
        title.setText(dir.nombre_usuario);

        TextView pos = v.findViewById(R.id.puntos_jornada);
        pos.setText(dir.getPuntuacion() + "");




        return v;
    }
}

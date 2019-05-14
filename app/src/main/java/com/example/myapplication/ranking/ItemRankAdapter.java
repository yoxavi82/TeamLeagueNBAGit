package com.example.myapplication.ranking;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ItemRankAdapter extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<ItemRank> items;

    public ItemRankAdapter(Activity activity, ArrayList<ItemRank> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        View vi=contentView;

        if(contentView == null) {
            LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.list_rank_items, null);
        }


//        ItemRank item = MainActivity.orden.get(position);
//
//        ImageView image = vi.findViewById(R.id.imagen);
//        if(position==0){
//            image.setImageResource(R.drawable.oro);
//            image.setVisibility(View.VISIBLE);
//        }else if(position==1){
//            image.setImageResource(R.drawable.plata);
//            image.setVisibility(View.VISIBLE);
//        }else if(position==2){
//            image.setImageResource(R.drawable.bronce);
//            image.setVisibility(View.VISIBLE);
//        }else{
//            image.setImageResource(R.drawable.oro);
//            image.setVisibility(View.INVISIBLE);
//        }

//
//        TextView nombre = (TextView) vi.findViewById(R.id.nombre);
//        nombre.setText(item.getNombre());
//
//        TextView score = (TextView) vi.findViewById(R.id.score);
//        score.setText(item.getPuntuacion()+"");

        return vi;
    }
}

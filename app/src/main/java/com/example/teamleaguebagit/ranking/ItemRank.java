package com.example.teamleaguebagit.ranking;
import android.support.v7.app.AppCompatActivity;

public class ItemRank extends AppCompatActivity implements Comparable<ItemRank> {
    protected long id;
    protected String nombre;
    protected Integer puntuacion;

    public ItemRank() {
        this.nombre = "";
        this.puntuacion = 0;
    }
    public  long getId(){return id;}

    public void setId(long id){this.id=id;}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public int compareTo(ItemRank o) {
        if (this.getPuntuacion()>o.getPuntuacion())return -1;
        if(this.getPuntuacion()<o.getPuntuacion())return 1;
        else return 0;
    }
}

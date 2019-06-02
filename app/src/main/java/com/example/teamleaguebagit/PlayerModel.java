package com.example.teamleaguebagit;

import android.graphics.Bitmap;

import com.example.teamleaguebagit.pojos.Jugadores;

public class PlayerModel {
    private String name;
    private int resourceImg;
    private Jugadores jugador;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResourceImg() {
        return resourceImg;
    }

    public void setResourceImg(int resourceImg) {
        this.resourceImg = resourceImg;
    }

    public Jugadores getJugador() {
        return jugador;
    }

    public void setJugador(Jugadores jugador) {
        this.jugador = jugador;
    }
}

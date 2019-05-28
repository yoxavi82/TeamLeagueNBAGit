package com.example.teamleaguebagit;

import android.graphics.drawable.Drawable;

import com.example.teamleaguebagit.pojos.Jugadores;

public class PlayerModel {
    private String name;
    private Drawable image_drawable;
    private Jugadores jugador;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getImage_drawable() {
        return image_drawable;
    }

    public void setImage_drawable(Drawable image_drawable) {
        this.image_drawable = image_drawable;
    }

    public Jugadores getJugador() {
        return jugador;
    }

    public void setJugador(Jugadores jugador) {
        this.jugador = jugador;
    }
}

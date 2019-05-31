package com.example.teamleaguebagit;

public class Lista_jornada {
    String nombre_usuario;
    int puntuacion;

    public Lista_jornada(String nombre_usuario, int puntuacion) {
        this.nombre_usuario = nombre_usuario;
        this.puntuacion = puntuacion;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}

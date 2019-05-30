package com.example.teamleaguebagit;

import com.example.teamleaguebagit.pojos.Usuarios;

public class Lista_clasificacion {
    String nombre;
    int clasificacion;
    Usuarios user;

    public Lista_clasificacion(String nombre, int clasificacion, Usuarios user) {
        this.nombre = nombre;
        this.clasificacion = clasificacion;
        this.user = user;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Usuarios getUser() {
        return user;
    }

    public void setUser(Usuarios user) {
        this.user = user;
    }


}

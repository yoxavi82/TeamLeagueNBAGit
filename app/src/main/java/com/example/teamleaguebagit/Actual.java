package com.example.teamleaguebagit;

import com.example.teamleaguebagit.pojos.Ligas;
import com.example.teamleaguebagit.pojos.Usuarios;

public class Actual {
    static Usuarios usuarioActual=null;
    static Ligas ligaActual=null;
    static Boolean iniciarSesion=true;

    public Actual(Usuarios j, Ligas l) {
        this.usuarioActual = j;
        this.ligaActual = l;
    }

    public static boolean getIniciarSesion(){
        return iniciarSesion;
    }

    public static void setIniciarSesion(){
        iniciarSesion=false;
    }


    public static Usuarios getUsuarioActual() {
        return usuarioActual;
    }

    public static void setUsuarioActual(Usuarios jugadorActual) {
        Actual.usuarioActual = jugadorActual;
    }

    public static Ligas getLigaActual() {
        return ligaActual;
    }

    public static void setLigaActual(Ligas ligaActual) {
        Actual.ligaActual = ligaActual;
    }
}

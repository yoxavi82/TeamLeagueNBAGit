package com.example.teamleaguebagit;

import android.support.design.widget.NavigationView;
import android.view.Menu;

import com.example.teamleaguebagit.Conexiones.LigaConexiones;
import com.example.teamleaguebagit.pojos.Ligas;
import com.example.teamleaguebagit.pojos.Usuarios;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Actual {
    static Usuarios usuarioActual=null;
    static Ligas ligaActual=null;
    static Boolean iniciarSesion=true;
    static ArrayList<Ligas> ligasUsuarioActual=null;

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

    @NotNull
    public static void initArrayMenu() {
        LigaConexiones liga = new LigaConexiones();
        ligasUsuarioActual =liga.getAllLigas();

    }


    public static void setLigaActual(Ligas ligaActual) {
        Actual.ligaActual = ligaActual;
    }
}

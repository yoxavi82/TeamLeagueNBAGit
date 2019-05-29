package com.example.teamleaguebagit;

import com.example.teamleaguebagit.Conexiones.LigaConexiones;
import com.example.teamleaguebagit.pojos.EquiposUsuarios;
import com.example.teamleaguebagit.pojos.Ligas;
import com.example.teamleaguebagit.pojos.Usuarios;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Actual {
    static Usuarios usuarioActual=null;
    static Ligas ligaActual=null;
    static Boolean iniciarSesion=true;
    static ArrayList<Ligas> ligasUsuarioActual = new ArrayList();
    static ArrayList<EquiposUsuarios> equiposUsuarios = new ArrayList();

    public static ArrayList<EquiposUsuarios> getEquiposUsuariosSesion(){
        return equiposUsuarios;
    }

    public static void setEquiposUsuarios(ArrayList<EquiposUsuarios> equipos){
        ligasUsuarioActual.clear();
        for(EquiposUsuarios eq: equipos){
            ligasUsuarioActual.add(new LigaConexiones().get(eq.getLigas().getIdLiga()));
        }
        equiposUsuarios = equipos;
    }

    public static ArrayList<Ligas> getLigaSesion(){
        return ligasUsuarioActual;
    }

    public static void setLigasUsuarioActual(ArrayList<Ligas> ligas){
        ligasUsuarioActual = ligas;
    }


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

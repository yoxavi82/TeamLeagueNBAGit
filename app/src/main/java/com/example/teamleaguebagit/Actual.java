package com.example.teamleaguebagit;

import com.example.teamleaguebagit.Conexiones.LigaConexiones;
import com.example.teamleaguebagit.pojos.EquiposUsuarios;
import android.support.design.widget.NavigationView;
import android.view.Menu;

import com.example.teamleaguebagit.Conexiones.LigaConexiones;
import com.example.teamleaguebagit.pojos.Ligas;
import com.example.teamleaguebagit.pojos.Usuarios;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Actual {
    static Usuarios usuarioActual=null;
    static Ligas ligaActual=null;
    static Boolean iniciarSesion=true;
    static EquiposUsuarios equipoActual;
    static ArrayList<Ligas> ligasUsuarioActual = null;
    static ArrayList<EquiposUsuarios> equiposUsuarios = new ArrayList();

    public static ArrayList<EquiposUsuarios> getEquiposUsuariosSesion(){
        return equiposUsuarios;
    }

    public static void setEquiposUsuarios(ArrayList<EquiposUsuarios> equipos){
        ligasUsuarioActual=new ArrayList();
        Alineacion.actPlantilla=true;

        for(EquiposUsuarios eq: equipos){
            ligasUsuarioActual.add(eq.getLigas());
        }
        equiposUsuarios = equipos;
    }

    public static EquiposUsuarios getEquipoActual() {
        //Alineacion.actPlantilla=true;
        return equipoActual;
    }

    public static void setEquipoActual(EquiposUsuarios equipoActual) {
        Actual.equipoActual = equipoActual;
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
        disconect();
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

    public static void disconect(){
        usuarioActual=null;
        ligaActual=null;
        iniciarSesion=false;
        ligasUsuarioActual = null;
        equiposUsuarios = new ArrayList();

    }
}

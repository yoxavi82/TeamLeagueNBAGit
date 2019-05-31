package com.example.teamleaguebagit;

import com.example.teamleaguebagit.Conexiones.EquipoUsuarioConexiones;
import com.example.teamleaguebagit.Conexiones.PlantillaConexiones;
import com.example.teamleaguebagit.pojos.Jugadores;
import com.example.teamleaguebagit.pojos.Plantillas;

import java.util.ArrayList;
import java.util.Date;

class ThreadIntroducir extends Thread{
    int j;
    int i;
    ArrayList<Jugadores> jugadores;
    public ThreadIntroducir(int j, ArrayList<Jugadores> jugadores,int i) {
        this.j=j;
        this.i=i;
        this.jugadores=jugadores;
    }

    @Override
    public void run(){
        Plantillas plantilla = new Plantillas();
        plantilla.setLigas(Actual.getLigaActual());
        plantilla.setTitular(0);
        plantilla.setPuja(0);
        plantilla.setJugadores(jugadores.get(j));
        plantilla.setEquiposUsuarios(new EquipoUsuarioConexiones().getByLigaAndUser(Actual.getUsuarioActual().getIdUsuario(),
                Actual.getLigaActual().getIdLiga()));
        plantilla.setFechaCompra(new java.sql.Date(new Date().getTime()));
        plantilla.setPrecio(i*1000000);
        new PlantillaConexiones().addPlantilla(plantilla);
    }
}

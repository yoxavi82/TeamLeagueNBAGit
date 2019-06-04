package com.example.teamleaguebagit;

import com.example.teamleaguebagit.Conexiones.JugadorConexiones;
import com.example.teamleaguebagit.pojos.Jugadores;

import java.util.ArrayList;

public class ThreadAddPlantilla extends Thread{
    int i =0;
    public ThreadAddPlantilla(int i) {
        this.i=i;
    }

    @Override
    public void run() {
        ArrayList<Jugadores> jugadores1Estrellas = new JugadorConexiones().getByStarsRandom(i,Actual.getLigaActual().getIdLiga());
        int ayuda=0;
        switch (i){
            case 1:
                ayuda=4;
                break;
            case 2:
                ayuda= 5;
                break;
            case 3:
                ayuda=3;
                break;
            case 4:
                ayuda=1;
                break;
        }
        ThreadIntroducir[] nuevo = new ThreadIntroducir[ayuda];
        for (int j=0;j<ayuda;j++){
            nuevo[j] = new ThreadIntroducir(j,jugadores1Estrellas,i);
            nuevo[j].start();
        }
    }
}


package com.example.teamleaguebagit.ConexionInterficies;

import com.example.teamleaguebagit.pojos.Puntuaciones;

import java.util.ArrayList;

public interface PuntuacionRepository {
    boolean addPuntuacion(Puntuaciones pun);
    ArrayList<Puntuaciones> getByPartido(String idPartido);
    ArrayList<Puntuaciones> getByJugador(String idJugador);
    ArrayList<Puntuaciones> getAll();
}

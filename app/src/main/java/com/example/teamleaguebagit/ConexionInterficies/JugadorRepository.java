
package com.example.teamleaguebagit.ConexionInterficies;

import com.example.teamleaguebagit.pojos.Jugadores;

import java.util.ArrayList;

public interface JugadorRepository {
    ArrayList<Jugadores> getAll();
    ArrayList<Jugadores> getByJugador(Jugadores jugador);
    Jugadores getById(String id);
    
}


package com.example.teamleaguebagit.ConexionInterficies;

import com.example.teamleaguebagit.pojos.Jugadores;

import java.util.ArrayList;

public interface JugadorRepository {
    ArrayList<Jugadores> getByStars(int i);
    Jugadores getById(String id);
    ArrayList<Jugadores> getByTeam(String equipo);
    
}

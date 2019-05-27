
package com.example.teamleaguebagit.ConexionInterficies;

import com.example.teamleaguebagit.pojos.Equipos;
import com.example.teamleaguebagit.pojos.Partidos;

import java.util.ArrayList;

public interface PartidosRepository {
    boolean addPartido(Partidos partido);
    ArrayList<Partidos> getAll();
    ArrayList<Partidos> getById(String id);
    ArrayList<Partidos> getByEquipo(Equipos equipo);
    ArrayList<Partidos> getBySemana(Integer semana);
}

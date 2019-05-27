package com.example.teamleaguebagit.Conexiones;

import com.example.teamleaguebagit.ConexionInterficies.PartidosRepository;
import com.example.teamleaguebagit.pojos.Equipos;
import com.example.teamleaguebagit.pojos.Partidos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alber
 */
//TODO
public class PartidosConexiones implements PartidosRepository {

    @Override
    public boolean addPartido(Partidos partido) {
        return false;
    }

    @Override
    public ArrayList<Partidos> getAll() {
        return null;
    }

    @Override
    public ArrayList<Partidos> getById(String id) {
        return null;
    }

    @Override
    public ArrayList<Partidos> getByEquipo(Equipos equipo) {
        return null;
    }

    @Override
    public ArrayList<Partidos> getBySemana(Integer semana) {
        return null;
    }
}
package com.example.teamleaguebagit.Conexiones;

import com.example.teamleaguebagit.ConexionInterficies.PlantillaRepository;
import com.example.teamleaguebagit.pojos.Plantillas;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alber
 */
//TODO
public class PlantillaConexiones implements PlantillaRepository {
    @Override
    public boolean addPlantilla(Plantillas plantilla) {
        return false;
    }

    @Override
    public boolean updatePlantilla(Plantillas plantilla) {
        return false;
    }

    @Override
    public ArrayList<Plantillas> getByIdJugador(String idJugador) {
        return null;
    }

    @Override
    public ArrayList<Plantillas> getByIdLoiga(String idLiga) {
        return null;
    }

    @Override
    public ArrayList<Plantillas> getByIdEquipo(String idEquipo) {
        return null;
    }
}
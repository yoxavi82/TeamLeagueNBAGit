
package com.example.teamleaguebagit.ConexionInterficies;

import com.example.teamleaguebagit.pojos.Plantillas;

import java.sql.Date;
import java.util.ArrayList;

public interface PlantillaRepository {
    boolean addPlantilla(Plantillas plantilla);
    boolean updatePlantilla(Plantillas plantilla);
    ArrayList<Plantillas> getByIdJugador(String idJugador);
    ArrayList<Plantillas> getByIdLiga(String idLiga);
    ArrayList<Plantillas> getByIdEquipo(String idEquipo);
    ArrayList<Plantillas> getByDate(Date date);
}

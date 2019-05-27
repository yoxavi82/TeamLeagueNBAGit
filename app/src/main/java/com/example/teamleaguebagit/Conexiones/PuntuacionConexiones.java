package com.example.teamleaguebagit.Conexiones;

import com.example.teamleaguebagit.ConexionInterficies.PuntuacionRepository;
import com.example.teamleaguebagit.pojos.Puntuaciones;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alber
 */
//TODO
public class PuntuacionConexiones implements PuntuacionRepository {

    @Override
    public boolean addPuntuacion(Puntuaciones pun) {
        return false;
    }

    @Override
    public ArrayList<Puntuaciones> getByPartido(String idPartido) {
        return null;
    }

    @Override
    public ArrayList<Puntuaciones> getByJugador(String idJugador) {
        return null;
    }

    @Override
    public ArrayList<Puntuaciones> getAll() {
        return null;
    }
}

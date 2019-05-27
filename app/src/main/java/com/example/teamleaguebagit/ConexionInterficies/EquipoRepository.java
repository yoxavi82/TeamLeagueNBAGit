
package com.example.teamleaguebagit.ConexionInterficies;

import com.example.teamleaguebagit.pojos.Equipos;

import java.util.ArrayList;

public interface EquipoRepository {
    Equipos get(String id);
    ArrayList<Equipos> getAll();
}

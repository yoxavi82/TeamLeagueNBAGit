
package com.example.teamleaguebagit.ConexionInterficies;

import com.example.teamleaguebagit.pojos.EquiposUsuarios;

import java.util.ArrayList;

public interface EquipoUsuarioRepository {
    boolean register(EquiposUsuarios nuevo);
    boolean update(EquiposUsuarios nuevo);
    ArrayList<EquiposUsuarios> getAll();
    ArrayList<EquiposUsuarios> getByUser(String idUser);
    ArrayList<EquiposUsuarios> getByLiga(String idLiga);
    EquiposUsuarios getEquipo(String id);
    
    
}

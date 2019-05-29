
package com.example.teamleaguebagit.ConexionInterficies;


import java.util.ArrayList;

public interface EquipoUsuarioRepository {
    boolean register(pojos.EquiposUsuarios nuevo);
    ArrayList<pojos.EquiposUsuarios> getAll();
    ArrayList<pojos.EquiposUsuarios> getByUser(String idUser);
    ArrayList<pojos.EquiposUsuarios> getByLiga(String idLiga);
    pojos.EquiposUsuarios getEquipo(String id);
}

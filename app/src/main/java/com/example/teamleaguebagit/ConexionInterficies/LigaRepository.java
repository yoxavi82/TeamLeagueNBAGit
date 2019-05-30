
package com.example.teamleaguebagit.ConexionInterficies;

import com.example.teamleaguebagit.pojos.Ligas;
import com.example.teamleaguebagit.pojos.PasswordLigas;
import com.example.teamleaguebagit.pojos.Usuarios;

import java.util.ArrayList;

public interface LigaRepository {
    boolean register(String nombre, Usuarios admin);
    boolean registerPass(PasswordLigas passwordLigas);
    PasswordLigas unirte(Ligas ligas);
    ArrayList<PasswordLigas> getAll();
    ArrayList<Ligas> getAllLigas();

    Ligas get(String id);
}

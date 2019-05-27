
package com.example.teamleaguebagit.ConexionInterficies;

import com.example.teamleaguebagit.pojos.Ligas;
import com.example.teamleaguebagit.pojos.PasswordLigas;

import java.util.ArrayList;

public interface LigaRepository {
    boolean register(Ligas liga);
    boolean update(Ligas liga);
    PasswordLigas unirte(Ligas ligas);
    ArrayList<Ligas> getAll();
    Ligas get(String id);
}

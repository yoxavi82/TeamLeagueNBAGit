
package com.example.teamleaguebagit.ConexionInterficies;

import com.example.teamleaguebagit.pojos.PasswordUsuarios;
import com.example.teamleaguebagit.pojos.Usuarios;

import java.util.ArrayList;

public interface UsuarioRepository {
    boolean register(Usuarios usuario);
    boolean update(Usuarios usuario);
    PasswordUsuarios login(Usuarios usuario);
    Usuarios get(String id);
    ArrayList<Usuarios> getAll();
}

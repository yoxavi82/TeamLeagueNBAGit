package com.example.teamleaguebagit.Conexiones;


import com.example.teamleaguebagit.ConexionInterficies.UsuarioRepository;
import com.example.teamleaguebagit.pojos.PasswordUsuarios;
import com.example.teamleaguebagit.pojos.Usuarios;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author alber
 */
public class UsuarioConexiones implements UsuarioRepository {

    @Override
    public boolean register(Usuarios usuario) {
        try{
            Connection connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                String query ="Insert into Usuarios (IdUsuario,Nombre,Apellidos,Correo,FechaNacimiento,Admin)" +
                        " VALUES ('"+usuario.getIdUsuario()+"','"+usuario.getNombre()+"','"+usuario.getApellidos()
                        +"','"+usuario.getCorreo()+"','"+usuario.getFechaNacimiento()+"',0)";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                stmt.executeUpdate(query);
            }
            Conexion.cerrarConexion(connection);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(Usuarios usuario) {
        try{
            Connection connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                String query ="UPDATE Usuarios SET " +
                        "Nombre='"+usuario.getNombre()+"',Apellidos='"+usuario.getApellidos()+"',Correo='"+usuario.getCorreo()
                        +"',FechaNacimiento='"+usuario.getFechaNacimiento()+"'";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                stmt.executeUpdate(query);
            }
            Conexion.cerrarConexion(connection);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public PasswordUsuarios login(String user) {
        PasswordUsuarios buscado = new PasswordUsuarios();
        try{
            Connection connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM PasswordUsuarios WHERE IdUsuario='"+user+"'";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                if(rs.next()){
                    buscado.setPassword(rs.getString("Password"));
                    buscado.setIdUsuario(user);
                }else{
                    return null;
                }
            }
            Conexion.cerrarConexion(connection);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return buscado;

    }

    @Override
    public Usuarios get(String id) {
        Usuarios user = new Usuarios();
        try{
            Connection connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Usuarios WHERE IdUsuario='"+id+"'";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                if(rs.next()){
                    user.setApellidos(rs.getString("Apellidos"));
                    user.setNombre(rs.getString("Nombre"));
                    user.setIdUsuario(rs.getString("IdUsuario"));
                    user.setFechaNacimiento(rs.getDate("FechaNacimiento"));
                    user.setCorreo(rs.getString("Correo"));
                }else{
                    return null;
                }
            }
            Conexion.cerrarConexion(connection);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public ArrayList<Usuarios> getAll() {
        ArrayList<Usuarios> users = new ArrayList<>();
        try{
            Connection connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Usuarios";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while (rs.next()){
                    Usuarios user = new Usuarios();
                    user.setApellidos(rs.getString("Apellidos"));
                    user.setNombre(rs.getString("Nombre"));
                    user.setIdUsuario(rs.getString("IdUsuario"));
                    user.setFechaNacimiento(rs.getDate("FechaNacimiento"));
                    user.setCorreo(rs.getString("Correo"));
                    users.add(user);
                }
            }
            Conexion.cerrarConexion(connection);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return users;
    }

//TODO
    @Override
    public boolean registrarPassword(PasswordUsuarios passwordUsuarios) {
        try{
            Connection connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                String query ="Insert into PasswordUsuarios (IdUsuario,Password) VALUES" +
                        " ('"+passwordUsuarios.getIdUsuario()+"','"+passwordUsuarios.getPassword()+"')";
                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                stmt.executeUpdate(query);
            }
            Conexion.cerrarConexion(connection);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return true;
    }
}
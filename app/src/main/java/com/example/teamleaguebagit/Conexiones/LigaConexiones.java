package com.example.teamleaguebagit.Conexiones;

import com.example.teamleaguebagit.ConexionInterficies.LigaRepository;
import com.example.teamleaguebagit.pojos.Ligas;
import com.example.teamleaguebagit.pojos.PasswordLigas;
import com.example.teamleaguebagit.pojos.Usuarios;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author alber
 */
public class LigaConexiones implements LigaRepository {

    @Override
    public boolean register(String id, Usuarios admin) {
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                String query ="Insert into Ligas (IdLiga,Admin) VALUES ('"+id+"','"+admin.getIdUsuario()+"')";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                stmt.executeUpdate(query);
            }
            Conexion.cerrarConexion(connection);

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            Conexion.cerrarConexion(connection);
        }
        return true;
    }

    //TODO
    @Override
    public boolean registerPass(PasswordLigas passwordLigas) {
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                String query ="Insert into PasswordLigas (IdLiga,Password) VALUES ('"+passwordLigas.getLigas().getIdLiga()
                        +"','"+passwordLigas.getPassword()+"')";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                stmt.executeUpdate(query);
            }
            Conexion.cerrarConexion(connection);

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            Conexion.cerrarConexion(connection);
        }
        return true;
    }

    @Override
    public PasswordLigas unirte(Ligas ligas) {
        PasswordLigas buscado = new PasswordLigas();
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM PasswordLigas WHERE IdLiga='"+ligas.getIdLiga()+"'";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                if(rs.next()){
                    buscado.setPassword(rs.getString("Password"));
                    buscado.setIdLiga(ligas.getIdLiga());
                }else{
                    return null;
                }

            }
            Conexion.cerrarConexion(connection);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            Conexion.cerrarConexion(connection);
        }
        return buscado;
    }

    @Override
    public ArrayList<PasswordLigas> getAll() {
        ArrayList<PasswordLigas> liga = new ArrayList<>();
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM PasswordLigas";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while (rs.next()){
                    PasswordLigas pass = new PasswordLigas();
                    pass.setPassword(rs.getString("Password"));
                    pass.setIdLiga(rs.getString("IdLiga"));
                    liga.add(pass);
                }

            }
            Conexion.cerrarConexion(connection);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            Conexion.cerrarConexion(connection);
        }
        return liga;
    }

    @Override
    public ArrayList<Ligas> getAllLigas() {
        ArrayList<Ligas>  liga = new ArrayList<Ligas> ();
        UsuarioConexiones user = new UsuarioConexiones();

        try{
            Connection connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Ligas ";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while(rs.next()){
                    liga.add(new Ligas(rs.getString("IdLiga"),user.get(rs.getString("Admin"))));
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return liga;    }

    @Override
    public Ligas get(String id) {
        Connection connection= null;
        Ligas liga = new Ligas();
        UsuarioConexiones user = new UsuarioConexiones();

        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Ligas WHERE IdLiga='"+id+"'";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                if(rs.next()){
                    liga.setIdLiga(id);
                    liga.setUsuarios(user.get(rs.getString("Admin")));
                }else{
                    return null;
                }
            }
            Conexion.cerrarConexion(connection);
        }catch (Exception ex){
//            ex.printStackTrace();
        }finally {
            Conexion.cerrarConexion(connection);
        }
        return liga;
    }
}
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
    public Ligas register(String id, Usuarios admin) {
        Ligas liga = new Ligas(id, admin);
        try{
            Connection connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                String query ="Insert into Ligas (IdLiga,Admin) VALUES ('"+id+"','"+admin.getIdUsuario()+"')";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                stmt.executeUpdate(query);
                connection.close();
            }
        }catch (Exception ex){
            return null;
        }
        return liga;
    }

    //TODO
    @Override
    public boolean registerPass(PasswordLigas passwordLigas) {
        try{
            Connection connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                String query ="Insert into PasswordLigas (IdLiga,Password) VALUES ('"+passwordLigas.getIdLiga()
                        +"','"+passwordLigas.getPassword()+"')";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                stmt.executeUpdate(query);
            }

        }catch (Exception ex){
            return false;
        }
        return true;
    }

    @Override
    public PasswordLigas unirte(Ligas ligas) {
        PasswordLigas buscado = new PasswordLigas();
        try{
            Connection connection = Conexion.obtenerConexion();
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
        }catch (Exception ex){
            return null;
        }
        return buscado;
    }

    @Override
    public ArrayList<PasswordLigas> getAll() {
        ArrayList<PasswordLigas> liga = new ArrayList<>();
        try{
            Connection connection = Conexion.obtenerConexion();
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
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return (ArrayList<PasswordLigas>) liga;
    }

    @Override
    public Ligas get(String id) {
        Ligas liga = new Ligas();
        try{
            Connection connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Ligas WHERE IdLiga='"+id+"'";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                if(rs.next()){
                    liga.setIdLiga(id);
                }else{
                    return null;
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return liga;
    }
}
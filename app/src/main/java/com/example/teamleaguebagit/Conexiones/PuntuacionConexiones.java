package com.example.teamleaguebagit.Conexiones;

import com.example.teamleaguebagit.ConexionInterficies.PuntuacionRepository;
import com.example.teamleaguebagit.pojos.EquiposUsuarios;
import com.example.teamleaguebagit.pojos.Partidos;
import com.example.teamleaguebagit.pojos.Puntuaciones;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.ResultSet;
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
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                String query ="Insert into Puntuaciones (IdJugador,IdPartido,Puntuacion)" +
                        " VALUES ('"+pun.getJugadores().getIdJugador()+"','"+pun.getPartidos().getIdPartido()
                        +"',"+pun.getPuntuacion()+")";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                stmt.executeUpdate(query);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            Conexion.cerrarConexion(connection);
        }
        return true;
    }

    @Override
    public ArrayList<Puntuaciones> getByPartido(String idPartido) {
        Connection connection= null;
        ArrayList<Puntuaciones> punts = new ArrayList<>();
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Puntuaciones WHERE IdPartido='"+idPartido+"'";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while(rs.next()){
                    Puntuaciones punt = new Puntuaciones();
                    punt.setJugadores(new JugadorConexiones().getById(rs.getString("IdJugador")));
                    punt.setPartidos(new PartidosConexiones().getById(rs.getString("IdPartido")));
                    punt.setPuntuacion(rs.getInt("Puntuacion"));
                    punts.add(punt);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            Conexion.cerrarConexion(connection);
        }
        return punts;
    }

    @Override
    public ArrayList<Puntuaciones> getByJugador(String idJugador) {
        ArrayList<Puntuaciones> punts = new ArrayList<>();
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Puntuaciones WHERE IdJugador='"+idJugador+"'";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while(rs.next()){
                    Puntuaciones punt = new Puntuaciones();
                    punt.setJugadores(new JugadorConexiones().getById(rs.getString("IdJugador")));
                    punt.setPartidos(new PartidosConexiones().getById(rs.getString("IdPartido")));
                    punt.setPuntuacion(rs.getInt("Puntuacion"));
                    punts.add(punt);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            Conexion.cerrarConexion(connection);
        }
        return punts;
    }

    @Override
    public ArrayList<Puntuaciones> getAll() {
        ArrayList<Puntuaciones> punts = new ArrayList<>();
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Puntuaciones";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while(rs.next()){
                    Puntuaciones punt = new Puntuaciones();
                    punt.setJugadores(new JugadorConexiones().getById(rs.getString("IdJugador")));
                    punt.setPartidos(new PartidosConexiones().getById(rs.getString("IdPartido")));
                    punt.setPuntuacion(rs.getInt("Puntuacion"));
                    punts.add(punt);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            Conexion.cerrarConexion(connection);
        }
        return punts;
    }
}

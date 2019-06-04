package com.example.teamleaguebagit.Conexiones;

import com.example.teamleaguebagit.ConexionInterficies.PlantillaRepository;
import com.example.teamleaguebagit.pojos.EquiposUsuarios;
import com.example.teamleaguebagit.pojos.Partidos;
import com.example.teamleaguebagit.pojos.Plantillas;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alber
 */

public class PlantillaConexiones implements PlantillaRepository {

    @Override
    public boolean addPlantilla(Plantillas plantilla) {
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                String query ="Insert into Plantillas (IdJugador,IdLiga,IdEquipo,Precio,FechaCompra,Puja,Titular)" +
                        " VALUES ('"+plantilla.getJugadores().getIdJugador()+"','"+plantilla.getLigas().getIdLiga()+"',"
                        +plantilla.getEquiposUsuarios().getIdEquipo()+","+plantilla.getPrecio()+",'"+plantilla.getFechaCompra()
                        +"',"+plantilla.getPuja()+","+plantilla.getTitular()+")";

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
    public boolean updatePlantilla(Plantillas plantilla) {
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                String query ="Update Plantillas Set IdJugador='"+plantilla.getJugadores().getIdJugador()+"'," +
                        "IdLiga='"+plantilla.getLigas().getIdLiga()+"',IdEquipo="+plantilla.getEquiposUsuarios().getIdEquipo()
                        +",Precio="+plantilla.getPrecio()+",FechaCompra='"+plantilla.getFechaCompra()+"'," +
                        "Puja="+plantilla.getPuja()+",Titular="+plantilla.getTitular()+" WHERE IdEquipo=37";

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
    public ArrayList<Plantillas> getByIdJugador(String idJugador) {
        ArrayList<Plantillas> plantillas = new ArrayList<>();
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Plantillas WHERE IdJugador='"+idJugador+"'";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while(rs.next()){
                    Plantillas plantilla = new Plantillas();
                    plantilla.setFechaCompra(rs.getDate("FechaCompra"));
                    plantilla.setPrecio(rs.getInt("Precio"));
                    plantilla.setEquiposUsuarios(new EquipoUsuarioConexiones().getEquipo(rs.getInt("IdEquipo")+""));
                    plantilla.setJugadores(new JugadorConexiones().getById(rs.getString("IdJugador")));
                    plantilla.setPuja(rs.getInt("Puja"));
                    plantilla.setTitular(rs.getInt("Titular"));
                    plantilla.setLigas(new LigaConexiones().get(rs.getString("IdLiga")));
                    plantillas.add(plantilla);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            Conexion.cerrarConexion(connection);
        }
        return plantillas;
    }

    public boolean deleteByTeamUser(int i) {
        Connection connection = null;
        try {
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query = "DELETE FROM Plantillas WHERE IdEquipo="+i;

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                stmt.executeUpdate(query);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Conexion.cerrarConexion(connection);
        }
        return true;
    }


    @Override
    public ArrayList<Plantillas> getByIdLiga(String idLiga) {
        ArrayList<Plantillas> plantillas = new ArrayList<>();
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Plantillas WHERE IdLiga='"+idLiga+"'";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while(rs.next()){
                    Plantillas plantilla = new Plantillas();
                    plantilla.setFechaCompra(rs.getDate("FechaCompra"));
                    plantilla.setPrecio(rs.getInt("Precio"));
                    plantilla.setEquiposUsuarios(new EquipoUsuarioConexiones().getEquipo(rs.getString("IdEquipo")));
                    plantilla.setJugadores(new JugadorConexiones().getById(rs.getString("IdJugador")));
                    plantilla.setPuja(rs.getInt("Puja"));
                    plantilla.setTitular(rs.getInt("Titular"));
                    plantilla.setLigas(new LigaConexiones().get(rs.getString("IdLiga")));
                    plantillas.add(plantilla);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            Conexion.cerrarConexion(connection);
        }
        return plantillas;
    }

    public int getNumberPlayer(String idLiga,String idJugador) {
        Connection connection= null;
        ResultSet rs = null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                String query ="Select COUNT(IdJugador) FROM Plantillas WHERE IdLiga='"+idLiga+"' " +
                        "AND IdJugador='"+idJugador+"' AND Puja=1 GROUP BY IdJugador";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            Conexion.cerrarConexion(connection);
        }
        try {
            return rs.getInt(0);
        } catch (SQLException e) {
            return 0;
        }
    }


    @Override
    public ArrayList<Plantillas> getJugadoresByIdEquipoYLiga(int idEquipo,String idLiga) {
        ArrayList<Plantillas> plantillas = new ArrayList<>();
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Plantillas WHERE IdEquipo="+idEquipo+" AND IdLiga='"+idLiga+"'";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while(rs.next()){
                    Plantillas plantilla = new Plantillas();
                    plantilla.setFechaCompra(rs.getDate("FechaCompra"));
                    plantilla.setPrecio(rs.getInt("Precio"));
                    plantilla.setEquiposUsuarios(new EquipoUsuarioConexiones().getEquipo(rs.getString("IdEquipo")));
                    plantilla.setJugadores(new JugadorConexiones().getById(rs.getString("IdJugador")));
                    plantilla.setPuja(rs.getInt("Puja"));
                    plantilla.setTitular(rs.getInt("Titular"));
                    plantilla.setLigas(new LigaConexiones().get(rs.getString("IdLiga")));
                    plantillas.add(plantilla);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            Conexion.cerrarConexion(connection);
        }
        return plantillas;
    }

    @Override
    public ArrayList<Plantillas> getByIdEquipo(int idEquipo) {
        ArrayList<Plantillas> plantillas = new ArrayList<>();
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Plantillas WHERE IdEquipo="+idEquipo+"";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while(rs.next()){
                    Plantillas plantilla = new Plantillas();
                    plantilla.setFechaCompra(rs.getDate("FechaCompra"));
                    plantilla.setPrecio(rs.getInt("Precio"));
                    plantilla.setEquiposUsuarios(new EquipoUsuarioConexiones().getEquipo(rs.getInt("IdEquipo")+""));
                    plantilla.setJugadores(new JugadorConexiones().getById(rs.getString("IdJugador")));
                    plantilla.setPuja(rs.getInt("Puja"));
                    plantilla.setTitular(rs.getInt("Titular"));
                    plantilla.setLigas(new LigaConexiones().get(rs.getString("IdLiga")));
                    plantillas.add(plantilla);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            Conexion.cerrarConexion(connection);
        }
        return plantillas;
    }

    @Override
    public ArrayList<Plantillas> getByDate(Date date) {
        ArrayList<Plantillas> plantillas = new ArrayList<>();
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Plantillas WHERE FechaCompra='"+date+"'";
                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs = stmt.executeQuery(query);
                while(rs.next()){
                    Plantillas plantilla = new Plantillas();
                    plantilla.setFechaCompra(rs.getDate("FechaCompra"));
                    plantilla.setPrecio(rs.getInt("Precio"));
                    plantilla.setEquiposUsuarios(new EquipoUsuarioConexiones().getEquipo(rs.getString("IdEquipo")));
                    plantilla.setJugadores(new JugadorConexiones().getById(rs.getInt("IdJugador") + ""));
                    plantilla.setPuja(rs.getInt("Puja"));
                    plantilla.setTitular(rs.getInt("Titular"));
                    plantilla.setLigas(new LigaConexiones().get(rs.getString("IdLiga")));
                    plantillas.add(plantilla);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return plantillas;
    }

    @Override
    public ArrayList<Plantillas> getTitulares(String idLiga, String idEquipo) {
        ArrayList<Plantillas> plantillas = new ArrayList<>();
        Connection connection = null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Plantillas WHERE IdEquipo="+idEquipo+" AND IdLiga='"+idLiga+"' AND Titular=1";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while(rs.next()){
                    Plantillas plantilla = new Plantillas();
                    plantilla.setFechaCompra(rs.getDate("FechaCompra"));
                    plantilla.setPrecio(rs.getInt("Precio"));
                    plantilla.setEquiposUsuarios(new EquipoUsuarioConexiones().getEquipo(rs.getInt("IdEquipo")+""));
                    plantilla.setJugadores(new JugadorConexiones().getById(rs.getString("IdJugador")));
                    plantilla.setPuja(rs.getInt("Puja"));
                    plantilla.setTitular(rs.getInt("Titular"));
                    plantilla.setLigas(new LigaConexiones().get(rs.getString("IdLiga")));
                    plantillas.add(plantilla);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            Conexion.cerrarConexion(connection);
        }
        return plantillas;
    }

    @Override
    public boolean modificarPorPrecio(Plantillas plantillas) {
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                String query ="Update Plantillas Set IdJugador='"+plantillas.getJugadores().getIdJugador()+"'," +
                        "IdLiga='"+plantillas.getLigas().getIdLiga()+"',IdEquipo='"+plantillas.getEquiposUsuarios().getIdEquipo()+
                        ",FechaCompra='"+plantillas.getFechaCompra()+"'," +
                        "Puja="+plantillas.getPuja()+",Titular="+plantillas.getTitular() +"',Precio="+plantillas.getPrecio()+ "' WHERE Puja> (SELECT" +
                        " Puja FROM Plantillas WHERE IdLiga='"+plantillas.getLigas().getIdLiga()+"')";

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
}
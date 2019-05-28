package com.example.teamleaguebagit.Conexiones;

import com.example.teamleaguebagit.ConexionInterficies.PlantillaRepository;
import com.example.teamleaguebagit.pojos.EquiposUsuarios;
import com.example.teamleaguebagit.pojos.Partidos;
import com.example.teamleaguebagit.pojos.Plantillas;
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
public class PlantillaConexiones implements PlantillaRepository {
    @Override
    public boolean addPlantilla(Plantillas plantilla) {
        try{
            Connection connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                String query ="Insert into Plantillas (IdJugador,IdLiga,IdEquipo,Precio,FechaCompra,Puja,Titular)" +
                        " VALUES ('"+plantilla.getJugadores().getIdJugador()+"','"+plantilla.getLigas().getIdLiga()+"','"
                        +plantilla.getEquiposUsuarios().getIdEquipo()+"',"+plantilla.getPrecio()+",'"+plantilla.getFechaCompra()
                        +"',"+plantilla.getPuja()+","+plantilla.getTitular()+")";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                stmt.executeUpdate(query);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean updatePlantilla(Plantillas plantilla) {
        try{
            Connection connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                String query ="Update Plantillas Set IdJugador='"+plantilla.getJugadores().getIdJugador()+"'," +
                        "IdLiga='"+plantilla.getLigas().getIdLiga()+"',IdEquipo='"+plantilla.getEquiposUsuarios().getIdEquipo()
                        +"',Precio="+plantilla.getPrecio()+",FechaCompra='"+plantilla.getFechaCompra()+"'," +
                        "Puja="+plantilla.getPuja()+",Titular="+plantilla.getTitular();

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                stmt.executeUpdate(query);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public ArrayList<Plantillas> getByIdJugador(String idJugador) {
        ArrayList<Plantillas> plantillas = new ArrayList<>();
        try{
            Connection connection = Conexion.obtenerConexion();
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
        }
        return plantillas;
    }

    @Override
    public ArrayList<Plantillas> getByIdLiga(String idLiga) {
        ArrayList<Plantillas> plantillas = new ArrayList<>();
        try{
            Connection connection = Conexion.obtenerConexion();
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
        }
        return plantillas;
    }

    @Override
    public ArrayList<Plantillas> getByIdEquipo(String idEquipo) {
        ArrayList<Plantillas> plantillas = new ArrayList<>();
        try{
            Connection connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Plantillas WHERE IdEquipo='"+idEquipo+"'";

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
        }
        return plantillas;
    }
}
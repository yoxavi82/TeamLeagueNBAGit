package com.example.teamleaguebagit.Conexiones;

import com.example.teamleaguebagit.Actual;
import com.example.teamleaguebagit.ConexionInterficies.PlantillaRepository;
import com.example.teamleaguebagit.pojos.Equipos;
import com.example.teamleaguebagit.pojos.EquiposUsuarios;
import com.example.teamleaguebagit.pojos.Jugadores;
import com.example.teamleaguebagit.pojos.Partidos;
import com.example.teamleaguebagit.pojos.Plantillas;
import com.example.teamleaguebagit.pojos.Usuarios;
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
    public boolean removePlantilla(int idEquipo, String idJugador){
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                String query ="DELETE FROM `Plantillas` WHERE  WHERE IdEquipo='"+idEquipo+"' AND IdJugador='"+idJugador+"'";

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

    public boolean actualizarPlantilla(Plantillas plantilla) {
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                String query ="Update Plantillas Set IdJugador='"+plantilla.getJugadores().getIdJugador()+"'," +
                        "IdLiga='"+plantilla.getLigas().getIdLiga()+"',IdEquipo="+plantilla.getEquiposUsuarios().getIdEquipo()
                        +",Precio="+plantilla.getPrecio()+",FechaCompra='"+plantilla.getFechaCompra()+"'," +
                        "Puja="+plantilla.getPuja()+",Titular="+plantilla.getTitular()+" WHERE IdEquipo="+plantilla.getEquiposUsuarios().getIdEquipo()+" AND IdJugador ='" + plantilla.getJugadores().getIdJugador()+"'";

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
                String query ="Select * FROM Plantillas JOIN Jugadores ON Plantillas.IdJugador=Jugadores.IdJugador " +
                        "JOIN EquiposUsuarios ON Plantillas.IdEquipo=EquiposUsuarios.IdEquipo " +
                        "JOIN Equipos ON EquiposUsuarios.EquipoNBA=Equipos.IdEquipo WHERE Plantillas.IdLiga='"+idLiga+"'";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while(rs.next()){
                    Plantillas plantilla = new Plantillas();
                    plantilla.setFechaCompra(rs.getDate("Plantillas.FechaCompra"));
                    plantilla.setPrecio(rs.getInt("Plantillas.Precio"));
                    EquiposUsuarios equiposUsuarios = new EquiposUsuarios();
                    equiposUsuarios.setDinero(rs.getInt("EquiposUsuarios.Dinero"));

                    Equipos equipos = new Equipos();
                    equipos.setNombre(rs.getString("Equipos.Nombre"));
                    equipos.setIdEquipo(rs.getString("Equipos.IdEquipo"));
                    equiposUsuarios.setEquipos(equipos);
                    equiposUsuarios.setNombreEquipo(rs.getString("EquiposUsuarios.NombreEquipo"));
                    equiposUsuarios.setLigas(Actual.getLigaActual());
                    equiposUsuarios.setIdEquipo(rs.getInt("EquiposUsuarios.IdEquipo"));
                    equiposUsuarios.setPuntosTotales(rs.getInt("EquiposUsuarios.PuntosTotales"));

                    Usuarios user = new Usuarios();
                    user.setIdUsuario(rs.getString("EquiposUsuarios.IdUsuario"));

                    equiposUsuarios.setUsuarios(user);

                    Jugadores jugador = new Jugadores();
                    jugador.setIdJugador(rs.getString("Jugadores.IdJugador"));
                    jugador.setNombre(rs.getString("Jugadores.Nombre"));
                    jugador.setApellido(rs.getString("Jugadores.Apellido"));
                    jugador.setDorsal(rs.getString("Jugadores.Dorsal"));
                    jugador.setEquipos(equipos);
                    jugador.setPrecioMercado(rs.getInt("Jugadores.PrecioMercado"));
                    jugador.setPosicion(rs.getString("Jugadores.Posicion"));
                    jugador.setEstrellas(rs.getInt("Jugadores.Estrellas"));
                    jugador.setPuntosTotales(rs.getInt("Jugadores.PuntosTotales"));
                    jugador.setMedia(rs.getInt("Jugadores.Media"));

                    plantilla.setEquiposUsuarios(equiposUsuarios);
                    plantilla.setJugadores(jugador);
                    plantilla.setPuja(rs.getInt("Puja"));
                    plantilla.setTitular(rs.getInt("Titular"));
                    plantilla.setLigas(Actual.getLigaActual());
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
        int i=0;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                String query ="Select COUNT(IdJugador) FROM Plantillas WHERE IdLiga='"+idLiga+"' " +
                        "AND IdJugador='"+idJugador+"' AND Puja=1 GROUP BY IdJugador";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                if(rs.next()){
                    i = rs.getInt(1);
                }else return 0;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            Conexion.cerrarConexion(connection);
        }
        return i;
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
                String query ="Select * FROM Plantillas JOIN Jugadores ON Plantillas.IdJugador=Jugadores.IdJugador " +
                        "JOIN EquiposUsuarios ON Plantillas.IdEquipo=EquiposUsuarios.IdEquipo " +
                        "JOIN Equipos ON EquiposUsuarios.EquipoNBA=Equipos.IdEquipo" +
                        " WHERE Plantillas.IdEquipo="+idEquipo+" AND Plantillas.IdLiga='"+idLiga+"'";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while(rs.next()){
                    Plantillas plantilla = new Plantillas();
                    plantilla.setFechaCompra(rs.getDate("Plantillas.FechaCompra"));
                    plantilla.setPrecio(rs.getInt("Plantillas.Precio"));
                    EquiposUsuarios equiposUsuarios = new EquiposUsuarios();
                    equiposUsuarios.setDinero(rs.getInt("EquiposUsuarios.Dinero"));

                    Equipos equipos = new Equipos();
                    equipos.setNombre(rs.getString("Equipos.Nombre"));
                    equipos.setIdEquipo(rs.getString("Equipos.IdEquipo"));
                    equiposUsuarios.setEquipos(equipos);
                    equiposUsuarios.setNombreEquipo(rs.getString("EquiposUsuarios.NombreEquipo"));
                    equiposUsuarios.setLigas(Actual.getLigaActual());
                    equiposUsuarios.setIdEquipo(rs.getInt("EquiposUsuarios.IdEquipo"));
                    equiposUsuarios.setPuntosTotales(rs.getInt("EquiposUsuarios.PuntosTotales"));

                    Usuarios user = new Usuarios();
                    user.setIdUsuario(rs.getString("EquiposUsuarios.IdUsuario"));

                    equiposUsuarios.setUsuarios(user);

                    Jugadores jugador = new Jugadores();
                    jugador.setIdJugador(rs.getString("Jugadores.IdJugador"));
                    jugador.setNombre(rs.getString("Jugadores.Nombre"));
                    jugador.setApellido(rs.getString("Jugadores.Apellido"));
                    jugador.setDorsal(rs.getString("Jugadores.Dorsal"));
                    jugador.setEquipos(equipos);
                    jugador.setPrecioMercado(rs.getInt("Jugadores.PrecioMercado"));
                    jugador.setPosicion(rs.getString("Jugadores.Posicion"));
                    jugador.setEstrellas(rs.getInt("Jugadores.Estrellas"));
                    jugador.setPuntosTotales(rs.getInt("Jugadores.PuntosTotales"));
                    jugador.setMedia(rs.getInt("Jugadores.Media"));

                    plantilla.setEquiposUsuarios(equiposUsuarios);
                    plantilla.setJugadores(jugador);
                    plantilla.setPuja(rs.getInt("Puja"));
                    plantilla.setTitular(rs.getInt("Titular"));
                    plantilla.setLigas(Actual.getLigaActual());
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

    public Plantillas getJugadoresByJugadorYLiga(String idJugador,String idLiga) {
        Plantillas plantilla = new Plantillas();
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Plantillas WHERE IdJugador='"+idJugador+"' AND IdLiga='"+idLiga+"'";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                if(rs.next()){

                    plantilla.setFechaCompra(rs.getDate("FechaCompra"));
                    plantilla.setPrecio(rs.getInt("Precio"));
                    plantilla.setEquiposUsuarios(new EquipoUsuarioConexiones().getEquipo(rs.getString("IdEquipo")));
                    plantilla.setJugadores(new JugadorConexiones().getById(rs.getString("IdJugador")));
                    plantilla.setPuja(rs.getInt("Puja"));
                    plantilla.setTitular(rs.getInt("Titular"));
                    plantilla.setLigas(new LigaConexiones().get(rs.getString("IdLiga")));
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            Conexion.cerrarConexion(connection);
        }
        return plantilla;
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

                String query ="Select * FROM Plantillas JOIN Jugadores ON Plantillas.IdJugador=Jugadores.IdJugador " +
                        "JOIN EquiposUsuarios ON Plantillas.IdEquipo=EquiposUsuarios.IdEquipo " +
                        "JOIN Equipos ON EquiposUsuarios.EquipoNBA=Equipos.IdEquipo" +
                        " WHERE Plantillas.IdEquipo="+idEquipo+" AND Plantillas.IdEquipo='"+idEquipo+"'";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while(rs.next()){
                    Plantillas plantilla = new Plantillas();
                    plantilla.setFechaCompra(rs.getDate("Plantillas.FechaCompra"));
                    plantilla.setPrecio(rs.getInt("Plantillas.Precio"));
                    EquiposUsuarios equiposUsuarios = new EquiposUsuarios();
                    equiposUsuarios.setDinero(rs.getInt("EquiposUsuarios.Dinero"));
                    Equipos equipos = new Equipos();
                    equipos.setNombre(rs.getString("Equipos.Nombre"));
                    equipos.setIdEquipo(rs.getString("Equipos.IdEquipo"));
                    equiposUsuarios.setEquipos(equipos);
                    equiposUsuarios.setNombreEquipo(rs.getString("EquiposUsuarios.NombreEquipo"));
                    equiposUsuarios.setLigas(Actual.getLigaActual());
                    equiposUsuarios.setIdEquipo(rs.getInt("EquiposUsuarios.IdEquipo"));
                    equiposUsuarios.setPuntosTotales(rs.getInt("EquiposUsuarios.PuntosTotales"));

                    Usuarios user = new Usuarios();
                    user.setIdUsuario(rs.getString("EquiposUsuarios.IdUsuario"));
                    equiposUsuarios.setUsuarios(user);

                    Jugadores jugador = new Jugadores();
                    jugador.setIdJugador(rs.getString("Jugadores.IdJugador"));
                    jugador.setNombre(rs.getString("Jugadores.Nombre"));
                    jugador.setApellido(rs.getString("Jugadores.Apellido"));
                    jugador.setDorsal(rs.getString("Jugadores.Dorsal"));
                    jugador.setEquipos(equipos);
                    jugador.setPrecioMercado(rs.getInt("Jugadores.PrecioMercado"));
                    jugador.setPosicion(rs.getString("Jugadores.Posicion"));
                    jugador.setEstrellas(rs.getInt("Jugadores.Estrellas"));
                    jugador.setPuntosTotales(rs.getInt("Jugadores.PuntosTotales"));
                    jugador.setMedia(rs.getInt("Jugadores.Media"));

                    plantilla.setEquiposUsuarios(equiposUsuarios);
                    plantilla.setJugadores(jugador);
                    plantilla.setPuja(rs.getInt("Puja"));
                    plantilla.setTitular(rs.getInt("Titular"));
                    plantilla.setLigas(Actual.getLigaActual());
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
package com.example.teamleaguebagit.Conexiones;

import com.example.teamleaguebagit.ConexionInterficies.EquipoUsuarioRepository;
import com.example.teamleaguebagit.pojos.EquiposUsuarios;
import com.mysql.jdbc.Statement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author alber
 */
public class EquipoUsuarioConexiones implements EquipoUsuarioRepository {
    @Override
    public boolean register(EquiposUsuarios nuevo) {
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                String query ="Insert into EquiposUsuarios (NombreEquipo,EquipoNBA,IdUsuario,IdLiga," +
                        "Dinero,IdEquipo,PuntosTotales) VALUES ('"+nuevo.getNombreEquipo()+"','"
                        +nuevo.getEquipos().getIdEquipo()+"','"+nuevo.getUsuarios().getIdUsuario()+"','"
                        +nuevo.getLigas().getIdLiga()+"',"+nuevo.getDinero()+",'"+nuevo.getIdEquipo()
                        +"',"+nuevo.getPuntosTotales()+")";

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

    public boolean updateEquipoUsuario(EquiposUsuarios equipo){
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                String query ="Update EquiposUsuarios Set NombreEquipo='"+equipo.getNombreEquipo()+"'," +
                        "IdLiga='"+equipo.getLigas().getIdLiga()+"',EquipoNBA='"+equipo.getEquipos().getIdEquipo()
                        +"',IdUsuario='"+equipo.getUsuarios().getIdUsuario()+"',Dinero="+equipo.getDinero()+"," +
                        "PuntosTotales="+equipo.getPuntosTotales()+" WHERE IdEquipo="+equipo.getIdEquipo();

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

    public EquiposUsuarios getByLigaAndUser(String user,String liga){
        EquiposUsuarios equipo = new EquiposUsuarios();
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM EquiposUsuarios WHERE IdLiga='"+liga+"' AND IdUsuario='"+user+"'";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                if (rs.next()){
                    equipo.setIdEquipo(rs.getInt("IdEquipo"));
                    equipo.setDinero(rs.getInt("Dinero"));
                    equipo.setEquipos(new EquipoConexiones().get(rs.getString("EquipoNBA")));
                    equipo.setNombreEquipo(rs.getString("NombreEquipo"));
                    equipo.setLigas(new LigaConexiones().get(rs.getString("IdLiga")));
                    equipo.setUsuarios(new UsuarioConexiones().get(rs.getString("IdUsuario")));
                    equipo.setPuntosTotales(rs.getInt("PuntosTotales"));
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            Conexion.cerrarConexion(connection);
        }
        return equipo;
    }

    @Override
    public ArrayList<EquiposUsuarios> getAll() {
        ArrayList<EquiposUsuarios> equipos = new ArrayList<>();
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM EquiposUsuarios";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while(rs.next()){
                    EquiposUsuarios equipo = new EquiposUsuarios();
                    equipo.setIdEquipo(rs.getInt("IdEquipo"));
                    equipo.setDinero(rs.getInt("Dinero"));
                    equipo.setEquipos(new EquipoConexiones().get(rs.getString("EquipoNBA")));
                    equipo.setNombreEquipo(rs.getString("NombreEquipo"));
                    equipo.setLigas(new LigaConexiones().get(rs.getString("IdLiga")));
                    equipo.setPuntosTotales(rs.getInt("PuntosTotales"));
                    equipo.setUsuarios(new UsuarioConexiones().get(rs.getString("IdUsuario")));
                    equipos.add(equipo);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            Conexion.cerrarConexion(connection);
        }
        return equipos;
    }

    @Override
    public ArrayList<EquiposUsuarios> getByUser(String idUser) {
        ArrayList<EquiposUsuarios> equipos = new ArrayList<>();
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM EquiposUsuarios WHERE IdUsuario='"+idUser+"'";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while(rs.next()){
                    EquiposUsuarios equipo = new EquiposUsuarios();
                    equipo.setIdEquipo(rs.getInt("IdEquipo"));
                    equipo.setDinero(rs.getInt("Dinero"));
                    equipo.setEquipos(new EquipoConexiones().get(rs.getString("EquipoNBA")));
                    equipo.setNombreEquipo(rs.getString("NombreEquipo"));
                    equipo.setLigas(new LigaConexiones().get(rs.getString("IdLiga")));
                    equipo.setPuntosTotales(rs.getInt("PuntosTotales"));
                    equipo.setUsuarios(new UsuarioConexiones().get(rs.getString("IdUsuario")));
                    equipos.add(equipo);
                }
            }
        }catch (Exception ex){
//            ex.printStackTrace();
        }finally {

            Conexion.cerrarConexion(connection);
        }
        return equipos;
    }

    @Override
    public ArrayList<EquiposUsuarios> getByLiga(String idLiga) {
        ArrayList<EquiposUsuarios> equipos = new ArrayList<>();
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM EquiposUsuarios WHERE IdLiga='"+idLiga+"'";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while(rs.next()){
                    EquiposUsuarios equipo = new EquiposUsuarios();
                    equipo.setIdEquipo(rs.getInt("IdEquipo"));
                    equipo.setDinero(rs.getInt("Dinero"));
                    equipo.setEquipos(new EquipoConexiones().get(rs.getString("EquipoNBA")));
                    equipo.setNombreEquipo(rs.getString("NombreEquipo"));
                    equipo.setLigas(new LigaConexiones().get(rs.getString("IdLiga")));
                    equipo.setUsuarios(new UsuarioConexiones().get(rs.getString("IdUsuario")));
                    equipo.setPuntosTotales(rs.getInt("PuntosTotales"));
                    equipos.add(equipo);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            Conexion.cerrarConexion(connection);
        }
        return equipos;
    }

    @Override
    public EquiposUsuarios getEquipo(String id) {
        EquiposUsuarios equipo = new EquiposUsuarios();
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM EquiposUsuarios WHERE IdEquipo='"+id+"'";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                if (rs.next()){
                    equipo.setIdEquipo(rs.getInt("IdEquipo"));
                    equipo.setDinero(rs.getInt("Dinero"));
                    equipo.setEquipos(new EquipoConexiones().get(rs.getString("EquipoNBA")));
                    equipo.setNombreEquipo(rs.getString("NombreEquipo"));
                    equipo.setLigas(new LigaConexiones().get(rs.getString("IdLiga")));
                    equipo.setUsuarios(new UsuarioConexiones().get(rs.getString("IdUsuario")));
                    equipo.setPuntosTotales(rs.getInt("PuntosTotales"));
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            Conexion.cerrarConexion(connection);
        }
        return equipo;
    }

    @Override
    public boolean abandonar(int i) {
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                String query ="DELETE FROM EquiposUsuarios WHERE IdEquipo="+i;
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
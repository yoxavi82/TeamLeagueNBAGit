package com.example.teamleaguebagit.Conexiones;

import com.example.teamleaguebagit.ConexionInterficies.PartidosRepository;
import com.example.teamleaguebagit.pojos.Equipos;
import com.example.teamleaguebagit.pojos.Partidos;
import com.example.teamleaguebagit.pojos.Puntuaciones;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alber
 */
//TODO
public class PartidosConexiones implements PartidosRepository {

    @Override
    public boolean addPartido(Partidos partido) {
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                String query ="Insert into Partidos (IdPartido,IdVisitante,IdLocal,Semana)" +
                        " VALUES ('"+partido.getIdPartido()+"','"+partido.getEquiposByIdVisitante().getIdEquipo()+"','"
                        +partido.getEquiposByIdLocal().getIdEquipo()+"',"+partido.getSemana()+")";

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
    public ArrayList<Partidos> getAll() {
        ArrayList<Partidos> partidos = new ArrayList<>();
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Partidos";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while(rs.next()){
                    Partidos partido = new Partidos();
                    partido.setEquiposByIdLocal(new EquipoConexiones().get(rs.getString("IdLocal")));
                    partido.setEquiposByIdVisitante(new EquipoConexiones().get(rs.getString("IdVisitante")));
                    partido.setSemana(rs.getDate("Semana"));
                    partido.setIdPartido(rs.getInt("IdPartido"));
                    partidos.add(partido);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            Conexion.cerrarConexion(connection);
        }
        return partidos;
    }

    @Override
    public Partidos getById(String id) {
        Connection connection= null;
        Partidos partido = new Partidos();
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Partidos WHERE IdPartido="+id;

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while(rs.next()){

                    partido.setEquiposByIdLocal(new EquipoConexiones().get(rs.getString("IdLocal")));
                    partido.setEquiposByIdVisitante(new EquipoConexiones().get(rs.getString("IdVisitante")));
                    partido.setSemana(rs.getDate("Semana"));
                    partido.setIdPartido(rs.getInt("IdPartido"));
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            Conexion.cerrarConexion(connection);
        }
        return partido;
    }

    @Override
    public ArrayList<Partidos> getByEquipo(Equipos equipo) {
        ArrayList<Partidos> partidos = new ArrayList<>();
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Partidos WHERE IdLocal='"+equipo.getIdEquipo()
                        +"' OR IdVisitante='"+equipo.getIdEquipo()+"'";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while(rs.next()){
                    Partidos partido = new Partidos();
                    partido.setEquiposByIdLocal(new EquipoConexiones().get(rs.getString("IdLocal")));
                    partido.setEquiposByIdVisitante(new EquipoConexiones().get(rs.getString("IdVisitante")));
                    partido.setSemana(rs.getDate("Semana"));
                    partido.setIdPartido(rs.getInt("IdPartido"));
                    partidos.add(partido);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            Conexion.cerrarConexion(connection);
        }
        return partidos;
    }

    @Override
    public ArrayList<Partidos> getBySemana(Date semana) {
        ArrayList<Partidos> partidos = new ArrayList<>();
        Connection connection= null;
        try{
            connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Partidos WHERE Semana="+new java.sql.Date(semana.getDay());

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while(rs.next()){
                    Partidos partido = new Partidos();
                    partido.setEquiposByIdLocal(new EquipoConexiones().get(rs.getString("IdLocal")));
                    partido.setEquiposByIdVisitante(new EquipoConexiones().get(rs.getString("IdVisitante")));
                    partido.setSemana(rs.getDate("Semana"));
                    partido.setIdPartido(rs.getInt("IdPartido"));
                    partidos.add(partido);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            Conexion.cerrarConexion(connection);
        }
        return partidos;
    }
}
package com.example.teamleaguebagit.Conexiones;

import com.example.teamleaguebagit.ConexionInterficies.PartidosRepository;
import com.example.teamleaguebagit.pojos.Equipos;
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
public class PartidosConexiones implements PartidosRepository {

    @Override
    public boolean addPartido(Partidos partido) {
        try{
            Connection connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                String query ="Insert into Partidos (IdPartido,IdVisitante,IdLocal,Semana)" +
                        " VALUES ('"+partido.getIdPartido()+"','"+partido.getEquiposByIdVisitante().getIdEquipo()+"','"
                        +partido.getEquiposByIdLocal().getIdEquipo()+"',"+partido.getSemana()+")";

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
    public ArrayList<Partidos> getAll() {
        ArrayList<Partidos> partidos = new ArrayList<>();
        try{
            Connection connection = Conexion.obtenerConexion();
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
                    partido.setSemana(rs.getInt("Semana"));
                    partido.setIdPartido(rs.getInt("IdPartido"));
                    partidos.add(partido);
                }
            }
            Conexion.cerrarConexion(connection);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return partidos;
    }

    @Override
    public Partidos getById(String id) {
        Partidos partido = new Partidos();
        try{
            Connection connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Partidos WHERE IdPartido="+id;

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while(rs.next()){

                    partido.setEquiposByIdLocal(new EquipoConexiones().get(rs.getString("IdLocal")));
                    partido.setEquiposByIdVisitante(new EquipoConexiones().get(rs.getString("IdVisitante")));
                    partido.setSemana(rs.getInt("Semana"));
                    partido.setIdPartido(rs.getInt("IdPartido"));
                }
            }
            Conexion.cerrarConexion(connection);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return partido;
    }

    @Override
    public ArrayList<Partidos> getByEquipo(Equipos equipo) {
        ArrayList<Partidos> partidos = new ArrayList<>();
        try{
            Connection connection = Conexion.obtenerConexion();
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
                    partido.setSemana(rs.getInt("Semana"));
                    partido.setIdPartido(rs.getInt("IdPartido"));
                    partidos.add(partido);
                }
            }
            Conexion.cerrarConexion(connection);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return partidos;
    }

    @Override
    public ArrayList<Partidos> getBySemana(Integer semana) {
        ArrayList<Partidos> partidos = new ArrayList<>();
        try{
            Connection connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Partidos WHERE Semana="+semana;

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while(rs.next()){
                    Partidos partido = new Partidos();
                    partido.setEquiposByIdLocal(new EquipoConexiones().get(rs.getString("IdLocal")));
                    partido.setEquiposByIdVisitante(new EquipoConexiones().get(rs.getString("IdVisitante")));
                    partido.setSemana(rs.getInt("Semana"));
                    partido.setIdPartido(rs.getInt("IdPartido"));
                    partidos.add(partido);
                }
            }
            Conexion.cerrarConexion(connection);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return partidos;
    }
}
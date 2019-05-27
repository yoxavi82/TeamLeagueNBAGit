package com.example.teamleaguebagit.Conexiones;

import com.example.teamleaguebagit.ConexionInterficies.JugadorRepository;
import com.example.teamleaguebagit.pojos.Jugadores;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class JugadorConexiones implements JugadorRepository {

    @Override
    public ArrayList<Jugadores> getByStars(int i) {
        ArrayList<Jugadores> buscado =new ArrayList<>();
        try{
            Connection connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Jugadores WHERE Estrellas="+i+"";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while(rs.next()){
                    Jugadores jugador = new Jugadores();
                    jugador.setApellido(rs.getString("Apellido"));
                    jugador.setDorsal(rs.getString("Dorsal"));
                    jugador.setEquipos(new EquipoConexiones().get(rs.getString("Equipos")));
                    jugador.setNombre(rs.getString("Nombre"));
                    jugador.setIdJugador(rs.getString("IdJugador"));
                    jugador.setLesionado(rs.getInt("Lesionado"));
                    jugador.setPrecioMercado(rs.getInt("PrecioMercado"));
                    jugador.setPosicion(rs.getString("Posicion"));
                    jugador.setEstrellas(rs.getInt("Estrellas"));
                    jugador.setPuntosTotales(rs.getInt("PuntosTotales"));
                    jugador.setMedia(rs.getInt("Media"));
                    jugador.setImagen(rs.getBytes("Imagen"));
                    buscado.add(jugador);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return buscado;
    }

    @Override
    public Jugadores getById(String id) {
        Jugadores jugador=new Jugadores();
        try{
            Connection connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Jugadores WHERE IdJugador='"+id+"'";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                if(rs.next()){
                    jugador.setApellido(rs.getString("Apellido"));
                    jugador.setDorsal(rs.getString("Dorsal"));
                    jugador.setNombre(rs.getString("Nombre"));
                    jugador.setEquipos(new EquipoConexiones().get(rs.getString("Equipos")));
                    jugador.setIdJugador(rs.getString("IdJugador"));
                    jugador.setLesionado(rs.getInt("Lesionado"));
                    jugador.setPrecioMercado(rs.getInt("PrecioMercado"));
                    jugador.setPosicion(rs.getString("Posicion"));
                    jugador.setEstrellas(rs.getInt("Estrellas"));
                    jugador.setPuntosTotales(rs.getInt("PuntosTotales"));
                    jugador.setMedia(rs.getInt("Media"));
                    jugador.setImagen(rs.getBytes("Imagen"));
                }else {
                    return null;
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return jugador;
    }

    @Override
    public ArrayList<Jugadores> getByTeam(String equipo) {
        ArrayList<Jugadores> buscado =new ArrayList<>();
        try{
            Connection connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Jugadores WHERE IdEquipo='"+equipo+"'";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while(rs.next()){
                    Jugadores jugador = new Jugadores();
                    jugador.setApellido(rs.getString("Apellido"));
                    jugador.setDorsal(rs.getString("Dorsal"));
                    jugador.setEquipos(new EquipoConexiones().get(rs.getString("IdEquipo")));
                    jugador.setNombre(rs.getString("Nombre"));
                    jugador.setIdJugador(rs.getString("IdJugador"));
                    jugador.setLesionado(rs.getInt("Lesionado"));
                    jugador.setPrecioMercado(rs.getInt("PrecioMercado"));
                    jugador.setPosicion(rs.getString("Posicion"));
                    jugador.setEstrellas(rs.getInt("Estrellas"));
                    jugador.setPuntosTotales(rs.getInt("PuntosTotales"));
                    jugador.setMedia(rs.getInt("Media"));
                    jugador.setImagen(rs.getBytes("Imagen"));
                    buscado.add(jugador);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return buscado;
    }
}

package com.example.teamleaguebagit.Conexiones;

import com.example.teamleaguebagit.ConexionInterficies.EquipoRepository;
import com.example.teamleaguebagit.pojos.Equipos;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author alber
 */
public class EquipoConexiones implements EquipoRepository {

    @Override
    public Equipos get(String id) {
        Equipos equipo = new Equipos();
        try{
            Connection connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Equipos WHERE IdEquipo='"+id+"'";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                if (rs.next()){
                    equipo.setColor(rs.getString("Color"));
                    equipo.setIdEquipo(rs.getString("IdEquipo"));
                    equipo.setNombre(rs.getString("Nombre"));
                    equipo.setLogo(rs.getString("Logo"));
                    equipo.setCampo(rs.getString("Campo"));
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return equipo;
    }

    @Override
    public ArrayList<Equipos> getAll() {
        ArrayList<Equipos> equipos = new ArrayList<>();
        try{
            Connection connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM Equipos";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                while (rs.next()){
                    Equipos equipo = new Equipos();
                    equipo.setColor(rs.getString("Color"));
                    equipo.setIdEquipo(rs.getString("IdEquipo"));
                    equipo.setNombre(rs.getString("Nombre"));
                    equipo.setLogo(rs.getString("Logo"));
                    equipo.setCampo(rs.getString("Campo"));
                    equipos.add(equipo);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return equipos;
    }

}

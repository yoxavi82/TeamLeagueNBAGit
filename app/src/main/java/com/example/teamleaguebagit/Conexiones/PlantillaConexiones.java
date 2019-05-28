package com.example.teamleaguebagit.Conexiones;///*

import com.example.teamleaguebagit.ConexionInterficies.PlantillaRepository;
import com.example.teamleaguebagit.pojos.Plantillas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class PlantillaConexiones implements PlantillaRepository {

    @Override
    public boolean addPlantilla(Plantillas plantilla) {
        try{
            Connection connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                String query ="INSERT INTO `Plantillas`(IdJugador, IdLiga, IdEquipo, Precio, FechaCompra, Puja, Titular)" +
                        " VALUES ('"+plantilla.getId().getIdJugador()+"','"+plantilla.getId().getIdLiga()+"','"+plantilla.getId().getIdEquipo()
                        +"','"+plantilla.getPrecio()+"','"+plantilla.getFechaCompra()+"','"+plantilla.getPuja()+"','"+plantilla.getTitular()+")";

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
        return false;
    }

    @Override
    public ArrayList<Plantillas> getByIdJugador(String idJugador) {
        return null;
    }

    @Override
    public ArrayList<Plantillas> getByIdLoiga(String idLiga) {
        return null;
    }

    @Override
    public ArrayList<Plantillas> getByIdEquipo(String idEquipo) {
        return null;
    }
}

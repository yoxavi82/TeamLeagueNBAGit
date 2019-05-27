package com.example.teamleaguebagit.Conexiones;

import android.os.StrictMode;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    static String url = "jdbc:mysql://remotemysql.com:3306/k0zCh3gTgb";
    static String user = "k0zCh3gTgb";
    static String passwrd = "HD5V4w6oyv";

    public static Connection obtenerConexion(){

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, user, passwrd);
            if (connection != null) {
                return connection;
            }
            return null;
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

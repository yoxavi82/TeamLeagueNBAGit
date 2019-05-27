/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.teamleaguebagit.Conexiones;

import android.widget.Toast;

import com.example.teamleaguebagit.ConexionInterficies.UsuarioRepository;
import com.example.teamleaguebagit.pojos.PasswordUsuarios;
import com.example.teamleaguebagit.pojos.Usuarios;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alber
 */
public class UsuarioConexiones implements UsuarioRepository {

    @Override
    public boolean register(Usuarios usuario) {
        try{
            Connection connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                String query ="Insert into Usuarios (IdUsuario,Nombre,Apellidos,Correo,FechaNacimiento,Admin)" +
                        " VALUES ('"+usuario.getIdUsuario()+"','"+usuario.getNombre()+"','"+usuario.getApellidos()
                        +"','"+usuario.getCorreo()+"','"+usuario.getFechaNacimiento()+"',0)";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                stmt.executeUpdate(query);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(Usuarios usuario) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            session.update(usuario);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//            return false;
//        } finally {
//            session.close();
//        }
        return true;
    }

    @Override
    public PasswordUsuarios login(String user) {
        PasswordUsuarios buscado = new PasswordUsuarios();
        try{
            Connection connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                ResultSet rs = null;
                String query ="Select * FROM PasswordUsuarios WHERE IdUsuario='"+user+"'";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = stmt.executeQuery(query);
                if(rs.next()){
                    buscado.setPassword(rs.getString("Password"));
                    buscado.setIdUsuario(user);
                }else{
                    return null;
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return buscado;

    }

    @Override
    public Usuarios get(String id) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
        Usuarios buscado = new Usuarios();
//        try {
//            tx = session.beginTransaction();
//            buscado =  (Usuarios) session.get(Usuarios.class, id);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
        return buscado;
    }

    @Override
    public ArrayList<Usuarios> getAll() {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
        List usuarios = new ArrayList();
//        try {
//            tx = session.beginTransaction();
//            usuarios = session.createQuery("FROM Usuarios").list();
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
        return (ArrayList<Usuarios>) usuarios;
    }

    @Override
    public boolean registrarPassword(PasswordUsuarios passwordUsuarios) {
        try{
            Connection connection = Conexion.obtenerConexion();
            if (connection == null) {
            } else {
                String query ="Insert into PasswordUsuarios (IdUsuario,Password) VALUES" +
                        " ('"+passwordUsuarios.getIdUsuario()+"','"+passwordUsuarios.getPassword()+"')";

                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                stmt.executeUpdate(query);
//                rs.next();
//                connection.close();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return true;
    }
}
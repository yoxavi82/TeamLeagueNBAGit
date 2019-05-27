///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.example.teamleaguebagit.Conexiones;
//
//import java.util.ArrayList;
//import java.util.List;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import pojos.Equipos;
//import pojos.Jugadores;
//import repository.JugadorRepository;
//import service.SessionFactoryUtil;
//
///**
// *
// * @author alber
// */
//public class JugadorConexiones implements JugadorRepository{
//
//    @Override
//    public ArrayList<Jugadores> getAll() {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        List ligas = new ArrayList();
//        try {
//            tx = session.beginTransaction();
//            ligas = session.createQuery("FROM Jugadores").list();
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return (ArrayList<Jugadores>) ligas;
//    }
//
//
//    @Override
//    public ArrayList<Jugadores> getByJugador(Jugadores jugador) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        List ligas = new ArrayList();
//        try {
//            tx = session.beginTransaction();
//            ligas = session.createQuery("FROM Jugadores WHERE Nombre like '%"+jugador.getNombre()+"%' AND "
//                    + "Apellido like '%"+jugador.getApellido()+"%' AND Id_Equipo like '%"+jugador.getEquipos()+"%'"
//                    + "AND Posicion like '%"+jugador.getPosicion()+"%' ").list();
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return (ArrayList<Jugadores>) ligas;
//    }
//
//    @Override
//    public Jugadores getById(String id) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        Jugadores ligas = new Jugadores();
//        try {
//            tx = session.beginTransaction();
//            ligas = (Jugadores) session.get(Jugadores.class,id);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return ligas;
//    }
//}

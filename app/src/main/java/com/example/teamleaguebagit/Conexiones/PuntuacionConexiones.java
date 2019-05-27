package com.example.teamleaguebagit.Conexiones;///*
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
//import pojos.EquiposUsuarios;
//import pojos.Jugadores;
//import pojos.Puntuaciones;
//import repository.PuntuacionRepository;
//import service.SessionFactoryUtil;
//
///**
// *
// * @author alber
// */
//public class PuntuacionConexiones implements PuntuacionRepository{
//
//    @Override
//    public boolean addPuntuacion(Puntuaciones pun) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            session.save(pun);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx!=null) tx.rollback();
//            e.printStackTrace();
//            return false;
//        } finally {
//            session.close();
//        }
//        return true;
//    }
//
//    @Override
//    public ArrayList<Puntuaciones> getByPartido(String idPartido) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        List ligas = new ArrayList();
//        try {
//            tx = session.beginTransaction();
//            ligas = session.createQuery("FROM Puntuaciones Where Id_Partido='"+idPartido+"'").list();
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return (ArrayList<Puntuaciones>) ligas;
//    }
//
//    @Override
//    public ArrayList<Puntuaciones> getByJugador(String idJugador) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        List ligas = new ArrayList();
//        try {
//            tx = session.beginTransaction();
//            ligas = session.createQuery("FROM Puntuaciones Where Id_Jugador='"+idJugador+"'").list();
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return (ArrayList<Puntuaciones>) ligas;
//    }
//
//    @Override
//    public ArrayList<Puntuaciones> getAll() {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        List ligas = new ArrayList();
//        try {
//            tx = session.beginTransaction();
//            ligas = session.createQuery("FROM Puntuaciones").list();
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return (ArrayList<Puntuaciones>) ligas;
//    }
//}
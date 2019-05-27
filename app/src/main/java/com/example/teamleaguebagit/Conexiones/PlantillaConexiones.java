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
//import pojos.Partidos;
//import pojos.Plantillas;
//import repository.PlantillaRepository;
//import service.SessionFactoryUtil;
//
///**
// *
// * @author alber
// */
//public class PlantillaConexiones implements PlantillaRepository{
//
//    @Override
//    public boolean addPlantilla(Plantillas plantilla) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            session.save(plantilla);
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
//    public boolean updatePlantilla(Plantillas plantilla) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            session.update(plantilla);
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
//    public ArrayList<Plantillas> getByIdJugador(String idJugador) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        List ligas = new ArrayList();
//        try {
//            tx = session.beginTransaction();
//            ligas = session.createQuery("FROM Plantilla WHERE Id_Jugador='"+idJugador+"'").list();
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return (ArrayList<Plantillas>) ligas;
//    }
//
//    @Override
//    public ArrayList<Plantillas> getByIdLoiga(String idLiga) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        List ligas = new ArrayList();
//        try {
//            tx = session.beginTransaction();
//            ligas = session.createQuery("FROM Plantilla WHERE Id_Liga='"+idLiga+"'").list();
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return (ArrayList<Plantillas>) ligas;
//    }
//
//    @Override
//    public ArrayList<Plantillas> getByIdEquipo(String idEquipo) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        List ligas = new ArrayList();
//        try {
//            tx = session.beginTransaction();
//            ligas = session.createQuery("FROM Plantilla WHERE Id_Equipo='"+idEquipo+"'").list();
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return (ArrayList<Plantillas>) ligas;
//    }
//
//}

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
//import pojos.Partidos;
//import pojos.Puntuaciones;
//import repository.PartidosRepository;
//import service.SessionFactoryUtil;
//
///**
// *
// * @author alber
// */
//public class PartidosConexiones implements PartidosRepository{
//
//    @Override
//    public boolean addPartido(Partidos partido) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            session.save(partido);
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
//    public ArrayList<Partidos> getAll() {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        List ligas = new ArrayList();
//        try {
//            tx = session.beginTransaction();
//            ligas = session.createQuery("FROM Partidos").list();
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return (ArrayList<Partidos>) ligas;
//    }
//
//    @Override
//    public ArrayList<Partidos> getById(String id) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        List ligas = new ArrayList();
//        try {
//            tx = session.beginTransaction();
//            ligas = session.createQuery("FROM Partidos WHERE Id_Partido='"+id+"'").list();
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return (ArrayList<Partidos>) ligas;
//    }
//
//    @Override
//    public ArrayList<Partidos> getByEquipo(Equipos equipo) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        List ligas = new ArrayList();
//        try {
//            tx = session.beginTransaction();
//            ligas = session.createQuery("FROM Partidos WHERE Id_Visitante='"+equipo.getIdEquipo()
//                    +" OR Id_Local='"+equipo.getIdEquipo()+"'").list();
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return (ArrayList<Partidos>) ligas;
//    }
//
//    @Override
//    public ArrayList<Partidos> getBySemana(Integer semana) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        List ligas = new ArrayList();
//        try {
//            tx = session.beginTransaction();
//            ligas = session.createQuery("FROM Partidos WHERE Semana="+semana.toString()).list();
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return (ArrayList<Partidos>) ligas;
//    }
//}
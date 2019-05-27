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
//import pojos.EquiposUsuarios;
//import pojos.Ligas;
//import repository.EquipoUsuarioRepository;
//import service.SessionFactoryUtil;
//
///**
// *
// * @author alber
// */
//public class EquipoUsuarioConexiones implements EquipoUsuarioRepository{
//
//    @Override
//    public boolean register(EquiposUsuarios nuevo) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            session.save(nuevo);
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
//    public boolean update(EquiposUsuarios nuevo) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            session.update(nuevo);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//            return false;
//        } finally {
//            session.close();
//        }
//        return true;
//    }
//
//    @Override
//    public ArrayList<EquiposUsuarios> getAll() {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        List ligas = new ArrayList();
//        try {
//            tx = session.beginTransaction();
//            ligas = session.createQuery("FROM EquiposUsuarios").list();
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return (ArrayList<EquiposUsuarios>) ligas;
//    }
//
//    @Override
//    public ArrayList<EquiposUsuarios> getByUser(String idUser) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        List ligas = new ArrayList();
//        try {
//            tx = session.beginTransaction();
//            ligas = session.createQuery("FROM Equipos_usuarios Where Id_Usuario='"+idUser+"'").list();
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return (ArrayList<EquiposUsuarios>) ligas;
//    }
//
//    @Override
//    public ArrayList<EquiposUsuarios> getByLiga(String idLiga) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        List ligas = new ArrayList();
//        try {
//            tx = session.beginTransaction();
//            ligas = session.createQuery("FROM Equipos_usuarios Where Id_Liga='"+idLiga+"'").list();
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return (ArrayList<EquiposUsuarios>) ligas;
//    }
//
//    @Override
//    public EquiposUsuarios getEquipo(String id) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        EquiposUsuarios ligas = new EquiposUsuarios();
//        try {
//            tx = session.beginTransaction();
//            ligas = (EquiposUsuarios) session.get(EquiposUsuarios.class,id);
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

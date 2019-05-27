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
//import pojos.Ligas;
//import pojos.PasswordLigas;
//import pojos.Usuarios;
//import repository.LigaRepository;
//import service.SessionFactoryUtil;
//
///**
// *
// * @author alber
// */
//public class LigaConexiones implements LigaRepository{
//
//    @Override
//    public boolean register(Ligas liga) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            session.save(liga);
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
//    public boolean update(Ligas liga) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            session.update(liga);
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
//    public PasswordLigas unirte(Ligas ligas) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        PasswordLigas buscado = new PasswordLigas();
//        try {
//            tx = session.beginTransaction();
//            buscado =  (PasswordLigas) session.get(PasswordLigas.class, ligas.getIdLiga());
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return buscado;
//    }
//
//    @Override
//    public ArrayList<Ligas> getAll() {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        List ligas = new ArrayList();
//        try {
//            tx = session.beginTransaction();
//            ligas = session.createQuery("FROM Ligas").list();
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return (ArrayList<Ligas>) ligas;
//    }
//
//    @Override
//    public Ligas get(String id) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        Ligas buscado = new Ligas();
//        try {
//            tx = session.beginTransaction();
//            buscado =  (Ligas) session.get(Ligas.class, id);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return buscado;
//    }
//
//}

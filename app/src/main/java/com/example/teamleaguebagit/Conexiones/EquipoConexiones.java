///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.example.teamleaguebagit.Conexiones;
//
//import com.example.teamleaguebagit.ConexionInterficies.EquipoRepository;
//import com.example.teamleaguebagit.pojos.Equipos;
//
//import java.util.ArrayList;
//import java.util.List;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import pojos.Equipos;
//import pojos.EquiposUsuarios;
//import pojos.Usuarios;
//import repository.EquipoRepository;
//import service.SessionFactoryUtil;
//
///**
// *
// * @author alber
// */
//public class EquipoConexiones implements EquipoRepository {
//
//    @Override
//    public Equipos get(String id) {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        Equipos ligas = new Equipos();
//        try {
//            tx = session.beginTransaction();
//            ligas = (Equipos) session.get(Equipos.class,id);
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
//
//    @Override
//    public ArrayList<Equipos> getAll() {
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        List ligas = new ArrayList();
//        try {
//            tx = session.beginTransaction();
//            ligas = session.createQuery("FROM Equipos").list();
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return (ArrayList<Equipos>) ligas;
//    }
//
//}

package com.hibernate.framework.cinema.prectise;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.framework.src.Student;

public class ManageData {
	
	private static SessionFactory factory; 
	
	public ManageData(){
		try{
	        factory = new Configuration().configure().buildSessionFactory();
	     }catch (Throwable ex) { 
	        System.err.println("Failed to create sessionFactory object." + ex);
	        throw new ExceptionInInitializerError(ex); 
	     }
	}
	
	public Integer addCinema(CinemaBean cinemaBean){
		
		Session session = factory.openSession();
	      Transaction tx = null;
	      Integer cinemaID = null;
	      try{
	         tx = session.beginTransaction();
	         cinemaID = (Integer) session.save(cinemaBean); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return cinemaID;
	}
	
}

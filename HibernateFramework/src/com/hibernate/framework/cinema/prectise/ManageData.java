package com.hibernate.framework.cinema.prectise;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
	         DirectorBean directorBean = new DirectorBean();
	         
	         directorBean.setName("Max");
	         directorBean.setMovies(cinemaBean.getMovies());
	         
	         Set<MovieBean> movies = cinemaBean.getMovies();
	         
	         ActorBean actorBeanA = new ActorBean();
	         actorBeanA.setName("actorA");
	         ActorBean actorBeanB = new ActorBean();
	         actorBeanB.setName("actorB");
	         ActorBean actorBeanC = new ActorBean();
	         actorBeanC.setName("actorC");
	         
	         ActorBean actorBeanD = new ActorBean();
	         actorBeanD.setName("actorD");
	         
	         ActorBean actorBeanG = new ActorBean();
	         actorBeanG.setName("actorG");
	         ActorBean actorBeanH = new ActorBean();
	         actorBeanH.setName("actorH");
	         ActorBean actorBeanI = new ActorBean();
	         actorBeanI.setName("actorI");
	         
	         for (MovieBean movieBean : movies) {
			      if(movieBean.getName().toLowerCase().equals("troy")){
			    	  
			    	  actorBeanA.getMovies().add(movieBean);  
//			    	  actorBeanB.getMovies().add(movieBean);  
//			    	  actorBeanC.getMovies().add(movieBean);
//			    	  actorBeanD.getMovies().add(movieBean);
			    	  
			    	  movieBean.getActors().add(actorBeanA);
//			    	  movieBean.getActors().add(actorBeanB);
//			    	  movieBean.getActors().add(actorBeanC);
			    	  
			    	 // movieBean.getActors().add(actorBeanD);
			    	  
			      }else if(movieBean.getName().toLowerCase().equals("rocky")){
			    	  
			    	  //actorBeanA.getMovies().add(movieBean);  
			    	  //actorBeanB.getMovies().add(movieBean);  
			    	 // actorBeanC.getMovies().add(movieBean);
			    	 // actorBeanD.getMovies().add(movieBean);
			    	  
			    	  //movieBean.getActors().add(actorBeanA);
			    	//  movieBean.getActors().add(actorBeanB);
			    	 // movieBean.getActors().add(actorBeanC);
			    	  
			    	 // movieBean.getActors().add(actorBeanD);
			    	  
			      }else if(movieBean.getName().toLowerCase().equals("war stars")){
//			    	  actorBeanG.getMovies().add(movieBean);  
//			    	  actorBeanH.getMovies().add(movieBean);  
//			    	  actorBeanI.getMovies().add(movieBean);
			    	 // actorBeanD.getMovies().add(movieBean);
			    	  
//			    	  movieBean.getActors().add(actorBeanG);
//			    	  movieBean.getActors().add(actorBeanH);
//			    	  movieBean.getActors().add(actorBeanI);
			    	  
			    	  //movieBean.getActors().add(actorBeanD);
			      }
			 }
	         cinemaID = (Integer) session.save(cinemaBean);
	         session.save(directorBean);
	         
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return cinemaID;
	}
	
	public List<MovieBean> getMovies(){
		  List<MovieBean> movies = null;
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         movies = session.createQuery("FROM MovieBean").list(); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return movies;
	}
	
//	public void saveDirectors(List<DirectorBean> directores){
//		 Session session = factory.openSession();
//	     Transaction tx = null;
//	  try{
//		  List<MovieBean> movies = getMovies();
//		   tx = session.beginTransaction();
//		   for (DirectorBean directorBean : directores) {
//			   //directorBean.setMovies(movies);
//			   Integer id = (Integer) session.save(directorBean);
//			   for (int i = 0; i < movies.size() ; i++) {
//				   movies.get(i).s
//				   session.update(arg0);
//			    }
//		   }
//		   tx.commit();
//		  
//	  }catch (HibernateException e) {
//	         if (tx!=null) tx.rollback();
//	         e.printStackTrace(); 
//      }finally {
//         session.close(); 
//      }
//	}
	
//	public void update(Integer id){
//		 Session session = factory.openSession();
//	     Transaction tx = null;
//	  try{
//		  tx = session.beginTransaction();
//		  
//		  MovieBean movieBean = session.get(MovieBean.class, id);
//		  movieBean.set
//		  session.update(movieBean);
//		  tx.commit();
//	  }catch (HibernateException e) {
//	         if (tx!=null) tx.rollback();
//	         e.printStackTrace(); 
//      }finally {
//         session.close(); 
//      }
//	}
	   
	 public void truncate(){
		 Transaction tx = null;
		 Session session = factory.openSession();
		 
		 try{
			 tx = session.beginTransaction();
			// String sql = "TRUNCATE TABLE CINEMA_CONNECTOR_MOVIE";
			 //session.createSQLQuery(sql).executeUpdate();  cinema_connector_movie
			 String sql = "TRUNCATE TABLE CINEMA";
			 session.createSQLQuery(sql).executeUpdate();
			 sql = "TRUNCATE TABLE MOVIE";
			 session.createSQLQuery(sql).executeUpdate();
			 sql = "TRUNCATE TABLE DIRECTOR";
			 session.createSQLQuery(sql).executeUpdate();
//			 sql = "TRUNCATE TABLE MOVIE_CONNECTOR_ACTORS";
//			 session.createSQLQuery(sql).executeUpdate();
//			 sql = "TRUNCATE TABLE ACTORS";
//			 session.createSQLQuery(sql).executeUpdate();
			 tx.commit();
		 }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		 
	 }
}

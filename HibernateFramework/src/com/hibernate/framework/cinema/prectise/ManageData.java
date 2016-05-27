package com.hibernate.framework.cinema.prectise;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ManageData implements IManageData{
	
	public ManageData(){
	}
	
	public Integer addCinema(CinemaBean cinemaBean){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
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
	      Session session = HibernateUtil.getSessionFactory().openSession();
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
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 
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

	//***************************************************************************************************************************************//
	 
	@Override
	public void saveCinema(Set<CinemaBean> cinemas){
		 Transaction tx = null;
		 Session session = null;
		 
		 try{
			 session = HibernateUtil.getSessionFactory().openSession();
			 tx = session.beginTransaction();
			 for (CinemaBean cinemaBean : cinemas) {
				 session.save(cinemaBean);
			 }
			 tx.commit();
		  }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}

	@Override
	public void addMovieToCinemaCompanyByID(Integer id) {
		Set<CinemaBean> listcinemas = new HashSet<CinemaBean>();
		listcinemas.add(getByID(id));
		
		MovieBean movieBean_1 = new MovieBean();
		MovieBean movieBean_2 = new MovieBean();
		MovieBean movieBean_3 = new MovieBean();
		
		ActorBean actorBean1 = new ActorBean();
		ActorBean actorBean2 = new ActorBean();
		ActorBean actorBean3 = new ActorBean();
		
		actorBean1.setName("Johne Travolta");
		actorBean2.setName("Morgan Friman");
		actorBean3.setName("Silvestar Stalon");
		
		movieBean_1.setName("Troy");
		movieBean_2.setName("Rock");
		movieBean_3.setName("War Stars");
		
		Set<MovieBean> listMovies = new HashSet<MovieBean>();
		Set<ActorBean> listActors = new HashSet<ActorBean>();
		listActors.add(actorBean1);
		listActors.add(actorBean2);
		movieBean_1.setActors(listActors);
		movieBean_1.setCinemas(listcinemas);
		movieBean_3.setActors(listActors);
		movieBean_3.setCinemas(listcinemas);
		
		listMovies.add(movieBean_1);
		listMovies.add(movieBean_3);
		
		Set<ActorBean> listActors2 = new HashSet<ActorBean>();
		listActors2.add(actorBean3);
		movieBean_2.setActors(listActors2);
		movieBean_2.setCinemas(listcinemas);
		
		listMovies.add(movieBean_2);
		
		saveGeneric(listMovies);
//		
//		actorBean1.setMovies(listMovies);
//		actorBean2.setMovies(listMovies);
//		
//		actorBean3.setMovies(listMovies_2);
	}
	
	private void saveGeneric(Set<MovieBean> listObj){
		 Transaction tx = null;
		 Session session = null;
		 
		 try{
			 session = HibernateUtil.getSessionFactory().openSession();
			 tx = session.beginTransaction();
			 for (Object obj : listObj) {
				 session.save(obj);
			 }
			 tx.commit();
		  }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}
	
	private CinemaBean getByID(Integer id){
		Transaction tx = null;
		Session session = null;
		CinemaBean cinemaBean = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			cinemaBean = session.get(CinemaBean.class,id);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return cinemaBean;
	}

	public void insertDirectors(List<DirectorBean> directors){
		Transaction tx = null;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			List<MovieBean> movies = session.createQuery("FROM MovieBean").list();
			for (DirectorBean director : directors) {
				for (MovieBean movieBean : movies) {
					if((director.getName().toLowerCase().equals("rogers")) && (movieBean.getName().toLowerCase().equals("war stars") || movieBean.getName().toLowerCase().equals("troy"))){
						director.getMovies().add(movieBean);
					}else if((director.getName().toLowerCase().equals("shpilberg")) && movieBean.getName().toLowerCase().equals("rock")){
						director.getMovies().add(movieBean);
					}
				}
			}
			for (DirectorBean director : directors) {
				session.save(director);
			}
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
	}
}

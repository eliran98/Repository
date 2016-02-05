package com.hibernate.framework.cinema.prectise;

import java.util.HashSet;
import java.util.Set;

public class ManagerApp {
		
		public static void func(){
			ManageData manageData = new ManageData();
			
			Set<MovieBean> movies = new HashSet<MovieBean>();
			
			MovieBean movieBean = new MovieBean();
			MovieBean movieBeanB = new MovieBean();
			MovieBean movieBeanC = new MovieBean();
			
			movieBean.setName("Troy");
			movieBeanB.setName("Rocky");
			movieBeanC.setName("War Stars");
			
			movies.add(movieBean);
			movies.add(movieBeanB);
			movies.add(movieBeanC);
			
			CinemaBean cinemaBean = new CinemaBean();
			
			cinemaBean.setMovies(movies);
			cinemaBean.setName("CinemaCity");
			
			manageData.addCinema(cinemaBean);
			
		}
}

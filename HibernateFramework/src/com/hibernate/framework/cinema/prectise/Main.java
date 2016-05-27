package com.hibernate.framework.cinema.prectise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		//ManagerApp.func();
		ManageData manageData = new ManageData();
		
		List<DirectorBean> directors = new ArrayList<DirectorBean>();
		DirectorBean  directorBean1 = new DirectorBean();
		directorBean1.setName("Rogers");
		DirectorBean  directorBean2 = new DirectorBean();
		directorBean2.setName("Shpilberg");
		directors.add(directorBean1);
		directors.add(directorBean2);
		manageData.insertDirectors(directors);
	}
	
	private static Set<CinemaBean> generateCinemasList(){
		Set<CinemaBean> cinemas = new HashSet<CinemaBean>();
		
		CinemaBean cinemaBean_1 = new CinemaBean();
		Set<CinemaDetials> listCinemaDetails_1 = new HashSet<CinemaDetials>();
		CinemaDetials cinemaDetials1 = new CinemaDetials();
		CinemaDetials cinemaDetials2 = new CinemaDetials();
		
		cinemaDetials1.setCity("Herzeliia");
		cinemaDetials1.setAddress("hmenofim 69");
		cinemaDetials1.setPhoneNumber("035775788");
		
		cinemaDetials2.setCity("Reshon Lezion");
		cinemaDetials2.setAddress("herzel 90");
		cinemaDetials2.setPhoneNumber("03544444");
		
		listCinemaDetails_1.add(cinemaDetials1);
		listCinemaDetails_1.add(cinemaDetials2);
		
		cinemaBean_1.setName("Cinima City");
		
		cinemaBean_1.setListCinemaDetails(listCinemaDetails_1);
		
		CinemaBean cinemaBean_2 = new CinemaBean();
		
		cinemaBean_2.setName("Yes Planet");
		
		Set<CinemaDetials> listCinemaDetails_2 = new HashSet<CinemaDetials>();
		CinemaDetials cinemaDetials3 = new CinemaDetials();
		CinemaDetials cinemaDetials4 = new CinemaDetials();
		
		cinemaDetials3.setCity("Ramat Gan");
		cinemaDetials3.setAddress("Kenyon Aylon 55");
		cinemaDetials3.setPhoneNumber("035666666");
		
		cinemaDetials4.setCity("Reshon Lezion");
		cinemaDetials4.setAddress("rothshild 90");
		cinemaDetials4.setPhoneNumber("035777444");
		
		listCinemaDetails_2.add(cinemaDetials3);
		listCinemaDetails_2.add(cinemaDetials4);
		
		cinemaBean_2.setListCinemaDetails(listCinemaDetails_2);
		
//		cinemaBean_3.setName("Globes");
//		cinemaBean_4.setName("Lev");
//		cinemaBean_5.setName("Rav Chen");
		
		cinemas.add(cinemaBean_1);
		cinemas.add(cinemaBean_2);
		
//		cinemas.add(cinemaBean_3);
//		cinemas.add(cinemaBean_4);
//		cinemas.add(cinemaBean_5);
		
		return cinemas;
	}

}

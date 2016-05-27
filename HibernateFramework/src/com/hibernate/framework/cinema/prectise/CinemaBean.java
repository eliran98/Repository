package com.hibernate.framework.cinema.prectise;

import java.util.HashSet;
import java.util.Set;

public class CinemaBean implements  java.io.Serializable{
  
	private Integer id;
	private String name;
	
	private Set<MovieBean> movies = new HashSet<MovieBean>();
	private Set<CinemaDetials> listCinemaDetails = new HashSet<CinemaDetials>();
	
	public CinemaBean(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<CinemaDetials> getListCinemaDetails() {
		return listCinemaDetails;
	}

	public void setListCinemaDetails(Set<CinemaDetials> listCinemaDetails) {
		this.listCinemaDetails = listCinemaDetails;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<MovieBean> getMovies() {
		return movies;
	}

	public void setMovies(Set<MovieBean> movies) {
		this.movies = movies;
	}
	
}

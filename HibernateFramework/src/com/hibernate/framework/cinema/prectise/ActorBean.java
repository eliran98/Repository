package com.hibernate.framework.cinema.prectise;

import java.util.HashSet;
import java.util.Set;

public class ActorBean implements java.io.Serializable{
   
	private Integer id;
	private String name;
	
	private Set<MovieBean> movies = new HashSet<MovieBean>();
	
	public ActorBean(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

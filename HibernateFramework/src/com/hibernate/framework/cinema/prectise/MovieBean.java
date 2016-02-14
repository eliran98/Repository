package com.hibernate.framework.cinema.prectise;

import java.util.HashSet;
import java.util.Set;

public class MovieBean implements java.io.Serializable{
  
	private Integer id;
	private String name;
	
	private Set<CinemaBean> cinemas = new HashSet<CinemaBean>();
	
	private Set<ActorBean> actors = new HashSet<ActorBean>();
	
	public MovieBean(){}
	
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

	public Set<CinemaBean> getCinemas() {
		return cinemas;
	}

	public void setCinemas(Set<CinemaBean> cinemas) {
		this.cinemas = cinemas;
	}

	public Set<ActorBean> getActors() {
		return actors;
	}

	public void setActors(Set<ActorBean> actors) {
		this.actors = actors;
	}
	
}

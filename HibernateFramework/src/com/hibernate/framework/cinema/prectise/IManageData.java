package com.hibernate.framework.cinema.prectise;

import java.util.Set;

public interface IManageData {
	
	public void saveCinema(Set<CinemaBean> cinemas);
	
	public void addMovieToCinemaCompanyByID(Integer id);
	
}

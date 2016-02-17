package com.hibernate.framework.tutorials;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.hibernate.framework.cinema.prectise.CinemaBean;
import com.hibernate.framework.cinema.prectise.MovieBean;

public class CriteriaQueriesTutorial {
	
	 private static SessionFactory factory; 
	
	public static void criteriaExamples(){
		
		 try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		
		 Session session = factory.openSession();
		 
		//Basic usage -  criteria query is one with no optional parameters or restrictions
		//get all CinemaBean
		Criteria crit = session.createCriteria(CinemaBean.class);
		List<Object> results = crit.list();
		
		//retrieve CinemaBean which NAME-COL equal YES_Plent.
		crit = session.createCriteria(CinemaBean.class);
		crit.add(Restrictions.eq("name","YES_Plent"));
		results = crit.list();
		
		//retrieve CinemaBean which NAME-COL not equal YES_Plent.
		crit = session.createCriteria(CinemaBean.class);
		crit.add(Restrictions.ne("name","YES_Plent"));
		results = crit.list();
		
		//retrieve all CinemaBean which his name contain given pattern
		//The ilike() method is case-insensitive.
		//ANYWHERE: Anyplace in the string
		//END: The end of the string
		//EXACT: An exact match
		//START: The beginning of the string
		crit = session.createCriteria(CinemaBean.class);
		crit.add(Restrictions.like("name","YES%",MatchMode.ANYWHERE));
		results = crit.list();
		
		//The isNull() and isNotNull() restrictions allow you to do a search for objects that have (or do not have) null property values.
		crit = session.createCriteria(CinemaBean.class);
		crit.add(Restrictions.isNull("name"));
		results = crit.list();
		
		//Restrictions.gt(), Restrictions.ge(), Restrictions.lt() and Restrictions.le()
		//gt() - greater-than comparison 
		//ge() - greater-than-or-equal-to comparison.
		//lt() - less-than comparison.
		//le() -  less-than-or-equal-to comparison
		crit = session.createCriteria(CinemaBean.class);
		crit.add(Restrictions.gt("id", 2));
		results = crit.list();
		
		//Combining Two or More Criteria - AND example. 
		crit = session.createCriteria(CinemaBean.class);
		crit.add(Restrictions.gt("id", 2));
		crit.add(Restrictions.like("name","YES%",MatchMode.ANYWHERE));
		results = crit.list();
		
		//Combining Two or More Criteria - OR example. 
		crit = session.createCriteria(CinemaBean.class);
		Criterion crit1 = Restrictions.gt("id", 2);
		Criterion crit2 = Restrictions.ilike("name", "YES_plant", MatchMode.ANYWHERE);
		LogicalExpression orExp = Restrictions.or(crit1, crit2);
		crit.add(orExp);
		results=crit.list();
		
		//If we wanted to create an OR expression with more than two different criteria - Using Disjunction Objects with Criteria
		crit = session.createCriteria(CinemaBean.class);
		crit1 = Restrictions.gt("id", 2);
		crit2 = Restrictions.ilike("name", "YES_plant", MatchMode.ANYWHERE);
		Criterion crit3 = Restrictions.ilike("name", "GLILOT", MatchMode.ANYWHERE);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(crit1);
		disjunction.add(crit2);
		disjunction.add(crit3);
		crit.add(disjunction);
		results = crit.list();
		
		//Restrictions.sqlRestriction() 
		//Your application’s code does not need to know the name of the table your class uses. Use {alias} to signify the class’s table
		crit = session.createCriteria(CinemaBean.class);
		crit.add(Restrictions.sqlRestriction("{alias}.name like 'Yes%'"));
		results = crit.list();
		
		//Paging Through the ResultSet
		crit = session.createCriteria(CinemaBean.class);
		crit.setFirstResult(1);
		crit.setMaxResults(20);
		results = crit.list();
		
		//please note that you need to make sure that your query returns only one or zero results if you use the uniqueResult() method.
		//Otherwise, Hibernate will throw a NonUniqueResultException exception.
		crit = session.createCriteria(CinemaBean.class);
		Criterion price = Restrictions.gt("id",3);
		crit.setMaxResults(1);
		CinemaBean cinemaBean = (CinemaBean) crit.uniqueResult();
		
		//Sorting the Query’s Results
		crit = session.createCriteria(CinemaBean.class);
		crit.add(Restrictions.gt("id",3));
		crit.addOrder(Order.desc("id"));
		results = crit.list();
		
		//Performing Associations (Joins)
		//The association works when going from either one-to-many or from many-to-one
		crit = session.createCriteria(MovieBean.class);
		Criteria prdCrit = crit.createCriteria("director");
		prdCrit.add(Restrictions.gt("id",2));
		results = crit.list();
		
		//Other aggregate functions available through the Projections factory class include the following:
		//avg(String propertyName): Gives the average of a property’s value
		//count(String propertyName): Counts the number of times a property occurs
		//countDistinct(String propertyName): Counts the number of unique values the property contains
		//max(String propertyName): Calculates the maximum value of the property values
		//min(String propertyName): Calculates the minimum value of the property values
		//sum(String propertyName): Calculates the sum total of the property values
		crit = session.createCriteria(CinemaBean.class);
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.max("price"));
		projList.add(Projections.min("price"));
		projList.add(Projections.avg("price"));
		projList.add(Projections.countDistinct("name"));
		crit.setProjection(projList);
		List<Object[]> result = crit.list();
		
		//Getting Selected Columns
		crit = session.createCriteria(CinemaBean.class);
		projList = Projections.projectionList();
		projList.add(Projections.property("name"));
		projList.add(Projections.property("id"));
		crit.setProjection(projList);
		crit.addOrder(Order.asc("price"));
		result = crit.list();
		
		//Query By Example (QBE)
		crit = session.createCriteria(CinemaBean.class);
		cinemaBean = new CinemaBean();
		cinemaBean.setName("Glilot");
		crit.add(Example.create(cinemaBean));
		results = crit.list();
	}
	
}

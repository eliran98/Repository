package com.hibernate.framework.src;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManageStudent {
   private static SessionFactory factory; 
   public static void main(String[] args) {
      try{
         factory = new Configuration().configure().buildSessionFactory();
      }catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
      ManageStudent ME = new ManageStudent();

      /* Add few Students records in database */
      Integer empID1 = ME.addStudent("eliran", "atias", 80, "elirann.atias@gmail.com", "0547760732");
      Integer empID2 = ME.addStudent("lior", "atias", 80, "lior.atias@gmail.com", "0547760732");
      Integer empID3 = ME.addStudent("dan", "oren", 80, "dan.oren@gmail.com", "0547760732");
      
//      ME.listStudents();
//      System.out.println("");
//      System.out.println("");
//      ME.updateStudent(empID3,100);
//      
//      ME.listStudents();
//      System.out.println("");
//      System.out.println("");
//      
//      ME.deleteStudent(empID3);
//      
//      ME.listStudents();
//      System.out.println("");
//      System.out.println("");
      
      	//ME.addBatchStudents();
        ME.truncate();
      	ME.listStudents();

   }
   /* Method to CREATE an employee in the database */
   public Integer addStudent(String firstName, String lastName, int grade,String email,String phoneNumber){
      Session session = factory.openSession();
      Transaction tx = null;
      Integer studentID = null;
      try{
         tx = session.beginTransaction();
         Student student = new Student(grade, phoneNumber, firstName, lastName, email);
         studentID = (Integer) session.save(student); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return studentID;
   }
   
   public void listStudents( ){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         List<Student> students = session.createQuery("FROM Student").list(); 
	         for (Iterator<Student> iterator = students.iterator(); iterator.hasNext();){
	            Student student = (Student) iterator.next(); 
	            System.out.print("First Name: " + student.getFirstName()); 
	            System.out.print("  Last Name: " + student.getLastName()); 
	            System.out.println("  grade: " + student.getGrade()); 
	            System.out.println("  phoneNumber: " + student.getPhoneNumber()); 
	            System.out.println("  email: " + student.getEmail()); 
	         }
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	   /* Method to UPDATE salary for an employee */
	   public void updateStudent(Integer studentID, int grade ){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Student student = (Student)session.get(Student.class, studentID); 
	         student.setGrade(grade);
			 session.update(student); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	   /* Method to DELETE an employee from the records */
	   public void deleteStudent(Integer studentID){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Student student = (Student)session.get(Student.class, studentID); 
	         session.delete(student); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	   
	   public void addBatchStudents( ){
		      Session session = factory.openSession();
		      Transaction tx = null;
		      try{
		         tx = session.beginTransaction();
		         for ( int i=0; i<100000; i++ ) {
		            String firstName = "First Name " + i;
		            String lastName = "Last Name " + i;
		            String email = "mail" + i + "@gmail.com";
		            String phoneNumber = "054776073" + (i%10);
		            Integer grade = i % 101;
		            Student student = new Student(grade, phoneNumber, firstName, lastName, email);
		            session.save(student);
		         	if( i % 50 == 0 ) {
		               session.flush();
		               session.clear();
		            }
		         }
		         tx.commit();
		      }catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      }finally {
		         session.close(); 
		      }
		      return ;
	 }
	   
	 public void truncate(){
		 Transaction tx = null;
		 Session session = factory.openSession();
		 
		 try{
			 tx = session.beginTransaction();
			 String sql = "TRUNCATE TABLE STUDENTS";
			 session.createSQLQuery(sql).executeUpdate();
			 tx.commit();
		 }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		 
	 }
   
}
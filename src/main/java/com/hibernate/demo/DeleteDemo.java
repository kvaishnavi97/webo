package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tut.hybernate.demo.entity.Instructor;
import com.tut.hybernate.demo.entity.InstructorDetail;
import com.tut.hybernate.demo.entity.Student;

public class DeleteDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();
			
			//get instructor by primary key/id
			int theId=2;
			Instructor tempInstructor=session.get(Instructor.class, theId);
			System.out.println("Found instructor : "+tempInstructor);
			if(tempInstructor!=null) {
				System.out.println("Deleting instructor: "+tempInstructor);
				//Will also delete associated object's 
				session.delete(tempInstructor);
			}

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

}
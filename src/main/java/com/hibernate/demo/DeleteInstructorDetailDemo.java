package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tut.hybernate.demo.entity.Instructor;
import com.tut.hybernate.demo.entity.InstructorDetail;
import com.tut.hybernate.demo.entity.Student;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			int theId = 6;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);

			System.out.println("tempInstructorDeatil: " + tempInstructorDetail);

			// print out associated instructor
			System.out.println(tempInstructorDetail.getInstructor());
			
			//remove the associated object reference 
			//break bi-directional link
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			
			
			session.delete(tempInstructorDetail);
			
			System.out.println("After deleting Instructor : "+tempInstructorDetail);
			

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		} catch (Exception e) {
			//handle session leaking issue
			session.close();
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}

}
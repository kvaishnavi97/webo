package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tut.hybernate.demo.entity.Instructor;
import com.tut.hybernate.demo.entity.InstructorDetail;
import com.tut.hybernate.demo.entity.Student;

public class BidirectionalMappingDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			int theId = 5;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);

			System.out.println("tempInstructorDeatil: " + tempInstructorDetail);

			// print out associated instructor
			System.out.println(tempInstructorDetail.getInstructor());
			// start a transaction

			// save the instructor
			//
			// Note: this will ALSO save the details object
			// because of CascadeType.ALL
			//

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
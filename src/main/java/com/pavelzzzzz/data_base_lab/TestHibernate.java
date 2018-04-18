package com.pavelzzzzz.data_base_lab;

import com.pavelzzzzz.data_base_lab.dto.UserEntity;
import java.util.Iterator;
import org.hibernate.Session;

public class TestHibernate {
	
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
       
		//Add new Employee object
		UserEntity emp = new UserEntity();
		emp.setEmail("demo-user2@mail.com");
		emp.setFirstName("demo2");
		emp.setLastName("user2");
		
		session.save(emp);
		
		session.getTransaction().commit();
		HibernateUtil.shutdown();

		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Iterator iterator = session.createCriteria(UserEntity.class).list().iterator();
		while (iterator.hasNext()) {
			UserEntity user = (UserEntity) iterator.next();
			System.out.println(user.getUserId());
			System.out.println(user.getEmail());
			System.out.println(user.getFirstName());
			System.out.println(user.getLastName());
		}

			session.getTransaction().commit();
			HibernateUtil.shutdown();
	}

}

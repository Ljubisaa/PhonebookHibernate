package org.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.dto.User;
import org.hibernate.query.Query;

public class Login {

	public static boolean loginUser(User user){
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Query query = session.createQuery("FROM User where userName = :userName AND password = :password");
		query.setParameter("userName", user.getUserName());
		query.setParameter("password", user.getPassword());
		
		if((User) query.uniqueResult() != null){
			return true;
		}
		else{
			return false;
		}
	}
	
}

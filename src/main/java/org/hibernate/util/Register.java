package org.hibernate.util;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.dto.User;

public class Register {

	public static void registerUser() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		Scanner input = new Scanner(System.in);

		System.out.println("Enter fullname: ");
		String fullName = input.nextLine();
		System.out.println("Enter username:");
		String userName = input.nextLine();
		System.out.println("Enter password: ");
		String password = input.nextLine();

		User user = new User();
		user.setFullName(fullName);
		user.setUserName(userName);
		user.setPassword(password);

		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
		input.close();
	}
}

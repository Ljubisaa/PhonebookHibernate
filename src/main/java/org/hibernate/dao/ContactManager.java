package org.hibernate.dao;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.dto.Contact;
import org.hibernate.dto.User;
import org.hibernate.query.Query;

public class ContactManager implements ContactInterface {

	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session = sessionFactory.openSession();

	@Override
	public void addNewContact(User user) {
		Scanner input = new Scanner(System.in);

		System.out.println("Enter contact name:");
		String name = input.nextLine();
		System.out.println("Enter phone number:");
		String number = input.nextLine();

		Contact contact = new Contact();
		contact.setContactName(name);
		contact.setPhoneNumber(number);
		contact.setUserName(user.getUserName());

		session.beginTransaction();
		session.save(contact);
		session.getTransaction().commit();
		session.close();
		input.close();

	}

	@Override
	public void deleteContact(String phoneNumber, User user) {
		session.beginTransaction();

		Query query = session.createQuery("delete from Contact where phoneNumber= :phoneNumber AND userName = :userName");
		query.setParameter("userName", user.getUserName());
		query.setParameter("phoneNumber", phoneNumber);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void editContact(Contact contact, User user) {
		Scanner input = new Scanner(System.in);
		session.beginTransaction();

		System.out.println("Enter new contact name:");
		String contactName = input.nextLine();

		System.out.println("Enter new phone number:");
		String phoneNumber = input.nextLine();

		Query query = session.createQuery(
				"UPDATE Contact SET contactName = :contactName, phoneNumber = :phoneNumber WHERE phoneNumber = :oldPhoneNumber AND userName = :userName");

		query.setParameter("contactName", contactName);
		query.setParameter("phoneNumber", phoneNumber);
		query.setParameter("oldPhoneNumber", contact.getPhoneNumber());
		query.setParameter("userName", user.getUserName());
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		input.close();
	}

	@Override
	public void showAllContacts(User user) {
		session.beginTransaction();

		Query query = session.createQuery("FROM Contact WHERE userName = :userName");
		query.setParameter("userName", user.getUserName());
		List<Contact> contacts = (List<Contact>) query.list();
		session.getTransaction().commit();
		session.close();

		for (Contact con : contacts) {
			System.out.println(con.getContactName() + " " + con.getPhoneNumber());
		}

	}

	@Override
	public void searchByName(User user) {
		Scanner input = new Scanner(System.in);
		session.beginTransaction();

		System.out.println("Enter the name you want: ");
		String contactName = input.nextLine();
		Query query = session.createQuery("FROM Contact WHERE contactName = :contactName AND userName = :userName");
		query.setParameter("contactName", contactName);
		query.setParameter("userName", user.getUserName());
		List<Contact> contacts = (List<Contact>) query.list();

		for (Contact con : contacts) {
			System.out.println(con.getContactName() + " " + con.getPhoneNumber());
		}
		
		input.close();

	}

}

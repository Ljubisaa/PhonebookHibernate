package org.hibernate.dao;

import org.hibernate.dto.Contact;
import org.hibernate.dto.User;

public interface ContactInterface {

	// method to add new contact
		void addNewContact(User user);

		// method to delete contact
		void deleteContact(String phoneNumber, User user);

		// method to edit contact
		void editContact(Contact contact, User user);

		// method to show all contacts
		void showAllContacts(User user);

		// method to search contact by name
		void searchByName(User user);
}



import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;
import java.util.UUID;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Deque;
import java.util.HashSet;

public class Contactlist {
	private final ArrayList<Contact> contacts = new ArrayList<>();

	Chat obj = new Chat();
	
	
	
//add/remove contacts
public void addContact(Contact contact) {
	contacts.add(contact);
}

public void removeContact(Contact contact) {
	contacts.remove(contact);
}

//display contacts
public void displaycontacts() {
	for (int i = 0; i < contacts.size(); i++) {
	
	if (contacts.isEmpty()) {
		System.out.println("No contacts :(");
		return;
	}
	System.out.println("Contact list");
	for (Contact c : contacts) {
		System.out.println("                         ");
		System.out.println("ID: " + c.getConid());
		System.out.println("Name: " + c.getConname());
		System.out.println("Phone: " + c.getConnum());
		System.out.println("                         ");
	}
	}
}

//get contacts
public ArrayList<Contact> getContacts() {
	return contacts;
}

//search name
public Contact searchName(String name) {
	for (Contact c : contacts) {
		if (c.getConname().equalsIgnoreCase(name)) {
			return c;
		}
	}
	return null;
}

//Search phone num
public Contact searchNum(String Num) {
	for (Contact c : contacts) {
		if (c.getConnum().equalsIgnoreCase(Num)) {
			return c;
		}
	}
	return null;
}

//Search id
public Contact searchId(String Id) {
	for (Contact c : contacts) {
		if (c.getConid().equalsIgnoreCase(Id)) {
			return c;
		}
	}
	return null;
}
}

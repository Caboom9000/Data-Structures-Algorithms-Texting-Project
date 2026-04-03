

import java.util.ArrayList;



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

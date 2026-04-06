import java.util.ArrayList;

public class Contactlist
{
	private final ArrayList<Contact> contacts = new ArrayList<>();

	public void addContact(Contact contact)
	{
		contacts.add(contact);
	}

	public void removeContact(Contact contact)
	{
		contacts.remove(contact);
	}

	public ArrayList<Contact> getContacts()
	{
		return contacts;
	}
}

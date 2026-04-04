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

	public void displaycontacts()
	{
		if (contacts.isEmpty())
		{
			System.out.println("No contacts :(");
			return;
		}
		System.out.println("Contact list");
		for (Contact c : contacts)
		{
			System.out.println("                         ");
			System.out.println("ID: " + c.getConid());
			System.out.println("Name: " + c.getConname());
			System.out.println("Phone: " + c.getConnum());
			System.out.println("                         ");
		}
	}

	public ArrayList<Contact> getContacts()
	{
		return contacts;
	}

	/* Searching */
	public Contact searchName(String name)
	{
		for (Contact c : contacts)
		{
			if (c.getConname().equalsIgnoreCase(name))
			{
				return c;
			}
		}
		return null;
	}
	public Contact searchNum(String Num)
	{
		for (Contact c : contacts)
		{
			if (c.getConnum().equalsIgnoreCase(Num))
			{
				return c;
			}
		}
		return null;
	}
	public Contact searchId(String Id)
	{
		for (Contact c : contacts)
		{
			if (c.getConid().equalsIgnoreCase(Id))
			{
				return c;
			}
		}
		return null;
	}
}

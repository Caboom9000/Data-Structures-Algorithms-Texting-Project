import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("CallToPrintStackTrace")
public final class FileSystem
{
	private final Contactlist contactlist; // contact list contains all the information for contacts as well as the chats
	private final Profile profile;

	public FileSystem()
	{
		this.contactlist = new Contactlist();
		this.profile = new Profile("User", "0", "000000000000");

		loadProfile();
		loadContacts();
	}

	// checks if file/folder exists. if not, creates it
	public void pathCheck(String path)
	{
		File file = new File(path);
		File parentDir = file.getParentFile();
		
		if (parentDir != null && !parentDir.exists())
			parentDir.mkdirs();

		if (!file.exists())
		{
			try
			{
				file.createNewFile();
			}
			catch (IOException error)
			{
				error.printStackTrace();
			}
		}
	}

	/*		Contact Manager		*/
	public void addContact(Contact contact)
	{
		contactlist.addContact(contact);
		saveContactMetaData();
		saveChat(contact);
	}
	public void editContact(Contact contact, String name, String num)
	{
		contact.setConname(name);
		contact.setConnum(num);
		saveContactMetaData();
	}
	public void removeContact(Contact contact)
	{
		contactlist.removeContact(contact);
		saveContactMetaData();
		// deleteChat(Contact contact)
	}
	public ArrayList<Contact> getContacts()
	{
		return contactlist.getContacts();
	}
	
	public int getNewId() // Generates new id
	{
		int newId = 0;
		if (contactlist.getContacts().isEmpty())
			return newId;

		// new id will be the current highest id + 1. this is to prevent two different contacts using the same id
		for (int i=0; i<contactlist.getContacts().size(); i++)
		{
			int curConId = contactlist.getContacts().get(i).getConid();
			if (curConId > newId)
				newId = curConId;
		}
		return newId+1;
	}
	/*--------------------------*/


	/*		Profile Manager		*/
	public void setName(String newName)
	{
		profile.setName(newName);
		saveProfile();
	}
	public void setNum(String newNum)
	{
		profile.setNum(newNum);
		saveProfile();
	}

	public String getUsername()
	{
		return profile.getUsername();
	}
	public String getPhonenum()
	{
		return profile.getPhonenum();
	}
	/*--------------------------*/


	/*			Saving			*/
	// Save contact names + meta data. Chats are not saved in this method
	public void saveContactMetaData()
	{
		pathCheck("cache/contacts.csv");

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("cache/contacts.csv")))
		{
			for (Contact contact : contactlist.getContacts())
				bw.write(String.format("%s,%s,%s\n", contact.getConid(), contact.getConname(), contact.getConnum()));
		}
		catch (IOException error)
		{
			error.printStackTrace();
		}
	}

	// Save the FULL contact (contact info + contact chat)
	public void saveContact(Contact contact)
	{
		pathCheck("cache/contacts.csv");
		pathCheck(String.format("cache/chats/%d.csv", contact.getConid()));
	}

	// Save ONLY the chat for a specific contact
	public void saveChat(Contact contact)
	{
		pathCheck(String.format("cache/chats/%d.csv", contact.getConid()));
		// if no chat file exists, make one
	}

	public void saveProfile()
	{
		pathCheck("cache/profile.csv");
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("cache/profile.csv")))
		{
			bw.write(String.format("%s,%s", profile.getUsername(), profile.getPhonenum()));
		}
		catch (IOException error)
		{
			error.printStackTrace();
		}
	}
	/*--------------------------*/


	/*			Loading			*/
	public void loadProfile()
	{
		pathCheck("cache/profile.csv");

		try (BufferedReader br = new BufferedReader(new FileReader("cache/profile.csv")))
		{
			String line = br.readLine();

			if (line !=null)
			{
				String[] parts = line.split(",");
				
				String name = parts[0].trim(); // [TODO] VALIDATE ARRAY IS LONG ENOUGH
				String num  = parts[1].trim();

				this.profile.setName(name);
				this.profile.setNum(num);
			}
			else
			{ // if file is empty then just save default values for now
				saveProfile();
			}
		}
		catch (IOException error)
		{
			error.printStackTrace();
		}
	}
	public void loadContacts()
	{
		pathCheck("cache/contacts.csv");

		try (BufferedReader br = new BufferedReader(new FileReader("cache/contacts.csv")))
		{
			String line;

			while ((line = br.readLine()) != null)
			{
				String[] parts = line.split(","); // [TODO] VALIDATE ARRAY IS LONG ENOUGH

				int id = Integer.parseInt(parts[0].trim());
				String name = parts[1].trim();
				String num = parts[2].trim();

				Contact contact = new Contact(name, id, num);
				this.contactlist.addContact(contact);
			}
		}
		catch (IOException error)
		{
			error.printStackTrace();
		}
	}
	/*--------------------------*/
}

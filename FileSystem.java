import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Deque;

@SuppressWarnings("CallToPrintStackTrace")
public final class FileSystem
{
	private final Contactlist contactlist; // contact list contains all the information for contacts as well as the chats
	private final Profile profile;

	public FileSystem()
	{
		this.contactlist = new Contactlist();
		this.profile = new Profile();

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
		
		File chatFile = new File(String.format("cache/chats/%d.csv", contact.getConid()));
		if (chatFile.exists()) chatFile.delete();
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
		
		Deque<Msg> chat = contact.getChat().getMessages();

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(String.format("cache/chats/%s.csv", contact.getConid()))))
		{
			for (Msg msg : chat)
			{
				// saved as: owner,hh:mm:ssssss,""
				bw.write(String.format("%s,%s,\"%s\"\n", msg.getOwner(), msg.getTime(), msg.getCont()));
			}
		}
		catch (IOException error)
		{
			error.printStackTrace();
		}
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
				
				String name = parts[0].trim();
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
				String[] parts = line.split(",");

				int id = Integer.parseInt(parts[0].trim());
				String name = parts[1].trim();
				String num = parts[2].trim();

				Contact contact = new Contact(name, id, num);
				contact = this.loadChat(contact);
				this.contactlist.addContact(contact);
			}
		}
		catch (IOException error)
		{
			error.printStackTrace();
		}
	}
	public Contact loadChat(Contact contact)
	{
		pathCheck(String.format("cache/chats/%d.csv", contact.getConid()));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSS");

		try (BufferedReader br = new BufferedReader(new FileReader(String.format("cache/chats/%d.csv", contact.getConid()))))
		{
			String line;

			while ((line = br.readLine()) != null)
			{
				// chat messages are saved ass owner, time, content
				String[] parts = line.split(",", 3);
				String owner = parts[0];
				LocalTime time = LocalTime.parse(parts[1], formatter);
				String cont = parts[2];

				// remove the start and end quotations from content
				cont = cont.substring(1, cont.length()-1);

				contact.addMessage(owner, time, cont);
			}
		}
		catch (IOException error)
		{
			error.printStackTrace();
		}

		return contact;
	}
	/*--------------------------*/
}

import java.util.ArrayList;

public class FileSystem
{
	private Contactlist contactlist; // contact list contains all the information for contacts as well as the chats
	private Profile profile;

	public FileSystem()
	{
		this.contactlist = new Contactlist();
		this.profile = new Profile("User", "0", "000000000000");
	}


	/*		Contact Manager		*/
	public void addContact(Contact contact)
	{
		contactlist.addContact(contact);
		saveContactMetaData();
		saveChat(contact);
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
	/*--------------------------*/


	/*		Profile Manager		*/
	public void setName(String newName)
	{
		profile.setName(newName);
	}
	public void setId(String newId)
	{
		profile.setId(newId);
	}
	public void setNum(String newNum)
	{
		profile.setName(newNum);
	}

	public String getUsername()
	{
		return profile.getUsername();
	}
	public String getId()
	{
		return profile.getId();
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
		// cache/contacts.csv
	}

	// Save the FULL contact (contact info + contact chat)
	public void saveContact()
	{
		// cache/contacts.csv  +  cache/chats/*.csv
	}

	// Save ONLY the chat for a specific contact
	public void saveChat(Contact contact)
	{
		// cache/chats/*.csv
		// if no chat file exists, make one
	}

	public void saveProfile()
	{
		// cache/profile.csv
	}
	/*--------------------------*/
}

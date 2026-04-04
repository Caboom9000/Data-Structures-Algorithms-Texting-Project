
public class FileSystem
{
	private Contactlist contactlist; // contact list contains all the information for contacts as well as the chats
	private Profile profile;

	public FileSystem()
	{
		this.contactlist = new Contactlist();
		this.profile = new Profile("User", "0", "0000000000");
	}

	// Save the FULL contact list (contact info + contact chat)
	public void saveContactlist()
	{

	}

	// Save ONLY the chat for a specific contact
	public void saveChat(Contact contact)
	{

	}

	public void saveProfile()
	{

	}
}

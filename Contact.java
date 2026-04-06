
import java.time.LocalTime;

public class Contact
{
	private String Conname;
	private final int Conid;
	private String Connum;
	private final Chat chat;
	

	public Contact(String Conname, int Conid, String Connum)
	{
		this.Conname = Conname;
		this.Conid = Conid;
		this.Connum = Connum;
		this.chat = new Chat();
	}

	//add message (used on program startup)
	public void addMessage(String owner, LocalTime time, String cont)
	{
		this.chat.sendMessage(owner, time, cont);
	}
	
	//set methods
	public void setConname(String conname)
	{
		this.Conname = conname;
	}
	
	public void setConnum(String connum)
	{
		this.Connum = connum;
	}
	
	//get methods
	public String getConname()
	{
		return Conname;
	}
	
	public int getConid()
	{
		return Conid;
	}
	
	public String getConnum()
	{
		return Connum;
	}
	
	public Chat getChat()
	{
		return chat;
	}
}

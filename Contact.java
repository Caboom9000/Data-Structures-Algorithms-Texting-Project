import java.util.Deque;
import java.util.LinkedList;

public class Contact {
	private String Conname;
	private String Conid;
	private String Connum;
	private Chat chat;
	
	//constructor
public Contact(String Conname, String Conid, String Connum) {
	this.Conname = Conname;
	this.Conid = Conid;
	this.Connum = Connum;
	this.chat = new Chat();
}

//set methods
public void setConname(String conname) {
	this.Conname = conname;
}

public void setConid(String conid) {
	this.Conid = conid;
}

public void setConnum(String connum) {
	this.Connum = connum;
}

//get methods
public String getConname(){
	return Conname;
}

public String getConid(){
	return Conid;
}

public String getConnum(){
	return Connum;
}

public Chat getChat() {
	return chat;
}

//display contact
public void displaycontact(){
		System.out.println("                         ");
		System.out.println("ID: " + Conid);
		System.out.println("Name: " + Conname);
		System.out.println("Phone: " + Connum);
		System.out.println("                         ");
	}
}


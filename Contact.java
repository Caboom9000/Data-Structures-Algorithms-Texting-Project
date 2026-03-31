package messageappgp;

public class Contact {
	private String Conname;
	private String Conid;
	private String Connum;

public Contact(String Conname, String Conid, String Connum) {
	this.Conname = Conname;
	this.Conid = Conid;
	this.Connum = Connum;
}



public String getConname(){
	return Conname;
}

public String getConid(){
	return Conid;
}

public String getConnum(){
	return Connum;
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


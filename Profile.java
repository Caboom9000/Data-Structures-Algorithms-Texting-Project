

public class Profile {
	private String Username;
	private String Phonenum;

public Profile(String Username, String Id, String Phonenum) {
	this.Username = Username;
	this.Phonenum = Phonenum;
}

//set methods
public void setName(String name) {
	this.Username = name;
}

public void setNum(String num) {
	this.Phonenum = num;
}

//get methods
public String getUsername(){
	return Username;
}

public String getPhonenum(){
	return Phonenum;
}

public void displayprofile(){
	System.out.println("                         ");
	System.out.println("Name: " + Username);
	System.out.println("Phone: " + Phonenum);
	System.out.println("                         ");
}
}




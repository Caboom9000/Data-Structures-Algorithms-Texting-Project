

public class Profile {
	private String Username;
	private String Id;
	private String Phonenum;

	//constructor
public Profile(String Username, String Id, String Phonenum) {
	this.Username = Username;
	this.Id = Id;
	this.Phonenum = Phonenum;
}

//set methods
public void setName(String name) {
    this.Username = name;
}
public void setId(String id) {
    this.Id = id;
}

public void setNum(String num) {
    this.Phonenum = num;
}

//get methods
public String getUsername(){
	return Username;
}

public String getId(){
	return Id;
}

public String getPhonenum(){
	return Phonenum;
}

//display profile
public void displayprofile(){
	System.out.println("                         ");
	System.out.println("ID: " + Id);
	System.out.println("Name: " + Username);
	System.out.println("Phone: " + Phonenum);
	System.out.println("                         ");
}
}




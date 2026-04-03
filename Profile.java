

public class Profile {
	private String Username;
	private String Id;
	private String Phonenum;

public Profile(String Username, String Id, String Phonenum) {
	this.Username = Username;
	this.Id = Id;
	this.Phonenum = Phonenum;
}

Contactlist obj = new Contactlist(); 

public String getUsername(){
	return Username;
}

public String getId(){
	return Id;
}

public String getPhonenum(){
	return Phonenum;
}
}



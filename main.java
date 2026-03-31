package messageappgp;

import java.util.Scanner;


public class main {	
public static void main (String[] args){
	Contactlist list = new Contactlist();
	Scanner scanner = new Scanner(System.in);

	 Menu menu = new Menu(scanner);
	 
while(true) {
	int choice = menu.showMenu();
	  if(choice == 1) {
		  System.out.println("enter contact name");
			String Conname = scanner.nextLine();
			
			System.out.println("enter contact id");
			String Conid = scanner.nextLine();
			
			System.out.println("enter contact number");
			String Connum = scanner.nextLine();
			
			Contact newContact = new Contact(Conname, Conid, Connum);
			list.addContact(newContact);
	  } else if 
	  
	  (choice == 2) {
		  System.out.println("Enter the name, phone number or id of the contact you'd like to remove");
		  String input = scanner.nextLine();
		  
		  Contact found = list.searchName(input);
		  if(found == null) {
			  found = list.searchNum(input);
		  }
		  if(found == null) {
			  found = list.searchId(input);
		  }
		  
		  if(found != null) {
			  list.removeContact(found);
			  System.out.println("Contact removed successfully");
		  }
		  else { 
			  System.out.println("Contact not found :(");
		  }
		  } else if 
	  	(choice == 3) {
			  list.displaycontacts();
		  } else if
	  
	  	(choice == 4) {
			  System.out.println("Enter the name, phone number or id of the contact you'd like to find");
			  String input = scanner.nextLine();
			  
			  Contact found = list.searchName(input);
			  if(found == null) {
				  found = list.searchNum(input);
			  }
			  if(found == null) {
				  found = list.searchId(input);
			  }
			  
			  if(found != null) {
				  found.displaycontact();
			  }
			  else { 
				  System.out.println("Contact not found :(");
			  }
	  } else if 
	  
	  (choice == 5) {
		  System.out.println("Goodbye");
			scanner.close();
			return;  
	  	}
		}	
	  }
/*while (true){
	System.out.println("Would you like to add a new contact (y/n)");

	String yn = scanner.nextLine().trim();
	
	//if statement so loop can be exited
	if(yn.equalsIgnoreCase("n")) {
		break;
	}
	
	System.out.println("enter contact name");
	String Conname = scanner.nextLine();
	
	System.out.println("enter contact id");
	String Conid = scanner.nextLine();
	
	System.out.println("enter contact number");
	String Connum = scanner.nextLine();
	
	Contact newContact = new Contact(Conname, Conid, Connum);
	list.addContact(newContact);
}
list.displaycontacts();*/
}


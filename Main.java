

public class main {
    public static void main(String[] args) {
Contactlist contactlist = new Contactlist();

contactlist.addContact(new Contact("Frank", "1", "1111111111"));
contactlist.addContact(new Contact("Bob", "2", "222222222222"));
contactlist.addContact(new Contact("Fred", "3", "33333333333"));
        
new LandingPageGUI(contactlist);
    }
}
package DataMessagingAppLanding;

public class Main {
    public static void main(String[] args) {

        UserProfile user = new UserProfile("user1", "Name", "000000000");
        ChatManager chatManager = new ChatManager();
        ContactManager contactManager = new ContactManager();

        new LandingPageGUI(user, chatManager, contactManager);
    }
}
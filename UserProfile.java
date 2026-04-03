package DataMessagingAppLanding;

public class UserProfile {

    private String id;
    private String name;
    private String phone;

    public UserProfile(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void openProfile() {
        System.out.println("Profile editor:");
    }
}

package DataMessagingAppLanding;

public class Chat {

    private int id;
    private String senderName;
    private String readStatus;
    private String time;

    public Chat(int id, String senderName, String readStatus, String time) {
        this.id = id;
        this.senderName = senderName;
        this.readStatus = readStatus;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getReadStatus() {
        return readStatus;
    }

    public String getTime() {
        return time;
    }
}
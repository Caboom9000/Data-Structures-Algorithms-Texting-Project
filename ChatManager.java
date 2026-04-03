package DataMessagingAppLanding;

import java.util.LinkedList;
import java.util.Queue;

public class ChatManager {

    private Queue<Chat> chats = new LinkedList<>();

    public ChatManager() {
        loadSampleChats();
    }

    public Queue<Chat> getChatsQueue() {
        return chats;
    }

    public void openChat(int chatId) {
        System.out.println("Opening chat: " + chatId);
    }

    public void createChat() {
        System.out.println("New chat");
    }

    private void loadSampleChats() {
        chats.add(new Chat(1, "guy", "Unread", "10:45"));
        chats.add(new Chat(2, "guy2", "Read", "Yesterday"));
        chats.add(new Chat(3, "guy3", "Unread", "Monday"));
    }
}
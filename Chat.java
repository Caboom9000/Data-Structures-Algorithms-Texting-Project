<<<<<<< HEAD


=======
>>>>>>> 3cab36adc3742454735b5026f733268dcba7aa75
import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Deque;
import java.util.LinkedList;

public class Chat extends JPanel {

    private JButton send;
    private JTextField chatbox;
    private JTextArea chatWindow;

    private Deque<Msg> queue;

    public Chat() {

        queue = new LinkedList<>();

        // Use BorderLayout for clean structure
        setLayout(new BorderLayout(10, 10));

        // ===== CHAT DISPLAY AREA =====
        chatWindow = new JTextArea();
        chatWindow.setEditable(false);
        chatWindow.setLineWrap(true);
        chatWindow.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(chatWindow);
        add(scrollPane, BorderLayout.CENTER);

        // ===== INPUT AREA (BOTTOM) =====
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));

        chatbox = new JTextField();
        send = new JButton("Send");

        inputPanel.add(chatbox, BorderLayout.CENTER);
        inputPanel.add(send, BorderLayout.EAST);

        add(inputPanel, BorderLayout.SOUTH);

        // ===== ACTION LISTENER =====
        send.addActionListener(e -> sendMessage());

        // Allow pressing ENTER to send
        chatbox.addActionListener(e -> sendMessage());
    }

    private void sendMessage() {
        String message = chatbox.getText().trim();

        if (!message.isEmpty()) {

            Msg newMessage = new Msg();
            newMessage.setCont(message);
            newMessage.setTime(LocalTime.now());

            queue.add(newMessage);

            // Format time
            String time = newMessage.getTime()
                    .format(DateTimeFormatter.ofPattern("HH:mm"));

            // Display message
            chatWindow.append("You (" + time + "): " + newMessage.getCont() + "\n");

            chatbox.setText("");
        }
    }
    
    public void deleteLastMessage() {
        if (!queue.isEmpty()) {
            queue.removeLast();
            refreshChatWindow();
        }
    }

    public void editLastMessage(String newContent) {
        if (!queue.isEmpty()) {
            Msg last = queue.removeLast();
            last.setCont(newContent);
            queue.addLast(last);
            refreshChatWindow();
        }
    }

    // Refresh the chatWindow display
    private void refreshChatWindow() {
        chatWindow.setText("");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        for (Msg msg : queue) {
            chatWindow.append("You (" + msg.getTime().format(formatter) + "): " + msg.getCont() + "\n");
        }
    }
}

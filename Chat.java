
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Deque;
import java.util.LinkedList;
import javax.swing.*;

public class Chat extends JPanel {

	private final JButton send;
	private final JTextField chatbox;
	private final JTextArea chatWindow;

	private final JTextField searchField;
	private final JButton searchButton;

	private final Deque<Msg> queue;

	private Runnable onChatUpdate;
	public void setOnChatUpdate(Runnable listener)
	{
		this.onChatUpdate = listener;
	}

	//constructor
	public Chat()
	{
		// Use BorderLayout for clean structure
		setLayout(new BorderLayout(10, 10));

		// search stuff
		JPanel searchPanel = new JPanel(new BorderLayout(5,5));
		searchField = new JTextField();
		searchButton = new JButton("Search");

		searchPanel.add(searchField, BorderLayout.CENTER);
		searchPanel.add(searchButton, BorderLayout.EAST);

		add(searchPanel, BorderLayout.NORTH);

		searchButton.addActionListener(e->searchMessages());
		searchField.addActionListener(e->searchMessages());

		queue = new LinkedList<>();

		// chat display area
		chatWindow = new JTextArea();
		chatWindow.setEditable(false);
		chatWindow.setLineWrap(true);
		chatWindow.setWrapStyleWord(true);

		JScrollPane scrollPane = new JScrollPane(chatWindow);
		add(scrollPane, BorderLayout.CENTER);

		// input area (bottom)
		JPanel inputPanel = new JPanel(new BorderLayout(5, 5));

		chatbox = new JTextField();
		send = new JButton("Send");

		inputPanel.add(chatbox, BorderLayout.CENTER);
		inputPanel.add(send, BorderLayout.EAST);

		add(inputPanel, BorderLayout.SOUTH);

		// action listeners
		send.addActionListener(e -> sendMessage());

		// Allow pressing ENTER to send
		chatbox.addActionListener(e -> sendMessage());
	}

	private void searchMessages() {
		String query = searchField.getText().trim().toLowerCase();
	
		chatWindow.setText(""); // clear
	
		if (query.isEmpty())
		{ // search being empty means user is done searching
			refreshChatWindow();
			return;
		}
	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

		for (Msg msg : queue)
		{
			if (msg.getCont().toLowerCase().contains(query))
			{
				chatWindow.append("You (" + msg.getTime().format(formatter) + "): " + msg.getCont() + "\n");
			}
		}
	}

	private void sendMessage()
	{
		String message = chatbox.getText().trim();

		if (!message.isEmpty()) {

			Msg newMessage = new Msg();
			newMessage.setOwner("You");
			newMessage.setTime(LocalTime.now());
			newMessage.setCont(message);

			queue.add(newMessage);

			// Format time
			String time = newMessage.getTime()
					.format(DateTimeFormatter.ofPattern("HH:mm"));

			// Display message
			chatWindow.append(
				String.format("%s (%s): %s\n",
				newMessage.getOwner(),
				time,
				newMessage.getCont()));

			chatbox.setText("");

			// broadcast an event that will be picked up by the FileSystem in LandingPageGUI to save the chat
			if (onChatUpdate != null)
				onChatUpdate.run();
		} 
	}
	public void sendMessage(String _owner, LocalTime _time, String _cont)
	{
		Msg newMessage = new Msg();
		newMessage.setOwner(_owner);
		newMessage.setTime(_time);
		newMessage.setCont(_cont);

		queue.add(newMessage);

		// Format time
		String time = newMessage.getTime()
				.format(DateTimeFormatter.ofPattern("HH:mm"));

		// Display message
		chatWindow.append(
			String.format("%s (%s): %s\n",
			newMessage.getOwner(),
			time,
			newMessage.getCont()));

		// broadcast an event that will be picked up by the FileSystem in LandingPageGUI to save the chat
		if (onChatUpdate != null)
			onChatUpdate.run();
	}
	
	//get messages
	public Deque<Msg> getMessages(){
			return queue;
			}
	// Returns the content of the last message, or null if no messages
	public String getLastMessage() {
		if (!queue.isEmpty()) {
			return queue.getLast().getCont();
		}
		return null;
	}
	//delete
	public void deleteLastMessage()
	{
		if (!queue.isEmpty())
		{
			queue.removeLast();
			refreshChatWindow();

			if (onChatUpdate != null)
				onChatUpdate.run();
		}
	}
	//edit
	public void editLastMessage(String newContent)
	{
		if (!queue.isEmpty())
		{
			Msg last = queue.removeLast();
			last.setCont(newContent);
			queue.addLast(last);
			refreshChatWindow();

			if (onChatUpdate != null)
				onChatUpdate.run();
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

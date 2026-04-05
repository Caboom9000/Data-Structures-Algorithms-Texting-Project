
import java.awt.*;     // Provides GUI components like JFrame, JButton, JList
import javax.swing.*;        // Provides layout managers and styling tools

public class LandingPageGUI extends JFrame {
private DefaultListModel<String> contactListModel;
private JList<String> contactList;
	private final FileSystem fileSys;
   
	private JPanel centerPanel;
	private JLabel contactName;

	// profile
	private JLabel nameLabel;
	private JLabel phoneLabel;

	
	public LandingPageGUI(FileSystem _fileSys)
	{
		this.fileSys = _fileSys;

		// Basic window settings
		setTitle("chirp!");
		setSize(950, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// BorderLayout splits screen into top, left, centre, right, bottom
		setLayout(new BorderLayout(10, 10));
	}

	// Builds the entire UI layout
	public void buildUI()
	{
		// Top section/App title
		JLabel title = new JLabel("chirp!", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 22)); // Styling
		add(title, BorderLayout.NORTH);

		// Left panel/Contacts
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());

		// Adds a border with title "Contact list"
		leftPanel.setBorder(BorderFactory.createTitledBorder("Contact list"));

		// Panel for buttons at top of contact section
		JPanel contactButtons = new JPanel(new GridLayout(1, 3));

		JButton addContact = new JButton("Add contact");
		JButton removeContact = new JButton("Remove");
		JButton editContact = new JButton("Edit contact");
		
		//add contact 
		addContact.addActionListener(e -> {
			String[] contactInfo = showInfoDialog("Add Contact", "", "");
			if (contactInfo == null) return;

			String name = contactInfo[0];
			String num  = contactInfo[1];

			//create new contact object add to list and refresh
			Contact newContact = new Contact(name, fileSys.getNewId(), num);
			fileSys.addContact(newContact);
			refreshContactList();
	   	});
		
		//remove contact
		removeContact.addActionListener(e -> {
			//get selected index from jlist
			int index = contactList.getSelectedIndex();
			

			if (index >= 0) {
				int confirm = JOptionPane.showConfirmDialog(
					this,
					"Are you sure?",
					"Confirm deletion",
					JOptionPane.YES_NO_OPTION
				);
				if (confirm == JOptionPane.NO_OPTION)
					return;
				//gets contact from list + remove it
				Contact selectedContact;
							selectedContact = fileSys.getContacts().get(index);
				fileSys.removeContact(selectedContact);
				refreshContactList();
				
				//reset chat panel
				centerPanel.removeAll();
				contactName.setText("Select a contact");
				centerPanel.add(contactName, BorderLayout.NORTH);
				centerPanel.revalidate();
				centerPanel.repaint();
			} else {
				//no selection made
				JOptionPane.showMessageDialog(this, "Please select a contact to remove");
				
			}
		});
		
		//edit contact
		editContact.addActionListener(e -> {
			int index = contactList.getSelectedIndex();
			
			if(index >= 0)
			{
				//get selected contact [TODO]
				Contact selectedContact = fileSys.getContacts().get(index);
				
				String [] newInfo = showInfoDialog("Edit contact", selectedContact.getConname(), selectedContact.getConnum());

				String name = newInfo[0];
				String num = newInfo[1];

				fileSys.editContact(selectedContact, name, num);

				refreshContactList();
				contactList.setSelectedIndex(index);
				contactName.setText(selectedContact.getConname());
			}
			else 
			{
				//no selected contact
				JOptionPane.showMessageDialog(this, "Please choose a contact to edit");
			}
		});
		//add buttons to panel
		contactButtons.add(addContact);
		contactButtons.add(removeContact);
		contactButtons.add(editContact);

		// Place buttons at top of left panel
		leftPanel.add(contactButtons, BorderLayout.NORTH);

		// List model holds contact data
		contactListModel = new DefaultListModel<>();
	   for (Contact c : fileSys.getContacts()) {
		   contactListModel.addElement(c.getConname());
	   }

	   contactList = new JList<>(contactListModel);
	   
		// Scroll pane allows scrolling through contacts
		leftPanel.add(new JScrollPane(contactList), BorderLayout.CENTER);

		// Add left panel to main window
		add(leftPanel, BorderLayout.WEST);

		// Main panel/Chat area
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());

		// Label showing selected contact name
		contactName = new JLabel("Contact name");
		contactName.setFont(new Font("Arial", Font.BOLD, 16));

		centerPanel.add(contactName, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		// Add centre panel to main window
		/*add(centerPanel, BorderLayout.CENTER);*/
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(2, 1, 10, 10));
		rightPanel.setBorder(BorderFactory.createTitledBorder("Profile"));
		
		JPanel profilePanel = new JPanel(); //JButton showProfile = new JButton("Show profile");
		JButton editProfile = new JButton("Edit profile");
		
		/* Profile Panel */
		profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
		profilePanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5)); // padding

		this.nameLabel = new JLabel("Name:" + fileSys.getUsername());
		this.phoneLabel = new JLabel("Phone No.: " + fileSys.getPhonenum());

		this.nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.phoneLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

		profilePanel.add(nameLabel);
		profilePanel.add(phoneLabel);
		/*				 */

		rightPanel.add(profilePanel);
		rightPanel.add(editProfile);
		
		add(rightPanel, BorderLayout.EAST);
		
		//contact selection
		contactList.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int index = contactList.getSelectedIndex();
				
				if (index >= 0) {
					//clear previous ui
					centerPanel.removeAll();

					Contact selectedContact = fileSys.getContacts().get(index);
					JPanel topBar = new JPanel();
					topBar.setLayout(new BoxLayout(topBar, BoxLayout.Y_AXIS));
					
					contactName.setText(selectedContact.getConname());
					contactName.setAlignmentX(Component.LEFT_ALIGNMENT);
					topBar.add(contactName);

					JPanel chatButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
					JButton deleteLast = new JButton("Delete last message");
					JButton editLast = new JButton("Edit last message");
					chatButtons.setAlignmentX(Component.LEFT_ALIGNMENT);

					deleteLast.setMargin(new Insets(2, 8, 2, 8));
					editLast.setMargin(new Insets(2, 8, 2, 8));

					chatButtons.add(deleteLast);
					chatButtons.add(editLast);

					Chat chat = selectedContact.getChat();

					chat.setOnMessageSent(msg ->
					{
						fileSys.saveChat(selectedContact);
					});

					deleteLast.addActionListener(ev ->
					{
						if (chat.getLastMessage() == null) return;

						int confirm = JOptionPane.showConfirmDialog(
							this,
							"Are you sure?",
							"Confirm deletion",
							JOptionPane.YES_NO_OPTION
						);
						
						if (confirm == JOptionPane.YES_OPTION)
							chat.deleteLastMessage();
					});
					editLast.addActionListener(ev -> 
					{
						String lastMsg = chat.getLastMessage();
						if (lastMsg == null) return;

						String newMsg = JOptionPane.showInputDialog(this, "Edit last message:", lastMsg);
						if (newMsg != null && !newMsg.trim().isEmpty())
						{
							chat.editLastMessage(newMsg.trim());
						}
					});

					topBar.add(chatButtons);

					// 4. Add top bar to center panel
					centerPanel.add(topBar, BorderLayout.NORTH);

					// 5. Add chat panel
					centerPanel.add(selectedContact.getChat(), BorderLayout.CENTER);

					centerPanel.revalidate();
					centerPanel.repaint();
				}
			}
		});
		//show profile // REDUNDANT to be deleted ?
		/*showProfile.addActionListener(e ->
			JOptionPane.showMessageDialog(this, "Name: " + profile.getUsername() + "\nID: " + profile.getId() + "\nPhone number: " + profile.getPhonenum()));*/
			
		//edit profile
		editProfile.addActionListener(e -> {
			String[] newInfo = showInfoDialog("Edit profile", fileSys.getUsername(), fileSys.getPhonenum());
			if (newInfo == null) return;

			String newName = newInfo[0];
			String newNum  = newInfo[1];

			nameLabel.setText(newName);
			phoneLabel.setText(newNum);

			fileSys.setName(newName);
			fileSys.setNum(newNum);
		});}
	 //refresh (syncs ui)   	
	private void refreshContactList() {
		contactListModel.clear();
		
		for (Contact c : fileSys.getContacts()) {
			contactListModel.addElement(c.getConname());
		}
	}

	// Reusable edit/add dialogue for contacts and user profile
	private String[] showInfoDialog(String title, String currentName, String currentNum)
	{
		JTextField nameField = new JTextField(currentName, 15);
		JTextField phoneField = new JTextField(currentNum, 12);

		JPanel editPanel = new JPanel(new GridLayout(0, 1));
		editPanel.add(new JLabel("Name:"));
		editPanel.add(nameField);
		editPanel.add(new JLabel("Phone:"));
		editPanel.add(phoneField);

		int result = JOptionPane.showConfirmDialog(
			this, 
			editPanel, 
			title, 
			JOptionPane.OK_CANCEL_OPTION, 
			JOptionPane.PLAIN_MESSAGE
		);

		if (result == JOptionPane.OK_OPTION)
		{
			String inputName = nameField.getText().trim();
			String inputNum = phoneField.getText().trim();
			if (!inputName.isEmpty() && !inputNum.isEmpty())
			{
				return new String[]{inputName, inputNum};
			}
		}
		return null; // user canceled or fields invalid
	}
}

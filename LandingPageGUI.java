
import java.awt.*;     // Provides GUI components like JFrame, JButton, JList
import javax.swing.*;        // Provides layout managers and styling tools

public class LandingPageGUI extends JFrame {
private DefaultListModel<String> contactListModel;
private JList<String> contactList;
	private final Contactlist contactlist;
	private final Profile profile;
   
	private JPanel centerPanel;
	private JLabel contactName;

	// profile
	private JLabel nameLabel;
	private JLabel idLabel;
	private JLabel phoneLabel;

	
	public LandingPageGUI(Contactlist contactlist)
	{
		this.contactlist = contactlist; 
		//default profile
		this.profile = new Profile("Your name", "0", "000000000000");
		// Basic window settings
		setTitle("Chat Application");
		setSize(950, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// BorderLayout splits screen into top, left, centre, right, bottom
		setLayout(new BorderLayout(10, 10));

		buildUI();     // Create all UI components
		setVisible(true); // Make the window visible
	}

	// Builds the entire UI layout
	private void buildUI()
	{
		// Top section/App title
		JLabel title = new JLabel("Name of App", SwingConstants.CENTER);
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
			//input dialog for name id and phone
			String name = JOptionPane.showInputDialog(this, "Enter contact name:");
			if (name == null || name.trim().isEmpty()) {
				return;
			}
			
			String id = JOptionPane.showInputDialog(this, "Enter contact id:");
			if (id == null || id.trim().isEmpty()) {
				return;
			}
			
			String num = JOptionPane.showInputDialog(this, "Enter phone number:");
			if (num == null || num.trim().isEmpty()) {
			return;
		}
		//create new contact object add to list and refresh
		Contact newContact = new Contact(name.trim(), id.trim(), num.trim());
		contactlist.addContact(newContact);
		refreshContactList();
	   	});
		
		//remove contact
		removeContact.addActionListener(e -> {
			//get selected index from jlist
			int index = contactList.getSelectedIndex();
			

			if (index >= 0) {
				//gets contact from list + remove it
				Contact selectedContact = contactlist.getContacts().get(index);
				contactlist.removeContact(selectedContact);
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
			
			if(index >= 0) {
				//get selected contact
				Contact selectedContact = contactlist.getContacts().get(index);
				
				String newName = JOptionPane.showInputDialog(this, "edit contact name:", selectedContact.getConname());
				
				if (newName == null || newName.trim().isEmpty()) {
				return;			
			}
			
			String newId = JOptionPane.showInputDialog(this, "edit contact id:", selectedContact.getConid());
			
			if (newId == null || newId.trim().isEmpty()) {
			return;		
		}
		
		String newNum = JOptionPane.showInputDialog(this, "edit contact phone number:", selectedContact.getConnum());
		
		if (newNum == null || newNum.trim().isEmpty()) {
		return;	
		}
		
		selectedContact.setConname(newName.trim());
		selectedContact.setConid(newId.trim());
		selectedContact.setConnum(newNum.trim());
		
		refreshContactList();
		contactList.setSelectedIndex(index);
		contactName.setText(selectedContact.getConname());
			} else {
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
	   for (Contact c : contactlist.getContacts()) {
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

		this.nameLabel = new JLabel("Name:" + profile.getUsername());
		this.idLabel = new JLabel("ID: " + profile.getId());
		this.phoneLabel = new JLabel("Phone No.: " + profile.getPhonenum());

		this.nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.idLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.phoneLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

		profilePanel.add(nameLabel);
		profilePanel.add(idLabel);
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

					Contact selectedContact = contactlist.getContacts().get(index);
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
			JTextField nameField = new JTextField(profile.getUsername(), 15);
			JTextField idField = new JTextField(profile.getId(), 10);
			JTextField phoneField = new JTextField(profile.getPhonenum(), 12);

			// Panel full of multiple fields
			JPanel profileChangePanel = new JPanel(new GridLayout(0, 1));
			profileChangePanel.add(new JLabel("Name:"));
			profileChangePanel.add(nameField);
			profileChangePanel.add(new JLabel("ID:"));
			profileChangePanel.add(idField);
			profileChangePanel.add(new JLabel("Phone:"));
			profileChangePanel.add(phoneField);
			
			// Confirmation dialogue
			int result = JOptionPane.showConfirmDialog
			(
				this, 
				profileChangePanel, 
				"Edit Profile", 
				JOptionPane.OK_CANCEL_OPTION, 
				JOptionPane.PLAIN_MESSAGE
			);

			if (result == JOptionPane.OK_OPTION)
			{
				String newName = nameField.getText().trim();
				String newId = idField.getText().trim();
				String newNum = phoneField.getText().trim();
			
				if (!newName.isEmpty() && !newId.isEmpty() && !newNum.isEmpty())
				{
					profile.setName(newName);
					profile.setId(newId);
					profile.setNum(newNum);

					// refresh labels for ui
					nameLabel.setText("Name: " + profile.getUsername());
					idLabel.setText("ID: " + profile.getId());
					phoneLabel.setText("Phone No.: " + profile.getPhonenum());
				
					JOptionPane.showMessageDialog(this, "Profile updated!");
				}
			}

			// update profile
			/*profile.setName(newName.trim());
			profile.setId(newId.trim());
			profile.setNum(newNum.trim());*/
			
			//JOptionPane.showMessageDialog(this, "Profile updated \nName: " + profile.getUsername() + "\nId: " + profile.getId() + "\nPhone number: " +profile.getPhonenum());
		});}
	 //refresh (syncs ui)   	
	private void refreshContactList() {
		contactListModel.clear();
		
		for (Contact c : contactlist.getContacts()) {
			contactListModel.addElement(c.getConname());
		}
	}
	}
			
			/*    //main panel/Chat area
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());

	   contactName = new JLabel("select a contact");
	   contactName.setFont(new Font("Arial", Font.BOLD, 16));
	   
	   centerPanel.add(contactName, BorderLayout.NORTH);
	   
	   add(centerPanel, BorderLayout.CENTER);

		// right panel profile
		JPanel rightPanel = new JPanel();

		// 2 rows: Change profile + Edit profile
		rightPanel.setLayout(new GridLayout(2, 1, 10, 10));

		// Border label
		rightPanel.setBorder(BorderFactory.createTitledBorder("Profile"));

		JButton changeProfile = new JButton("Change profile");
		JButton editProfile = new JButton("Edit profile");

		rightPanel.add(changeProfile);
		rightPanel.add(editProfile);

		// Add right panel to main window
		add(rightPanel, BorderLayout.EAST);

		// Buttons
		// These call methods in other classes 
		 
		contactList.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int index = contactList.getSelectedIndex();
				
				if (index >= 0) {
					Contact selectedContact = contactlist.getContacts().get(index);
					
					centerPanel.removeAll();
					
					contactName.setText(selectedContact.getConname());
					centerPanel.add(contactName, BorderLayout.NORTH);
					centerPanel.add(selectedContact.getChat(), BorderLayout.CENTER);
					
					centerPanel.revalidate();
					centerPanel.repaint();
				}
			}
		}}
		changeProfile.addActionListener(e -> user.openProfile());

		editProfile.addActionListener(e -> user.openProfile());
		
	 // chat edit/delete buttons
		JPanel chatButtonsPanel = new JPanel(new GridLayout(1, 2, 5, 5));

		JButton editChat = new JButton("Edit Last Chat");
		JButton deleteChat = new JButton("Delete Last Chat");

		chatButtonsPanel.add(editChat);
		chatButtonsPanel.add(deleteChat);

		// Add this panel below chat window
		centerPanel.add(chatButtonsPanel, BorderLayout.SOUTH);

		// buttons
		deleteChat.addActionListener(e -> chatPanel.deleteLastMessage());

		editChat.addActionListener(e -> {
			String newContent = JOptionPane.showInputDialog(this, "Edit last message:");
			if (newContent != null && !newContent.trim().isEmpty()) {
				chatPanel.editLastMessage(newContent.trim());
			}
		});

	}*/

	


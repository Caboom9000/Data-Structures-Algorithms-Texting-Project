

import javax.swing.*;     // Provides GUI components like JFrame, JButton, JList
import java.awt.*;        // Provides layout managers and styling tools
import java.util.Queue;   // Used for storing chats 

public class LandingPageGUI extends JFrame {
private DefaultListModel<String> contactListModel;
private JList<String> contactList;
    private Contactlist contactlist;
    
    private JPanel centerPanel;
    private JLabel contactName;

    // Constructor: sets up the window
    public LandingPageGUI(Contactlist contactlist) {
    	this.contactlist = contactlist; 
    	
        // Basic window settings
        setTitle("Chat Application");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // BorderLayout splits screen into top, left, centre, right, bottom
        setLayout(new BorderLayout(10, 10));

        buildUI();     // Create all UI components

        setVisible(true); // Make the window visible
    }

    // Builds the entire UI layout
    private void buildUI() {
    	
    	
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

        addContact.addActionListener(e -> {
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
        
        Contact newContact = new Contact(name.trim(), id.trim(), num.trim());
        contactlist.addContact(newContact);;
        refreshContactList();
       	});
        
        //remove contact
        removeContact.addActionListener(e -> {
    		int index = contactList.getSelectedIndex();
    		
    		if (index >= 0) {
    			Contact selectedContact = contactlist.getContacts().get(index);
    			contactlist.removeContact(selectedContact);
    			
    			refreshContactList();
    			
    			centerPanel.removeAll();
    			contactName.setText("Select a contact");
    			centerPanel.add(contactName, BorderLayout.NORTH);
    			centerPanel.revalidate();
    			centerPanel.repaint();
    		} else {
    			JOptionPane.showMessageDialog(this, "Please select a contact to remove");
    			
    		}
    	});
    	
        //edit contact
    	editContact.addActionListener(e -> {
    		int index = contactList.getSelectedIndex();
    		
    		if(index >= 0) {
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
    			JOptionPane.showMessageDialog(this, "Please choose a contact to edit");
    		}
    	});
        
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
        
        JButton changeProfile = new JButton("Change profile");
        JButton editProfile = new JButton("Edit profile");
        
        rightPanel.add(changeProfile);
        rightPanel.add(editProfile);
        
        add(rightPanel, BorderLayout.EAST);
        
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
        });
        
        changeProfile.addActionListener(e ->
        	JOptionPane.showMessageDialog(this, "Change profile clicked"));
        	
        	editProfile.addActionListener(e ->
        		JOptionPane.showMessageDialog(this, "Edit profile clicked"));
    }
    
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

        // ===== RIGHT PANEL: PROFILE OPTIONS =====
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

    }*/

    


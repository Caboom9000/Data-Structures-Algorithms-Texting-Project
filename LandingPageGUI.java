

import javax.swing.*;     // Provides GUI components like JFrame, JButton, JList
import java.awt.*;        // Provides layout managers and styling tools
import java.util.Queue;   // Used for storing chats 

public class LandingPageGUI extends JFrame {

    // References to other classes 
    UserProfile user = new UserProfile("user1", "Name", "000000000");
    ContactManager contactManager = new ContactManager();

    // Models store data for JList components
    private DefaultListModel<String> contactListModel;
    private DefaultListModel<String> chatListModel;

    // Constructor: sets up the window
    public LandingPageGUI() {

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
        JPanel contactButtons = new JPanel(new GridLayout(1, 2));

        JButton addContact = new JButton("Add contact");
        JButton removeContact = new JButton("Remove/Edit");

        contactButtons.add(addContact);
        contactButtons.add(removeContact);

        // Place buttons at top of left panel
        leftPanel.add(contactButtons, BorderLayout.NORTH);

        // List model holds contact data
        contactListModel = new DefaultListModel<>();
        JList<String> contactList = new JList<>(contactListModel);

        // Scroll pane allows scrolling through contacts
        leftPanel.add(new JScrollPane(contactList), BorderLayout.CENTER);

        // Add left panel to main window
        add(leftPanel, BorderLayout.WEST);

        // Main panel/Chat area
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        // Label showing selected contact name
        JLabel contactName = new JLabel("Contact name");
        contactName.setFont(new Font("Arial", Font.BOLD, 16));

        centerPanel.add(contactName, BorderLayout.NORTH);

        // Chat list model + list
        Chat chatPanel = new Chat();

        // Wrap in scroll if needed
        centerPanel.add(chatPanel, BorderLayout.CENTER);

        // Add centre panel to main window
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

        changeProfile.addActionListener(e -> user.openProfile());

        editProfile.addActionListener(e -> user.openProfile());

    }

    
}

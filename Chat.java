import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;
import java.util.UUID;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Deque;
import java.util.HashSet;


public class Chat {
	JFrame frame1 = new JFrame();
	
	JButton send = new JButton("send");
	
	JTextField chatbox = new JTextField();
	
	JTextArea chatWindow = new JTextArea();
	
	Deque<Msg> queue = new LinkedList<>();
	public Chat() {
	send.setBounds(350, 600, 100, 50);
	
	
	chatbox.setBounds(50,600, 300, 50);
	
	chatWindow.setBounds(50,100,400,500);
	
	
	// add action listener
		send.addActionListener(new ActionListener()
		        {  
		            public void actionPerformed(ActionEvent e)
		            {  
		            	String message = chatbox.getText();
		                if (!message.isEmpty()) {
		                	
		                	Msg newMessage = new Msg();
		                    
		                    
		                	newMessage.setCont(message);
		                	newMessage.setTime(LocalTime.now());
		                	queue.add(newMessage);
		                    chatWindow.append("You: " + queue.getLast() + "\n");
		                    chatbox.setText("");
		            }  
		            }
		        });
		        
	
	
	frame1.add(send);
	
	frame1.add(chatbox);
	
	frame1.add(chatWindow);
	
	
	frame1.setLayout(null);
	
	
	frame1.setVisible(true);
	
	frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
	
	
	
	


}
	
}
	

	


	
	


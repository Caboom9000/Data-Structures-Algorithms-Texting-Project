import java.util.Scanner;
import java.util.UUID;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Msg {
		String content;
		LocalTime timeSent;
		boolean Sent;
	
		@Override
	    public String toString() {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
	        return "[" + timeSent.format(format) + "] " + content;
	    }
	
	public Msg(){
		
		this.content = "";
		this.timeSent = null;
		this.Sent = false;
		
		
		
		
		
		
		
		
		
	}
	
	public String getCont() {
		return content;
	}
	
	public void setCont(String cont) {
		content = cont;
		
	}
	
	public LocalTime getTime() {
		return timeSent;
	}
	
	public void setTime(LocalTime time) {
		timeSent = time;
		
	}


}

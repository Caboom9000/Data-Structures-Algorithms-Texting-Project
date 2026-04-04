import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Msg {
		String content;
		LocalTime timeSent;
		//boolean Sent; // this is never read..?
	
		@Override
	    public String toString() {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
	        return "[" + timeSent.format(format) + "] " + content;
	    }
	
	public Msg(){
		
		this.content = "";
		this.timeSent = null;
		//this.Sent = false;
		
		
		
		
		
		
		
		
		
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

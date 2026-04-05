import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Msg {
		private String owner;
		private String content;
		private LocalTime timeSent;
	
		@Override
	    public String toString() {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
	        return "[" + timeSent.format(format) + "] " + content;
	    }
	
	public Msg()
	{
		this.owner = "?";
		this.content = "";
		this.timeSent = null;
	}
	

	public String getOwner()
	{
		return owner;
	}
	public String getCont()
	{
		return content;
	}
	public LocalTime getTime()
	{
		return timeSent;
	}
	
	public void setOwner(String _owner)
	{
		this.owner = _owner;
	}
	public void setCont(String cont)
	{
		content = cont;
		
	}
	public void setTime(LocalTime time)
	{
		timeSent = time;
	}
}

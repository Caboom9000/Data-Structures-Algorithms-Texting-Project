public class Main
{
	public static void main(String[] args)
	{
		FileSystem fileSys = new FileSystem();
			
		//open gui
		LandingPageGUI gui = new LandingPageGUI(fileSys);
		gui.buildUI();
		gui.setVisible(true);
	}
}

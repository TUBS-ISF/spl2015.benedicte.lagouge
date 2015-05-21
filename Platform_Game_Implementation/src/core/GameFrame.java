package core;

import javax.swing.JFrame;
// This is the main entry point..

public class GameFrame extends JFrame {

	public GameFrame(String l, String s,String ss) {	

		int w = 420;
		int h = 330;       
		setSize(w, h);
		setResizable(false);
		setTitle("Game Frame"); 
		GamePanel panel = new GamePanel(w, h,l,s,ss);
		add(panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);	
	}

	public static void main(String[] args) {
		if(args.length != 3){
			System.out.println("3 arguments : the first says if there is a lifebar or not ." +
					"the second says if the score is visible or not ."+
					"the third says if the game should be launched with sounds or not"+
					"the arguments are \"yes\" or \"no\"");
		}
		else 
			new GameFrame(args[0],args[1],args[2]);
	}

}

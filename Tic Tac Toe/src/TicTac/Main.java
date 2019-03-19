package TicTac;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame obj = new JFrame();
		Gameplay gamePlay = new Gameplay();
		obj.setBounds(10, 10, 600, 600);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.setVisible(true);
		obj.setResizable(false);
		obj.setTitle("Tic Tac Toe");
		obj.add(gamePlay);
		
	
		
	}

}

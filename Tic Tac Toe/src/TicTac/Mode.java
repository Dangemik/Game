package TicTac;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Mode {

	public int map[][];
	public int HEIGHT = 600 / 3;
	public int WIDTH = 600 / 3;
	private int X;
	private int Y;
	

	public Mode() {
		map = new int[3][3];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = 0;
			}
		}
	}

	public void draw(Graphics2D g) {

		g.setStroke(new BasicStroke(5));
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				
				if (map[i][j] == 1) {
					g.setColor(Color.yellow);
					g.drawOval(i * WIDTH + 50, j * HEIGHT + 50, 100, 100);
					
				} else if (map[i][j] == 2) {
					g.setColor(Color.red);
					g.drawLine(i * WIDTH + 50, j * HEIGHT + 50, i * WIDTH + WIDTH - 50, j * HEIGHT + HEIGHT - 50);
					g.drawLine(i * WIDTH + WIDTH - 50, j * HEIGHT + 50, i * WIDTH + 50, j * HEIGHT + HEIGHT - 50);
					

				}
			}
		}
	}

	public boolean find(int x, int y, int value) {
		
		for (int i = 0; i < 3; i++) {
			if (x >= i * WIDTH && x <= i * WIDTH + WIDTH) {
				X = i;
			}
			if (y >= i * HEIGHT && y <= i * HEIGHT + HEIGHT) {
				Y = i;
			}
		}
		
		if(map[X][Y] == 0) {
			if (value == 0) {
				map[X][Y] = 1;
			}
			else if (value == 1) {
				map[X][Y] = 2;
			}
			return true;
		}
		
		return false;
	}
	
}

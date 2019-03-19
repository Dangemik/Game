package TicTac;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Gameplay extends JPanel implements MouseListener {

	private boolean play = false;
	private int x;
	private int y;
	private Mode mode;
	private int value = 0;
	private int total = 9;
	private boolean recurrence;
	private int SIZE = 600 / 3;

	private int scoreA = 0;
	private int scoreB = 0;

	public Gameplay() {
		addMouseListener(this);
		mode = new Mode();
		play = true;
	}

	public void paint(Graphics g) {
		// Background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		// Score
		g.setColor(Color.yellow);
		g.setFont(new Font("Serif", Font.BOLD, 20));
		g.drawString("Kółko:" + scoreA, 20, 20);
		g.setColor(Color.red);
		g.drawString("Krzyżyk:" + scoreB, 420, 20);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				((Graphics2D) g).setStroke(new BasicStroke(2));
				g.setColor(Color.white);
				g.drawRect(i * SIZE, j * SIZE, SIZE, SIZE);
			}
		}

		mode.draw((Graphics2D) g);

		if ((mode.map[0][0] == 1 && mode.map[0][1] == 1 && mode.map[0][2] == 1)
				|| (mode.map[1][0] == 1 && mode.map[1][1] == 1 && mode.map[1][2] == 1)
				|| (mode.map[2][0] == 1 && mode.map[2][1] == 1 && mode.map[2][2] == 1)
				|| (mode.map[0][0] == 1 && mode.map[1][0] == 1 && mode.map[2][0] == 1)
				|| (mode.map[0][1] == 1 && mode.map[1][1] == 1 && mode.map[2][1] == 1)
				|| (mode.map[0][2] == 1 && mode.map[1][2] == 1 && mode.map[2][2] == 1)
				|| (mode.map[0][0] == 1 && mode.map[1][1] == 1 && mode.map[2][2] == 1)
				|| (mode.map[0][2] == 1 && mode.map[1][1] == 1 && mode.map[2][0] == 1)

		) {
			play = false;
			g.setColor(Color.yellow);
			g.setFont(new Font("Serif", Font.BOLD, 25));
			g.drawString("Wygrywa kółko Brawo ty!", 120, 20);
			scoreA++;
		}
		if ((mode.map[0][0] == 2 && mode.map[0][1] == 2 && mode.map[0][2] == 2)
				|| (mode.map[1][0] == 2 && mode.map[1][1] == 2 && mode.map[1][2] == 2)
				|| (mode.map[2][0] == 2 && mode.map[2][1] == 2 && mode.map[2][2] == 2)
				|| (mode.map[0][0] == 2 && mode.map[1][0] == 2 && mode.map[2][0] == 2)
				|| (mode.map[0][1] == 2 && mode.map[1][1] == 2 && mode.map[2][1] == 2)
				|| (mode.map[0][2] == 2 && mode.map[1][2] == 2 && mode.map[2][2] == 2)
				|| (mode.map[0][0] == 2 && mode.map[1][1] == 2 && mode.map[2][2] == 2)
				|| (mode.map[0][2] == 2 && mode.map[1][1] == 2 && mode.map[2][0] == 2)) {
			play = false;
			g.setColor(Color.red);
			g.setFont(new Font("Serif", Font.BOLD, 25));
			g.drawString("Wygrywa Krzyżyk Brawo ty!", 100, 20);
			scoreB++;
		}

		if (total <= 0 && play == true) {
			play = false;
			g.setColor(Color.green);
			g.setFont(new Font("Serif", Font.BOLD, 25));
			g.drawString("REMIS!", 250, 20);
		}

		g.dispose();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (play) {
			x = e.getX();
			y = e.getY();
			recurrence = mode.find(x, y, value);
			if (value == 0 && recurrence==true) {
				value = 1;
			} else if (value == 1 && recurrence==true) {
				value = 0;
			}
			if (recurrence == true) {
				total--;
			}
			repaint();
		} else {
			play = true;
			for (int i = 0; i < mode.map.length; i++) {
				for (int j = 0; j < mode.map[0].length; j++) {
					mode.map[i][j] = 0;
				}
			}
			total = 9;

		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

}

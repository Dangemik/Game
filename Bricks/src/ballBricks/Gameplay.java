package ballBricks;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Gameplay extends JPanel implements KeyListener, ActionListener {

	private boolean play = false;
	private int score = 0;

	private int totalBrick = 24;

	private Timer time;

	private int delay = 6;

	private int playerX = 310;
	private int playerWidth = 100;

	private int ballposX = 120;
	private int ballposY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;

	// bonus
	private Random generator = new Random();
	private boolean bonus = false;
	private int bonusPosX;
	private int bonusPosY = 50;
	private int bonusYdir = 1;
	private int randomBonus;

	private int level = 1;

	private MapGenerator map;

	public Gameplay() {
		map = new MapGenerator(4, 6);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

		time = new Timer(delay, this);
		time.start();
	}

	public void paint(Graphics g) {
		// Background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		// DrawingMap
		map.draw((Graphics2D) g);
		// borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 1, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		// scores
		g.setColor(Color.white);
		g.setFont(new Font("Serif", Font.BOLD, 25));
		g.drawString("" + score, 650, 30);
		// the paddle
		g.setColor(Color.green);
		g.fillRect(playerX, 550, playerWidth, 8);
		// level
		g.setColor(Color.white);
		g.setFont(new Font("Serif", Font.BOLD, 25));
		g.drawString("Level:" + level, 0, 30);
		// the ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);
		// bonus
		if (bonus == true) {
			if (randomBonus == 1) {
				g.setColor(Color.green);
				g.fillRect(bonusPosX, bonusPosY, 30, 10);
			} else if (randomBonus == 2) {
				g.setColor(Color.pink);
				g.fillRect(bonusPosX, bonusPosY, 30, 10);
			} else if (randomBonus == 3) {
				g.setColor(Color.green);
				g.fillOval(bonusPosX, bonusPosY, 15, 15);
			} 

		}

		if (totalBrick <= 0 && level != 0) {
			bonus = false;
			bonusPosY = 50;
			bonusPosX = generator.nextInt(600) + 10;
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.green);
			g.setFont(new Font("Serif", Font.BOLD, 30));
			g.drawString("Level " + level + " z wynikiem:" + score, 190, 300);

			g.setFont(new Font("Serif", Font.BOLD, 30));
			g.drawString("Kliknij Enter aby przejść dalej", 150, 350);

		}
		if (level == 0) {
			bonus = false;
			bonusPosY = 50;
			bonusPosX = generator.nextInt(600) + 10;
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.green);
			g.setFont(new Font("Serif", Font.BOLD, 30));
			g.drawString("Udało ci sie przejść gre!", 190, 300);

			g.setFont(new Font("Serif", Font.BOLD, 30));
			g.drawString("Kliknij Enter aby zagrać jeszcze raz", 150, 350);
		}

		if (ballposY > 570) {
			bonus = false;
			bonusPosY = 50;
			bonusPosX = generator.nextInt(600) + 10;
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("Serif", Font.BOLD, 30));
			g.drawString("Nie udało się wynikiem:" + score, 190, 300);

			g.setFont(new Font("Serif", Font.BOLD, 30));
			g.drawString("Kliknij Enter żeby zresetować", 150, 350);
		}
		if (bonusPosY > 570) {
			bonus = false;
			bonusPosY = 50;
			bonusPosX = generator.nextInt(600) + 10;

		}

		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		time.start();
		if (play) {

			if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, playerWidth, 8))) {
				ballYdir = -ballYdir;
			}
			if (new Rectangle(bonusPosX, bonusPosY, 30, 10).intersects(new Rectangle(playerX, 550, playerWidth, 8))) {
				bonus = false;
				bonusPosY = 50;
				bonusPosX = generator.nextInt(600) + 10;
				if (randomBonus == 1) {
					playerWidth += 20;
				} else if (randomBonus == 2) {
					playerWidth -= 20;
				} else if (randomBonus == 3) {
					if (ballXdir > 0) {
						ballXdir += 1;
					}
					if (ballXdir < 0) {
						ballXdir -= 1;
					}
					if (ballYdir > 0) {
						ballYdir += 1;
					}
					if (ballYdir < 0) {
						ballYdir -= 1;
					}
				} 

			}
			A: for (int i = 0; i < map.map.length; i++) {
				for (int j = 0; j < map.map[0].length; j++) {
					if (map.map[i][j] == 1) {
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;

						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;

						if (ballRect.intersects(brickRect)) {
							map.setBrickValue(0, i, j);
							totalBrick--;
							score += 5;
							if (ballposX + 19 <= brickRect.x || ballposX + 1 > brickRect.x + brickRect.width) {
								ballXdir = -ballXdir;
							} else {
								ballYdir = -ballYdir;
							}
							break A;

						}
					} else if (map.map[i][j] == 2) {
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;

						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;

						if (ballRect.intersects(brickRect)) {
							map.setBrickValue(1, i, j);
							score += 5;
							if (ballposX + 19 <= brickRect.x || ballposX + 1 > brickRect.x + brickRect.width) {
								ballXdir = -ballXdir;
							} else {
								ballYdir = -ballYdir;
							}
							break A;
						}
					} else if (map.map[i][j] == 3) {
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;

						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;

						if (ballRect.intersects(brickRect)) {
							map.setBrickValue(1, i, j);
							score += 5;
							if (ballposX + 19 <= brickRect.x || ballposX + 1 > brickRect.x + brickRect.width) {
								ballXdir = -ballXdir;
							} else {
								ballYdir = -ballYdir;
							}
							if (bonus == false) {
								bonus = true;
								randomBonus = generator.nextInt(3) + 1;
							}

							break A;
						}
					}

				}

			}

			ballposX += ballXdir;
			ballposY += ballYdir;
			if (bonus == true) {
				bonusPosY += bonusYdir;
			}
			if (ballposX < 0) {
				ballXdir = -ballXdir;
			}
			if (ballposY < 0) {
				ballYdir = -ballYdir;
			}
			if (ballposX > 670) {
				ballXdir = -ballXdir;
			}

		}

		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (playerX + playerWidth >= 700)
				playerX = 600;
			else {
				moveRight();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (playerX < 10)
				playerX = 10;
			else {
				moveLeft();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!play) {
				play = true;
				ballposX = 120;
				ballposY = 350;
				ballXdir = -1;
				ballYdir = -2;
				playerX = 310;
				playerWidth = 100;
				score = 0;
				if (totalBrick == 0) {
					level++;
				}
				if (level == 1) {
					totalBrick = 24;
					map = new MapGenerator(4, 6);
				} else if (level == 2) {
					totalBrick = 25;
					map = new MapGenerator(5, 5);
				} else if (level == 3) {
					map = new MapGenerator();
					totalBrick = map.randomCol * map.randomRow;
				}

				else {
					level = 0;
				}

				repaint();
			}

		}
	}

	public void moveRight() {
		play = true;
		playerX += 20;
	}

	public void moveLeft() {
		play = true;
		playerX -= 20;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}

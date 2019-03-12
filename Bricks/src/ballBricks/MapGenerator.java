package ballBricks;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class MapGenerator {
	public int map[][];
	public int brickWidth;
	public int brickHeight;

	private Random generator = new Random();
	public int randomRow, randomCol;

	public double randomValue;

	public MapGenerator() {
		randomRow = generator.nextInt(20) + 1;
		randomCol = generator.nextInt(20) + 1;
		map = new int[randomRow][randomCol];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {

				randomValue = generator.nextDouble();
				if (randomValue < 0.40) {
					map[i][j] = 1;
				} else if (randomValue <= 0.80) {
					map[i][j] = 2;
				} else if (randomValue <= 1) {
					map[i][j] = 3;
				
				}

			}
		}
		brickWidth = 540 / randomCol;
		brickHeight = 150 / randomRow;
	}

	public MapGenerator(int row, int colums) {
		map = new int[row][colums];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				randomValue = generator.nextDouble();
				if (randomValue < 0.40) {
					map[i][j] = 1;
				} else if (randomValue <= 0.80) {
					map[i][j] = 2;
				} else if (randomValue <= 1) {
					map[i][j] = 3;
				}
			}
		}
		brickWidth = 540 / colums;
		brickHeight = 150 / row;
	}

	public void draw(Graphics2D g) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == 1) {
					g.setColor(Color.orange);
					g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);

					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
				} else if (map[i][j] == 2) {
					g.setColor(Color.red);
					g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);

					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
				} else if (map[i][j] == 3) {
					g.setColor(Color.blue);
					g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);

					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
				}
			}
		}
	}

	public void setBrickValue(int value, int row, int col) {
		map[row][col] = value;
	}
}

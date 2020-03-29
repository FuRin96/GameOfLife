package gameOfLife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import core.Cell;
import core.StateButton;

public class World extends JPanel implements MouseListener, MouseMotionListener {
	private static int cellSize = 10;
	private int cols;
	private int rows;
	private int width;
	private int height;

	private Cell[][] world;
	private Cell[][] tempWorld;

	StateButton state;

	public World(Cell[][] world) {
		this.world = world;
		cols = rows = world.length;
		tempWorld = world;
	}

	public World(int cols, int rows) {
		this.cols = cols;
		this.rows = rows;
		width = cellSize * cols;
		height = cellSize * rows;
		world = new Cell[cols][rows];
		tempWorld = new Cell[cols][rows];
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				world[i][j] = new Cell();
				tempWorld[i][j] = new Cell();
			}
		}

		addMouseListener(this);
		addMouseMotionListener(this);

		state = StateButton.STOP;
	}

	public void setState(StateButton state) {
		this.state = state;
	}

	public boolean isAlive(int row, int col) {
		return world[row][col].getState();
	}

	public void update() {
		if (state == StateButton.PLAYING) {
			prepare();
			changeCell();
		} else if (state == StateButton.NEXT) {
			prepare();
			changeCell();
			state = StateButton.STOP;
		} else if (state == StateButton.RESTART) {
			for (int i = 0; i < cols; i++) {
				for (int j = 0; j < rows; j++) {
					world[i][j] = new Cell();
					tempWorld[i][j] = new Cell();
				}
			}
			state = StateButton.STOP;
		}
		this.repaint();
		try {
			Thread.sleep(1000 / 10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) {
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				Graphics2D g2 = (Graphics2D) g;
				if (world[i][j].getState()) {
					g2.setColor(Color.BLACK);
				} else {
					g2.setColor(Color.LIGHT_GRAY);
				}
				g2.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
				g2.setColor(Color.GRAY);
				g2.drawRect(i * cellSize, j * cellSize, cellSize, cellSize);
			}
		}
	}
	
	// Comprobar la celulas vecinas
	public int checkCell(int col, int row) {
		int surrounding = 0;
		if (col - 1 >= 0 && row - 1 >= 0) {
			if (world[col - 1][row - 1].getState()) {
				surrounding++;
			}
		}
		if (col - 1 >= 0) {
			if (world[col - 1][row].getState()) {
				surrounding++;
			}
		}
		if (col - 1 >= 0 && row + 1 < rows) {
			if (world[col - 1][row + 1].getState()) {
				surrounding++;
			}
		}
		if (row - 1 >= 0) {
			if (world[col][row - 1].getState()) {
				surrounding++;
			}
		}
		if (row + 1 < rows) {
			if (world[col][row + 1].getState()) {
				surrounding++;
			}
		}
		if (col + 1 < cols && row - 1 >= 0) {
			if (world[col + 1][row - 1].getState()) {
				surrounding++;
			}
		}
		if (col + 1 < cols) {
			if (world[col + 1][row].getState()) {
				surrounding++;
			}
		}
		if (col + 1 < cols && row + 1 < rows) {
			if (world[col + 1][row + 1].getState()) {
				surrounding++;
			}
		}
		return surrounding;
	}

	// Avanzar por la matriz comprobando las celulas
	public void prepare() {
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[i].length; j++) {
				int aliveCells = checkCell(i, j);
				if (aliveCells < 2 || aliveCells > 3) {
					tempWorld[i][j].setState(false);
				} else if (aliveCells == 3) {
					tempWorld[i][j].setState(true);
				} else if (aliveCells == 2) {
					tempWorld[i][j].setState(world[i][j].getState());
				}
			}
		}
	}

	// cambiar el estado de las celulas
	public void changeCell() {
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[i].length; j++) {
				world[i][j].setState(tempWorld[i][j].getState());
				tempWorld[i][j].setState(false);
			}
		}
	}

	public void addCell(MouseEvent e) {
		if (e.getPoint().x >= 0 && e.getPoint().y >= 0 && e.getPoint().x < width && e.getPoint().y < height) {
			int x = e.getPoint().x / cellSize;
			int y = e.getPoint().y / cellSize;

			if (SwingUtilities.isRightMouseButton(e)) {
				world[x][y].setState(false);
			} else if (SwingUtilities.isLeftMouseButton(e)){
				world[x][y].setState(true);
			}
		}
		this.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		addCell(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		addCell(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}

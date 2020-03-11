package core;

import java.util.EventObject;

public class StartEvent extends EventObject {

	private int grid[][];
	
	public StartEvent(Object source) {
		super(source);
	}
	
	public StartEvent(Object source, int[][] grid) {
		super(source);
		
		this.grid = grid;
	}

	public int[][] getGrid() {
		return grid;
	}

	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

}

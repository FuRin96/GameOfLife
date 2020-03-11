package core;

public class Cell {
    private boolean state = false;

    public Cell() {

    }
    
    public Cell(boolean state) {
        this.state = state;
    }

    public void setState(boolean state) {
		this.state = state;
	}

	public boolean getState() {
        return state;
    }
}
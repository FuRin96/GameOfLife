package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import core.Cell;
import core.StateButton;
import gameOfLife.World;

public class TestLogic {
	
	@Test
	public void testIsAlive() {
        Cell[][] cells = {
                {new Cell(true), new Cell(), new Cell(true)}, 
                {new Cell(), new Cell(), new Cell()}, 
                {new Cell(true), new Cell(), new Cell(true)}
            };
        
        World world = new World(cells);

        assertEquals(true, world.isAlive(0, 0));
        assertEquals(false, world.isAlive(1, 0));
        assertEquals(true, world.isAlive(2, 0));
        assertEquals(false, world.isAlive(0, 1));
        assertEquals(false, world.isAlive(1, 1));
        assertEquals(false, world.isAlive(2, 1));
        assertEquals(true, world.isAlive(0, 2));
        assertEquals(false, world.isAlive(1, 2));
        assertEquals(true, world.isAlive(2, 2));
	}

	@Test
    public void testNeighbours() {
        
        Cell[][] cells = {
            {new Cell(true), new Cell(true), new Cell(true)}, 
            {new Cell(true), new Cell(true), new Cell(true)}, 
            {new Cell(true), new Cell(true), new Cell(true)}
        };
        World world = new World(cells);

        assertEquals(3, world.checkCell(0,0));
        assertEquals(8, world.checkCell(1,1));
        assertEquals(5, world.checkCell(1,0));
    }
	
    @Test
    public void testAllCellsDie() {
        Cell[][] cells = {
            {new Cell(true), new Cell(), new Cell(true)}, 
            {new Cell(), new Cell(), new Cell()}, 
            {new Cell(true), new Cell(), new Cell(true)}
        };

        World world = new World(cells);

        assertEquals(true, world.isAlive(0, 0));
        assertEquals(false, world.isAlive(1, 1));

        world.setState(StateButton.NEXT);
        world.update();
        
        assertEquals(false, world.isAlive(0, 0));
        assertEquals(false, world.isAlive(2, 2));
        assertEquals(false, world.isAlive(2, 0));
        assertEquals(false, world.isAlive(0, 2));
        assertEquals(false, world.isAlive(1, 1));
    }
    
}

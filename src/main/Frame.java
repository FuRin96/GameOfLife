// GitHub https://github.com/FuRin96/GameOfLife

package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import core.ButtonListener;
import core.StateButton;
import gameOfLife.ButtonPanel;
import gameOfLife.World;

public class Frame extends JFrame {

	public static void main(String[] args) {
		ButtonPanel buttonPanel;
		World world;

		JFrame frame = new JFrame("Game Of Life");
		
		frame.setLayout(new BorderLayout());
		buttonPanel = new ButtonPanel();
		world = new World(100, 50);

		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.add(world, BorderLayout.CENTER);

		//frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 800);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		buttonPanel.setButtonListener(new ButtonListener() {

			@Override
			public void btnPressed(StateButton s) {
				StateButton state = s;
				world.setState(state);

			}
		});

		while (true) {
			world.update();
		}
	}
}

package gameOfLife;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import core.ButtonListener;
import core.StateButton;

public class ButtonPanel extends JPanel implements ActionListener {
	JButton[] buttons = new JButton[4];

	private JButton startBtn;
	private JButton stopBtn;
	private JButton restartBtn;
	private JButton nextBtn;

	private ButtonListener buttonListener;

	public ButtonPanel() {
		startBtn = new JButton("Start");
		stopBtn = new JButton("Stop");
		restartBtn = new JButton("Restart");
		nextBtn = new JButton("Next gen");

		buttons[0] = startBtn;
		buttons[1] = stopBtn;
		buttons[2] = restartBtn;
		buttons[3] = nextBtn;

		setLayout(new FlowLayout(FlowLayout.CENTER));

		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setPreferredSize(new Dimension(150, 40));
			buttons[i].addActionListener(this);
			add(buttons[i]);
		}
	}

	public void setButtonListener(ButtonListener buttonListener) {
		this.buttonListener = buttonListener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton btnClicked = (JButton) e.getSource();
		if (buttonListener != null) {
			if (btnClicked == startBtn) {
				buttonListener.btnPressed(StateButton.PLAYING);
			} else if (btnClicked == stopBtn) {
				buttonListener.btnPressed(StateButton.STOP);
			} else if (btnClicked == restartBtn) {
				buttonListener.btnPressed(StateButton.RESTART);
			} else if (btnClicked == nextBtn) {
				buttonListener.btnPressed(StateButton.NEXT);
			}
		}

	}

}

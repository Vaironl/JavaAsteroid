package Display;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Container extends JFrame {

	public Container(String TITLE, int SCREEN_WIDTH, int SCREEN_HEIGHT) {

		setTitle(TITLE);
		// setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		// setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		// Panel
		Panel newPanel = new Panel();
		add(newPanel);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

}

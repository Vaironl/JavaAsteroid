package AffineTest;

import javax.swing.JFrame;

public class TestContainer extends JFrame {

	public TestContainer(String TITLE, int SCREEN_WIDTH, int SCREEN_HEIGHT) {

		setTitle(TITLE);
		// setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		// setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		// Panel
		TestPanel newPanel = new TestPanel();
		add(newPanel);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

}

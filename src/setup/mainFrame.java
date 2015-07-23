package setup;

import java.awt.BorderLayout;

import javax.swing.*;

import players.Player;


public class mainFrame extends JFrame implements myConstants{
	
	
	private static final long serialVersionUID = 1L;
	public mainFrame(Player[] p, int n) {
		super("RPG");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		mainCanvas canvas = new mainCanvas(p, n);
		add(canvas, BorderLayout.CENTER);
		
		// set it's size and make it visible
		setSize(ESCALA*36, ESCALA*28-12);
		setVisible(true);		
		// now that is visible we can tell it that we will use 2 buffers to do the repaint
		// before being able to do that, the Canvas has to be visible
		canvas.createBufferStrategy(2);
	}
	
	// just to start the application
	
}


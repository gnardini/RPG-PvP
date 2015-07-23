package setup;

import java.awt.Color;
import java.awt.Point;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import players.Player;

public class StartMenu extends JFrame implements myConstants{

	private static final long serialVersionUID = 1L;
	private Player[] p;
	private int n;
	
	public StartMenu(int n){
		super("Class Selection");
		try { UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");}
		catch (UnsupportedLookAndFeelException ex) {}
		catch (IllegalAccessException ex) {} 
		catch (InstantiationException ex) {} 
		catch (ClassNotFoundException ex) {}
        UIManager.put("swing.boldMetal", Boolean.FALSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		
		this.n=n;
		p= new Player[n];
		
		JPanel jp = new JPanel();
		jp.setBackground(new Color(240,240,180));
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		for(int i=0 ; i<n ; i++)
			jp.add(new ClassSelect(this, p[i], i+1));
		
		setContentPane(jp);
		setVisible(true);
	}
	
	public void playerSet(Player p, int n){
		this.p[n-1]=p;
		startGame();
	}
	
	private void startGame(){
		for(int i=0 ; i<n ; i++){
			if(p[i]== null) return;
			p[i].setNumber(i+1);
		}
		p[0].setPosition(new Point(1,1));
		p[1].setPosition(new Point(1,DIMY-2));
		if(n>=3) p[2].setPosition(new Point(DIMX-2,1));
		if(n==4) p[3].setPosition(new Point(DIMX-2,DIMY-2));
		
		new mainFrame(p, n);
		dispose();
	}
	
	
	

}

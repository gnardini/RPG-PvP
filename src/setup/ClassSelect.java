package setup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import players.Archer;
import players.Mage;
import players.Paladin;
import players.Player;
import players.Warrior;

public class ClassSelect extends JPanel implements ActionListener, myConstants{

	private static final long serialVersionUID = 1L;
	private Player p;
	private StartMenu sm;
	private int n;
	private JLabel la;
	
	public ClassSelect(StartMenu sm, Player p, int n){
		this.sm=sm;
		this.p=p;  
		this.n=n;
		setLayout(new FlowLayout());
	    setPreferredSize(new Dimension(300,30));
	    setOpaque(false);
	      
	    setBorder(new SoftBevelBorder(BevelBorder.RAISED));
	     
	    JLabel l= new JLabel("Player " + n + ":" );
	    l.setForeground(Color.BLUE);
	    add(l);
		JButton[] b = new JButton[4];
		b[0]=new JButton("Warrior");
		b[1]=new JButton("Mage");
		b[2]=new JButton("Archer");
		b[3]=new JButton("Paladin");
		for(int i= 0 ; i<4 ; i++){
			b[i].addActionListener(this);
			add(b[i]);
		}
		la= new JLabel("Selected: ");
		add(la);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Warrior"))p= new Warrior();
		else if(e.getActionCommand().equals("Mage"))p= new Mage();
		else if(e.getActionCommand().equals("Archer"))p= new Archer();
		else p= new Paladin();
		la.setText("Selected: " + p.getClassName());
		sm.playerSet(p, n);
	}
	
}

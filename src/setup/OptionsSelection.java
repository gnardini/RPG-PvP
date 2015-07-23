package setup;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class OptionsSelection extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	public OptionsSelection() {
		super("Options Selection");
		try { UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");}
		catch (UnsupportedLookAndFeelException ex) {}
		catch (IllegalAccessException ex) {} 
		catch (InstantiationException ex) {} 
		catch (ClassNotFoundException ex) {}
        UIManager.put("swing.boldMetal", Boolean.FALSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 200);
		
		
		JPanel jp = new JPanel();
		JPanel boxes = new JPanel();
		jp.setBackground(new Color(240,240,180));
		jp.setForeground(Color.BLUE);
		boxes.setBackground(new Color(240,240,180));
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		boxes.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton[] b = new JButton[3];
		for ( int i=2 ; i<=4 ; i++) {
			b[i-2]=new JButton(i + "");
			b[i-2].addActionListener(this);
			boxes.add(b[i-2]);
		}
		jp.add(new JLabel("Select Number of Players:"));
		jp.add(boxes);
		
		setContentPane(jp);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent a) {
		String s = a.getActionCommand();
		if(s.equals("2")) new StartMenu(2);
		if(s.equals("3")) new StartMenu(3);
		if(s.equals("4")) new StartMenu(4);
		dispose();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new OptionsSelection();
			}
		});
	}

}

package setup;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import bonuses.RandomSpawns;
import map.Bush;
import map.Grass;
import map.Maps;
import map.Rock;
import map.Wall;
import players.Player;

// OK this the class where we will draw
public class mainCanvas extends Canvas implements KeyListener, myConstants, MouseListener{

	private static final long serialVersionUID = 1L;
	boolean repaintInProgress = false;
	private Maps map;
	private Player[] p;
	private Info statusp1, statusp2, statusp3,statusp4;
	private Image[] img;
	private Map<String, Image> images = new HashMap<String, Image>();
	private int n;
	
	public mainCanvas(Player[] p, int n) {
		setIgnoreRepaint(true);
		addKeyListener(this);
		addMouseListener(this);
		statusp1= new Info(10, 10);
		statusp2= new Info(ESCALA*18+ 30, 10);
		statusp3= new Info(10, ESCALA*25+3);
		statusp4= new Info(ESCALA*18+ 30, ESCALA*25+3);
		
		this.p= new Player[n];
		this.n=n;
		for(int i = 0 ; i<n ; i++)
			this.p[i]=p[i];
		
		setPlayerkeys();
		map= new Maps("missiles",p);
		for(int i=0 ; i<n ; i++)
			map.getMap()[(int)p[i].getX()][(int)p[i].getY()].isEmpty(false,p[i]);
		new RandomSpawns(map);
		loadImages();
		
		mainChrono chrono = new mainChrono(this);
		new Timer(16, chrono).start();
	}
	
	private void loadImages(){
		try{
		img= new Image[24];
		img[0]=loadImage("images/red hair.png");
		img[1]=loadImage("images/red hair back.png");
		img[2]=loadImage("images/red hair der.png");
		img[3]=loadImage("images/red hair izq.png");
		img[4]=loadImage("images/yellow hair.png");
		img[5]=loadImage("images/yellow hair atras.png");
		img[6]=loadImage("images/yellow hair right.png");
		img[7]=loadImage("images/yellow hair left.png");
		img[8]=loadImage("images/black hair.png");
		img[9]=loadImage("images/black hair atras.png");
		img[10]=loadImage("images/black hair right.png");
		img[11]=loadImage("images/black hair left.png");
		img[12]=loadImage("images/blue hair.png");
		img[13]=loadImage("images/blue hair atras.png");
		img[14]=loadImage("images/blue hair right.png");
		img[15]=loadImage("images/blue hair left.png");
		img[16]=loadImage("images/bush.png");
		img[17]=loadImage("images/ladrillos.png");
		img[18]=loadImage("images/pasto2.png");
		img[19]=loadImage("images/roca2.png");
		img[20]=loadImage("images/bola de fuego.png");
		img[21]=loadImage("images/cruz.png");
		img[22]=loadImage("images/espada.png");
		img[23]=loadImage("images/veneno violeta.png");
		}catch(IOException e){}
	}
	
	private void setPlayerkeys(){
		p[1].setNorth(KeyEvent.VK_NUMPAD5);
		p[1].setSouth(KeyEvent.VK_NUMPAD2);
		p[1].setEast(KeyEvent.VK_NUMPAD3);
		p[1].setWest(KeyEvent.VK_NUMPAD1);
		p[1].setAtt1(KeyEvent.VK_CONTROL);
		p[1].setAtt2(KeyEvent.VK_LEFT);
		p[1].setAtt3(KeyEvent.VK_UP);
		p[1].setAtt4(KeyEvent.VK_DOWN);	
		p[1].setAtt5(KeyEvent.VK_RIGHT);
		
		if(n<3) return;
		p[2].setNorth(KeyEvent.VK_HOME);
		p[2].setSouth(KeyEvent.VK_END);
		p[2].setEast(KeyEvent.VK_PAGE_DOWN);
		p[2].setWest(KeyEvent.VK_DELETE);
		p[2].setAtt1(KeyEvent.VK_INSERT);
		p[2].setAtt2(KeyEvent.VK_X);
		p[2].setAtt3(KeyEvent.VK_C);
		p[2].setAtt4(KeyEvent.VK_V);	
		p[2].setAtt5(KeyEvent.VK_Z);
		
		if(n<4) return;
		p[3].setNorth(KeyEvent.VK_DIVIDE);
		p[3].setSouth(KeyEvent.VK_NUMPAD8);
		p[3].setEast(KeyEvent.VK_NUMPAD9);
		p[3].setWest(KeyEvent.VK_NUMPAD7);
		p[3].setAtt1(KeyEvent.VK_MULTIPLY);
		p[3].setAtt2(KeyEvent.VK_F2);
		p[3].setAtt3(KeyEvent.VK_F3);
		p[3].setAtt4(KeyEvent.VK_F4);	
		p[3].setAtt5(KeyEvent.VK_F1);
	}
	
	public void initImages() {
		try{
			images.put(Grass.class.getName(), loadImage("pasto2.png"));
			images.put(Wall.class.getName(), loadImage("ladrillos.png"));
			images.put(Bush.class.getName(), loadImage("bush.png"));
			images.put(Rock.class.getName(), loadImage("roca2.png"));
			images.put("Front", loadImage("red hair.png"));
			images.put("Back", loadImage("red hair back.png"));
			images.put("Left", loadImage("red hair izq.png"));
			images.put("Right", loadImage("red hair der.png"));
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static Image loadImage(String fileName) throws IOException {
		InputStream stream = ClassLoader.getSystemResourceAsStream(fileName);
		if (stream == null) {	
			return ImageIO.read(new File(fileName)); 
		} else {
			return ImageIO.read(stream);
		}
	}
	
	public void myRepaint() {
		if(repaintInProgress)
			return;
		repaintInProgress = true;
		BufferStrategy strategy = getBufferStrategy();
		Graphics g = strategy.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 226+ESCALA*31, 20+ESCALA*31);
		
		
		for(int i=0; i<DIMX ; i++){
			for( int j=0 ; j<DIMY; j++){
				map.getMap()[i][j].paintSelf(g, j, i+2, img);
			}
		}
		
		statusp1.showPlayerInfo(g, p[0], map);
		statusp2.showPlayerInfo(g, p[1], map);
		if(n>=3) statusp3.showPlayerInfo(g, p[2], map);
		if(n==4) statusp4.showPlayerInfo(g, p[3], map);
		g.setColor(Color.WHITE);
		g.setFont(getFont().deriveFont(27.0f));
		g.drawString(p[0].getKills() + " - " + p[1].getKills(),ESCALA*17-15 ,ESCALA+8 );
		if(n==4) g.drawString(p[2].getKills() + " - " + p[3].getKills(),ESCALA*17-15 ,ESCALA*26 );
		else if(n==3) g.drawString(p[2].getKills() + "",ESCALA*17-15 ,ESCALA*26 );
		
		strategy.show();
		Toolkit.getDefaultToolkit().sync();
		repaintInProgress = false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		
		for(int i = 0 ; i<n ; i++)
			if(p[i].isAlive())
				new PlayerAction(key, p[i], map);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	@Override public void mousePressed(MouseEvent e) {}
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void keyReleased(KeyEvent e) {}
	@Override public void keyTyped(KeyEvent e) {}
}


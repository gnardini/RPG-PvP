package players;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import map.Maps;
import setup.myConstants;

public abstract class Player extends LifeObject implements myConstants{

	private static final long serialVersionUID = 1L;
	protected Point mana;
	protected long CDatt1;
	protected long CDatt2;
	protected long CDatt3;
	protected long CDatt4;
	protected long CDatt5;
	protected HashMap<String, Long> cooldowns;
	protected int kills;
	
	private boolean canmove=true;
	
	private int number;
	private Point respawn;
	private int north= KeyEvent.VK_Y;
	private int south= KeyEvent.VK_H;
	private int east= KeyEvent.VK_J;
	private int west= KeyEvent.VK_G;
	private int att1= KeyEvent.VK_SPACE;
	private int att2= KeyEvent.VK_A;
	private int att3= KeyEvent.VK_W;
	private int att4= KeyEvent.VK_S;
	private int att5= KeyEvent.VK_D;
	
	public Player(int HP){
		super(HP);
		name = "Player";
		alive=true;
	}
	
	public void paintSelf(Graphics g, int x, int y, Image[] img){
			if(face.x==1)
				g.drawImage(img[0+(number-1)*4],10+x*ESCALA,-5+y*ESCALA,null);
			else if(face.x==-1)
				g.drawImage(img[1+(number-1)*4],10+x*ESCALA,-5+y*ESCALA,null);
			else if(face.y==1)
				g.drawImage(img[2+(number-1)*4],10+x*ESCALA,-5+y*ESCALA,null);
			else if(face.y==-1)
				g.drawImage(img[3+(number-1)*4],10+x*ESCALA,-5+y*ESCALA,null);
		
		g.setColor(Color.white);
		g.fillRect(10+x*ESCALA, -9+y*ESCALA, ESCALA, ESCALA/8);
		g.setColor(Color.RED);
		g.fillRect(10+x*ESCALA, -9+y*ESCALA, (int)(HP.getX()/HP.getY()*ESCALA), ESCALA/8);
		if(usesMana()){
			g.setColor(Color.white);
			g.fillRect(10+x*ESCALA, -14+y*ESCALA, ESCALA, ESCALA/8);
			g.setColor(Color.blue);
			g.fillRect(10+x*ESCALA, -14+y*ESCALA, (int)(mana.getX()/mana.getY()*ESCALA), ESCALA/8);
		}/*
		g.setColor(Color.blue);
		g.fillRoundRect(11+x*ESCALA, 11+y*ESCALA, 14, 14, 4,4);
		g.setColor(Color.white);
		g.fillOval(15+x*ESCALA, 14+y*ESCALA, 2, 2);
		g.fillOval(20+x*ESCALA, 14+y*ESCALA, 2, 2);
		g.drawArc(15+x*ESCALA, 20+y*ESCALA, 6, 2, 170, 190);
		*/
	}
	
	public void setPosition(Point p){
		respawn=new Point(p.x,p.y);
		pos=new Point(p.x,p.y);
	}
	
	public void addKill(){
		kills++;
	}
	
	public int getKills(){
		return kills;
	}
	
	
	public void canMove(boolean b){
		canmove=b;
	}
	
	public boolean canMove(){
		return canmove;
	}
	
	public int getPlayerNum(){
		return number;
	}
	
	@Override
	public int hashCode() {
		return number;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (number != other.number)
			return false;
		return true;
	}

	public void setNumber(int n){
		number=n;
	}
	
	@Override
	public void die(Player p, Maps map) {
		p.addKill();
		alive=false;
		
			HP.setLocation(HP.getY(), HP.getY());
			if(usesMana()) mana.setLocation(mana.getY(), mana.getY());
			map.getMap()[(int)pos.getX()][(int)pos.getY()].isEmpty(true, null);
			pos.setLocation(respawn.x, respawn.y);
			face.x=1;
			face.y=0;
			new RespawnTimer(this, map);
	}
	
	public void Interact(Maps map){
		if(map.getMap()[(int)(pos.getX()+face.getX())][(int)(pos.getY()+face.getY())].hasNPC())
			map.getMap()[(int)(pos.getX()+face.getX())][(int)(pos.getY()+face.getY())].openMenu(this);
	}
	
	public abstract boolean usesMana();
	public abstract void attack1(Maps map);
	public abstract void attack2(Maps map);
	public abstract void attack3(Maps map);
	public abstract void attack4(Maps map);
	public abstract void attack5(Maps map);
	public abstract String getAtt1name();
	public abstract String getAtt2name();
	public abstract String getAtt3name();
	public abstract String getAtt4name();
	public abstract String getAtt5name();
	public abstract boolean isOnCooldown(String name);
	public abstract void setCooldown(String name, long time);
	public abstract double getTimeLeft(String name);
	public abstract double getCDatt1();
	public abstract double getCDatt2();
	public abstract double getCDatt3();
	public abstract double getCDatt4();
	public abstract double getCDatt5();
	public abstract String getClassName();
	
	public void addCDatt1(int value){
		CDatt1+=value;
	}
	
	protected void blink(Maps map){
		int x=(int)(pos.getX()+5*face.getX()), y=(int)(pos.getY()+5*face.getY()),contador=0;
		while((x<0 || x>=DIMX || y<0 || y>=DIMY || !map.getMap()[x][y].isEmpty()) && contador<5){
			x-=(int)face.getX();
			y-=(int)face.getY();
			contador++;
		}
		map.getMap()[(int)pos.getX()][(int)pos.getY()].isEmpty(true,null);
		pos.move(x, y);
		map.getMap()[(int)pos.getX()][(int)pos.getY()].isEmpty(false,this);
	}
	
	
	public int getAtt1() {
		return att1;
	}

	public void setAtt1(int att) {
		this.att1 = att;
	}
	
	public int getAtt2() {
		return att2;
	}

	public void setAtt2(int att) {
		this.att2 = att;
	}
	
	public int getAtt3() {
		return att3;
	}

	public void setAtt3(int att) {
		this.att3 = att;
	}
	
	public int getAtt4() {
		return att4;
	}

	public void setAtt4(int att) {
		this.att4 = att;
	}
	
	public int getAtt5() {
		return att5;
	}

	public void setAtt5(int att) {
		this.att5 = att;
	}
	
	public int getNorth() {
		return north;
	}

	public void setNorth(int north) {
		this.north = north;
	}

	public int getSouth() {
		return south;
	}

	public void setSouth(int south) {
		this.south = south;
	}

	public int getEast() {
		return east;
	}

	public void setEast(int east) {
		this.east = east;
	}

	public int getWest() {
		return west;
	}

	public void setWest(int west) {
		this.west = west;
	}
	
	public void fullMana(){
		mana.x=mana.y;
	}
	
	public Point getMana(){
		return mana;
	}
	
	public Point getHP(){
		return HP;
	}
	
	public boolean isEnemy(){
		return false;
	}
	
	public class ManaRegen implements Runnable{
		Player p;
		int manaregen;
		
		public ManaRegen(Player p, int manaregen){
			this.p=p;
			this.manaregen=manaregen;
			Thread t=new Thread(this,"Mana Regen");
			t.setPriority(1);
			t.start();
		}
		@Override
		public void run() {
			while(true){
				if(mana.getX()<mana.getY()) mana.translate(manaregen, 0);
				if(mana.getX()>mana.getY()) mana.setLocation(mana.getY(), mana.getY());
				try{Thread.sleep(2000);}catch(InterruptedException e){}
			}
		}
	}
	public class HealthRegen implements Runnable{
		Player p;
		int healthregen, dur;
		
		public HealthRegen(Player p, int healthregen, int dur){
			this.p=p;
			this.healthregen=healthregen;
			this.dur=dur;
			Thread t=new Thread(this,"Health Regen");
			t.setPriority(1);
			t.start();
		}
		@Override
		public void run() {
			int i=0;
			while(i < dur){
				if(HP.getX()<HP.getY()) HP.translate(healthregen, 0);
				if(HP.getX()>HP.getY()) HP.setLocation(HP.getY(), HP.getY());
				try{Thread.sleep(1000);}catch(InterruptedException e){}
				i++;
			}
		}
	}
}

package map;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;

import players.Mage;
import players.Player;
import setup.myConstants;
import abilities.FireBall;
import abilities.Proyectile;

public class Missiles extends Proyectile implements Runnable, myConstants {
	
	private Maps map;
	private Player[] p;
	private Player pl;
	private Point pos;
	private int[][] distancias;
	private int[] dx={0,0,-1,1};
	private int[] dy={1,-1,0,0};
	
	public Missiles(Maps map, Player[] p) {
		this.map=map;
		this.p=p;
		pl=new Mage();
		pl.setAD(0);
		pl.setAP(50);
		pos=new Point(DIMX/2,DIMY/2);
		pl.setPosition(pos);
		map.getMap()[pos.x][pos.y].hasProy(true,this);
		
		Thread t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run() {
		try{
			while(true){
				if(farAway()){
					map.getMap()[pos.x][pos.y].hasProy(false,null);
					pos.setLocation(DIMX/2, DIMY/2);
					map.getMap()[pos.x][pos.y].hasProy(true,this);
					Thread.sleep(2000);
				}
				else{
					for(int i=0 ; i<10 ; i++){
						Thread.sleep(200);
						actualizarDistancias();
						move();
					}
				}
				new FireBall(map,0,50,new Point(-1,0),pl,15,40);
				new FireBall(map,0,50,new Point(1,0),pl,15,40);
				new FireBall(map,0,50,new Point(0,-1),pl,15,40);
				new FireBall(map,0,50,new Point(0,1),pl,15,40);
			}
		}catch(InterruptedException e){}
	}
	
	private void move(){
		Point dir = new Point(0,0);
		int i,c,random,x=8,y=8;
		Random r = new Random();
		random=r.nextInt(4);
		for(c=0; c<4; c++){
			i=(c+random)%4;
			if(distancias[x+dir.x][y+dir.y]>distancias[x+dx[i]][y+dy[i]]) dir.move(dx[i],dy[i]);
		}
		if(map.getMap()[pos.x+dir.x][pos.y+dir.y].hasEnemy()){
			map.getMap()[pos.x+dir.x][pos.y+dir.y].attack(0, 50, map,pl);
			map.getMap()[pos.x][pos.y].hasProy(false,null);
			pos.setLocation(DIMX/2, DIMY/2);
			return;
		}
		map.getMap()[pos.x][pos.y].hasProy(false,null);
		pos.translate(dir.x, dir.y);
		map.getMap()[pos.x][pos.y].hasProy(true,this);
	}
	
	private boolean farAway(){
		for(int i=0 ; i<p.length ; i++)
			if(Math.abs(p[i].getX()-pos.x)<=8 && Math.abs(p[i].getY()-pos.y)<=8)
				return false;
		return true;
	}
	
	private boolean farAway(Player p){
		if(Math.abs(p.getX()-pos.x)<=8 && Math.abs(p.getY()-pos.y)<=8)
			return false;
		return true;
	}

	public void actualizarDistancias(){
		int x,y, x1,y1;
		distancias= new int[17][17];
		for(int i=0 ; i<17 ; i++)
			for(int j=0 ; j<17 ; j++)
				distancias[i][j]=40;
		x=pos.x;
		y=pos.y;
		for(int i=0 ; i<p.length ; i++)
			if(!farAway(p[i]))
				distancias[8-pos.x+p[i].getX()][8-pos.y+p[i].getY()]=0;
		LinkedList<Point> cola = new LinkedList<Point>();
		Point punto;
		for(int i=0 ; i<p.length ; i++)
			if(!farAway(p[i]))
				cola.push(new Point(8-pos.x+p[i].getX(),8-pos.y+p[i].getY()));
		while( !cola.isEmpty()){
			punto=cola.pop();
			x1=(int)punto.getX();
			y1=(int)punto.getY();
			for(int i=0; i<4; i++){
				if(x1+dx[i]>=0 && x1+dx[i]<17 && y1+dy[i]>=0 && y1+dy[i]<17 && x+x1+dx[i]-8 >0 && x+x1+dx[i]-8<DIMX && y+y1+dy[i]-8>0 && y+y1+dy[i]-8<DIMY){
				if(map.getMap()[x+x1+dx[i]-8][y+y1+dy[i]-8].isEmpty() && distancias[x1+dx[i]][y1+dy[i]]>distancias[x1][y1]+1){
					distancias[x1+dx[i]][y1+dy[i]]=distancias[x1][y1]+1;
					cola.push(new Point(x1+dx[i],y1+dy[i]));
				}
				}
			}	
		}
	}

	@Override
	public void paintSelf(Graphics g, int x, int y, Image[] img) {
		g.drawImage(img[20],10+x*ESCALA,-5+y*ESCALA,null);
	}

	@Override
	public int getADdmg() {
		return 0;
	}

	@Override
	public int getAPdmg() {
		return 50;
	}
	
	@Override
	public Player getPlayer() {
		return pl;
	}
	
	public Maps getMap(){
		return map;
	}

	@Override
	public void targetHit() {
		pos.setLocation(DIMX/2,DIMY/2);
	}
	
}

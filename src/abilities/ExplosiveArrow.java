package abilities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import map.Maps;
import players.Player;
import setup.myConstants;

public class ExplosiveArrow extends Proyectile implements myConstants {

	int ADdmg,APdmg, range, speed;
	Point dir;
	boolean active=true;
	
	public ExplosiveArrow(Maps m, int AD,int AP, Point dir, Player pl, int range, int speed){
		map=m;
		ADdmg=AD;
		APdmg=AP;
		this.range=range;
		this.speed=speed;
		name="ExplosiveArrow";
		this.dir=dir;
		p=pl;
		Thread t=new Thread(this,name);
		t.start();
	}
	
	@Override
	public void run() {
		try{
		Point p1= new Point((int)(p.getX()),(int)(p.getY()));
		Point p2= new Point((int)(p.getX()+dir.getX()),(int)(p.getY()+dir.getY()));
		for(int i=0 ; i<range && active ; i++){
			if(!map.getMap()[(int)p2.getX()][(int)(p2.getY())].isEmpty()){
					if(map.getMap()[(int)p2.getX()][(int)(p2.getY())].hasEnemy()){
						map.getMap()[(int)p2.getX()][(int)(p2.getY())].attack(ADdmg, APdmg, map, (Player)p);
					}
				active=false;
			}else{
				map.getMap()[(int)p1.getX()][(int)p1.getY()].hasProy(false, null);
				map.getMap()[(int)p2.getX()][(int)p2.getY()].hasProy(true,this);
				p1.translate((int)dir.getX(), (int)dir.getY());
				p2.translate((int)dir.getX(), (int)dir.getY());
			}
			Thread.sleep(speed);
		}
		int x=(int)p2.getX(),y=(int)p2.getY();
		for(int i=-2 ; i<=2 ; i++)
			for(int j=-2 ; j<=2 ; j++)
				if((i!=0 || j!=0) && x+i>=0 && x+i<DIMX && y+j>=0 && y+j<DIMY)
					map.getMap()[x+i][y+j].snare(ADdmg/(1+Math.abs(Math.max(i, j))),APdmg,map,(Player)p,0);
		map.getMap()[(int)p1.getX()][(int)p1.getY()].hasProy(false, null);
		map.getMap()[(int)p2.getX()][(int)p2.getY()].hasProy(false, null);
		}catch(InterruptedException e){}
	}
	
	public void targetHit(){
		active=false;
	}
	
	@Override
	public int getADdmg() {
		return ADdmg;
	}
	
	@Override
	public int getAPdmg() {
		return APdmg;
	}
	
	@Override
	public void paintSelf(Graphics g, int x, int y, Image[] img) {
		g.setColor(Color.GREEN);
		g.fillOval(18+x*ESCALA, 3+y*ESCALA, ESCALA/2, ESCALA/2);
		
	}
}
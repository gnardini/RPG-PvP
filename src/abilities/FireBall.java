package abilities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import map.Maps;
import players.Player;
import setup.myConstants;

public class FireBall extends Proyectile implements Runnable, myConstants{
	
	int ADdmg,APdmg, range, speed;
	Point dir;
	boolean active=true;
	
	public FireBall(Maps m, int AD,int AP, Point dir, Player pl, int range, int speed){
		map=m;
		ADdmg=AD;
		APdmg=AP;
		this.range=range;
		this.speed=speed;
		name="FireBall";
		this.dir=dir;
		p=pl;
		Thread t=new Thread(this,"FireBall");
		t.setPriority(3);
		t.start();
	}
	
	
	@Override
	public void run() {
		try{
		Point p1= new Point((int)(p.getX()),(int)(p.getY()));
		Point p2= new Point((int)(p.getX()+dir.getX()),(int)(p.getY()+dir.getY()));
		map.getMap()[(int)p2.getX()][(int)p2.getY()].hasProy(true,this);
		for(int i=0 ; i<range && active ; i++){
			if(!map.getMap()[(int)p2.getX()][(int)(p2.getY())].isEmpty()){
					if(map.getMap()[(int)p2.getX()][(int)(p2.getY())].hasEnemy())
						map.getMap()[(int)p2.getX()][(int)(p2.getY())].attack(ADdmg, APdmg, map,p);
				active=false;
			}else{
				p1.translate((int)dir.getX(), (int)dir.getY());
				p2.translate((int)dir.getX(), (int)dir.getY());
				map.getMap()[(int)p2.getX()][(int)p2.getY()].hasProy(true,this);
				map.getMap()[(int)p1.getX()][(int)p1.getY()].hasProy(false, null);
			}
			Thread.sleep(speed);
		}
		//map.getMap()[(int)p1.getX()][(int)p1.getY()].hasProy(false, null);
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
		if(range==10 || range==15){
			g.drawImage(img[20],10+x*ESCALA,-5+y*ESCALA,null);
		}else if(range==3){
			g.setColor(new Color(100,50,140));
			g.fillOval(22+x*ESCALA, 7+y*ESCALA, ESCALA/4, ESCALA/4);
		}else if(range==7){
			g.setColor(new Color(66,00,00));
			g.fillOval(22+x*ESCALA, 7+y*ESCALA, ESCALA/4, ESCALA/4);
		}
		
	}

}

package setup;

import java.awt.Point;

import map.Maps;
import players.Player;

public class PlayerAction implements Runnable{
	
	private Maps map;
	private Player p;
	private int key;
	
	public PlayerAction(int key, Player p, Maps map){
		this.map=map;
		this.p=p;
		this.key=key;
		
		Thread t = new Thread(this);
		t.start();
		
	}
	
	@Override
	public void run() {
		if(p.canMove()){
		if(key==p.getNorth()){ p.move(new Point(-1,0), map,p);new StopMovement(p);}
		else if(key==p.getSouth()){p.move(new Point(1,0), map,p);new StopMovement(p);}
		else if(key==p.getEast()){p.move(new Point(0,1), map,p);new StopMovement(p);}
		else if(key==p.getWest()){p.move(new Point(0,-1), map,p);new StopMovement( p);}
		}
		if(key==p.getAtt1()) p.attack1(map);
		else if(key==p.getAtt2()) p.attack2(map);
		else if(key==p.getAtt3()) p.attack3(map);
		else if(key==p.getAtt4()) p.attack4(map);
		else if(key==p.getAtt5()) p.attack5(map);
	}

}

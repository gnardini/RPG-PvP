package abilities;

import java.awt.Graphics;
import java.awt.Image;

import map.Maps;
import players.Player;

public abstract class Proyectile implements Runnable {

	String name;
	Maps map;
	Player p;
	
	public Maps getMap(){
		return map;
	}
	
	public Player getPlayer(){
		return p;
	}
	
	public abstract int getADdmg();
	public abstract int getAPdmg();
	public abstract void targetHit();
	public abstract void paintSelf(Graphics g, int x, int y, Image[] img);
}

package map;

import java.awt.Graphics;
import java.awt.Image;

import bonuses.BonusItem;
import players.LifeObject;
import players.Player;
import abilities.Proyectile;

public abstract class Physics {
	
	protected boolean empty;
	protected boolean proy;
	
	public void setHeal(){
		
	}
	
	public abstract void setBI(BonusItem BI);
	public abstract void snare(int ADdmg, int APdmg, Maps map,Player p, int dur);
	public abstract Player getLo();
	public abstract boolean isEmpty();
	public abstract boolean isEmpty(LifeObject lo);
	public abstract boolean hasEnemy();
	public abstract boolean hasNPC();
	public abstract void openMenu(Player p);
	public abstract void isEmpty(boolean b, LifeObject lo);
	public abstract boolean hasProy();
	public abstract void hasProy(boolean b, Proyectile py);
	public abstract void paintSelf(Graphics g, int x, int y, Image[] img);
	public void attack(int ADdmg,int APdmg, Maps map, Player p){}
	
}

package map;

import bonuses.BonusItem;
import players.LifeObject;
import players.Player;
import setup.myConstants;
import abilities.Proyectile;

public abstract class Terrain extends Physics implements myConstants {
	LifeObject lo;
	Proyectile py;
	BonusItem BI;
	
	public boolean isEmpty(LifeObject lo) {
		return isEmpty();
	}
	
	public boolean isEmpty(){
		return empty;
	}
		
	public void setBI(BonusItem BI){
		this.BI=BI;
	}
	
	public void isEmpty(boolean b, LifeObject lo) {
		empty=b;
		this.lo=lo;
		
		if(BI != null){
			BI.effect((Player)lo);
			BI=null;
		}
		
		if(empty==false && hasProy() ){
			py.targetHit();
			lo.lowerHP(py.getADdmg(), py.getAPdmg(), py.getMap(),py.getPlayer());
		}
	}
	
	public Player getLo() {return (Player)lo;}
	
	@Override
	public boolean hasNPC() {
		return false;
	}
	public void openMenu(Player p) {}
	
	public boolean hasProy(){
		return proy;
	}
	
	public void hasProy(boolean b, Proyectile py){
		proy=b;
		this.py=py;
	}
	
	public boolean hasEnemy(){
		return lo!=null;
	}
	
	public synchronized void attack(int ADdmg, int APdmg, Maps map, Player p){
		if(lo!=null){
			lo.lowerHP(ADdmg, APdmg, map, p);
		}
	}
	
}

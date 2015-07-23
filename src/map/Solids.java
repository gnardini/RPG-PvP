package map;

import players.LifeObject;
import players.Player;
import setup.myConstants;

public abstract class Solids extends Terrain implements myConstants{
	
	public boolean isEmpty(){
		return empty;
	}
	
	public void isEmpty(boolean b, LifeObject lo) {
		empty=b;
		this.lo=lo;
	}

	public void snare(int ADdmg, int APdmg, Maps map, Player p,int dur){}
}

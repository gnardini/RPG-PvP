package abilities;

import java.awt.Point;

import map.Maps;
import players.Player;
import setup.myConstants;

public class RangedStun implements myConstants {

	public RangedStun(Player p,Maps map, int ADdmg, int APdmg, int range, int dur, Point face){
		int x = p.getX() + face.x, y=p.getY()+face.y;
		for(int i = 0 ; i<range && x>=0 && x<DIMX && y>=0 && y<DIMY ; i++){
			if(map.getMap()[x][y].hasEnemy()){
				map.getMap()[x][y].attack(ADdmg, APdmg, map, p);
				new Stun(map.getMap()[x][y].getLo(), dur);
			}
			x+=face.x;
			y+=face.y;
		}
	}
	
}

package map;

import java.awt.Color;
import java.awt.Graphics;

import players.Player;
import setup.myConstants;
import abilities.Snare;



public abstract class Floor extends Terrain implements myConstants {

	public void snare(int ADdmg, int APdmg, Maps map,Player p, int dur){
		if(lo!=null){
			attack(ADdmg, APdmg, map, p);
			new Snare(lo,dur);
		}
	}
	
	public void paintSelf(Graphics g, int x, int y){
		
		if(lo!=null && lo.isStunned()){
			g.setColor(Color.RED);
			g.fillOval(18+x*ESCALA, -11+y*ESCALA, ESCALA/2, 10);
			g.setColor(Color.black);
			g.drawOval(18+x*ESCALA, -11+y*ESCALA, ESCALA/2, 10);
			
		}else if(lo!=null && lo.isSnared()){
			g.setColor(Color.CYAN);
			g.fillRect(7+x*ESCALA, -8+y*ESCALA, ESCALA+6, ESCALA+6);
		}
			
	}
}

package map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class FightGround extends Floor {

	public FightGround(){
		empty=true;
	}
	
	@Override
	public void paintSelf(Graphics g, int x, int y, Image[] img) {
		g.setColor(new Color(240,240,150));
		g.fillRect(10+x*ESCALA, -5+y*ESCALA, ESCALA, ESCALA);
		super.paintSelf(g, x, y);
		if(!isEmpty()){
			lo.paintSelf(g,x,y, img);
		}
		if(hasProy()){
			py.paintSelf(g,x,y,img);
		}
	}

}

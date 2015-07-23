package setup;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import map.Maps;
import players.Player;

public class Info implements myConstants {

	int x,y;
	
	public Info(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public void showPlayerInfo(Graphics g,Player p, Maps map){
		
		g.setColor(Color.WHITE);
		for(int i=0; i<3 ; i++) g.fillRect(x+i*180, y, 150, 15);
		for(int i=1; i<3 ; i++) g.fillRect(x+i*180, y+25, 150, 15);
		
		Point HP = p.getHP();
		Point mana=p.getMana();
		
		if(p.usesMana()){
			g.fillRect(x, y+25, 150, 15);
			g.setColor(Color.BLUE);
			g.fillRect(x,y+25 ,  (int)(mana.getX()/mana.getY()*150) , 15);
		}
		
		g.setColor(Color.RED);
		g.fillRect(x,y ,  (int)(HP.getX()/HP.getY()*150) , 15);
		
		g.setColor(Color.GREEN);
		g.fillRect(x+180,y , (int)((double)(p.getTimeLeft(p.getAtt5name())/p.getCDatt5())*150) , 15);
		g.fillRect(x+180,y+25 , (int)((double)(p.getTimeLeft(p.getAtt2name())/p.getCDatt2())*150) , 15);
		g.fillRect(x+360,y , (int)((double)(p.getTimeLeft(p.getAtt3name())/p.getCDatt3())*150) , 15);
		g.fillRect(x+360,y+25 , (int)((double)(p.getTimeLeft(p.getAtt4name())/p.getCDatt4())*150) , 15);
		g.setColor(Color.RED);
		g.drawString(p.getAtt5name(),x+195  ,y+12 );
		g.drawString(p.getAtt2name(),x+195  ,y+37 );
		g.drawString(p.getAtt3name(),x+375  ,y+12 );
		g.drawString(p.getAtt4name(),x+375  ,y+37 );
		g.setColor(Color.YELLOW);
		g.drawString(HP.x + " / " + HP.y,x+40  ,y+12 );
		if(p.usesMana()) g.drawString(mana.x + " / " + mana.y,x+30  ,y+37 );
		
		
		
	}
	
}

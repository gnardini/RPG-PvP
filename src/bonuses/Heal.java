package bonuses;

import java.awt.Graphics;
import java.awt.Image;

import players.Player;
import setup.myConstants;

public class Heal implements BonusItem, myConstants {

	@Override
	public void effect(Player p) {
		p.heal(13);
		if(p.usesMana()) p.fullMana();
	}

	@Override
	public void paintSelf(Graphics g, int x, int y, Image[] img) {
		g.drawImage(img[21],10+x*ESCALA,-5+y*ESCALA,null);
	}

}

package bonuses;

import java.awt.Graphics;
import java.awt.Image;

import players.Player;
import setup.myConstants;

public class AttackBonus implements BonusItem, myConstants {

	@Override
	public void effect(Player p) {
		new AttackBuff(p);
	}

	@Override
	public void paintSelf(Graphics g, int x, int y, Image[] img) {
		g.drawImage(img[22],10+x*ESCALA,-5+y*ESCALA,null);
	}

}
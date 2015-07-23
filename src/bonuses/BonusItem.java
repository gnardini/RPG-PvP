package bonuses;

import java.awt.Graphics;
import java.awt.Image;

import players.Player;

public interface BonusItem {

	public void effect(Player p);
	
	public void paintSelf(Graphics g, int x, int y, Image[] img);
}

package map;

import java.awt.Graphics;
import java.awt.Image;

import setup.myConstants;

public class Rock extends Solids implements myConstants{
	
	@Override
	public void paintSelf(Graphics g, int x, int y, Image[] img) {
		g.drawImage(img[19],10+x*ESCALA,-5+y*ESCALA,null);
		/*g.setColor(Color.DARK_GRAY);
		g.fillRect(10+x*ESCALA, 10+y*ESCALA, ESCALA, ESCALA);
		*/
	}
	

}

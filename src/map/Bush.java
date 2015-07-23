package map;

import java.awt.Graphics;
import java.awt.Image;

import setup.myConstants;

public class Bush extends Solids implements myConstants {

	@Override
	public void paintSelf(Graphics g, int x, int y, Image[] img) {
		g.drawImage(img[18],10+x*ESCALA,-5+y*ESCALA,null);
		g.drawImage(img[16],10+x*ESCALA,-5+y*ESCALA,null);
		/*g.setColor(Color.green);
		g.fillRect(10+x*ESCALA, 10+y*ESCALA, ESCALA, ESCALA);
		g.setColor(new Color(30,80,35));
		g.fillOval(10+x*ESCALA, 10+y*ESCALA, ESCALA, ESCALA);*/
	}

}

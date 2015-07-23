package map;

import java.awt.Graphics;
import java.awt.Image;

import setup.myConstants;

public class Grass extends Floor implements myConstants {

	public Grass() {
		empty=true;
	}
	
		
	@Override
	public void paintSelf(Graphics g, int x, int y, Image[] img) {
		/*g.setColor(Color.green);
		g.fillRect(10+x*ESCALA, 10+y*ESCALA, ESCALA, ESCALA);*/
		super.paintSelf(g,x,y);
		//if(x%3==0)
		//g.drawImage(img[4],10+x*ESCALA,10+y*ESCALA,null);
		//else if(x%3==1)
		g.drawImage(img[18],10+x*ESCALA,-5+y*ESCALA,null);
		//else if(x%3==2)
		//g.drawImage(img[15],10+x*ESCALA,10+y*ESCALA,null);
		if(!isEmpty()){
			lo.paintSelf(g,x,y, img);
		}
		if(hasProy()){
			py.paintSelf(g,x,y, img);
		}
		
		if(BI!=null) BI.paintSelf(g,x,y, img);
	}
	/*
	public static Image loadImage(String fileName) throws IOException {
		InputStream stream = ClassLoader.getSystemResourceAsStream(fileName);
		if (stream == null) {	
			return ImageIO.read(new File(fileName)); 
		} else {
			return ImageIO.read(stream);
		}
	}*/
}

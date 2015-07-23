package bonuses;

import java.util.Random;

import setup.myConstants;
import map.Maps;

public class RandomSpawns implements Runnable, myConstants{

	Maps map;
	
	public RandomSpawns(Maps map) {
		this.map=map;
		Thread t = new Thread(this);
		t.setPriority(3);
		t.start();
	}
	
	@Override
	public void run() {
		while(true){
			try{Thread.sleep(20000);}catch(InterruptedException e){}
			int x, y;
			Random r = new Random();
			do{
				x=r.nextInt(DIMX);
				y=r.nextInt(DIMY);
			}while(!map.getMap()[x][y].isEmpty());
			int rand=r.nextInt(100);
			if(rand<80) map.getMap()[x][y].setBI(new Heal());
			else map.getMap()[x][y].setBI(new AttackBonus());
		}
	}
}

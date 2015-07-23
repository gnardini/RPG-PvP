package abilities;

import map.Maps;
import players.Player;


public class DrainLife implements Runnable {

	Player p;
	Player rec;
	int AD,AP;
	Maps map;
	
	public DrainLife(int ADdmg, int APdmg, Player p, Player rec, Maps map){
		this.p=p;
		this.rec=rec;
		this.map=map;
		AD=ADdmg;
		AP=APdmg;
		Thread t=new Thread(this,"DrainLife");
		t.start();
	}
	
	@Override
	public void run() {
		try{	
			int contador=0;
			while(contador<5 && rec.isAlive()){
				contador++;
				rec.lowerHP(AD, AP, map,p);
				Thread.sleep(800);
			}
		}catch(InterruptedException e){}
		
	}
}

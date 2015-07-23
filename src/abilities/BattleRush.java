package abilities;

import players.Player;

public class BattleRush implements Runnable{

	private Player p;
	
	public BattleRush(Player p){
		this.p=p;
		Thread t = new Thread(this);
		t.setPriority(2);
		t.start();
	}
	
	@Override
	public void run() {
		int contador=0;
		try{
			while(contador<10){
				contador++;
				Thread.sleep(900);
				p.addCDatt1(100);
			}
		}catch(InterruptedException e){}
		
	}
}

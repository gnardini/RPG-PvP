package setup;

import players.Player;

public class StopMovement implements Runnable {

	Player p;
	
	public StopMovement(Player p){
		this.p=p;
		Thread t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run() {
		p.canMove(false);
		try{Thread.sleep(50);}catch(InterruptedException e ){}
		p.canMove(true);
	}
}

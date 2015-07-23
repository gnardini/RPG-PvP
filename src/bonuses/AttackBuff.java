package bonuses;

import players.Player;

public class AttackBuff implements Runnable {

	private Player p;
	
	public AttackBuff(Player p){
		this.p=p;
		Thread t = new Thread(this);
		t.setPriority(2);
		t.start();
	}
	
	@Override
	public void run() {
		p.setAD(p.getAD()+15);
		p.setAP(p.getAP()+15);
		try{Thread.sleep(8000);}catch(InterruptedException e){}
		p.setAD(p.getAD()-15);
		p.setAP(p.getAP()-15);
	}
}

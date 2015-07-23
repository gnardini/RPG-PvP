package abilities;

import players.Player;

public class DefenseDebuff implements Runnable{

	Player p;
	
	public DefenseDebuff(Player p){
		this.p=p;
		Thread t = new Thread(this);
		t.setPriority(1);
		t.start();
	}
	
	@Override
	public void run() {
		p.setArmor(p.getArmor()-5);
		p.setMR(p.getMR()-5);
		try{Thread.sleep(5000);}catch(InterruptedException e){}
		p.setArmor(p.getArmor()+5);
		p.setMR(p.getMR()+5);
	}
}

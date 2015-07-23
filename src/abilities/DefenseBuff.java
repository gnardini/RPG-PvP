package abilities;

import players.Player;

public class DefenseBuff implements Runnable{

	Player p;
	
	public DefenseBuff(Player p){
		this.p=p;
		Thread t = new Thread(this);
		t.setPriority(1);
		t.start();
	}
	
	@Override
	public void run() {
		p.setArmor(p.getArmor()+20);
		p.setMR(p.getMR()+20);
		p.setAD(p.getAD()-10);
		try{Thread.sleep(9000);}catch(InterruptedException e){}
		p.setAD(p.getAD()+10);
		p.setArmor(p.getArmor()-20);
		p.setMR(p.getMR()-20);
	}
}

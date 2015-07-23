package abilities;

import players.LifeObject;

public class Stun implements Runnable{
	LifeObject lo;
	int dur;
	
	public Stun(LifeObject lo, int dur){
		this.lo=lo;
		this.dur=dur;
		Thread t=new Thread(this,"Stun");
		t.setPriority(2);
		t.start();
	}
	@Override
	public void run() {
		lo.stunned(true);
		lo.snared(true);
		try{Thread.sleep(dur);}catch(InterruptedException e){}
		lo.stunned(false);
		lo.snared(false);
	}
}
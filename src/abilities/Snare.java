package abilities;

import players.LifeObject;

public class Snare implements Runnable{
	LifeObject lo;
	int dur;
	
	public Snare(LifeObject lo, int dur){
		this.lo=lo;
		this.dur=dur;
		Thread t=new Thread(this,"Snare");
		t.setPriority(2);
		t.start();
	}
	@Override
	public void run() {
		lo.snared(true);
		try{Thread.sleep(dur);}catch(InterruptedException e){}
		lo.snared(false);
	}
}

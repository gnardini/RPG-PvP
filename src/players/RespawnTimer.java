package players;

import map.Maps;

public class RespawnTimer implements Runnable {

	private Player p;
	private Maps map;
	
	public RespawnTimer(Player p, Maps map) {
		this.p=p;
		this.map=map;
		Thread t = new Thread(this);
		t.setPriority(2);
		t.start();
	}
	
	@Override
	public void run() {
		try{Thread.sleep(5000);}catch(InterruptedException e){}
		map.getMap()[(int)p.pos.getX()][(int)p.pos.getY()].isEmpty(false, p);
		p.alive=true;
	}
}

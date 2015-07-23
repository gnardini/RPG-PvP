package map;

import players.Player;
import setup.myConstants;


public class Maps implements myConstants {
	
	private int respawnX, respawnY;
	private Physics[][] map;
	private String name;
	
	public Maps(String name, Player[] p){
		this.name=name;
		map= new Physics[DIMX][DIMY];
		MapReader.readMap(name, this);
		if(name.equals("PVPmap")) new Missiles(this,p);
	}
	
	public Physics[][] getMap(){
		return map;
	}
	
	public void setMap(String name){
		this.name=name;
		MapReader.readMap(name, this);
	}
	
	public void setRespawnX(int x){
		respawnX=x;
	}
	
	public void setRespawnY(int y){
		respawnY=y;
	}
	
	public int getRespawnLocationX(){
		return respawnX;
	}
	
	public int getRespawnLocationY(){
		return respawnY;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

package players;

import java.awt.Point;
import java.util.HashMap;

import map.Maps;
import abilities.FireBall;



public class Mage extends Player {

	private static final long serialVersionUID = 1L;

	public Mage() {
		super(80);
		cooldowns = new HashMap<>();
		AD=5;
		AP=50;
		armor=0;
		MR=0;
		CDatt1=1000;
		CDatt2=6000;
		CDatt3=9000;
		CDatt4=7500;
		CDatt5=10000;
		mana=new Point(200,200);
		new ManaRegen(this, 10);
		cooldowns.put("AA", (long)0);
		cooldowns.put("FireBall", (long)0);
		cooldowns.put("Blink", (long)0);
		cooldowns.put("Arcane Nova", (long)0);
		cooldowns.put("Freeze", (long)0);
	}
	
	public void attack1(Maps map){
		if(isStunned()) return;
		int dmg = AD;
		if(!isOnCooldown("AA")){
			setCooldown("AA", CDatt1);
			map.getMap()[(int)(pos.getX()+face.getX())][(int)(pos.getY()+face.getY())].attack(dmg, 0, map, this);
		}
	}
	
	public void attack2(Maps map){
		if(isStunned()) return;
		int dmg=(int)((AP)*0.78);
		if(!isOnCooldown("FireBall")){
			if(mana.getX()>=25){
				mana.translate(-25, 0);
				setCooldown("FireBall", (long)(CDatt2));
				new FireBall(map,0, dmg, face, this,10,50);
			}
		}
	}
	
	public void attack3(Maps map){
		if(isStunned()) return;
		if(!isOnCooldown("Blink")){
			if(mana.getX()>=10){
				mana.translate(-10, 0);
				setCooldown("Blink", (long)(CDatt3));
				blink(map);
			}
		}
	}
	
	public void attack4(Maps map){
		if(isStunned()) return;
		int dmg=(int)((AP)*0.58);
		if(!isOnCooldown("Arcane Nova")){
			if(mana.getX()>=25){
				mana.translate(-25, 0);
				setCooldown("Arcane Nova", (long)(CDatt4));
				for(int i=-1; i<2 ; i++)
					for(int j=-1; j<2 ; j++)
						if(i!=0 || j!=0)
							new FireBall(map,0,dmg,new Point(i,j),this,3,50);
			}
		}
	}
	
	public void attack5(Maps map) {
		if(isStunned()) return;
		int dmg=(int)((AP)*0.28);
		if(!isOnCooldown("Freeze")){
			if(mana.getX()>=20){
				mana.translate(-20, 0);
				setCooldown("Freeze", (long)(CDatt5));
				map.getMap()[(int)(pos.getX()+face.getX())][(int)(pos.getY()+face.getY())].attack(dmg, 0, map, this);
				int x = (int)pos.getX(), y=(int)pos.getY();
				for(int i=-2 ; i<=2 ; i++)
					for(int j=-2 ; j<=2 ; j++)
						if((i!=0 || j!=0) && x+i>=0 && x+i<DIMX && y+j>=0 && y+j<DIMY)
							map.getMap()[x+i][y+j].snare(0,dmg,map,this,2200);
			}
		}
	}
	
	
	public String getAtt1name(){
		return "AA";
	}
	
	public String getAtt2name(){
		return "FireBall";
	}
	
	public String getAtt3name(){
		return "Blink";
	}
	
	public String getAtt4name(){
		return "Arcane Nova";
	}
	
	public String getAtt5name(){
		return "Freeze";
	}
	
	public double getCDatt1() {
		return (double)(CDatt1)/1000;
	}

	public double getCDatt2() {
		return (double)(CDatt2)/1000;
	}

	public double getCDatt3() {
		return (double)(CDatt3)/1000;
	}
	
	public double getCDatt4() {
		return (double)(CDatt4)/1000;
	}
	
	public double getCDatt5() {
		return (double)(CDatt5)/1000;
	}
	
	@Override
	public boolean usesMana() {
		return true;
	}
	
	public String getClassName(){
		return "Mage";
	}
	
	public boolean isOnCooldown(String name){
		return getTimeLeft(name)!=0;		
	}
	
	public void setCooldown(String name, long time){
		long l= System.currentTimeMillis() + time;
		cooldowns.put(name, l);
	}
	
	public double getTimeLeft(String name){
		long l = cooldowns.get(name);
		if(System.currentTimeMillis()>l) return 0;
		else return /*roundTwoDecimals(*/((double)(l-System.currentTimeMillis())/1000);
	}
	
}

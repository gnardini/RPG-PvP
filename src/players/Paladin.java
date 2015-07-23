package players;

import java.awt.Point;
import java.util.HashMap;

import map.Maps;
import abilities.DefenseDebuff;
import abilities.RangedStun;

public class Paladin extends Player {

	private static final long serialVersionUID = 1L;

	public Paladin() {
		super(80);
		cooldowns = new HashMap<>();
		AD=30;
		AP=30;
		armor=0;
		MR=0;
		CDatt1=1200;
		CDatt2=9000;
		CDatt3=8000;
		CDatt4=7500;
		CDatt5=18000;
		mana=new Point(100,100);
		cooldowns.put("AA", (long)0);
		cooldowns.put("Rejuvenation", (long)0);
		cooldowns.put("Judgement", (long)0);
		cooldowns.put("Holy Nova", (long)0);
		cooldowns.put("Desecration", (long)0);
	}
	
	public void attack1(Maps map){
		if(isStunned()) return;
		int dmg = (int)((AD)*0.32);
		if(!isOnCooldown("AA")){
			setCooldown("AA", CDatt1);
			map.getMap()[(int)(pos.getX()+face.getX())][(int)(pos.getY()+face.getY())].attack(dmg, 0, map, this);
		}
	}
	
	public void attack2(Maps map){
		if(!isOnCooldown("Judgement")){
			setCooldown("Judgement", (long)CDatt2);
			int ADdmg=(int)(AD*0.3);
			int APdmg=(int)(AP*0.3);
			new RangedStun(this,map,ADdmg,APdmg,8,2000, face);
		}
	}
	
	public void attack3(Maps map){
		if(!isOnCooldown("Rejuvenation")){
			setCooldown("Rejuvenation", (long)CDatt3);
			new HealthRegen(this, 3, 10);
		}
	}
	
	public void attack4(Maps map){
		int ADdmg=(int)(AD*0.3);
		int APdmg=(int)(AP*0.42);
		if(!isOnCooldown("Holy Nova")){
			setCooldown("Holy Nova", (long)CDatt4);
			int x = (int)pos.getX(), y=(int)pos.getY();
			for(int i=-3 ; i<=3 ; i++)
				for(int j=-3 ; j<=3 ; j++)
					if((i!=0 || j!=0) && x+i>=0 && x+i<DIMX && y+j>=0 && y+j<DIMY)
						map.getMap()[x+i][y+j].attack(ADdmg, APdmg, map, this);
		}
	}
	
	public void attack5(Maps map){
		int ADdmg=(int)(AD*0.2);
		int APdmg=(int)(AP*0.2);
		if(!isOnCooldown("Desecration")){
			setCooldown("Desecration", (long)CDatt5);
			int x = pos.x, y=pos.y;
			for(int i=-4 ; i<=4 ; i++)
				for(int j=-4 ; j<=4 ; j++)
					if((i!=0 || j!=0) && x+i>=0 && x+i<DIMX && y+j>=0 && y+j<DIMY && map.getMap()[x+i][y+j].hasEnemy()){
						new DefenseDebuff(map.getMap()[x+i][y+j].getLo());
						map.getMap()[x+i][y+j].attack(ADdmg,APdmg,map,this);
					}
			
		}
	}
	
	public String getAtt1name(){
		return "AA";
	}
	
	public String getAtt2name(){
		return "Judgement";
	}
	
	public String getAtt3name(){
		return "Rejuvenation";
	}
	
	public String getAtt4name(){
		return "Holy Nova";
	}
	
	public String getAtt5name(){
		return "Desecration";
	}
	
	public double getCDatt1() {
		return (double)CDatt1/1000;
	}

	public double getCDatt2() {
		return (double)CDatt2/1000;
	}

	public double getCDatt3() {
		return (double)CDatt3/1000;
	}
	
	public double getCDatt4() {
		return (double)CDatt4/1000;
	}
	
	public double getCDatt5() {
		return (double)CDatt5/1000;
	}
	
	@Override
	public boolean usesMana() {
		return false;
	}
	
	public String getClassName(){
		return "Paladin";
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

package players;

import java.awt.Point;
import java.util.HashMap;

import map.Maps;
import abilities.BattleRush;
import abilities.ExplosiveArrow;
import abilities.FireBall;
import abilities.PoisonousArrow;

public class Archer extends Player {

	private static final long serialVersionUID = 1L;

	public Archer() {
		super(80);
		cooldowns = new HashMap<>();
		AD=27;
		AP=5;
		armor=0;
		MR=0;
		CDatt1=1500;
		CDatt2=20000;
		CDatt3=8000;
		CDatt4=14000;
		CDatt5=11000;
		mana=new Point(100,100);
		cooldowns.put("AA", (long)0);
		cooldowns.put("Poisonous Arrow", (long)0);
		cooldowns.put("Battle Rush", (long)0);
		cooldowns.put("Escape", (long)0);
		cooldowns.put("Explosive Arrow", (long)0);
	}
	
	public void attack1(Maps map){
		if(isStunned()) return;
		int dmg = (int)(AD*0.4);
		if(!isOnCooldown("AA")){
			setCooldown("AA", CDatt1);
			new FireBall(map,dmg, 0, face, this,7,20);
			
		}
	}
	
	public void attack2(Maps map){
		if(isStunned()) return;
		if(!isOnCooldown("Battle Rush")){
			setCooldown("Battle Rush", (long)CDatt2);
			CDatt1-=1000;
			new BattleRush(this);
		}
	}
	
	public void attack3(Maps map){
		if(isStunned()) return;
		if(!isOnCooldown("Escape")){
			setCooldown("Escape", (long)CDatt3);
			face = new Point(-face.x,-face.y);
			blink(map);
		face= new Point(-face.x, -face.y);
		}
	}
	
	public void attack4(Maps map){
		if(isStunned()) return;
		int dmg=(int)(AD*0.8);
		if(!isOnCooldown("Explosive Arrow")){
			setCooldown("Explosive Arrow", (long)CDatt4);
			new ExplosiveArrow(map,dmg,0,face,this,8,60);
		}
	}
	
	public void attack5(Maps map){
		if(isStunned()) return;
		int dmg=(int)(AD*0.75);
		if(!isOnCooldown("Poisonous Arrow")){
			setCooldown("Poisonous Arrow", (long)CDatt5);
			new PoisonousArrow(map,dmg,0,face,this,8,60);
		}
	}
	
	public String getAtt1name(){
		return "AA";
	}
	
	public String getAtt2name(){
		return "Battle Rush";
	}
	
	public String getAtt3name(){
		return "Escape";
	}
	
	public String getAtt4name(){
		return "Explosive Arrow";
	}
	
	public String getAtt5name(){
		return "Poisonous Arrow";
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
		return "Archer";
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

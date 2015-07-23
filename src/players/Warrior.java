package players;

import java.awt.Point;
import java.util.HashMap;

import map.Maps;
import abilities.ConstantProyectile;
import abilities.DefenseBuff;


public class Warrior extends Player {

	private static final long serialVersionUID = 1L;

	public Warrior() {
		super(80);
		cooldowns = new HashMap<>();
		AD=40;
		AP=5;
		armor=0;
		MR=0;
		CDatt1=1000;
		CDatt5=10000;
		CDatt3=9000;
		CDatt4=7500;
		CDatt2=20000;
		cooldowns.put("AA", (long)0);
		cooldowns.put("Sword Thrust", (long)0);
		cooldowns.put("Charge", (long)0);
		cooldowns.put("Quick Strikes", (long)0);
		cooldowns.put("Impenetrable Skin", (long)0);
	}
	
	public void attack1(Maps map){
		if(isStunned()) return;
		int dmg = (int)((AD)*0.25);
		if(!isOnCooldown("AA")){
			setCooldown("AA", CDatt1);
			map.getMap()[(int)(pos.getX()+face.getX())][(int)(pos.getY()+face.getY())].attack(dmg, 0, map, this);
		}
	}
	
	public void attack5(Maps map){
		if(isStunned()) return;
		int dmg=(int)((AD)*.9);
		if(!isOnCooldown("Sword Thrust")){
			setCooldown("Sword Thrust", (long)(CDatt5));
			new ConstantProyectile(map,dmg, 0, face, this,2,20,100);
		}
	}
	
	public void attack3(Maps map){
		
		int dmg= (int)((AD)*0.2);
		if(!isOnCooldown("Charge")){
			setCooldown("Charge", (long)(CDatt3));
			for(int i=0 ; i<5 ; i++)
				move(face,map,this);
			map.getMap()[(int)(pos.getX()+face.getX())][(int)(pos.getY()+face.getY())].attack(dmg, 0, map, this);
			for(int i=-1 ; i<=1 ; i++)
				for(int j=-1 ; j<=1 ; j++)
					if(i!=0 || j!=0)
						map.getMap()[(int)(pos.getX()+i)][(int)(pos.getY()+j)].snare(dmg,0,map,this,1500);
		}
	}
	
	public void attack4(Maps map){
		if(isStunned()) return;
		int dmg=(int)((AD)*0.7);
		if(!isOnCooldown("Quick Strikes")){
			setCooldown("Quick Strikes", (long)(CDatt4));
			quickStrikes(map, dmg);
		}
	}
	
	public void attack2(Maps map){
		if(isStunned()) return;
		if(!isOnCooldown("Impenetrable Skin")){
			setCooldown("Impenetrable Skin", (long)(CDatt2));
			new DefenseBuff(this);
			
			/*for(int i=-1 ; i<2 ; i++){
				if(face.x!=0){
					if(map.getMap()[(int)(pos.getX()+face.getX())][(int)(pos.getY()+face.getY()+i)].hasEnemy()){
						new Stun(map.getMap()[(int)(pos.getX()+face.getX())][(int)(pos.getY()+face.getY()+i)].getLo(),1500);
						map.getMap()[(int)(pos.getX()+face.getX())][(int)(pos.getY()+face.getY()+i)].attack(dmg, 0, map, this);
					}
				}else{
					if(map.getMap()[(int)(pos.getX()+face.getX()+i)][(int)(pos.getY()+face.getY())].hasEnemy()){
						new Stun(map.getMap()[(int)(pos.getX()+face.getX()+i)][(int)(pos.getY()+face.getY())].getLo(),1500);
						map.getMap()[(int)(pos.getX()+face.getX()+i)][(int)(pos.getY()+face.getY())].attack(dmg, 0, map, this);
					}
				}
			}*/
		}
	}
	
	public void quickStrikes(Maps map, int dmg){
		int x=(int)face.getX(), y=(int)face.getY();
		if(x==0){
			for(int i=-1; i<2 ; i++)
				new ConstantProyectile(map,dmg,0,new Point(i,y),this,2,100,50);
		}else 
			for(int i=-1; i<2 ; i++)
				new ConstantProyectile(map,dmg,0,new Point(x,i),this,2,100,50);
		
	}
	
	public String getAtt1name(){
		return "AA";
	}
	
	public String getAtt5name(){
		return "Sword Thrust";
	}
	
	public String getAtt3name(){
		return "Charge";
	}
	
	public String getAtt4name(){
		return "Quick Strikes";
	}
	
	public String getAtt2name(){
		return "Impenetrable Skin";
	}
	
	public double getCDatt1() {
		return (double)(CDatt1)/1000;
	}

	public double getCDatt5() {
		return (double)CDatt5/1000;
	}

	public double getCDatt3() {
		return (double)(CDatt3)/1000;
	}
	
	public double getCDatt4() {
		return (double)(CDatt4)/1000;
	}
	
	public double getCDatt2() {
		return (double)(CDatt2)/1000;
	}
	
	@Override
	public boolean usesMana() {
		return false;
	}
	
	public String getClassName(){
		return "Warrior";
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

package map;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import setup.myConstants;

public abstract class MapReader  implements myConstants{
	
	//private static int respawnX, respawnY, teleports;
	
	public static void readMap(String name, Maps map){
		name ="maps/" + name + ".txt";
		File fmap= new File(name);
		BufferedInputStream input=null;
		if (fmap.exists()) { 
			try{
				input = new BufferedInputStream(new FileInputStream(fmap));
				/*respawnX=(input.read()-'0')*10+input.read()-'0';
				respawnY=(input.read()-'0')*10+input.read()-'0';
				teleports=(input.read()-'0')*10+input.read()-'0';
				int[] coorx= new int[teleports];
				int[] coory= new int[teleports];
				StringBuffer[] telname = new StringBuffer[teleports];
				for(int i=0 ; i < teleports ; i++){
					coorx[i]=(input.read()-'0')*10+input.read()-'0';
					coory[i]=(input.read()-'0')*10+input.read()-'0';
					telname[i]=new StringBuffer();
				}
				input.read();input.read();
				map.setRespawnX(respawnX);
				map.setRespawnY(respawnY);
				char c;
				for(int i=0 ; i<teleports ; i++){
					while((c=(char)input.read())!='-'){
						telname[i].append(c);
					}
					input.read();input.read();
				}*/
				//int k=0;
				@SuppressWarnings("unused")
				char c;
				for(int i=0 ; i<DIMX ; i++){
					for(int j=0 ; j<DIMY ; j++){
						switch(c=(char)input.read()){
							case 'R': map.getMap()[i][j]= new Rock(); break;
							case ' ': map.getMap()[i][j]= new Grass(); break;
							case 'B': map.getMap()[i][j]= new Bush(); break;
							case '|': map.getMap()[i][j]= new Wall(); break;
							case '_': map.getMap()[i][j]= new Wall(); break;
							case 'Y': map.getMap()[i][j]= new FightGround(); break;
							//case 'T': map.getMap()[i][j]= new Teleport(map,telname[k].toString(),coorx[k],coory[k++]); break;
						}
					}
					input.read();
					input.read();	
			}
				input.close();
			}catch(IOException e){System.out.println("ERROR IO");}
			
		}else { 
			 System.out.println("ERROR");
		}
		
		
	}
	
	
}
	

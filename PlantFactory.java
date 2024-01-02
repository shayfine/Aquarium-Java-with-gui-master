/**
 * planet factory class return the specific object by getting message (string)
 * @author Adir Zoari 203002753
 * @author Idan Levi 305562431
 */

import java.awt.Color;

public class PlantFactory implements AbstractSeaFactory {

	int size,x,y;
	AquaPanel panel;
	Color col;
	public PlantFactory(AquaPanel panel,int size,int x,int y){
		this.panel=panel;
		this.size=size;
		this.x=x;
		this.y=x;
		this.col=Color.green;
		}
	public SeaCreature produceSeaCreature(String type){
		if(type.equalsIgnoreCase("Laminaria"))
			return new Laminaria(panel,size,x,y);
		
		else if(type.equalsIgnoreCase("Zostera")){
			return new Zostera(panel,size,x,y); 
		}
		return null;

	}

}

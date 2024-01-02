/**
 * Immobile is abstract class and zostera and laminaria inhriated from her
 * @author Adir Zoari 203002753
 * @author Idan Levi 305562431
 */

import java.awt.Color;

public abstract class Immobile implements SeaCreature {
	private String name;
	private static int counter=0;
	public final int objectID;
	protected int size,x,y;
	protected Color colorr;
	protected AquaPanel panel;
	public Immobile(AquaPanel panel,int size,String name,int x,int y){
		this.objectID=++counter;
		this.name=name;
		this.panel=panel;
		this.size=size;
		this.x=x;
		this.y=y;
		this.colorr=Color.green;
	}
	public int getID(){return objectID;}
	public String getPlanetName(){return name;}
	public String getColor(){return "green";}
	public int getSize(){return this.size;}
	public int getXfront(){return this.x;}
	public int getYfront(){return this.y;}
	public Color getColorPlanet(){return this.colorr;}
	public  void setState(Color colorr,int size,int x,int y)
	{
		this.colorr=colorr;
		this.size=size;
		this.x=x;
		this.y=y;
	
	}
	
	

}

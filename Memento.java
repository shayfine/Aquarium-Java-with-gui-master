/**
 * Memento class is class for save the current details of the object with his directly place
 * @author Adir Zoari 203002753
 * @author Idan Levi 305562431
 */

import java.awt.Color;

public class Memento {
	private int idObj,size,x_front,y_front,horSpeed,verSpeed;
	private double x_dir,y_dir;
	private Color col;
	private String ColorAnimal,animalType;
	private Swimmable swimObj=null;
	public Memento(Swimmable swimObj)
	{
		this.swimObj=swimObj;
		this.idObj=swimObj.getID();
		this.col=swimObj.getColorAnimal();
		this.size=swimObj.getSize();
		this.x_front=swimObj.getXfront();
		this.y_front=swimObj.getYfront();
		this.horSpeed=swimObj.getHorSpeed();
		this.verSpeed=swimObj.getHorSpeed();
		this.animalType=swimObj.getAnimalName();
		this.x_dir=swimObj.getX_dir();
		this.y_dir=swimObj.getX_dir();
	}
	public Memento(Immobile immObj){
		this.idObj=immObj.getID();
		this.col=immObj.getColorPlanet();
		this.size=immObj.getSize();
		this.x_front=immObj.getXfront();
		this.y_front=immObj.getYfront();
		this.animalType=immObj.getPlanetName();
	}
	/*
	public Memento(int idObj,Color col,int size,int x_front,int y_front,int horSpeed,int verSpeed,String animalType,double x_dir,double y_dir)
	{
		this.idObj=idObj;
		this.col=col;
		this.size=size;
		this.x_front=x_front;
		this.y_front=y_front;
		this.horSpeed=horSpeed;
		this.verSpeed=verSpeed;
		this.animalType=animalType;
		this.x_dir=x_dir;
		this.y_dir=y_dir;

	}
	public Memento(int idObj,Color col,int size,int x_front,int y_front,String animalType)
	{
		this.idObj=idObj;
		this.col=col;
		this.size=size;
		this.x_front=x_front;
		this.y_front=y_front;
		this.animalType=animalType;
	}
	*/
	public Color getCol(){return col;}
	public String getColor() {
		//String colorAnimal=null;
		if (col== Color.BLUE)
			ColorAnimal="blue";
		if (col==Color.RED)
			ColorAnimal="red";
		if (col== Color.GREEN)
			ColorAnimal="green";
		if (col== Color.BLACK)
			ColorAnimal="black";
		if (col==Color.yellow)
			ColorAnimal="yellow";
		if(col==Color.pink)
			ColorAnimal="pink";
		return ColorAnimal;
	} 
	public int getID(){return idObj;}
	public int getSize(){return size;}
	public int getXfront(){return x_front;}
	public int getYfront(){return y_front;}
	public int getHorSpeed(){return horSpeed;}
	public int getVerSpeed(){return verSpeed;}
	public String getAnimalName(){return animalType;}
	public double getX_dir(){return x_dir;}
	public double getY_dir(){return y_dir;}
	public String getHungerStatus(){return swimObj.getHungerState();}
	
	
	


}

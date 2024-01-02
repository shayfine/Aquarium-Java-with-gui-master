/**
 * swimmable is abstract class and inheriate from thread
 */
import java.awt.*;
import java.util.Observable;
import java.util.concurrent.CyclicBarrier;

public abstract class Swimmable extends Observable implements Runnable ,SeaCreature,Cloneable {
	private static int counter=0;
	public final int objectID;
	protected int horSpeed,verSpeed,foodFreq,freqCounter=0;
	
	
	public Swimmable() {
		this.objectID=++counter;
		horSpeed = 0;
		verSpeed = 0;
	}
	public Swimmable(int hor, int ver,int foodFreq) { 
		this.objectID=++counter;
		this.horSpeed = hor; 
		this.verSpeed = ver; 
		this.foodFreq=foodFreq;
	}
	
	abstract public String getHungerState();
	abstract public void resetSwimmable();
	abstract public void setHungeryState(HungerState state);
	public int getHorSpeed() { return horSpeed; }
	public int getVerSpeed() { return verSpeed; }
	public int getID(){return objectID;}
	abstract public int getXfront();
	abstract public int getYfront();
	public void setHorSpeed(int hor) { horSpeed = hor; } 
	public void setVerSpeed(int ver) { verSpeed = ver; } 
	abstract public void run();
	abstract public String getAnimalName(); 
	abstract public void setSuspend(); 
	abstract public void setResume(); 
	abstract public void setBarrier(CyclicBarrier b); 
	abstract public int getSize(); 
	abstract public void eatInc(); 
	abstract public int getEatCount(); 
	abstract public double getX_dir();
	abstract public double getY_dir();
	abstract public String getColor();
	abstract public Color getColorAnimal();
	abstract public boolean getSuspend();
	abstract public void setState(Color col,int size,int x_front,int y_front,int horSpeed,int verSpeed,double x_dir,double y_dir);
	abstract public void editSwimmable(int size,int horSpeed,int verSpeed,Color col);
	abstract public Swimmable clone();
	abstract public HungerState getState();
;

	
	
	
	
	
	
}

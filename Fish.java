
/**
 * Fish class inherite from swimmable and implements all the methods
 * @author Adir Zoari 203002753
 * @author Idan Levi 305562431
 */
import java.awt.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import javax.swing.JOptionPane;

import java.lang.Math;

public class Fish extends Swimmable implements MarineAnimal {

	private int x_front,y_front,size,eatCounter;
	private double newHorSpeed,newVerSpeed,x_dir,y_dir;
	private Color col;
	private AquaPanel panel;
	boolean isSuspended=false,resetFlag=false,flagFreq=false,flagCounter=false;
	private CyclicBarrier barrier=null;
	private String ColorAnimal=null;
	private HungerState currentState;
	
	
	
	
	public Fish(AquaPanel panel,int size,int horSpeed,int verSpeed,Color col,int foodFreq)
	{
		super(horSpeed,verSpeed,foodFreq);
		this.panel=panel;
		this.size=size;
		this.col=col;
		this.x_front=0; // begin the object in the leftest corner
		this.y_front=0;
		this.x_dir=1;
		this.y_dir=1;
		this.eatCounter=0;
		currentState=new Satiated();
				
	}
	
	/** @return the name of the class 'Fish' **/
	@Override
	public String getAnimalName() {
		return getClass().getName();
	}

	@Override
	public void drawCreatrue(Graphics g) {
		g.setColor(col); 
		if(x_dir==1) // fish swims to right side 
			{ // Body of fish 
			g.fillOval(x_front - size, y_front - size/4, size, size/2); // Tail of fish 
			int[] x_t={x_front-size-size/4,x_front-size-size/4,x_front-size}; 
			int [] y_t = {y_front - size/4, y_front + size/4, y_front}; 
			Polygon t = new Polygon(x_t,y_t,3); g.fillPolygon(t);
			Graphics2D g2 = (Graphics2D) g; 
			g2.setColor(new Color(255-col.getRed(),255-col.getGreen(),255- col.getBlue())); 
			g2.fillOval(x_front-size/5, y_front-size/10, size/10, size/10);
			// Mouth of fish 
			if(size>70) g2.setStroke(new BasicStroke(3)); 
			else if(size>30) g2.setStroke(new BasicStroke(2)); 
			else g2.setStroke(new BasicStroke(1)); 
			g2.drawLine(x_front, y_front, x_front-size/10, y_front+size/10); 
			g2.setStroke(new BasicStroke(1));
			}
		else // fish swims to left side 
			{ // Body of fish 
			g.fillOval(x_front, y_front - size/4, size, size/2); // Tail of fish 
			int[] x_t={x_front+size+size/4,x_front+size+size/4,x_front+size}; 
			int [] y_t = {y_front - size/4, y_front + size/4, y_front}; 
			Polygon t = new Polygon(x_t,y_t,3); 
			g.fillPolygon(t);
			// Eye of fish 
			Graphics2D g2 = (Graphics2D) g; 
			g2.setColor(new Color(255-col.getRed(),255-col.getGreen(),255-col.getBlue())); 
			g2.fillOval(x_front+size/10, y_front-size/10, size/10, size/10); 
			// Mouth of fish 
			if(size>70) g2.setStroke(new BasicStroke(3)); 
			else if(size>30) g2.setStroke(new BasicStroke(2));
			else g2.setStroke(new BasicStroke(1)); 
			g2.drawLine(x_front, y_front, x_front+size/10, y_front+size/10); 
			g2.setStroke(new BasicStroke(1));
			}
		
	}

	/**set the flag to true and it cause to suspend the object **/
	@Override
	public void setSuspend() {
		flagCounter=true;

		isSuspended=true;
	}
	public boolean getSuspend(){return isSuspended;}

	/**set the flag to false and cancel the suspend by notify the object **/
	@Override
	public void setResume() {
		synchronized(this){
		flagCounter=false;
		isSuspended=false;
		notify();
		}
		
	}

	/**
	 * @return the size of the fish
	 */
	@Override
	public int getSize() {
		return size;
	}

	/**increament the counter of the object after ate the worm**/
	@Override
	public void eatInc() {
		eatCounter++;
	}

	/**return the counter**/
	@Override
	public int getEatCount() {
		return eatCounter;
	}

	/** @return string of the fish's color **/
	@Override
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
	
	/**set the barrier that sent by AquaPanel class **/
	@Override
	public void setBarrier(CyclicBarrier barrier) {
		this.barrier=barrier;
		
	}
	
	public void resetSwimmable()
	{
		resetFlag=true;
        Thread.currentThread().interrupt();

	}
	@Override
	public void run() {
		while (!resetFlag){
			try {
				Thread.sleep(100); // sleep the thread for 1 sec
				//if(!flagCounter)
					//freqCounter++;
				if(isSuspended){ // if the user press on sleep button
					if(panel.getFlagWorm()==true){
						if(barrier!=null){
							barrier.await();
						}
						synchronized(this){
							wait();
						}
				}
				}
				
				else if(isSuspended==false){
					if(currentState instanceof Hungry){ 
						if(panel.getWormInstance()!=null)
						{
							TryEatWorm();
						}
						else
							IncreamentWithoutWorm();

					}
					else //in state of Satiated
					{
						if(!flagCounter)
							freqCounter++;
						
						if(freqCounter!=0 && freqCounter%foodFreq==0){	
							flagCounter=true;
							currentState=new Hungry();
							currentState.doAction(this);
							setChanged();
							notifyObservers(objectID);
							
						}	
						IncreamentWithoutWorm();			
					}
				}
					
				}catch (InterruptedException e) {
				// TODO Auto-generated catch block
					System.out.println("reset");
					return;
				} catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			panel.repaint();
		}
	}

	/** the swimmable try to eat the worm by changing the speed\direction to the worm **/
	public void TryEatWorm()
	{

		if(barrier!=null){
			try{
				System.out.println(Thread.currentThread().getName() + getColor()+ " waiting for others to complete....");
				barrier.await();
				
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			catch(BrokenBarrierException e)
			{
				e.printStackTrace();
			}
			
		}
		
		//System.out.println(Thread.currentThread().getName() + " go to eat");

		barrier=null;
		
		System.out.println("x1: "+ x_front + "   y1: "+ y_front);
		calculateNewVelocity(); //calculate new velocity
		if(x_front > panel.getWidth()/2)
		   x_dir = -1;
		else
		   x_dir=1;
		if(y_front > panel.getHeight()/2)
		   y_dir = -1;
		else
		   y_dir=1;
		this.x_front+=newHorSpeed*x_dir;
		this.y_front+=newVerSpeed*y_dir;
		
		synchronized(this){
		//System.out.println("x_front:"+x_front+" y_front:"+y_front+" panel w:"+panel.getWidth()/2+" :panel h"+panel.getHeight()/2);
		if((Math.abs(panel.getWidth()/2-x_front)<=5) && (Math.abs(panel.getHeight()/2-y_front)<=5)){ // if the object is close to the worm minimum 5 pixels
			panel.eatFood(this); // update the counter of object and total counter
			panel.setWormInstance(); // set the wormInstance to null
			currentState=new Satiated();
			currentState.doAction(this);
			panel.repaint(); //to remove the worm
			flagCounter=false;
		}
		}
	
	}
	

	/**change velocity of the object in case the worm not shown in the screen**/
	public void IncreamentWithoutWorm()
	{
		//System.out.println("x_front:"+x_front+" y_front:"+y_front+" panel w:"+panel.getWidth()/2+" :panel h"+panel.getHeight()/2 + " x_dir: "+x_dir+" y_dir :"+y_dir);
		this.x_front+=horSpeed*x_dir;
		this.y_front+=verSpeed*y_dir;
		if(x_front > panel.getWidth()) // if it arrive to the end of the screen (width-x_axis)
		    x_dir = -1;//change fish swimming to other side
		if(y_front > panel.getHeight())  //if if arrive to the to the end of the screen(height-y_axis)
		    y_dir = -1; //change fish swimming to other side
		if(x_front <0)
		    x_dir = 1;
		if(y_front <0)
		    y_dir = 1;
	}
	
	/**
	 * calculate the new velocity
	 */
	public void calculateNewVelocity()
	{

		double v_old=Math.sqrt(horSpeed*horSpeed+verSpeed*verSpeed);
		double k=(Math.abs((double)y_front-(double)(panel.getHeight())/2)/Math.abs((double)x_front-(double)(panel.getWidth())/2));
		newHorSpeed=v_old/Math.sqrt(k*k+1);
		newVerSpeed=newHorSpeed*k;
		
	}

	@Override
	public Fish clone() {
		return new Fish(panel,size,horSpeed,verSpeed,col,foodFreq);
	}
	
	@Override
	public void editSwimmable(int size, int horSpeed, int verSpeed, Color col) {
		this.size=size;
		this.horSpeed=horSpeed;
		this.verSpeed=verSpeed;
		this.col=col;
		
	}

	@Override
	public void PaintFish(Color col) {
		this.col=col;
	}

	@Override
	public int getXfront() {
		
		return this.x_front;
	}

	@Override
	public int getYfront() {
		return this.y_front;
	}

	@Override
	public Color getColorAnimal() {
		return this.col;
	}

	@Override
	//set the state of the object (memento design pattern)
	public void setState(Color col, int size, int x_front, int y_front, int horSpeed, int verSpeed,double x_dir,double y_dir) {
		this.col=col;
		this.size=size;
		this.x_front=x_front;
		this.y_front=y_front;
		this.horSpeed=horSpeed;
		this.verSpeed=verSpeed;
		this.x_dir=x_dir;
		this.y_dir=y_dir;
		
	}

	@Override
	public double getX_dir() {
		return this.x_dir;
	}

	@Override
	public double getY_dir() {
		return this.y_dir;
		
	}

	@Override
	public void setHungeryState(HungerState state) { 
		currentState=state;
		
	}
	public HungerState getState()
	{
		return currentState;
	}

	@Override
	public String getHungerState() {
		
		return currentState.toString();
	}
	
	

	

	



}
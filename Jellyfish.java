
/**
 * JellyFish class inherite from swimmable and implements all the methods
 * @author Adir Zoari 203002753
 * @author Idan Levi 305562431
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Jellyfish extends Swimmable implements MarineAnimal{
	private int x_front,y_front,size,eatCounter;
	private double newHorSpeed,newVerSpeed,x_dir,y_dir;
	private Color col;
	private AquaPanel panel;
	boolean isSuspended=false,resetFlag=false,flagFreq=false,flagCounter=false;
	private CyclicBarrier barrier=null;
	private HungerState currentState;

	
	
	public Jellyfish(AquaPanel panel,int size,int horSpeed,int verSpeed,Color col,int foodFreq)
	{
		super(horSpeed,verSpeed,foodFreq);
		this.panel=panel;
		this.size=size;
		this.col=col;
		this.x_front=0;
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
		int numLegs;
		if(size<40)
			numLegs = 5;
		else if(size<80)
			numLegs = 9;
		else
			numLegs = 12;

		g.setColor(col);
		g.fillArc(x_front - size/2, y_front - size/4, size, size/2, 0, 180);

		for(int i=0; i<numLegs; i++)
			g.drawLine(x_front - size/2 + size/numLegs + size*i/(numLegs+1), y_front, x_front - size/2 + size/numLegs + size*i/(numLegs+1), y_front+size/3);
		
	}
	
	/**set the flag to true and it cause to suspend the object **/

	@Override
	public void setSuspend() {
		isSuspended=true;
		
	}
	/**set the flag to false and cancel the suspend by notify the object **/

	public boolean getSuspend(){return isSuspended;}
	@Override
	public void setResume() {
		synchronized(this){
			isSuspended=false;
			notify();
			}
	}
	
	/**set the barrier that sent by AquaPanel class **/

	@Override
	public void setBarrier(CyclicBarrier barrier) {
		this.barrier=barrier;		
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
	
	/** @return string of the Jellyfish's color **/

	@Override
	public String getColor() {
		String colorAnimal=null;
		if (col== Color.BLUE)
			colorAnimal="blue";
		if (col==Color.RED)
			colorAnimal="red";
		if (col== Color.GREEN)
			colorAnimal="green";
		if (col== Color.BLACK)
			colorAnimal="black";
		if (col==Color.yellow)
			colorAnimal="yellow";
		if(col==Color.pink)
			colorAnimal="pink";
		return colorAnimal;
	}
	
	
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
	public Jellyfish clone() {
		return new Jellyfish(panel,size,horSpeed,verSpeed,col,foodFreq);
	}
	@Override
	public void editSwimmable(int size, int horSpeed, int verSpeed, Color col) {
		this.size=size;
		this.horSpeed=horSpeed;
		this.verSpeed=verSpeed;
		this.col=col;
	}

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
		// TODO Auto-generated method stub
		return this.y_dir;
	}
	
	@Override
	public void resetSwimmable() {
		resetFlag=true;
        Thread.currentThread().interrupt();
		
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
	
	
	
	
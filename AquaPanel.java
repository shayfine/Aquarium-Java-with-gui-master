/**
 * AquaPanel includes all the buttons at the bottom of the frame and also all the aquarium space
 * where the swimmable swimm
 * @author Adir Zoari 203002753
 * @author Idan Levi 305562431
 *
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.util.concurrent.CyclicBarrier;




public class AquaPanel extends JPanel implements ActionListener,Observer{
	
	
	private static final long serialVersionUID = 1L;
	private int totalEatCounter=0;

	private boolean flagTable=false,flagWorm=false;
	protected Image back;
	private JPanel buttonsPanel,tablePanel;
	private JButton addAnimal,addPlanet,duplicateAnimal,decorator,sleep,wakeUp,reset,food,info,exit;
	private JDialog dialogTable,changeColorDialog,colorChooser;
	

	
	private HashSet<Swimmable> animalsSet=new HashSet<Swimmable>(); //hash set for swimmable
	private HashSet<Immobile> planetsSet=new HashSet<Immobile>();  //hash set for immotible

	private Iterator <Swimmable>itrAnimals;
	private Iterator <Immobile>itrPlants;
	private CyclicBarrier barrier;
	private Singleton wormInstance=null; //instance of the worm
	private CareTaker carTakerMemento;
	private SwimmableReport swReport;
	private PlanetReport plReport;
	
	
	public AquaPanel(CareTaker carTakeMemento)
	{
		//setting panel
		super();
		setLayout(new BorderLayout());
		setBackground(Color.white);
		
		//general intialize
		this.carTakerMemento=carTakeMemento;


		//itrAnimals=animalsSet.iterator(); 
		
		//set the buttons panel
		setButtonPanel();
		add(buttonsPanel,BorderLayout.SOUTH); // add buttonsPanel to the panel/aquaPanel

		//create events 
		addAnimal.addActionListener(this);
		addPlanet.addActionListener(this);
		duplicateAnimal.addActionListener(this);
		decorator.addActionListener(this);
		sleep.addActionListener(this);
		wakeUp.addActionListener(this);
		reset.addActionListener(this);
		food.addActionListener(this);
		info.addActionListener(this);
		exit.addActionListener(this);		
		
	}
	
	public void paintComponent(Graphics g)
	{	
	 	super.paintComponent(g);
	 	if(this.back != null) {
            Graphics2D g1 = (Graphics2D) g;
            g1.drawImage(this.back,0,0,getWidth(),getHeight(),this);
	 	}
	 	
	 	itrAnimals= animalsSet.iterator(); //intialzie iterator 
	 	itrPlants=planetsSet.iterator();	
	 	
	 	// pass with the iterator and draw the animals
	 	while(itrAnimals.hasNext()){
	 		itrAnimals.next().drawCreatrue(g);
	 	}
	 	while(itrPlants.hasNext()){
	 		itrPlants.next().drawCreatrue(g);
	 	}	 	
	 	//draw the worm
		if(wormInstance!=null){ // check if user press on food button to create the worm
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(3));
			g2.setColor(Color.red);
			g2.drawArc(getWidth()/2, getHeight()/2-5, 10, 10, 30, 210);
			g2.drawArc(getWidth()/2, getHeight()/2+5, 10, 10, 180, 270);
			g2.setStroke(new BasicStroke(1));
		}

	}
	
	/**set buttonsPanel and create buttons **/
	public void setButtonPanel()
	{
		//create buttonsPanel
		buttonsPanel=new JPanel();
		buttonsPanel.setLayout(new GridLayout(0,10,0,0)); // set gridLayout to the panel
		
		//intialize buttons
		addAnimal=new JButton("Add Animal");
		addPlanet=new JButton("Add Planet");
		duplicateAnimal=new JButton("Duplicate Animal");
		decorator=new JButton("Decorator");
		sleep=new JButton("Sleep");
		wakeUp=new JButton("Wake up");
		reset=new JButton("Reset");
		food=new JButton("Food");
		info=new JButton("Info");
		exit=new JButton("Exit");
		
		//prefferedSize
		addAnimal.setPreferredSize(new Dimension(109,27));
		addPlanet.setPreferredSize(new Dimension(109,27));
		duplicateAnimal.setPreferredSize(new Dimension(109,27));
		decorator.setPreferredSize(new Dimension(109,27));
		sleep.setPreferredSize(new Dimension(109,27));
		wakeUp.setPreferredSize(new Dimension(109,27));
		reset.setPreferredSize(new Dimension(109,27));
		food.setPreferredSize(new Dimension(109,27));
		info.setPreferredSize(new Dimension(109,27));
		exit.setPreferredSize(new Dimension(109,27));
		
		// add buttons to the buttonsPanel
		buttonsPanel.add(addAnimal);
		buttonsPanel.add(addPlanet);
		buttonsPanel.add(duplicateAnimal);
		buttonsPanel.add(decorator);
		buttonsPanel.add(sleep);
		buttonsPanel.add(wakeUp);
		buttonsPanel.add(reset);
		buttonsPanel.add(food);
		buttonsPanel.add(info);
		buttonsPanel.add(exit);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==addAnimal)
		{
			try{
				if(animalsSet.size()>=5) 
					throw new Exception("The Aquarium can contains only 5 animals!"); // the aquarium limits to only 5 swimmable
				else{
					AddAnimalDialog dialog=new AddAnimalDialog(this); // open to dialog to fill the details of the swimmable
				}
				
			}
			catch(Exception e1){
				 JOptionPane.showMessageDialog(null,e1.getMessage());
			}
		}
		else if(e.getSource()==addPlanet)
		{
			try{
				if(planetsSet.size()>=5) 
					throw new Exception("The Aquarium can contains only 5 plantes!"); // the aquarium limits to only 5 swimmable
				else{
					AddPlanetDialog dialog=new AddPlanetDialog(this); // open to dialog to fill the details of the swimmable
				}
				
			}
			catch(Exception e1){
				 JOptionPane.showMessageDialog(null,e1.getMessage());
			}
		}
		else if(e.getSource()==duplicateAnimal)
		{
			try{
				if(animalsSet.size()>=5)
					throw new Exception("You can't duplicate animals, The Aquarium already contains 5 animals!"); // the aquarium limits to only 5 swimmable
				else if(animalsSet.size()==0)
					throw new Exception("no animals in aquarium to duplicate"); // the aquarium limits to only 5 swimmable
				else{
					AddDuplicateAnimal duplicateDialog=new AddDuplicateAnimal(this,animalsSet);	
					
					
				}
			}
			catch(Exception e1){
				 JOptionPane.showMessageDialog(null,e1.getMessage());
			}
			
		 	
		}
		else if(e.getSource()==decorator)
		{
			try{
				if(animalsSet.size()==0)
					throw new Exception("no animals in aquarium to change their colors"); // the aquarium limits to only 5 swimmable
				else{
					changeColorDialog=new JDialog();
					changeColorDialog.setSize(650,300);
					changeColorDialog.setLayout(new BorderLayout());
					changeColorDialog.setLocationRelativeTo(null);
					changeColorDialog.setVisible(true);
					changeColorDialog.setTitle("Decorator Design Pattern - Change Color");
					JPanelDecorator cp=new JPanelDecorator(this,animalsSet,changeColorDialog);
					changeColorDialog.add(cp);
					
				}
			}
				catch(Exception e1){
					 JOptionPane.showMessageDialog(null,e1.getMessage());
				}
		}
		else if(e.getSource()==sleep)
		{
			itrAnimals= animalsSet.iterator(); 
		 	while(itrAnimals.hasNext()){
		 		itrAnimals.next().setSuspend(); // suspend all the swimmable in the aquarium 
		 	
		 	}
		 	
		}
		else if(e.getSource()==wakeUp){
			itrAnimals= animalsSet.iterator(); 
		 	while(itrAnimals.hasNext()){
		 		itrAnimals.next().setResume(); // cancel the suspend to all simmable
		 	}
		}
		else if(e.getSource()==reset)
		{
			itrAnimals= animalsSet.iterator(); 
			while(itrAnimals.hasNext())
			{
				Swimmable objSwim=itrAnimals.next();
				//new Thread(objSwim).interrupt();
				objSwim.resetSwimmable();
				
			}
			flagWorm=false;
			animalsSet.clear();// remove all items from the hashset
			carTakerMemento.getPlantesMementoList().clear();
			carTakerMemento.getSwimmableMementoList().clear();
			repaint(); // call to function PaintCompoment to repaint the panel
			

		}
		else if(e.getSource()==food)
		{
			setFlagFood();
			
		}
		else if(e.getSource()==info)
		{
			if(!flagTable){
				flagTable=true;
				fillTable();
			}
			else{
				dialogTable.dispose();
				flagTable=false;
			}
		}
		
		else if(e.getSource()==exit)
		{
			System.exit(0);
		}
	
		
		
	}
	
	public HashSet<Swimmable> getSwimmableSet(){return animalsSet;}
	public HashSet<Immobile> getPlanetSet(){return planetsSet;}

	public int checkSwimmableSwim()
	{
		int count=0;
		itrAnimals=animalsSet.iterator(); 
		while(itrAnimals.hasNext())
		{
			Swimmable objSwim = (Swimmable) itrAnimals.next();
			if(objSwim.getState() instanceof Hungry){
			//if(!(((Swimmable) itrAnimals.next()).getSuspend())){
				count++;
			}
		}
		return count;	
	}
	public void barrierCall()
	{
		int countHungry = 0,countSwimmable=0;
		itrAnimals=animalsSet.iterator(); 
		while(itrAnimals.hasNext())
		{
			Swimmable objSwim = (Swimmable) itrAnimals.next();
			if(!(objSwim.getSuspend())){
				System.out.println(objSwim.getColor());
				countSwimmable++;
				if(objSwim.getState() instanceof Hungry){
					countHungry++;
				}
			}
		}
		if(countHungry>0)
			barrier=new CyclicBarrier(countHungry);
		if(countSwimmable>0){
			flagWorm=true; //delete the flag
			wormInstance=Singleton.getInstance();
			itrAnimals=animalsSet.iterator(); 
	
			while (itrAnimals.hasNext()){
				Swimmable objSwim = (Swimmable) itrAnimals.next();
				if(!objSwim.getSuspend())
					objSwim.setBarrier(barrier);
			}
		}
		
	}
	
	/*** add to Hashset function  **/
	/**add animal to Hashset
	 * @param newSwimm- object that inheriate from simmable**/
	public void addAnimal(Swimmable newSwimm)
	{
		newSwimm.addObserver(this);
		animalsSet.add(newSwimm); //add the new simmable object
		repaint(); // call the paintCompoment function
		new Thread(newSwimm).start(); // start the thread
	

	}
	
	/** add plant to Hashset **/
	public void addPlanet(Immobile newSwimm)
	{
		planetsSet.add(newSwimm);
		repaint();
	}
	/** create table and fill with details of the simmable **/
	public void fillTable()
	{
		dialogTable=new JDialog(); // the table is kind of JDialog, it pop and dispose after click
		tablePanel=new JPanel(); // create panel to the table

		String[] types = {"Swimmable types","Planets types"};
		JComboBox cmb_types=new JComboBox<String>(types);
		JPanel comboPanel=new JPanel(new FlowLayout());
		comboPanel.setSize(new Dimension(300,100));
		
		JPanel reportsPanel=new JPanel();
		swReport=new SwimmableReport(this,animalsSet,1);
		plReport=new PlanetReport(this,planetsSet,1);
		reportsPanel.add(swReport);
		reportsPanel.add(plReport);
		
		plReport.setVisible(false);
		swReport.setVisible(true);
		
		comboPanel.add(cmb_types);
		tablePanel.add(comboPanel,BorderLayout.PAGE_START);
		tablePanel.add(reportsPanel);
		cmb_types.addItemListener(new ItemListener()
				{
					@Override
					public void itemStateChanged(ItemEvent e2) {
						if(e2.getItem()=="Swimmable types")
						{
							swReport.setVisible(true);
							plReport.setVisible(false);
						}
						if(e2.getItem()=="Planets types")
						{
							swReport.setVisible(false);
							plReport.setVisible(true);


						}
						
					}
			
				});
		dialogTable.add(tablePanel); // add the panel to the JDialog dialogTable
		dialogTable.setVisible(true);// show the table
		dialogTable.setSize(650,300);// set size
		

	
		/*
		totalEatCounter=0;
		itrAnimals= animalsSet.iterator(); 
		tablePanel=new JPanel(); // create panel to the table
		dialogTable=new JDialog(); // the table is kind of JDialog, it pop and dispose after click
		String[] columns=new String[]{"Animal","Color","Size","Hor.Speed","Ver.speed","Eat counter"}; // columns
		DefaultTableModel tableModel = new DefaultTableModel(columns,0);
		JTable table=new JTable (tableModel);
		table.setPreferredScrollableViewportSize(new Dimension(400,100));
		
		while(itrAnimals.hasNext()){
			Swimmable sw = (Swimmable) itrAnimals.next();
			String[] swimm=new String[]{sw.getAnimalName(),sw.getColor(),String.valueOf(sw.getSize()),String.valueOf(sw.getHorSpeed()),String.valueOf(sw.getVerSpeed()),String.valueOf(sw.getEatCount())};
			totalEatCounter+=sw.getEatCount(); //sum all eat count of the swimmable
			tableModel.addRow(swimm);
			
		}
		if(animalsSet.size()>0) // display the eat counter
			tableModel.addRow(new String[]{"table","","","","",String.valueOf(totalEatCounter)});
		
		JScrollPane pane=new JScrollPane(table);
		
		tablePanel.add(pane); // add the table to the panel
		dialogTable.add(tablePanel); // add the panel to the JDialog dialogTable
		dialogTable.setVisible(true);// show the table
		dialogTable.setSize(420,200);// set size
		*/

	}
	
	/** get object who ate the worm and increament in 1 his counter
	 * @param swimm- object of swimmable
	 */	
	public synchronized void eatFood(Swimmable swimm)
	{
		swimm.eatInc();//increament the eat counter of the swimm object
	}
	
	
	
	/**set the flag worm to false after the worm ate by swimmable**/
	public void setFlagWorm(){
		flagWorm=false;
		Singleton.set();
	}
	public void setFlagFood()
	{
		if (Singleton.get()==null){
			if(wormInstance==null){
				try{
					barrierCall();
				
				}
				catch(IllegalArgumentException err){
					System.out.println("add");
					flagWorm=false;
					repaint();
					return;
				}
			
			}
		}
		
	}
	

	
	/***---------- Singelton design Pattern ----------- ***/
	/**set worm instance to null **/
	public void setWormInstance()
	{
		Singleton.set();
		wormInstance=null;
	}
	/**get the worm Instance **/
	public Singleton getWormInstance(){return wormInstance;}
	public boolean getFlagWorm(){return flagWorm;}

	@Override
	public void update(java.util.Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		//setFlagFood();
		JOptionPane.showMessageDialog(null,arg1 +" wants to eat! BloBlo ","Notify Food",JOptionPane.PLAIN_MESSAGE);
		
		
		
		
	}

	
	
	

	
	
	


	/*
	public MarineAnimal getObjectById(int idObject,String typeAnimal)
	{
		itrAnimals= animalsSet.iterator(); 
		while(itrAnimals.hasNext()){
			Swimmable sw=itrAnimals.next();
			if(sw.getID()==idObject){
				if(typeAnimal=="Fish")
					return (Fish)sw;
				if(typeAnimal=="Jelly Fish")
					return (Jellyfish)sw;
			}
			
		}

		return null;
	}
	*/
	
		
	
	

}	


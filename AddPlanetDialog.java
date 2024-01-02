import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddPlanetDialog extends JDialog implements ActionListener {


	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private JComboBox<String> cmb_AnimalsTypes; //ComboBox animals types 
	private String[] names = {"Laminaria","Zostera"};  
	private JLabel lb_Animal,lb_Size;
	private JTextField txt_size;
	private JButton b_submit,b_cancel;

	private AquaPanel pan;
	private AbstractSeaFactory seaFactory;
	private SeaCreature creObj;
	

	
	/**
	 * 
	 * @param the consturctor get the pen(AquaPanel instance)
	 */
	public AddPlanetDialog(AquaPanel pan)
	{
		this.pan=pan;
		
		//settings AddAnimalDialog
		setTitle("Add Planet Dialog");
		setSize(350,400);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		//set panel
		setPanel();
		
		b_submit.addActionListener(this);
		b_cancel.addActionListener(this);	

		
		setVisible(true);
	
	}
	
	
	/**set the panel of the JDialog **/
	public void setPanel()
	{
		//settings of panel frame
		panel=new JPanel();
		panel.setLayout(new GridLayout(4,2,1,2));
	
		
		//Initialize labels
		lb_Animal=new JLabel("Animal");
		lb_Size=new JLabel("Size");
		b_submit=new JButton("Submit");
		b_cancel=new JButton("Cancel");
		
		
		//Initialize textFields
		txt_size=new JTextField();
		
		
		//Initialize comboBox
		cmb_AnimalsTypes=new JComboBox<String>(names);
		
		//add components to the panel
		panel.add(lb_Animal);
		panel.add(cmb_AnimalsTypes);
		panel.add(lb_Size);
		panel.add(txt_size);
		panel.add(b_submit);
		panel.add(b_cancel);
		
		add(panel);
		
		
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b_submit)
		{
			fillSwimmable(); // fill animals details
		}
		else if(e.getSource()==b_cancel)
			dispose(); //close the instance dialog
			
		
	}
	

	/**fill animal details and create new **/
	public void fillSwimmable()
	{
		String animalType;
		int size=0;
		int x,y; //place of the plantes
	    Random rand = new Random();
	    x=rand.nextInt((500 - 50) + 1) + 150;
	    y=rand.nextInt((500 - 50) + 1) + 50;
	    
	    System.out.println(x+ "   " + y);
		try{
			animalType=cmb_AnimalsTypes.getItemAt(cmb_AnimalsTypes.getSelectedIndex()); // get the selectedItem 
			
			size=Integer.parseInt(txt_size.getText()); //get the size from textField
			
			// check if the terms exists
			if(size<20 || size>320)
				throw new Exception("size have to be between 20 to 320");
	
			if(animalType=="Laminaria"){
				seaFactory=new PlantFactory(pan,size,x,y);
				creObj=seaFactory.produceSeaCreature("Laminaria");
				pan.addPlanet((Immobile)creObj);
				}
			else if(animalType=="Zostera"){
				seaFactory=new PlantFactory(pan,size,x,y);
				creObj=seaFactory.produceSeaCreature("Zostera");
				pan.addPlanet((Immobile)creObj);
			}
				
			
			dispose(); // dispose the window after creating
		}
		/*catch(NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, "text fields have to be a number");
		}
		*/
		catch(Exception e1){
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}
	

	



	
}



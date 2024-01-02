/**
 * AddAniamlDialog class create dialog that user fill the details of the new Swimmable
 * @author Adir Zoari 203002753
 * @author Idan Levi 305562431
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAnimalDialog extends JDialog implements ActionListener {
	

	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private JComboBox<String> cmb_AnimalsTypes; //ComboBox animals types 
	private JComboBox<String> cmb_ColorTypes;  // ComboBox colors types
	private String[] names = {"Fish","Jelly Fish"};  
	private String[] colors = {"blue","red","green","black","yellow","pink"};
	private JLabel lb_Animal,lb_Size,lb_horSpeed,lb_verSpeed,lb_color,lb_foodFreq;
	private JTextField txt_size,txt_verSpeed,txt_horSpeed,txt_foodFreq;
	private JButton b_submit,b_cancel;

	private AquaPanel pan;
	private AbstractSeaFactory seaFactory;
	private SeaCreature creObj;
	

	
	/**
	 * 
	 * @param the consturctor get the pen(AquaPanel instance)
	 */
	public AddAnimalDialog(AquaPanel pan)
	{
		this.pan=pan;
		
		//settings AddAnimalDialog
		setTitle("Add Animal Dialog");
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
		panel.setLayout(new GridLayout(9,3,1,2));
	
		
		//Initialize labels
		lb_Animal=new JLabel("Animal");
		lb_Size=new JLabel("Size");
		lb_horSpeed=new JLabel("horizontal speed");
		lb_verSpeed=new JLabel("vertical speed");
		lb_color=new JLabel("Color");
		lb_foodFreq=new JLabel("food frequency(range: 100-200)");
		b_submit=new JButton("Submit");
		b_cancel=new JButton("Cancel");
		
		
		//Initialize textFields
		txt_size=new JTextField();
		txt_verSpeed=new JTextField();
		txt_horSpeed=new JTextField();
		txt_foodFreq=new JTextField();
		
		
		//Initialize comboBox
		cmb_AnimalsTypes=new JComboBox<String>(names);
		cmb_ColorTypes=new JComboBox<String>(colors);
		
		//add components to the panel
		panel.add(lb_Animal);
		panel.add(cmb_AnimalsTypes);
		panel.add(lb_Size);
		panel.add(txt_size);
		panel.add(lb_horSpeed);
		panel.add(txt_horSpeed);
		panel.add(lb_verSpeed);
		panel.add(txt_verSpeed);
		panel.add(lb_foodFreq);
		panel.add(txt_foodFreq);
		panel.add(lb_color);
		panel.add(cmb_ColorTypes);
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
		String animalType,colorType;
		Color clr = null;
		int size=0,hor=0,ver=0,foodFreq=0;
		try{
			animalType=cmb_AnimalsTypes.getItemAt(cmb_AnimalsTypes.getSelectedIndex()); // get the selectedItem 
			colorType=cmb_ColorTypes.getItemAt(cmb_ColorTypes.getSelectedIndex()); //get the selectedItem
			
			size=Integer.parseInt(txt_size.getText()); //get the size from textField
			hor=Integer.parseInt(txt_horSpeed.getText()); //get the horizontal from textField
			ver=Integer.parseInt(txt_verSpeed.getText());	// get the vertical from textField
			foodFreq=Integer.parseInt(txt_foodFreq.getText());	// get the vertical from textField

			
			// check if the terms exists
			if(size<20 || size>320)
				throw new Exception("size have to be between 20 to 320");
			if(hor<1 || hor>10)
				throw new Exception("horizontal speed have to be between 1 to 10");
			if(ver<1 || ver>10)
				throw new Exception("vertical speed have to be between 1 to 10");
			
			if(foodFreq<100 || foodFreq>200) // food frequency of the animal
				throw new Exception("food Frequency  have to be between 100 to 200");
			
			// set color by selctedItem from combobox
			switch(colorType)
			{
				case "blue":
					clr=Color.BLUE;
					break;
				case "red":
					clr=Color.RED;
					break;
				case "green":
					clr=Color.GREEN;
					break;
				case "black":
					clr=Color.BLACK;
					break;
				case "yellow":
					clr=Color.YELLOW;
					break;
				case "pink":
					clr=Color.PINK;
					break;
			}
			
			//create swimmable object
			if(animalType=="Fish"){
				seaFactory=new AnimalFactory(pan,size,hor,ver,clr,foodFreq);
				creObj=seaFactory.produceSeaCreature("Fish");
				pan.addAnimal((Swimmable)creObj); // add the new object to the hashset of the AquaPanel class
			}	
			
			else if(animalType=="Jelly Fish"){
				seaFactory=new AnimalFactory(pan,size,hor,ver,clr,foodFreq);
				creObj=seaFactory.produceSeaCreature("Jellyfish");
				pan.addAnimal((Swimmable)creObj); // add the new object to the hashset of the AquaPanel class
				
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


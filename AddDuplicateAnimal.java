/**
 * AddDuplicateAnimal class create dialog that user choose what swimmable to duplicate
 * @author Adir Zoari 203002753
 * @author Idan Levi 305562431
 */


import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.*;



public class AddDuplicateAnimal extends JDialog implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int size=0,hor=0,ver=0;


	private String typeAnimal=null,colorType=null;
	private Color clr = null;
	private String[] colors = {"blue","red","green","black","yellow","pink"};
	private JComboBox<String> cmb_ColorTypes;  // ComboBox colors types

	private AquaPanel panel;
	private HashSet<Swimmable> animalsSet=new HashSet<Swimmable>(); //hash set for swimmable
	private Iterator <Swimmable>itrAnimals;
	private JPanel DialogPanel,buttonsPanel; // create panels to the dialog
	private JButton b_duplicate,b_cancel;
	private SwimmableReport report;
	private Swimmable duplicateSwimmable;

	public AddDuplicateAnimal(AquaPanel panel,HashSet<Swimmable> animalsSet)
	{
		super();
		
		//settings AddDuplicateAnimal
		setTitle("ProtoType Design Pattern - Add Duplicate Dialog");
		setSize(650,300);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		
		
		
		this.report=new SwimmableReport(panel,animalsSet,1);
		this.panel=panel;
		this.animalsSet=new HashSet<Swimmable>(animalsSet);
		this.typeAnimal=null;
		
		setPanel();
		
		add(DialogPanel); // add the panel to the JDialog dialogTable 
		setVisible(true);// show the dialog

	}
	
	public void setPanel() {
	
		DialogPanel=new JPanel(new BorderLayout());
		buttonsPanel = new JPanel(new FlowLayout());

		
		b_duplicate=new JButton("Duplicate");
		b_cancel=new JButton("Cancel");
		
		buttonsPanel.add(b_duplicate);
		buttonsPanel.add(b_cancel);
		buttonsPanel.setVisible(true);

		DialogPanel.add(buttonsPanel,BorderLayout.SOUTH);
		DialogPanel.add(report);
		b_duplicate.addActionListener(this);
		b_cancel.addActionListener(this);
	}
	


	@Override
	public void actionPerformed(ActionEvent e1) {
		if(e1.getSource()==b_duplicate)
		{
			//System.out.println(report.getIDobject() + report.getTypeAnimal()); //for check
	
			duplicateAnimalObject(report.getIDobject(),report.getTypeAnimal());
            if (JOptionPane.showConfirmDialog(null, "Swimmable "+report.getTypeAnimal()+ " duplicated,do you want to edit the object?", "Edit Message",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) { //if the user want to edit the duplicate objet
            	 report.setVisible(false);
            	 buttonsPanel.setVisible(false);
            	 editDuplicateAnimal(duplicateSwimmable);
            } else {
            	panel.addAnimal(duplicateSwimmable);
            	JOptionPane.showMessageDialog(null,"Duplicate object added to set");
            	this.dispose();
            }
	          
			
		}
		else if(e1.getSource()==b_cancel)
		{
			dispose();
		}
		
	}
	
	public void duplicateAnimalObject(int idObject,String typeAnimal) //duplicate the swimmable by get id and type
	{
		itrAnimals= animalsSet.iterator(); 
		while(itrAnimals.hasNext()){
			Swimmable sw=itrAnimals.next();
			if(sw.getID()==idObject){
				if(typeAnimal=="Fish")
					duplicateSwimmable=(Fish)sw.clone();
				if(typeAnimal=="Jelly Fish")
					duplicateSwimmable=(Fish)sw.clone();
	
			}
		}
			
					
	}
	public void editDuplicateAnimal (Swimmable editSwimmable)
	{
		//setting edit panel
		JPanel editPanel=new JPanel();
		editPanel.setSize(800,300);
		editPanel.setLayout(new GridLayout(5,3,1,2));

		
		
		//intialize variable
		
		
		cmb_ColorTypes=new JComboBox<String>(colors);
		JLabel lb_Size=new JLabel("Size");
		JLabel lb_horSpeed=new JLabel("horizontal speed");
		JLabel lb_verSpeed=new JLabel("vertical speed");
		JLabel lb_color=new JLabel("Color");
		
		JButton b_edit=new JButton("edit");
		JButton b_cancel1=new JButton("Cancel");
		
		JTextField txt_size=new JTextField();	
		JTextField txt_verSpeed=new JTextField();
		JTextField txt_horSpeed=new JTextField();
		
		txt_size.setText(String.valueOf(editSwimmable.getSize()));
		txt_verSpeed.setText(String.valueOf(editSwimmable.getVerSpeed()));
		txt_horSpeed.setText(String.valueOf(editSwimmable.getVerSpeed()));

	
		editPanel.add(lb_Size);
		editPanel.add(txt_size);
		editPanel.add(lb_horSpeed);
		editPanel.add(txt_horSpeed);
		editPanel.add(lb_verSpeed);
		editPanel.add(txt_verSpeed);
		editPanel.add(lb_color);
		editPanel.add(cmb_ColorTypes);
		editPanel.add(b_edit);
		editPanel.add(b_cancel1);
		
		b_edit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg)
			{
				size=Integer.parseInt(txt_size.getText()); //get the size from textField
				hor=Integer.parseInt(txt_horSpeed.getText()); //get the horizontal from textField
				ver=Integer.parseInt(txt_verSpeed.getText());	// get the vertical from textField
				colorType=cmb_ColorTypes.getItemAt(cmb_ColorTypes.getSelectedIndex()); //get the selectedItem

				try{
					// check if the terms exists
					if(size<20 || size>320)
						throw new Exception("size have to be between 20 to 320");
					if(hor<1 || hor>10)
						throw new Exception("horizontal speed have to be between 1 to 10");
					if(ver<1 || ver>10)
						throw new Exception("vertical speed have to be between 1 to 10");
					
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
				}
					editSwimmable.editSwimmable(size,hor,ver,clr);
					JOptionPane.showMessageDialog(null,"The duplicate swimmable "+editSwimmable.getAnimalName() +" edited and add to Aquarium");
					panel.addAnimal(editSwimmable);
					dispose();
					
			}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}	
		});

		b_cancel1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg)
			{
				JOptionPane.showMessageDialog(null,"The duplicate swimmable "+editSwimmable.getAnimalName() +" add to Aquarium without NO EDIT!");
				panel.addAnimal(editSwimmable);
				dispose();

			}
		});
		DialogPanel.add(editPanel);
		editPanel.setVisible(true);
	}

}

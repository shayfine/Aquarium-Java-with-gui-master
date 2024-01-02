/**
 * JPanelDecorator is panel with option to choose color to specific swimmable in the aquarium
 * @author Adir Zoari 203002753
 * @author Idan Levi 305562431
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JPanelDecorator extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lb_color;
	private String[] colors = {"blue","red","green","black","yellow","pink"};
	
	private JButton b_changeColor,b_cancel,b_submitColor,b_cancelColor;// for JPanelDecorator - Decorator Design Pattern
	private JPanel buttonsPanel,buttonsChangePanel;
	private JDialog changeColorDialog,colorChooser;
	private JComboBox<String> cmb_ColorTypes;  // ComboBox colors types

	private SwimmableReport report;
	private Iterator <Swimmable>itrAnimals;
	private HashSet<Swimmable> animalsSet=new HashSet<Swimmable>(); //hash set for swimmable
	
	public JPanelDecorator(AquaPanel panel,HashSet<Swimmable> animalsSet,JDialog changeColorDialog )
	{
		super();

		setLayout(new BorderLayout());
		
		this.changeColorDialog=changeColorDialog;
		this.animalsSet=new HashSet<Swimmable>(animalsSet);
		this.report=new SwimmableReport(panel,animalsSet,1);

		setJPanelDecorator();
	
	}
	public void setJPanelDecorator() {
			
			buttonsPanel = new JPanel(new FlowLayout());
			b_changeColor=new JButton("Change Color");
			b_cancel=new JButton("Cancel");
			
			buttonsPanel.add(b_changeColor);
			buttonsPanel.add(b_cancel);
			buttonsPanel.setVisible(true);
	
			add(buttonsPanel,BorderLayout.SOUTH);
			add(report);
			b_changeColor.addActionListener(this);
			b_cancel.addActionListener(this);
			setVisible(true);
			
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b_changeColor)
		{
			setChooseColorPanel();

		}
		else if(e.getSource()==b_cancel)
		{
			changeColorDialog.dispose();
		}
		else if(e.getSource()==b_submitColor)
		{
			submitColor();

		}
		else if(e.getSource()==b_cancelColor)
		{
			colorChooser.dispose();
		}
		
	}
	
	public MarineAnimal getObjectById(int idObject,String typeAnimal)
	{
		itrAnimals= animalsSet.iterator(); 
		while(itrAnimals.hasNext()){
			Swimmable sw=itrAnimals.next();
			if(sw.getID()==idObject){
				if(typeAnimal=="Fish")
					return (Fish)sw;
				if(typeAnimal=="Jellyfish"){
					return (Jellyfish)sw;
				}
			}
			
		}

		return null;
	}
	public void setChooseColorPanel() {
		colorChooser=new JDialog();
		JPanel choosePanel=new JPanel();
		buttonsChangePanel = new JPanel(new FlowLayout());
		colorChooser.setSize(200,300);
		
		lb_color=new JLabel("Color");
		cmb_ColorTypes=new JComboBox<String>(colors);

		choosePanel.add(lb_color);
		choosePanel.add(cmb_ColorTypes);
		
		b_submitColor=new JButton("submit color");
		b_cancelColor=new JButton("Cancel");
		
		buttonsChangePanel.add(b_submitColor);
		buttonsChangePanel.add(b_cancelColor);
		
		choosePanel.add(buttonsChangePanel,BorderLayout.SOUTH);
		colorChooser.add(choosePanel);
	
		b_submitColor.addActionListener(this);
		b_cancelColor.addActionListener(this);
		colorChooser.setVisible(true);
		
		
	}
	
	public void submitColor() {
		Color clr = Color.BLACK;
		String colorType=null;
		
		colorType=cmb_ColorTypes.getItemAt(cmb_ColorTypes.getSelectedIndex()); //get the selectedItem
		
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

		MarineAnimal changeObject=new MarineAnimalDecorator(getObjectById(report.getIDobject(),report.getTypeAnimal())); 
		System.out.println(changeObject.getClass());
        JOptionPane.showMessageDialog(null,"the color of "+report.getTypeAnimal()+" #" +report.getIDobject()+ " changed to "+colorType );
		changeObject.PaintFish(clr);
		
		changeColorDialog.dispose();
		colorChooser.dispose();
		repaint();
		
	}


}

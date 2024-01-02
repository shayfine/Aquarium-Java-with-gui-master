/**
 * AquaFrame consists the menubar and main function
 * @author Adir Zoari 203002753
 * @author Idan Levi 305562431
 *
 */


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;






public class AquaFrame extends JFrame implements ActionListener {
	
	
	private static final long serialVersionUID = 1L;
	private AquaPanel panel; 
	private JLabel lb_background; // label for change photo to background
	private JMenuBar bar;
	private JMenu file,background,momento,help;
	private JMenuItem exit,image,blue,none,helpI,saveObjectState,restoreObjectState;
	private CareTaker carTakerMemento; // for memento design pattern

	public static void main(String[] args)
	{
		new AquaFrame();
		
	}
	
	public AquaFrame()
	{
		
		//settings frame
		super("my Aquarium");//title of the window 
		setSize(1100,650);
		setLocationRelativeTo(null);//open the window in the center of the screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setResizable(false);
		
		this.carTakerMemento=new CareTaker();
		//set panel(AquaPanel) to the frame
		panel=new AquaPanel(carTakerMemento);
		add(panel);
		
		//set menubar 
		intializeMenuBar();
		
		//create events
		exit.addActionListener(this);
		image.addActionListener(this);
		blue.addActionListener(this);
		helpI.addActionListener(this);
		none.addActionListener(this);
		saveObjectState.addActionListener(this);
		restoreObjectState.addActionListener(this);
		
		setVisible(true);//show the window
		
}
	
	/**set the menubar**/
	public void intializeMenuBar()
	{
		//create Menu bar with 3 options
		bar=new JMenuBar();
		file=new JMenu("File");
		background=new JMenu("Background");
		momento=new JMenu("Momento");
		help=new JMenu("Help");
	
		
		//create into the menu options
		exit=new JMenuItem("Exit");
		image=new JMenuItem("Image");
		blue=new JMenuItem("Blue");
		none=new JMenuItem("None");
		helpI=new JMenuItem("Help");
		saveObjectState=new JMenuItem("Save Object State");
		restoreObjectState=new JMenuItem("Restore Object State");
		
		//add the internal options to the menu bar
		file.add(exit);
		background.add(image);
		background.add(blue);
		background.add(none);
		help.add(helpI);
		momento.add(saveObjectState);
		momento.add(restoreObjectState);
		
		
		//add the menu option to the bar itself
		bar.add(file);
		bar.add(background);
		bar.add(momento);
		bar.add(help);
		setJMenuBar(bar);// add the menubar to the frame/aquaFrame instance
		
	}
	
	/**load image from computer and change the background in the frame**/
	public void loadImage()
    {
        if(lb_background!=null)
            panel.removeAll();
 
        FileDialog fd=new FileDialog(new Frame(),"please choose a file:",FileDialog.LOAD);
        fd.setVisible(true);
        File f;
        if(fd.getFile()!=null)
        {
            f=new File(fd.getDirectory(),fd.getFile());
            try{
               // ImageIcon a=new ImageIcon(ImageIO.read(f));
                panel.back = ImageIO.read(f);
                panel.repaint();
              
                setVisible(true);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
       
    }


	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==exit){
			System.exit(0);
		}
		else if(e.getSource()==blue){
			panel.setBackground(Color.blue);
		}
		else if(e.getSource()==image)
		{
			loadImage();
		}
		else if(e.getSource()==none)
		{
			panel.setBackground(Color.white);
		}
		else if(e.getSource()==helpI){
	        JOptionPane.showMessageDialog(null, "Home Work3:\n GUI @a Threads");
		}
		else if(e.getSource()==saveObjectState){
			SaveObjectDialog sbd=new SaveObjectDialog(panel,carTakerMemento);
			System.out.println(carTakerMemento.getSwimmableMementoList().size());


		}
		else if(e.getSource()==restoreObjectState){
			System.out.println(carTakerMemento.getSwimmableMementoList().size());

			RestoreObjectState ros=new RestoreObjectState(panel,carTakerMemento);
			
		}
	}
	

}
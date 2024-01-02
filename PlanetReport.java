/**
 * planet report class is report that display the report of all the immobile in the aquarium
 * @author Adir Zoari 203002753
 * @author Idan Levi 305562431
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PlanetReport extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String typeAnimal=null,idOb;
	private int idObject=0;
	private int optionTable;
	
	private HashSet<Immobile> planetSet=new HashSet<Immobile>(); //hash set for swimmable
	private HashMap<Integer, Memento> plantesMementoList; 
	private Set set;
	
	private Iterator <Immobile>itrPlantes;
	private JTable table;
	private AquaPanel panel;
	private JPanel tablePanel=new JPanel();
	

	public void setDialog()
	{
		setSize(650,300);
		setLayout(new BorderLayout());
	}
	public PlanetReport(AquaPanel panel,HashSet<Immobile> planetSet,int optionTable)
	{
		//setting Dialog
		setDialog();
		
		this.optionTable=optionTable;
		this.panel=panel;
		this.planetSet=new HashSet<Immobile>(planetSet);
		
		//set panel
		SettingTable();
		add(tablePanel);
	}
	
	public PlanetReport(HashMap<Integer, Memento> plantesMementoList,int optionTable)
	{
		setDialog();
		this.plantesMementoList=new HashMap<Integer, Memento>(plantesMementoList); 
		this.optionTable=optionTable;
		SettingTable();
		add(tablePanel);

		
	}

	public void SettingTable()
	{
		tablePanel=new JPanel();
        itrPlantes= planetSet.iterator();
        String[] columns=null;
        if(optionTable==1)
        	columns=new String[]{"","ID","Planet","Color","Size"}; // columns
        
        if(optionTable==2)
            columns=new String[]{"","ID","Planet","Color","Size","x_front","y_front"}; // columns
    
        DefaultTableModel tableModel = new DefaultTableModel(columns,0);
        table=new JTable (tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(600,150));
        
        if(optionTable==1){
            while(itrPlantes.hasNext()){
                Immobile im = itrPlantes.next();
                String[] swimm=new String[]{"",String.valueOf(im.getID()),im.getPlanetName(),im.getColor(),String.valueOf(im.getSize())};
                tableModel.addRow(swimm);
            }
            }
       
        if(optionTable==2){
    	set=plantesMementoList.entrySet();
    	Iterator iterator=set.iterator();
    	 while(iterator.hasNext()){
             Map.Entry mentry = (Map.Entry)iterator.next();
             Memento im = (Memento) mentry.getValue();
             String[] swimm=new String[]{"",String.valueOf(im.getID()),im.getAnimalName(),im.getColor(),String.valueOf(im.getSize()),String.valueOf(im.getXfront()),String.valueOf(im.getYfront())};   
             tableModel.addRow(swimm);
    	 }
        }
		
        table.addMouseListener(new java.awt.event.MouseAdapter() {
        	public void mouseClicked(java.awt.event.MouseEvent evt) {

        		int row = table.rowAtPoint(evt.getPoint());

        		int col = table.columnAtPoint(evt.getPoint());

        		if (row >= 0 && col >= 0) {

        			idOb=(String) table.getValueAt(row, 1); //for check
        			typeAnimal=(String) (table.getValueAt(row, 2));
        			idObject=Integer.valueOf(idOb);
        			//System.out.println(idObject);

        		}

        		}

        		});
        
	 JScrollPane pane=new JScrollPane(table);
     
     tablePanel.add(pane); // add the table to the panel
	}

	public int getIDobject(){return idObject;}
	public String getTypeAnimal(){return typeAnimal;}
	

}

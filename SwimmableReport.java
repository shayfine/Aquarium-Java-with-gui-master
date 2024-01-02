/**
 * Swimmable report class is report that display the report of all the swimmable in the aquarium
 * @author Adir Zoari 203002753
 * @author Idan Levi 305562431
 */

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SwimmableReport extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String typeAnimal=null,idOb;
	private int idObject=0,totalEatCounter=0;
	
	private HashSet<Swimmable> animalsSet=new HashSet<Swimmable>(); //hash set for swimmable
	private HashMap<Integer, Memento> swimmableMementoList;
	private Set set;
	private Iterator <Swimmable>itrAnimals;
	private JTable table;
	private AquaPanel panel;
	private JPanel tablePanel=new JPanel();
	private int optionTable;
   

	public void setDialog()
	{
		setSize(650,300);
		setLayout(new BorderLayout());
	}
	public SwimmableReport(AquaPanel panel,HashSet<Swimmable> animalsSet,int optionTable)
	{
		//settings SwimmableReport
		setDialog();
		this.panel=panel;
		this.animalsSet=new HashSet<Swimmable>(animalsSet);
		this.optionTable=optionTable;
		
		//set panel
		SettingTable();
		add(tablePanel);
	}
	public SwimmableReport(HashMap<Integer, Memento> swimmableMementoList,int optionTable)
	{
		setDialog();
		this.swimmableMementoList=new HashMap<Integer, Memento>(swimmableMementoList); 
		//System.out.println(swimmableMementoList.size());

		//System.out.println(swimmableMementoList.get(0).getSize());
		this.optionTable=optionTable;	
		SettingTable();
		add(tablePanel);
	}
	
	public void SettingTable()
	{
		tablePanel=new JPanel();
       
        String[] columns=null;
        if(optionTable==1){
        columns=new String[]{"","ID","Animal","Color","Size","Hor.Speed","Ver.speed","Eat counter","Hunger status"}; // columns
        }
        else if(optionTable==2){
          columns=new String[]{"","ID","Animal","Color","Size","x_front,y_front","Hor.Speed","Ver.speed","Eat counter","Hunger status"}; // columns

        }
        DefaultTableModel tableModel = new DefaultTableModel(columns,0);
        table=new JTable (tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(600,150));
        if(optionTable==1){
	        itrAnimals= animalsSet.iterator();
	        while(itrAnimals.hasNext()){
	            Swimmable sw = itrAnimals.next();
	            String[] swimm=new String[]{"",String.valueOf(sw.getID()),sw.getAnimalName(),sw.getColor(),String.valueOf(sw.getSize()),String.valueOf(sw.getHorSpeed()),String.valueOf(sw.getVerSpeed()),String.valueOf(sw.getEatCount()),sw.getHungerState()};
	            totalEatCounter+=sw.getEatCount(); //sum all eat count of the swimmable
	            tableModel.addRow(swimm);
	        }
        }
       if(optionTable==2){
        	set=swimmableMementoList.entrySet();
        	Iterator iterator=set.iterator();
        	 while(iterator.hasNext()){
                 Map.Entry mentry = (Map.Entry)iterator.next();
                 Memento sw = (Memento) mentry.getValue();
                 String[] swimm=new String[]{"",String.valueOf(sw.getID()),sw.getAnimalName(),sw.getColor(),String.valueOf(sw.getSize()),String.valueOf(sw.getXfront()),String.valueOf(sw.getYfront()),String.valueOf(sw.getHorSpeed()),String.valueOf(sw.getVerSpeed()),sw.getHungerStatus()};   
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

        		}

        		}

        		});
        
	 JScrollPane pane=new JScrollPane(table);
     
     tablePanel.add(pane); // add the table to the panel
        

	}
      
	public int getIDobject(){return idObject;}
	public String getTypeAnimal(){return typeAnimal;}
	
	
	

	
	

}

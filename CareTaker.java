/**
 * CareTaker class has hashMap of immobile and swimmable and save the memento of any object by getting id and 
 * memento object
 * @author Adir Zoari 203002753
 * @author Idan Levi 305562431
 *
 */
import java.util.HashMap;


public class CareTaker {
	private HashMap<Integer, Memento> swimmableMementoList; 
	private HashMap<Integer, Memento> plantesMementoList; 
	private static int counter=0;
	public final int objectID;
	
	public CareTaker()
	{
		this.objectID=++counter;
		swimmableMementoList=new HashMap<Integer, Memento>(); 
		plantesMementoList=new HashMap<Integer, Memento>(); 
		
	}
	public int getID(){return objectID;}

	public void addSwimmableMemento(int idObj,Memento state)
	{
		swimmableMementoList.put(idObj, state);

	}
	public void addImmobileMemento(int idObj,Memento state)
	{
		plantesMementoList.put(idObj, state);
	}
	
	public Memento getSwimmableMemento(int index){
		return swimmableMementoList.get(index);
	}
	
	public Memento getImmobileMemento(int index){
		return plantesMementoList.get(index);
	}
	public HashMap<Integer, Memento> getSwimmableMementoList(){return swimmableMementoList;}
	public HashMap<Integer, Memento>getPlantesMementoList(){return plantesMementoList;}

}

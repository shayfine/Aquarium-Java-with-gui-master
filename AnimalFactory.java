
/**
 * animal factory class return the specific object by getting message (string)
 * @author Adir Zoari 203002753
 * @author Idan Levi 305562431
 */
import java.awt.Color;


public class AnimalFactory implements AbstractSeaFactory {

	private int size,horSpeed,verSpeed,foodFreq;
	private Color col;
	private AquaPanel panel;
	public AnimalFactory(AquaPanel panel,int size,int horSpeed,int verSpeed,Color col,int foodFreq)
	{
		this.panel=panel;
		this.size=size;
		this.horSpeed=horSpeed;
		this.verSpeed=verSpeed;
		this.col=col;
		this.foodFreq=foodFreq;
	}
		


	/**
	 * interface with one method to create sea creature :swimmable\immobile object
	 * @return the object by the message 
	 *
	 */
	@Override
	public SeaCreature produceSeaCreature(String type){
		if(type.equalsIgnoreCase("Fish")){
			return new Fish(panel,size,horSpeed,verSpeed,col,foodFreq); 
		}
			
		else if(type.equalsIgnoreCase("Jellyfish"))
			return new Jellyfish(panel,size,horSpeed,verSpeed,col,foodFreq);
			
		return null;
	}
		

}

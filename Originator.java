// design patterm Memento
// לבדוק בסוף אם צריך את המחלקה הזו 

/*import java.awt.Color;

public class Originator {
	private int idObj,size,x_front,y_front,horSpeed=0,verSpeed=0;
	private Memento state;
	private Color col;
	private String typeAnimal;
	
	
	public void setState(int idObj,Color col,int size,int x_front,int y_front,int horSpeed,int verSpeed,String typeAnimal)
	{
		this.idObj=idObj;
		this.col=col;
		this.size=size;
		this.x_front=x_front;
		this.y_front=y_front;
		this.verSpeed=verSpeed;
	}
	
	public Memento saveSwimStateToMemento()
	{
		if(typeAnimal=="Fish" || typeAnimal=="Jellyfish")
			return new Memento(idObj,col,size,x_front,y_front,horSpeed,verSpeed);
		
		if(typeAnimal=="Laminaria" || typeAnimal=="Zostera")
			return new Memento(idObj,col,size,x_front,y_front,horSpeed,verSpeed);
		
		return state;
	}
	

}
*/
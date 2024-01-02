/**
 *Singelton class create object of worm and enable to create once a time object after each eating.
 * ---abstract factory design pattern----
 * @author Adir Zoari 203002753
 * @author Idan Levi 305562431
 */
public class Singleton {
	private static Singleton instance = null;
	
	/* A private Constructor prevents any other * class from instantiating.*/
	
	/* Static 'instance' method */
	public static Singleton getInstance(){
	
		if (instance==null){
			System.out.println("creating");
			instance=new Singleton();
		}
		
		return instance;
	}
	
	//intialize the instance to null after the thread ate the instance - worm
	public static void set(){
		if(instance!=null){
			instance=null;
		}
	}
	public static Singleton get()
	{
		return Singleton.instance;
	}
			
	
		
			
	
}

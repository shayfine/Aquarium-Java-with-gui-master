/**
 * interface with one method to get swimmable object and change his status - hungry or satiated
 * @author Adir Zoari 203002753
 * @author Idan Levi 305562431
 */

public interface HungerState {

	public void doAction(Swimmable objSwim);
	public String toString();

	

}

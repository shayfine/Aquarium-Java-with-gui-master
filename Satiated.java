/**
 * satiated class has 2 methods, get swimmable object and change his status to satiated
 * @author Adir Zoari 203002753
 * @author Idan Levi 305562431
 */
public class Satiated implements HungerState {

	@Override
	public String toString(){
		return "Satiated";
	}

	@Override
	public void doAction(Swimmable objSwim) {
		objSwim.setHungeryState(this);
	}

}

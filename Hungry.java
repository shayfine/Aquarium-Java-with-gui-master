/**
 * Hungery class has 2 methods, get swimmable object and change his status to hungry
 * @author Adir Zoari 203002753
 * @author Idan Levi 305562431
 */

public class Hungry implements HungerState {

	@Override
	public String toString(){
		return "Hungry";
	}

	@Override
	public void doAction(Swimmable objSwim) {
		objSwim.setHungeryState(this);
	}

}

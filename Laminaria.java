/**
 * Laminaria class inherite from Immobile and implements all the methods
 * @author Adir Zoari 203002753
 * @author Idan Levi 305562431
 */

import java.awt.Graphics;

public class Laminaria extends Immobile {

	public Laminaria(AquaPanel panel,int size,int x,int y) {
		super(panel,size,"Laminaria",x,y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawCreatrue(Graphics g) {
		g.setColor(colorr);
		g.fillArc(x-size/20, y-size, size/10, size*4/5,0, 360);
		g.fillArc(x-size*3/20, y-size*13/15, size/10, size*2/3,0, 360);
		g.fillArc(x+size/20,y-size*13/15,size/10, size*2/3, 0, 360);
		g.drawLine(x, y, x, y-size/5);
		g.drawLine(x,y,x-size/10,y-size/5);
		g.drawLine(x, y, x+size/10, y-size/5);

	}

}

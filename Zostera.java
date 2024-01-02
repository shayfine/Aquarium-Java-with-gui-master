/**
 * Zostera class inherite from Immobile and implements all the methods
 * @author Adir Zoari 203002753
 * @author Idan Levi 305562431
 */


import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Zostera extends Immobile{
	public Zostera(AquaPanel panel,int size,int x,int y) {
		super(panel,size,"Zostera",x,y);
		// TODO Auto-generated constructor stub
	}


	 
	@Override
	public void drawCreatrue(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g2.setColor(colorr);
		g.drawLine(x, y, x, y-size);
		g.drawLine(x-2, y, x-10, y-size*9/10);
		g.drawLine(x+2, y, x+10, y-size*9/10);
		g.drawLine(x-4, y, x-20, y-size*4/5);
		g.drawLine(x+4, y, x+20, y-size*4/5);
		g.drawLine(x-6, y, x-30, y-size*7/10);
		g.drawLine(x+6, y, x+30, y-size*7/10);
		g.drawLine(x-8, y, x-40, y-size*4/7);
		g.drawLine(x+8, y, x+40, y-size*4/7);
		g2.setStroke(new BasicStroke(1));	
		
	}

}

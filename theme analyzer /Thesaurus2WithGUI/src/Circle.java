

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Circle {
    double diameter;
    int x, y, width, height;

    /**
     * parameter needs to be tot size of text MAP, the percentage of 1 word in the whole
     * 1/total words
     * @param g
     */
    public Circle(int x, int y, int totSizeOfMap,  double wordRelativeToTotalTextSize) {
    	System.out.println("THE TOTAL SIZE MAP " + totSizeOfMap +  " the word relatice tot tot size is " +  wordRelativeToTotalTextSize );
    	this.diameter = 1000*wordRelativeToTotalTextSize;
    	if(diameter < 5) {
    		diameter = 5;
    	}
    	System.out.println("DIAMETER IS " + diameter);
        this.x = x;
        this.y = y;
    }

    /**
     * The size of each circle will be 
     * (area of box)*(1/size of text)
     * @param g
     */
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, diameter, diameter); //orginal diameter was each 5

        g2d.setColor(Color.blue);
        g2d.fill(circle);
    }

}
import java.awt.BorderLayout;
//import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//import DrawingShapesSwing.Circle;
//import DrawingShapesSwing.Coordinates;
//import DrawingShapesSwing.DynamicShapes2;
//import DrawingShapesSwing.Star;

//import javafx.scene.text.Font;

/**
 * Will draw a coordinate for every instance of theme in text
 * @author KAIK
 *
 */
public class DensityMap extends JPanel {


  
	private List<Object> shapes = new ArrayList<>();
   // private Random random = new Random();
    
    //arrCo is the map of every coordinate inside of graph....stays consistent based on xAxis and yAxis
    private static ArrayList<Coordinates> arrCo = new ArrayList<Coordinates>();
    private static int xAxis = 400;
    private static int yAxis = 400;
    static int mapSize = xAxis*yAxis;
    static double diameter;
    //coordinatesMap adds the specific coordinates of each instance of Theme in text
    private static ArrayList<Integer> coordinatesMap = new ArrayList<Integer>();

    /**
     * constructor 
     * @param theme
     * @param entry
     * @param text
     * @param usersChoice
     * @param synOrAnt
     */
    public DensityMap(String shape, ScanText st) {// String theme, SynAnt entry, String text, 
    		//String usersChoice, String synOrAnt)  {
    	System.out.println("TOTAL WORDS IS"  + st.getTotalWords());
    	this.diameter = (double) (1/ (double) (st.getTotalWords()));
    	int size = 0;
    	if(st.getTotalWords() <= 500) {
    		size = 100;
    	}
    	setBackground(Color.BLACK);
        setPreferredSize(new Dimension(xAxis+size, yAxis+size));
        
       // createArr();
     

    
    switch (shape) {
    case "Circles":
        for (int j = 0; j < coordinatesMap.size(); j++) {
        	//int loc =  (390 * al.get(j));
        //	System.out.println(  (int) (390 * al.get(j)));
        	//System.out.println(arrCo.get(coordinatesMap.get(j)).print();
            addCircle(arrCo.get(coordinatesMap.get(j)).getX(),
            		arrCo.get(coordinatesMap.get(j)).getY());
            


    }
    }
        
    }
    
//    public String densityAreaCirclesWithLabelStart(int i, String shape) { //ArrayList<Double> al) {
//        setBackground(Color.BLACK);
//        setPreferredSize(new Dimension(xAxis, yAxis));
//        
//        createArr();
     

    

    /**
     * Creates an arrayList of all points in the density graph
     */
    public static void createArr() {
    	//int totalPoints = xAxis*yAxis;
    	for(int i=0; i <= xAxis; i++) {
    		for(int k = 0; k <= yAxis; k++) {
    			Coordinates co = new Coordinates();
    			co.setX(k);
    			co.setY(i);
    			//co.print();
    			arrCo.add(co);
    		}
    		
    	}
    }
    
    /**
     * calculates the points in the density map where circles will appear
     * then adds it to coordinatesMap arrayList
     * @param st
     */
    public static void  calculatePoint(ScanText st) {
//    	System.out.println(st.getIndexOfWords().size());
    	coordinatesMap.clear();
    	double factor =  (((double)st.getTotalWords()/((double) xAxis*yAxis)));
    	//double factor = (double) (xAxis*yAxis) / ((double) tfr.getTotalWords());
    	for(int i = 0; i < st.getIndexOfWords().size(); i++) {
    	System.out.println("THE LOCATION IS " + st.getIndexOfWords().get(i));
    	System.out.println(factor);
    	// factor = 100/160000
    	// int position = foundWord / factor
    	double pos2 = ((double) (st.getIndexOfWords().get(i))/factor);
    	int pos = (int) pos2; // (tfr.getAl2().get(i)/factor);
    	//  turn it into an int
    	// put that word into coordinate
    	arrCo.get(pos).print();
    	System.out.println("The " + i + " position is " + pos + " the pos is " + st.getIndexOfWords().get(i));
    	coordinatesMap.add(pos);
    	
    	for(Integer j : coordinatesMap) {
    	System.out.println("the coord is " + i);
    	}
    	}
    	
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Object s : shapes) {
            if (s instanceof Circle) {
                ((Circle) s).draw(g);
            } else if (s instanceof Star) {
                ((Star) s).draw(g);
            }
        }
    }

    public void addCircle(int maxX, int maxY) {
    	System.out.println("the map size is " + mapSize + ".the diameter size is "  + diameter);
        shapes.add(new Circle(maxX, maxY, mapSize, diameter));                       //random.nextInt(maxX), random.nextInt(maxY)));
        repaint();
    }
    
    
//    //ORIGINAL
//    public void addCircle(int maxX, int maxY, double location) {
//        shapes.add(new Circle((int) (location*maxX), (int) (location*maxY), mapSize, diameter));
//        System.out.println("the map size is " + mapSize + ".the diameter size is "  + diameter);//random.nextInt(maxX), random.nextInt(maxY)));
//        repaint();
//    }

    public static void main(String[] args) {

////        String shapeAmount = JOptionPane.showInputDialog(null,
////                "How many shapes?", "Random Shapes...", JOptionPane.PLAIN_MESSAGE);
////        int amount = Integer.parseInt(shapeAmount);
//    	TextFileRead tfr = new TextFileRead("love", "RomeoJuliet.txt") ; // "RomeoJuliet.txt");
//    	//tfr.print();
//    	String theme = "love";
//    	tfr.indexOf("love");
//    	//densityAreaCircles dac = new densityAreaCircles();
//    	//tfr.printArr();
//    	createArr();
//    	calculatePoint(tfr);
//    	
//
//        String shapes[] = {"Stars", "Circles", "Both"};
//        String shape = "Circles";
////        		(String) JOptionPane.showInputDialog(null,
////                "Pick the shape you want", "Random Shapes...",
////                JOptionPane.PLAIN_MESSAGE, null, shapes, shapes[0]);
//       // JPanel panel = new JPanel();
//       // String theme = "love";
//        BorderLayout layout = new BorderLayout();
//        JPanel panel = new JPanel();
//        JLabel label = new JLabel("Density map of \"" + theme + "\" in given text. Top_left is first line");
//        label.setBackground(Color.BLACK);
//        label.setForeground(Color.YELLOW);
//        label.setOpaque(true);
//        
//        JFrame frame = new JFrame();
//        frame.setLayout(layout);
//        frame.add(new DynamicShapes2(200, shape), BorderLayout.CENTER);
//        frame.add(label, BorderLayout.NORTH);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//        
//
//    }
    	String theme = "love";
    	SynAnt entry = new SynAnt();
    	entry.addAnt("hatred");
    	//SynAnt entry = "text";
    	String text = "love hatred keeping love love hatred moose kitten worms hatred love";
    	String usersChoice = "text";
    	String synOrAnt = "ANT";
  //  DensityMap dcl = new DensityMap(theme, entry, text, usersChoice, synOrAnt);	
    }
}


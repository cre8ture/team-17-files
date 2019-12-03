//TO DO
//fix densityMap so it finds all instances of what I need
//with love only finding 2 in sentence

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//import DrawingShapesSwing.TextFileRead;
//import DrawingShapesSwing.densityAreaCirclesWithLabel3;

/**
 * this initiates the densityMap
 * @author info
 *
 */
public class IniDensityMap {
    public IniDensityMap(ScanText st) {
    	IniDensityMapStart(st);
    }
    
    /**
     * will initiate the density map using static method
     * @param st
     */
	public static void IniDensityMapStart(ScanText st){

  	DensityMap.createArr();
  	DensityMap.calculatePoint(st);
  	

      String shapes[] = {"Stars", "Circles", "Both"};
      String shape = "Circles";
//      		(String) JOptionPane.showInputDialog(null,
//              "Pick the shape you want", "Random Shapes...",
//              JOptionPane.PLAIN_MESSAGE, null, shapes, shapes[0]);
     // JPanel panel = new JPanel();
     // String theme = "love";
      BorderLayout layout = new BorderLayout();
      JPanel panel = new JPanel();
      JLabel label = new JLabel("Density map of \"" + st.getKeyWord() + "\" in given text. Top_left is first word");
      label.setBackground(Color.BLACK);
      label.setForeground(Color.YELLOW);
      label.setOpaque(true);
      
      JFrame frame = new JFrame();
      frame.setLayout(layout);
      frame.add(new DensityMap (shape, st), BorderLayout.CENTER);
      frame.add(label, BorderLayout.NORTH);
     // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocation(100, 300);
      frame.pack();
      //frame.setLocationRelativeTo(null);
      frame.setVisible(true);
      

	}
public static void main(String[] args) {
	String theme = "love";
	SynAnt entry = new SynAnt();
	entry.addAnt("hatred");
	//SynAnt entry = "text";
	
	FileChooser fc = new FileChooser();
	fc.open();
	String text = fc.textFromFile;
	//TextFileRead tfr = new TextFileRead("RomeoJuliet");
	
	//String 
	//text = "Paste text to analyze here poopy hatred love moose cat density hatred";
	String usersChoice = "text";
	String synOrAnt = "ANT";
	//System.out.println(text);
	ScanText st = new ScanText(theme, entry, text, usersChoice, "SYN");
	System.out.println("THE INDEX OF WORDS IS " + st.getIndexOfWords());
	IniDensityMap id = new IniDensityMap(st);
}
}

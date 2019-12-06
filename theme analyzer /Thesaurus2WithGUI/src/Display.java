import java.awt.Dimension;
import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFrame;

import openNLP.trainingCategoryBayes;

/**
 * This class will display the top results from the runner
 * author: Kai K
 */
public class Display {
	
private String displayToConsole = "";
//private String ml = "none";
	
	
	/**
	 * https://javainterviewpoint.com/how-to-sort-hashmap-in-java-by-keys/
	 * This method finds the top results from the hashmap from ScanText
	 */
	public void sortResults(ArrayList<TextObject> linesArr, String ml) {
	//	Map<Double,TextObject> sortedMap = new TreeMap<Double,TextObject>(lines);
		
//	       
	        Map<Double,TextObject> sortedMap2 = new TreeMap<Double,TextObject>(Comparator.reverseOrder());
	        
	        
	        JFrame frameText = new JFrame("Passages that relate to user's theme");//"BorderLayoutDemo"); 
			
			TextArea textBox = new TextArea("", 20, 60);
			textBox.setSize(new Dimension(300, 100));
			 frameText.setLocation(700, 200);
			frameText.add(textBox);
			frameText.pack();
			frameText.setVisible(true);
			
            		
			
			Collections.sort(linesArr, Collections.reverseOrder());
			//System.out.println(linesArr);
			//if(linesArr.size() > 0) {
			for(TextObject to : linesArr){
	 			//System.out.println(to);
	 			if(to.getScore() > 0.0) {
	 				textBox.append("Related themes found: " + to.SynOrAndFound + ":\n");
	        		textBox.append("Line Number " +  to.getLineNum() + ": "
	  	        		  +  to.getLine() + "\r\n" + "\n");
	        		if(ml.equals("ML")) {
	        			trainingCategoryBayes ctb = new trainingCategoryBayes(to.getLine());
	        			textBox.append(ctb.getCategories());
	        			textBox.append("\n---------------------------------------------------------------\n");
	        		}
	 			
	 	   }
	 			
	 			
	 		
			}  
			
			if(textBox.getText().length() < 1) {
 				textBox.append("No related themes found in your chosen text");
			}
 			}

	
	
	
	/**
	 * @return the displayToConsole
	 */
	public String getDisplayToConsole() {
		return displayToConsole;
	}
	
	
}

		
	


	
	
	

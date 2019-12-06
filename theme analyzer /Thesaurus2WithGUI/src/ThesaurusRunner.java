import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import openNLP.trainingCategoryBayes;
//import openNLPThemeProject.packages;
/**
 * This class initiates the scanning of the text once user enters theme
 * @author info
 *
 */
public class ThesaurusRunner {
static Display d; // = new Display().getDisplayToConsole();
static String displayText = "nothing to display";

public ThesaurusRunner() {
PassageFinderInterface2 pi = new PassageFinderInterface2();
}
	/**
	 * control center running entire program
	 * @param text
	 * @param userChoice
	 * @param tt
	 * @param theme
	 * @param synOrAnt
	 */
	 static void runThes(String text, String userChoice, tempThes tt, String theme, String synOrAnt, 
			 String experimentSelection) {
		
		SynAnt entry = IsolateThesaurusKeys.checkThemeInThesaurus(theme, tt);
		if(entry != null && synOrAnt.equals("SYN") || entry != null &&
				entry.getAnt() != null) {
		ScanText st = new ScanText(theme, entry, text, userChoice, synOrAnt);
		
		ArrayList<TextObject> linesArr = st.getLinesArr();
		d = new Display();
		d.sortResults(linesArr, experimentSelection);
		displayText = d.getDisplayToConsole();
		
		if(experimentSelection.equals("DD")) {
			IniDensityMap.IniDensityMapStart(st);
		}
		if(experimentSelection.equals("ML")) {
			//trainingCategoryBayes ctb = new trainingCategoryBayes();
		}
		
		}
		else{//(entry == null) {
			JFrame pane = new JFrame();
			JOptionPane.showMessageDialog(pane, "No Themes found. Please try another theme");
			System.out.println("No related themes found. Please another try a related theme");
		}
		//assert entry.getAnt() != null : "Not acceptable null value";
		
	
	}


	 
	 /**
	 * @return the displayText
	 */
	public String getDisplayText() {
		return displayText;
	}



/**
 * Main method desgined primarily for testing
 * @param args
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//        FileChooser fc = new FileChooser();
//        fc.open();
//        String text = fc.getTextFromFile();
//		String theme = "love";
//		//String text = "RomeoJuliet";
////		 text = "love is a dove and it don't dislike mean nothing to a dove. Draming of dawn with a fawn\r\n" + 
////		 		"They is love above kissing the sky estrangement like a shove bitterness.\r\n" + 
////		 		"Eat noodles and uncharitableness disaffection marriage infidelity disaffection.\r\n" + 
////		 		"wiiiggglless! puff!";
//		String userEntry = "file";
//		userEntry = "text";
//		String synOrAnt = "ANT";
//		tempThes tt = new tempThes("thesaurus.txt");
//		String es = "";
//		ThesaurusRunner tr = new ThesaurusRunner();
//        tr.runThes(text, userEntry, tt, theme, synOrAnt, es);
        
        PassageFinderInterface2 pi = new PassageFinderInterface2();
        
      //  System.out.println(tr.getDisplayText());
	}

}

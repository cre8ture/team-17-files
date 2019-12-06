//TO DO
//fix scoring so it accounts for percents using score method

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/**
 * This class scans text for passed in keyword
 * @author kaik
 *
 */
public class ScanText {
	private ArrayList<TextObject> linesArr = new ArrayList<TextObject>();
	private ArrayList<String> SynOrAndFound; 
//	private HashMap<Double, TextObject> lines = new HashMap<Double, TextObject>();
	private int lineNum = 0;
	private int location = 0;
	//private tempThes tt;//I dont think we need whole tt....only entry with theme in it
	private String keyWord;
	private SynAnt synAnt;
	private ArrayList<Integer> indexOfWords = new ArrayList<Integer>();
	private int totalWords;
	
	/**
	 * This will have to decide what type of command is given from GUI
	 * constructor will either have it read a text file, open API for Shiao
	 *note "entry will be a String, a filename, depending on userchoice
	 */
	public ScanText(String theme, SynAnt entry, String text, String usersChoice, String synOrAnt) {
		this.keyWord= theme;
		synAnt = entry;
//		if(usersChoice.equals("file")) {
//			readFile(text, synOrAnt);
//		}
		if(usersChoice.contentEquals("text") ) {
			readTextFromString(text, synOrAnt);
		}
	}
	
	/**
	 * Reads text using scanner with String
	 * @param userText
	 * @param synOrAnt
	 */
	public void readTextFromString(String userText, String synOrAnt) {
		
		Scanner in;
		    in = new Scanner(userText);
		    while (in.hasNext()) {
		    	lineNum++;
		    	String next = in.nextLine();
		    //	System.out.println(next);
		    	
		    	analyzeLine(next, synOrAnt);
		    	
		    
	}
	}
	
	/**
	 * open file using scanner
	 * MAY DELETE THIS?
	 * @param filename
	 */
	public void readFile(String filename, String synOrAnt) {
			Scanner in;
			try {
			    in = new Scanner(new FileReader(filename));
			    while (in.hasNext()) {
			    	lineNum++;
			    	String next = in.nextLine();
			    	totalWords += next.length();
			    	//System.out.println(next);
			    	analyzeLine(next, synOrAnt);
			    	
			    }
				
			} catch (FileNotFoundException e) {
				   System.out.println("HashMap theasaurus read incorrectly");
				    e.printStackTrace();
				}
	}
	
	/**
	 * will analyze the line of text to find word
	 * @param line
	 */
	public void analyzeLine(String line, String synOrAnt) {
		SynOrAndFound = new ArrayList<String>();
		double score = 0;
		double keyWord = 0;
		String cleanLine = line.toLowerCase();
		//clear out all formatting for words turns into word array
		String cleaned[] = TextClean.cleaner(cleanLine); 
		
		//add to totalWords each time
		totalWords += cleaned.length;
		
		//go through line now that all puncutation cleaned out
		//one word at a time
		for(String s : cleaned) {
			if(synOrAnt.equals("SYN")) {
				
				//check if word s is the keyword
			if(scoreKeyWord(s) == 1) {
				keyWord++;
			}
			//if word s is not keyword then see if it is any other synonym
			else {
			score += scoreSyn(s);
			}
			}
			if(synOrAnt.equals("ANT")) {
				score += scoreAnt(s);//score each word
				//System.out.println("Each new score for word is " + score);// scoreAnt(s));
			}
		}
		
		//System.out.println(lines);
		
		TextObject to = new TextObject(line, lineNum, SynOrAndFound);
		if(synOrAnt.equals("SYN")) {
			
			//add the score to the line hashmap along with textObject
			score = score*(synAnt.getSynonymScore());
			score = score + keyWord*synAnt.getKeyWordScore();
			to.setScore(score);
			//System.out.println(score);
			//lines.put(score, to);
			linesArr.add(to);
		}
		
		if(synOrAnt.equals("ANT")) {
		score = score*(synAnt.getAntonymScore());
		to.setScore(score);
		//System.out.println(score + " " + synAnt.getAntonymScore());
		//System.out.println("the score be this raw " + score);
  //lines.put(score, to);
		linesArr.add(to);
		}
		//new lines????  \r and \n. 
	}
	
	/**
	 * Find instances of the words with keyWords, synonyms 
	 * adds one point to line for each word found
	 * @param 
	 */
	public int  scoreSyn(String word) {
		location++;
		for(String s : synAnt.getSyn()) { //need to include keyWord in there
			//location++;
			//System.out.println("THE WORD IN SENTENCE IS " + s);
			
			if(s.equals(word))
			{
				SynOrAndFound.add(s);
				indexOfWords.add(location);
				//System.out.println("FIND ME A RIVER " + location + " THE WORD IS " + word);
				return 1;
						
				}
	}
		return 0;
	}
	
	/**
	 * check in keyword is inside of line
	 */
	public int scoreKeyWord(String word){
		if(word.equals(keyWord)) {
			
			location++;
			//System.out.println(word + " at location " + location);
			SynOrAndFound.add(keyWord);
			indexOfWords.add(location);
			return 1;
		}
		return 0;
	}
	
	
	/**
	 * Find instances of the words with antonyms 
	 * @param 
	 */
	public int scoreAnt(String word) {
		location++;
		for(String s : synAnt.getAnt()) { 
			//location++;
			//System.out.println(location);
			if(word.equals(s))
			{
				SynOrAndFound.add(s);
				indexOfWords.add(location);
				//System.out.println("it's at " + location);
				return 1;
						
				}
	}
		return 0;
	}
	
//	
//	/**
//	 * @return the lines
//	 */
//	public HashMap<Double, TextObject> getLines() {
//		return lines;
//	}

	
	
	
	
	/**
	 * @return the indexOfWords
	 */
	public ArrayList<Integer> getIndexOfWords() {
		return indexOfWords;
	}

	
	
	/**
	 * @return the keyWord
	 */
	public String getKeyWord() {
		return keyWord;
	}

	/**
	 * @return the location
	 */
	public int getLocation() {
		return location;
	}

	/**
	 * @return the totalWords
	 */
	public int getTotalWords() {
		return totalWords;
	}
	
	

	/**
	 * @return the linesArr
	 */
	public ArrayList<TextObject> getLinesArr() {
		return linesArr;
	}

	/**
	 * main for testing
	 * @param args
	 */
	public static void main(String[] args) {
		//(String theme, SynAnt entry, String text, String usersChoice, String synOrAnt
		String theme = "supernal";
		SynAnt entry = new SynAnt();
		entry.addSyn("heavenly");
		entry.addSyn("father");
		//SynAnt entry = "text";
		String text = "supernal SynAnt heavenly father \n [syn=[celestial, heavenly], \n heavenly \n SynAnt heavenly father [syn=[celestial, heavenly] ...\n heavenly \\n SynAnt heavenly father butt \n heavenly \n SynAnt heavenly father [syn=[celestial, heavenly]kooooooow3 \n o2l [syn=[celestial, heavenly]"; 
		String usersChoice = "text";
		String synOrAnt = "SYN";
		//System.out.println("WORKING???????");
		//System.out.println(entry.getSyn());
		ScanText st = new ScanText(theme, entry, text, usersChoice, synOrAnt);
		//System.out.println(st.getIndexOfWords());
		Display d = new Display();
		
		//System.out.println(st.getLines().size());
		//System.out.println(st.getLines());
		System.out.println(st.getLinesArr());
		System.out.println(st.getLinesArr().size());
		
        // Collections.sort(st.getLinesArr(), new CompareScoresSort());
         
		Collections.sort(st.getLinesArr(), Collections.reverseOrder());
		System.out.println(st.getLinesArr());
		for(TextObject to : st.getLinesArr()){
 			System.out.println(to);
 	   }
		//d.sortResults(st.getLines(), st.getLinesArr());
	}
}

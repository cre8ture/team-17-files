import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * This class currently reads in antonyms 
 * using SynAnt helper to store the info for each keyword
 * @author info
 *
 */
public class tempThes {
	String status = "";
	HashMap<String, SynAnt> thesaurus = new HashMap<>();
	ArrayList<String> justKeyWords = new ArrayList<String>();
	private int keyWordsInFile;
	
	tempThes(String filename){
		
		readFile(filename);
	}
	
	
	
/**
 * This will read the file passes to it and turn the file into
 * a thesaurus hashmap
 * @param filename
 */
	public void readFile(String filename) {
			Scanner in;
			String keyWord = "";
			
			SynAnt synAnt = new SynAnt();
			// read in thesaurus
			try {
			    in = new Scanner(new FileReader(filename));
			    while (in.hasNext()) {
			    	
			    	
			    	String next = in.nextLine().toLowerCase(); 
			    //  System.out.println(next);
			    	if(!next.contains("ant: none found.")) {
			    		if(next.contains("=")) {
			    			keyWordsInFile++;
			    		}
			    		
			    	//find with KEYWORDS:
					if (next.contains("key:")) { // look for "KEY" in the scanned line
						
					   if(next.contains(".")) {keyWord = next.substring(5, next.indexOf("."));}
					   
					 
					   else {
						   keyWord = next.substring(5, 5 + next.indexOf(" "));
						
					   }
					   if(keyWord.contains("_")) {keyWord = keyWord.substring(0, keyWord.indexOf("_"));}
					   if(keyWord.contains(" ")) {keyWord = keyWord.substring(0, keyWord.indexOf(" "));}
			    	 
			    	   synAnt = new SynAnt(); //initialize a new SynAnt
			    	  if(!thesaurus.containsKey(keyWord)) {
			    	  thesaurus.put(keyWord, synAnt);}
			    	   status = "syn";
			    	   
					}
					
					//--------------END OF KEYWORDS---------
					
					
					//--------------SEARCH FOR SYN
					if(next.contains("syn:") || (status.equals("syn") && !next.contains("key: ") &&
							!next.contains("=")) && !next.contains("ant: ")) {
						if(next.contains("syn:")) {
						next = next.substring(5);
						String[] lineWords = next.split(" "); //turn it into an array of words
						addWords(lineWords, synAnt, keyWord);}
						else if(!next.contains("syn:")){
							String[] lineWords = next.split(" "); //turn it into an array of words
							addWords(lineWords, synAnt, keyWord);}
						}
					
					
					
					
					
					
					//-------------Search for antonymes
					//find with antonyms
					
					if (next.contains("ant:") || (status.equals("ant") && !next.contains("="))) {
						if(next.contains("ant")) {
						status = "ant";
						next = next.substring(5); //get all the words after "ANT"
						String[] lineWords = next.split(" "); //turn it into an array of words
						addWords(lineWords, synAnt, keyWord);}
						else if(status.equals("ant") && !next.contains("=")) {
							String[] lineWords = next.split(" ");
							addWords(lineWords, synAnt, keyWord);
						}
//						for(String s: lineWords) {
//							if(s.indexOf("{") > -1) { //delete words with {}...if they don't have this}
//								s = s.replaceAll("[^a-zA-Z ]", "").toLowerCase(); //split("\\s+"); //delete all non letters and make lowercase
//								synAnt.addAnt(s); //add the word into the antonym as long as it's cleaned
//					
					}
					if(next.contains("=")) {
						synAnt.score();
					}
			    
			    }
			    }
			    } catch (FileNotFoundException e) {
			   System.out.println("HashMap theasaurus read incorrectly");
			    e.printStackTrace();
			}
					
			    }
			    	
					
					//thesaurus.put(keyWord);
			    	
			    	/**
			    	 * cleans and adds words into synAnt object
			    	 * BRIE THIS METHOD USES REGEZ TO TAKE AWAY ALL NON LETTERS
			    	 * @param lineWords
			    	 * @param synAnt
			    	 */
					private void addWords(String[] lineWords, SynAnt synAnt, String keyword) {
						for(String s: lineWords) {
							if(!s.contains("[")) { //delete words with {}...if they don't have this}
								s = s.replaceAll("[^a-zA-Z ]", "").toLowerCase(); //split("\\s+"); //delete all non letters and make lowercase
								//System.out.println("word is " + s);
								if(status.equals("ant")) {
								thesaurus.get(keyword).addAnt(s);   //synAnt.addAnt(s); //add the word into the antonym as long as it's cleaned
								//System.out.println(keyword);
								}
								else
								{
									thesaurus.get(keyword).addSyn(s);
								}
								
						}
					}
				}

			
					/**
					 * CALL THIS TO GET ALL ENTRIES IN HASHMAP
					 * @return the thesaurus
					 */
					public HashMap<String, SynAnt> getThesaurus() {
						return thesaurus;
					}
					
					/**
					 * gets all keywords from class
					 * @return
					 */
					public ArrayList<String> getKeyWords(){
						if(thesaurus.size() > 0) {
							for (Entry<String, SynAnt> entry : thesaurus.entrySet()) {
								 System.out.println(entry.getKey() + " = " + entry.getValue());
								justKeyWords.add(entry.getKey());
							   
							}
						}
							else {
								System.out.println("no keyWords found in thesaurus");
							}
							System.out.println("NOTE: these won't be in alphabetical order");
						
						return justKeyWords;
					}
					
					
					
					/**
					 * @return the keyWordsInFile
					 */
					public int getKeyWordsInFile() {
						return keyWordsInFile;
					}



					/**
					 * Just for testing purposes
					 * @param args
					 */
					public static void main(String[] args) {
						tempThes t = new tempThes("thesaurus.txt");
					//	t.readFile("thesaurus.txt");
						//System.out.println("Size is " + t.getThesaurus().size());
						for (String s : t.getThesaurus().keySet()) {
						    System.out.println(s + " " + t.getThesaurus().get(s) + " the antonym score is " 
						+ t.getThesaurus().get(s).getAntonymScore() + " the synonym score is " +  t.getThesaurus().get(s).getSynonymScore()
					+ " the keyword score is " + t.getThesaurus().get(s).getKeyWordScore()); 
//////					
//							
//							System.out.println("the antonym score is "+ t.getThesaurus().get(s).getAntonymScore() + " the synonym score is " +  t.getThesaurus().get(s).getSynonymScore()
//								+ " the keyword score is " + 
//									t.getThesaurus().get(s).getKeyWordScore());;
						}
						System.out.println("NUM ORIGINAL WORDS " + t.getKeyWordsInFile());
						System.out.println(("NUM ONCE FILTERED IN NEW THES " + t.getThesaurus().size()));
						//System.out.println(t.getThesaurus().get("memoir"));
					}


}



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.PrintWriter;



/**
 * This will clean the sentences to allow us to put them into a text file
 * @author Kai K
 *
 */
public class TextClean {

	/**
	 * Removes all punctuation from the sentences\\
	 * @param dirtyText
	 * @return
	 */
	public static String[] cleaner(String dirtyText) {
		String[] words = dirtyText.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
	
	return words;
}
	
	/**
	 * take the text of a file, read it, then pass into new file
	 * @param fileIn
	 * @param fileOut
	 * @throws IOException 
	 */
	public static void fileWrite(String fileIn, String fileOut, String cat) throws IOException {
		String[] lineClean = null;
		File file = new File(fileOut);
		 FileWriter fw = new FileWriter (file, true);
		 StringBuilder  sb = new StringBuilder();
		try
		  {
		    BufferedReader reader = new BufferedReader(new FileReader(fileIn));
		    String line;
		    
		    while ((line = reader.readLine()) != null)
		    {
		    	 sb.append(line);
		    	 if(line.isEmpty()==true ||  reader.read() == -1) {
		    	 line = sb.toString();
		    	 lineClean = cleaner(line);
		    	 writeToFile(lineClean, fw, cat);
		    	 sb.setLength(0);
		    	 }
		    }
		    reader.close();
		  }
		  catch (Exception e)
		  {
		    System.err.format("Exception occurred trying to read '%s'.", fileIn);
		    e.printStackTrace();
		 
		  }
		
//PrintWriter pw = null;
//BufferedReader br = null;
//br = new BufferedReader ( new FileReader ("filename") );
   
   

	}
	/**
	 * writes the actual data to a training file
	 * @param str
	 * @param fw
	 * @param cat
	 * @throws IOException
	 */
	public static void writeToFile(String[] str, FileWriter fw, String cat) throws IOException {
		fw.write("\n" + cat);
		System.out.print("\n" + cat);
		for(String s : str) {
			fw.write(" " + s);
			System.out.print(" " + s);
			fw.flush();
			
		}
		//fw.close();
	}
	
	/**
	 * The main method that allows us to clean data and taqg it
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			fileWrite("revengeTrain.txt", "firstTrain.txt", "revenge"); // "newTraingText"+File.separator+"loveTrain.txt",
		} catch (IOException e) {
			System.out.println("uh oh");
			e.printStackTrace();
		}
	}
}

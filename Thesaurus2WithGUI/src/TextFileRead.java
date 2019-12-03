

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * This class translates text into a StringBuilder from a file
 * 
 * @author info
 *
 */
public class TextFileRead {
	private static StringBuilder sb = new StringBuilder();

	/**
	 * This is the constructor for fileReader
	 * 
	 * @param f
	 */
	TextFileRead(File f) {
		try {
			processFile(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This turns each char into a StringBuilder
	 * 
	 * @param c
	 */
	public static void processChar(char c) {
		sb.append(c);
		// return ;
	}

	/**
	 * This processes the file
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void processFile(File file) throws IOException {
		try (InputStream in = new FileInputStream(file); Reader reader = new InputStreamReader(in)) {

			int c;
			while ((c = reader.read()) != -1) {
				processChar((char) c);
			}
		}
	}

	/**
	 * This allows the file text to be printed into console for testing
	 */
	public void print() {
		System.out.println(sb.toString());
	}

	/**
	 * This method returns the StringBuilder as a string object
	 */
	public String toString() {
		return sb.toString();
	}
}

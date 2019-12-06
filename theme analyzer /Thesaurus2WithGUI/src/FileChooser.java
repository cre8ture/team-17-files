

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class opens a diologue box of the file explorer so that user can upload
 * a txt file to analyze
 * 
 * @author Kai Kleinbard And TutorialPoint Tutorial on Swing [adapted]
 *
 */
public class FileChooser extends JPanel implements ActionListener {
	JFileChooser fc;
	String textFromFile = "";
	// Create a file chooser
	// fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

	/**
	 * This method opens up the file explorer and limits the file types to text
	 * based files we are not sure if we can find a way to read PDFs or anything
	 * beyond txt files at the moment
	 */
	public void open() {
		fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "pdf", "txt", "docx", "doc");
		// JFileChooser fileChooser = new JFileChooser();
		fc.setFileFilter(filter);
		// fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
//		 fc.addChoosableFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
//		 fc.addChoosableFileFilter(new FileNameExtensionFilter("txt files", "txt"));
//	      fc.addChoosableFileFilter(new FileNameExtensionFilter("MS Office Documents", "docx", "xlsx", "pptx"));

		// Handle open button action.
		// if (e.getSource() == openButton) {
		int returnVal = fc.showOpenDialog(FileChooser.this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			TextFileRead tfr = new TextFileRead(file);
             textFromFile = tfr.toString();
			//tfr.print();
		}
	
	}

	/**
	 * This allows the file to open
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		FileChooser fc = new FileChooser();
		fc.open();
	}
	
	

	/**
	 * @return the textFromFile
	 */
	public String getTextFromFile() {
		return textFromFile;
	}

	/**
	 * main method primarily for testing
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		FileChooser fc = new FileChooser();
		fc.open();

	}
}



/*
 * BorderLayoutDemo.java
 *
 */
import javax.swing.*;
import javax.swing.text.DefaultCaret;

import com.finalproject.maven.quickstart.APIRunner;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;

//API
//import com.mashape.unirest.http.exceptions.UnirestException;
/**
 * This is the user interface GUI for passageFinder Kai Kleinbard, 591
 * kaik@seas.upenn.edu Team17 PassageFinder
 *
 */
public class PassageFinderInterface2 {
	public static boolean RIGHT_TO_LEFT = false;
	private static JCheckBox checkbox = new JCheckBox("Synonyms");
	private static JCheckBox checkbox2 = new JCheckBox("Machine Learning");
	private static JCheckBox checkbox3 = new JCheckBox("Display Density");
	private static JCheckBox checkbox4 = new JCheckBox("Antonyms");
	private static JLabel labelExperiments = new JLabel("Experiments: ");
	
	
	//private static String[] options = new String[] {"Search your own pasted text" ,  "Search your own file", 
			//"Search website", "Search Lyric Database"};
	 private static JComboBox<String> bookList;// = new JComboBox<>();//options);
	//choices
	public static  String synOrAnt = "SYN";
	public static String experimentalSelection = "none";
	public static String songOrBook = "none";
	
	private JPanel checkPanel = new JPanel();
	private JPanel bottomPane = new JPanel(new GridBagLayout());
	private static int numFound2 = 0;
	private Container pane = new Container();
	JFrame frame = new JFrame("BorderLayoutDemo");
	static String textAreaText = "Paste text to analyze here";
	static TextArea typeText; 
	static tempThes tt = new tempThes("thesaurus.txt");; // = new tempThes("thesaurus.txt");
	 private static JTextArea textFieldTheme = new JTextArea("", 1, 49);
	  
	  private static  String theme = textFieldTheme.getText();
	//private static JTextField textFieldTheme = new JTextField(50); 
	//private static String theme = textFieldTheme.getText();
	
	  //API
	  private HashMap<String, String> songMap;
	
	
	/**
	 * Constructor for the passageFinder GUI it initiates the frame
	 */
	public PassageFinderInterface2() {
        // tt = new tempThes("thesaurus.txt");
		/* Use an appropriate Look and Feel */
		try {
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		/* Turn off metal's use bold fonts */
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				addComponentsToPane((frame.getContentPane()));
			}
		});
		
		System.out.println("Jcombobox " + bookList);
		System.out.println("array in combox " );//+);// options);

	}

	/**
	 * This adds components into the Frame the user sees one panel at a time and
	 * then starts the frame
	 * 
	 * @param pane
	 */
	public void addComponentsToPane(Container pane) {
		this.pane = pane;
		// System.out.println("we workin!");

//        if (!(pane.getLayout() instanceof BorderLayout)) {
//            pane.add(new JLabel("Container doesn't use BorderLayout!"));
//            return;
//        }

		if (RIGHT_TO_LEFT) {
			pane.setComponentOrientation(java.awt.ComponentOrientation.RIGHT_TO_LEFT);
		}

		// TOP OF FRAME
		// create comboBox
		// PassageFinderInterface2 pi = new PassageFinderInterface2();
		comboBox();

		// MIDDLE PANEL: textArea
		textArea();

		// MIDDLE PANEL: checkBox
		checkBoxes();

		// BOTTOM PANEL: button and text
		textAndButton();

		// Create and set up the window.

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set up the content pane.
		// addComponentsToPane(frame.getContentPane());
		// Use the content pane's default BorderLayout. No need for
		// setLayout(new BorderLayout());
		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	// ------------------------------------------------------TOP OF
	// FRAME------------------------------------------
	/**
	 * This allows user to choose what search they want to run = what database they
	 * want to use
	 * 
	 * @return
	 */
	public void comboBox() {
//		  String[] options = new String[] {"Search your own pasted text" ,  "Search your own file", 
//				"Search website", "Search Lyric Database"};
//        
//		  for(String s : options) {
//			  bookList.addItem(s);
//		  }
		bookList = new JComboBox<String>();
		
		//bookList.addItem("moose");
		String[] options = new String[] {"Search your own pasted text" ,  "Search your own file", 
				"Search website", "Search Lyric Database"};
        
		  for(String s : options) {
			  bookList.addItem(s);
		  }
		
		bookList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				JComboBox<String> combo = (JComboBox<String>) event.getSource();
				String selectedBook = (String) combo.getSelectedItem();
				// str = selectedBook;
				if (selectedBook.equals("Search Lyric Database")) {
					
					songOrBook = "song";
					System.out.println(songOrBook);
					typeText.setText("-Type a theme into the lower theme bar and a list of related songs will appear \n-Then click a song to analyze for the theme");
					
				}
//				if (selectedBook.equals("Search Books Database")) {
//					
//					songOrBook = "book";
//					System.out.println(songOrBook);
//				}

////				if (selectedBook.equals("Search your own pasted text")) {
////					System.out.println("box should popup add yo!");
//					//JOptionPane.showMessageDialog(null, "Paste your text into text field"); // displays diologue prompt
////bookList.addActionListener(
////       new ActionListener(){
////           public void actionPerformed(ActionEvent e) {
////               JOptionPane.showMessageDialog(frame,
////                       "You've clicked OK button"
////                       );
////           }
////       });
//				}
				if (selectedBook.equals("Search your own file")) {
					System.out.println("file chooser should popup");
					FileChooser fc = new FileChooser();
					fc.open();
					songOrBook = "none";
					textAreaText = fc.getTextFromFile();
					typeText.insert(textAreaText, 0);
					//textArea();
				}
				
				if (selectedBook.equals("Search your own pasted text")) {
					//System.out.println("file chooser should popup");
					//FileChooser fc = new FileChooser();
					//fc.open();
					songOrBook = "none";
					//textAreaText = fc.getTextFromFile();
					//typeText.insert(textAreaText, 0);
					//textArea();
				}
				
				if (selectedBook.equals("Search website")) {
      		System.out.println("file chooser should popup");
					 String url = JOptionPane.showInputDialog(null,
				                "What is the URL?", "Random Shapes...", JOptionPane.PLAIN_MESSAGE);
					 try {
						 System.out.println(url);
						ReadWebPage rwb = new ReadWebPage(url);
						songOrBook = "none";
						String site =  rwb.getAdjusted();
						System.out.println(site);
						// System.out.println(ReadWebPage.scrapeSite(url));
						typeText.setText(rwb.getAdjusted());
						if(rwb.getAdjusted().length() < 1) {
							typeText.setText("Your chosen website does not allow us to display its text. \n "
									+ "Please try another site");
						}
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				        //String url = Integer.parseInt(shapeAmount);
			}
				
				
			//	String str = selectedBook;
			}
		});
		
		
//		 String shapeAmount = JOptionPane.showInputDialog(null,
//	                "How many shapes?", "Random Shapes...", JOptionPane.PLAIN_MESSAGE);
//	        int amount = Integer.parseInt(shapeAmount);

		
	//	return str;
		pane.add(bookList, BorderLayout.PAGE_START);

	}
	//// ------------------------------------END OF TOP
	//// PANEL-------------------------------------------

	/**
	 * This allows users to input text to analyze by synomyms/antonyms method
	 * 
	 * @return
	 */
	public void textArea() {
		
		//String textAreaText = "Paste text to analyze here";
		// ------------------------------------START OF LEFT MIDDLE
		// PANEL-----------------------
		// Make the center component big, since that's the
		// typical usage of BorderLayout.
		//TextArea typeText; // displayText;
		// default BorderLayot is used
		typeText = new TextArea(textAreaText, 20, 60);
		typeText.setSize(new Dimension(300, 100));
		
		// displayText = new TextArea();
		// displayText.setRows(10);
		// displayText.setColumns(20);
		// button = new JButton("Button 2 (CENTER)");
		// button.setPreferredSize(new Dimension(200, 100));

		pane.add(typeText, BorderLayout.LINE_START);
		// pane.add(typeText, BorderLayout.CENTER);
		
	}
//------------------------------------END OF LEFT MIDDLE PANEL-------------------------

//------------------------------------START OF RIGHT MIDDLE PANEL----------------------
	/**
	 * This allows user to select what types of experiments they want to run on the
	 * databases or files
	 * 
	 * @return
	 */
	public void checkBoxes() {
		
		// create new panel
		// JPanel checkPanel = new JPanel();
		// Container pane2;
		checkPanel.setLayout(new BoxLayout(checkPanel, BoxLayout.Y_AXIS));
		// add a checkbox
		// JCheckBox checkbox = new JCheckBox("None");
		// add to a container

		// buttonGroup -- this means that it will uncheck another
		ButtonGroup checkboxGroup = new ButtonGroup();
		checkboxGroup.add(checkbox2);
		checkboxGroup.add(checkbox3);

		ButtonGroup basicsGroup = new ButtonGroup();
		basicsGroup.add(checkbox);
		basicsGroup.add(checkbox4);

		// add checkboxes to panel
		checkPanel.add(labelExperiments);
		checkPanel.add(checkbox);
		checkPanel.add(checkbox4);
		checkPanel.add(checkbox2);
		checkPanel.add(checkbox3);

		// set state
		checkbox.setSelected(true);
		// check state
		
		//if (checkbox.isSelected()) {
			// do something...
			
			//synOrAnt = "SYN";
			checkbox.addActionListener(new ActionListener() {

				   @Override
				   public void actionPerformed(ActionEvent event) {
			// return str;
					    //checkbox = (JCheckBox) event.getSource();
					    if (checkbox.isSelected()) {
					    	
			     synOrAnt = "SYN";
			     System.out.println(synOrAnt);
					    }

					   }
					  });

		checkbox2.setSelected(false);
		// check state
		//if (checkbox2.isSelected()) {
			// do something...
			checkbox2.addActionListener(new ActionListener() {

				   @Override
				   public void actionPerformed(ActionEvent event) {
			// return str;
					    //checkbox = (JCheckBox) event.getSource();
					    if (checkbox2.isSelected()) {
					    	 checkbox2.setSelected(false);
					    	experimentalSelection = "ML";
			     System.out.println(experimentalSelection);
					    }
					    
					   }
					  });
			
		//}
		// JCheckBox checkbox3 = new JCheckBox("Display Density");
		// add to a container

		// set state
		checkbox3.setSelected(false);
		// check state
		//if (checkbox3.isSelected()) {
			checkbox3.addActionListener(new ActionListener() {

				   @Override
				   public void actionPerformed(ActionEvent event) {
			// return str;
					    //checkbox = (JCheckBox) event.getSource();
					    if (checkbox3.isSelected()) {
					    	
					    	experimentalSelection = "DD";
			     System.out.println(experimentalSelection);
					    }

					   }
					  });
			//experimentalSelection = "DD";
		//}
		checkbox4.setSelected(false);
		// check state
		//if  (checkbox4.isSelected()) {
			checkbox4.addActionListener(new ActionListener() {

				   @Override
				   public void actionPerformed(ActionEvent event) {
			// return str;
					    //checkbox = (JCheckBox) event.getSource();
					    if (checkbox4.isSelected()) {
					    	
			     synOrAnt = "ANT";
			     System.out.println(synOrAnt);
					    }

					   }
					  });
//			synOrAnt = "ANT";
//			System.out.println("ANT!!!!!!!");
		//}
		//// button = new JButton("5 (LINE_END)");
		pane.add(checkPanel, BorderLayout.LINE_END);
		//System.out.println("comboBox none is selected ");
	
	}

//---------------------------Middle Panels End-------------------------------------

//---------------------------Start of lower panel----------------------------------

	/**
	 * This allows user to click buttons to initiate the search or reset the system
	 * 
	 * @return
	 */
	public void textAndButton() {
		// Container whaa = pane;
		// String str = "";
		// create new panel
		  DefaultCaret caret = (DefaultCaret)textFieldTheme.getCaret();
		    caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
		

		bottomPane.add(textFieldTheme);
		
		JButton btnOK = new JButton("Search");
		btnOK.setSize(20, 10);
		// Add event handler for OK button
		btnOK.addActionListener(new ActionListener() {
			//int numFound;
            @Override
			public void actionPerformed(ActionEvent e) {
				String theme = textFieldTheme.getText(); // users keyWords

				String userChoice = "text";
				//String synOrAnt= "ANT";
				
				String textString = typeText.getText();
				//System.out.println(textString);
				
			    
				
				System.out.println(synOrAnt);
				
				
				if(songOrBook.equals("none")) {
				ThesaurusRunner.runThes(textString, userChoice, tt, theme, synOrAnt, experimentalSelection);
				}
				if(songOrBook.contentEquals("song")) {
					try {
						APIRunner api = new APIRunner(theme);
						songMap = api.getSongs();
						System.out.println(songMap);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(songMap);
					SongDisplay2 sd = new SongDisplay2(songMap, theme, synOrAnt, experimentalSelection);
					//SongDisplay2.main(null);
				}
				
				
				if (textString.length() == 0) {
					System.out.println(theme);
					JOptionPane.showMessageDialog(pane, "No synomys match");
					
				}
			
				//buttonPrint(44);
			}
		}

		);
		bottomPane.add(btnOK);
		
		//------------------RESET-----------------------------

		JButton reset = new JButton("Reset");
		reset.setSize(20, 10);
		// Add event handler for OK button
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(pane, "No Themes found. Please try another theme");
				typeText.replaceRange("", 0, typeText.getText().length());
			textFieldTheme.setText("");
			}
		});
		bottomPane.add(reset);
		pane.add(bottomPane, BorderLayout.PAGE_END);
//		System.out.println(numFound2);
//		return numFound2;
	}

	// button = new JButton("Long-Named Button 4 (PAGE_END)");

//----------------------------END OF BOTTOM PANE---------------------------------------

	/**
	 * This allows the button to return a value
	 * 
	 * @param found
	 * @return
	 */
	public int buttonPrint(int found) {
		System.out.println(found);
		return found;
	}

	
	

	
	
	/**
	 * @return the textAreaText
	 */
	public String getTextAreaText() {
		return textAreaText;
	}

	/**
	 * also will change textArea text
	 * @param textAreaText the textAreaText to set
	 * @return 
	 */
	public static void setTextAreaText(String textAreaText) {
		typeText.replaceRange(textAreaText, 0, typeText.getText().length());
		//return textAreaText;
	}

	/**
	 * @return the theme
	 */
	public static String getTheme() {
		//if(theme.length() > 0) {
	//}
		return theme;
	}

	/**
	 * @return the tt
	 */
	public static tempThes getTt() {
		return tt;
	}
	
	
	
	

	/**
	 * @return the synOrAnt
	 */
	public static String getSynOrAnt() {
		return synOrAnt;
	}
	
	

	/**
	 * @return the experimentalSelection
	 */
	public static String getExperimentalSelection() {
		return experimentalSelection;
	}

	/**
	 * main method that will not be needed once other classes are connected to GUI
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/* Use an appropriate Look and Feel */
		/*
		 * try { //UIManager.setLookAndFeel(
		 * "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		 * UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel"); } catch
		 * (UnsupportedLookAndFeelException ex) { ex.printStackTrace(); } catch
		 * (IllegalAccessException ex) { ex.printStackTrace(); } catch
		 * (InstantiationException ex) { ex.printStackTrace(); } catch
		 * (ClassNotFoundException ex) { ex.printStackTrace(); }
		 */
		/* Turn off metal's use bold fonts */
		// UIManager.put("swing.boldMetal", Boolean.FALSE);

		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
		PassageFinderInterface2 pi = new PassageFinderInterface2();
		
//		String theme = "love";
//		//String text = "RomeoJuliet";
////		 text = "love is a dove and it don't dislike mean nothing to a dove. Draming of dawn with a fawn\r\n" + 
////		 		"They is love above kissing the sky estrangement like a shove bitterness.\r\n" + 
////		 		"Eat noodles and uncharitableness disaffection marriage infidelity disaffection.\r\n" + 
////		 		"wiiiggglless! puff!";
//		String userEntry = "file";
//		String userChoice = "text";
//		String synOrAnt = "ANT";
//		tempThes tt = new tempThes("thesaurus.txt");
//		String es = "";
//		//ThesaurusRunner tr = new ThesaurusRunner();
//        //tr.runThes(text, userEntry, tt, theme, synOrAnt, es);
//        String testString = "love is a dove and it don't dislike mean nothing to a dove. Draming of dawn with a fawn\r\n" + 
//        		"They is love above kissing the sky estrangement like a shove bitterness.\r\n" + 
//        		"Eat noodles and uncharitableness disaffection marriage infidelity disaffection.\r\n" + 
//        		"wiiiggglless! puff!";
//		
		
		//ThesaurusRunner.runThes(testString, userChoice, tt, theme, synOrAnt, experimentalSelection);
		// pi.addComponentsToPane(pane);
		// createAndShowGUI();
//            }
//        });
	}
}
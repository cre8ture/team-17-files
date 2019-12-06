


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/**
 * This class sets up song display 
 * @author Kai
 *
 */
public class SongDisplay2 {
	private static ArrayList<String> songs; 
	private static HashMap<String, String> songMap; //this can be added later once shiao passes info here
	private static String lyrics; //we can analyze these
    private static String theme;
    private static String synOrAnt;
    private static String experimental;
	
    /**
     * Constructor for displaying songs that will connect to API
     * @param songMap
     * @param theme
     * @param synOrAnt
     * @param experimental
     */
	SongDisplay2(HashMap<String, String> songMap, String theme, String synOrAnt, String experimental){ //ArrayList<String> songs){
		this.songMap = songMap;
		this.theme = theme;
		this.synOrAnt = synOrAnt;
		this.experimental = experimental;
		
		   /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
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
        
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
	
	/**
	 * identify how the panel is organized
	 */
    public static boolean RIGHT_TO_LEFT = false;
    
    /**
     * add coomponents to the GUI
     * @param pane
     */
    public static void addComponentsToPane(Container pane) {
        
        if (!(pane.getLayout() instanceof BorderLayout)) {
            pane.add(new JLabel("Container doesn't use BorderLayout!"));
            return;
        }
        
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(
                    java.awt.ComponentOrientation.RIGHT_TO_LEFT);
        }
        
        JLabel label = new JLabel(" Select Song to Analyze for Theme of " + "\"" + theme + "\" ");
        Font font = new Font("Courier", Font.BOLD,14);
        label.setFont(font);
       // JButton button = new JButton("Button 1 (PAGE_START)");
       // pane.add(button, BorderLayout.PAGE_START);
        pane.add(label, BorderLayout.PAGE_START);
       
        JPanel songListButtons = new JPanel();
        // GridBagLayout.Y_AXIS)); //new BoxLayout(songListButtons, BoxLayout.Y_AXIS));
        //songListButtons.setColumns(1);
        GridLayout gridLayout = new GridLayout();
        gridLayout.setColumns(1);
       
        gridLayout.setRows(songMap.size());
        songListButtons.setLayout(gridLayout);

        for (Map.Entry<String, String> entry : songMap.entrySet()) {
		    //System.out.println(entry.getKey()
		    		addAButton(entry.getKey(), songListButtons);
		    

        
        }
        
       
        JPanel gb = new JPanel();
        gb.setLayout(new BorderLayout()); //BoxLayout(gb, BoxLayout.Y_AXIS));
        gb.add(songListButtons, BorderLayout.CENTER);
       //JButton button1 = new JButton("5 (LINE_END)");
       // songListButtons.add(button1);
       // pane.add(button, BorderLayout.LINE_END);
      pane.add(gb, BorderLayout.CENTER);
    }
    
    /**
     * add buttons to panel
     * @param text
     * @param container
     */
    private static void addAButton(String text, Container container) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
      
        
        
        button.addActionListener(new ActionListener() {
			//int numFound;

			public void actionPerformed(ActionEvent e) {
				//= songMap.get(text);
				lyrics = processSongs(songMap.get(text));
				
				
				System.out.println(text);
				System.out.println(lyrics);
				System.out.println(PassageFinderInterface2.getTheme());
//				ThesaurusRunner.runThes(lyrics, "text", PassageFinderInterface2.getTt(), 
//                 PassageFinderInterface2.getTheme(), PassageFinderInterface2.getSynOrAnt(), 
//                 PassageFinderInterface2.getExperimentalSelection());
				ThesaurusRunner.runThes(lyrics, "text", PassageFinderInterface2.getTt(), 
		                 theme, PassageFinderInterface2.synOrAnt, //PassageFinderInterface2.getSynOrAnt(), 
		                PassageFinderInterface2.experimentalSelection);// PassageFinderInterface2.getExperimentalSelection());
				PassageFinderInterface2.setTextAreaText("Song: " + text + "\n\n" + lyrics);
				//openTextArea(lyrics); //songMap.get(text));
			}
		}

		);
        
        container.add(button);
    }

    /**
     * displays the song's found passages in new panel
     * @param song
     */
    public static void openTextArea(String song) {
    	  JFrame frameText = new JFrame("Passages Related to Selected Theme of " + "\"" + theme + "\"");//"BorderLayoutDemo"); 
    	   JPanel panel2 = new JPanel();
    	   GridLayout gridLayout = new GridLayout();
           gridLayout.setColumns(2);
           gridLayout.setRows(1);
           panel2.setLayout(gridLayout);
			TextArea textLyricBox = new TextArea(song, 20, 40);
			textLyricBox.setSize(new Dimension(300, 100));
			System.out.println("LENGTH OF SONG IS!!" + song.length());
			if(song.length()==0) {
				song = "No matching lyrics for theme of  " + theme + " found";
			}
			TextArea textPassageBox = new TextArea(song, 20, 40);
			textPassageBox.setSize(new Dimension(800, 100));
			panel2.add(textLyricBox);
			panel2.add(textPassageBox);
			 frameText.setLocation(800, 400);
			frameText.add(panel2);
			//frameText.add(textPassageBox);
			frameText.pack();
			frameText.setVisible(true);
    }
 
     	
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        
        //Create and set up the window.
        JFrame frame = new JFrame("BorderLayoutDemo");
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());
        //Use the content pane's default BorderLayout. No need for
        //setLayout(new BorderLayout());
        //Display the window.
        frame.setLocation(400, 300);
        frame.pack();
        frame.setVisible(true);
    }
    
   public static String processSongs(String lyrics) {
//	   int indexLeftBracket = 0;
//	   int indexRightBracket = 0;
//	   indexLeftBracket = lyrics.indexOf("[");
//	   indexRightBracket = lyrics.indexOf("]");
	   StringBuilder sb = new StringBuilder();
	  // int indexCap = 0;
	   if(lyrics.length() != 0) {
	   for(int i = 0; i < lyrics.length(); i++) {
		   String currentLetter = lyrics.substring(i, i+1);
		   //indexLeftBracket = lyrics.indexOf("[", indexLeftBracket+1);
		   //indexRightBracket = lyrics.indexOf("]", indexRightBracket+1);
		   if(currentLetter.equals("]")) {
			   currentLetter += "\n";
		   }
		   if(Character.isUpperCase(currentLetter.charAt(0)) && !currentLetter.equals("I")) {
				 //  || currentLetter.equals("[")) {
			   currentLetter = "\n" + currentLetter;
		   }
		   sb.append(currentLetter);
	   }
	   }
	   else {
		   JFrame pane = new JFrame();
			JOptionPane.showMessageDialog(pane, "Song lyrics unavailable. Please another try a song");
			System.out.println("Song lyrics unavailable. Please another try a song"); 
	   }
	   return sb.toString();
   }
    
    /**
     * main for testing
     * @param args
     */
    public static void main(String[] args) {
//        /* Use an appropriate Look and Feel */
//        try {
//            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//        } catch (UnsupportedLookAndFeelException ex) {
//            ex.printStackTrace();
//        } catch (IllegalAccessException ex) {
//            ex.printStackTrace();
//        } catch (InstantiationException ex) {
//            ex.printStackTrace();
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//        /* Turn off metal's use bold fonts */
//        UIManager.put("swing.boldMetal", Boolean.FALSE);
//        
//        //Schedule a job for the event dispatch thread:
//        //creating and showing this application's GUI.
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });
//    }
    	HashMap<String, String> songMap = new HashMap<String, String>();
    	songMap.put("moooose", "bubbba-bubbbaaaaaa");
    	songMap.put("bugg on my rug", "bug on a rug /n it at my hug");
    	songMap.put("oscar the pouch", "missle toe monke puff");
    	songMap.put("tough dooodoo", "love is a dove and it don't dislike mean nothing to a dove. Draming of dawn with a fawn\r\n" + 
    			"They is love above kissing the sky estrangement like a shove bitterness.\r\n" + 
    			"Eat noodles and uncharitableness disaffection marriage infidelity disaffection.\r\n" + 
    			"wiiiggglless! puff!");
    	songMap.put("peanut", "gagaggoooogoo");
    	
    	SongDisplay2 SD = new SongDisplay2(songMap, "love", "ANT", "");
}
}
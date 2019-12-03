

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;

//import javax.swing.text.Document;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

/**
 * this Class reads the context of a url and returns a string of it
 * 
 * @author kaik
 *
 */
public class ReadWebPage {
	private  StringBuilder sb = new StringBuilder();
   private  String adjusted = "";
	ReadWebPage(String s) throws MalformedURLException, IOException {
		scrapeSite(s);
	}

	/**
	 * takes all info from website to print it
	 * @param s
	 * @return 
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public  void scrapeSite(String s) throws MalformedURLException, IOException {

		BufferedReader br = null;

		try {

			URL url = new URL(s);
			br = new BufferedReader(new InputStreamReader(url.openStream()));

			String line;

			// StringBuilder sb = new StringBuilder();
//            //try (InputStream in = new FileInputStream(file);
//                    Reader reader = new InputStreamReader(in)) {
//
			int value;
			while ((value = br.read()) != -1) {
				char c = (char) value; // read each char on website as int then turn
				// into char
				sb.append(c);
			}

//
//            while ((line = br.readLine()) != null) {
//
//                sb.append(line);
//                sb.append(System.lineSeparator());
//            }
			String cleanedSite = cleanSite(sb.toString());
			this.adjusted = cleanedSite.replaceAll("(?m)^[ \t]*\r?\n", "");
			//System.out.println(adjusted);
		} finally {

			if (br != null) {
				br.close();
			}
		}
		//return adjusted;
	}
	
	/**
	 * 
	 * cleans all the non letters out of the website text
	 * @throws IOException
	 */
	public static void scrapeSiteJSoup() throws IOException {
		Document doc = Jsoup.connect("http://shakespeare.mit.edu/macbeth/full.html").get();//"http://en.wikipedia.org/wiki/Main_Page").get();
		org.jsoup.select.Elements el = doc.select("div#mp-tfa");
		for (org.jsoup.nodes.Element e : el) {
		    System.out.println(e.text());
		}
	}

	/**
	 * parses site and preserves line breaks
	 * @param html
	 * @return
	 */
	public  String cleanSite(String html) {
	    if(html==null)
	        return html;
	    Document document = Jsoup.parse(html);
	    document.outputSettings(new Document.OutputSettings().prettyPrint(false));//makes html() preserve linebreaks and spacing
	    document.select("br").append("\\n");
	    document.select("p").prepend("\\n\\n");
	    String s = document.html().replaceAll("\\\\n", "\n");
	    return Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
	}
	
	
	
	/**
	 * @return the adjusted
	 */
	public  String getAdjusted() {
		return adjusted;
	}

	/**
	 * This main method is just for testing and will be deleted
	 * 
	 * @param args
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static void main(String[] args) throws MalformedURLException, IOException {
		ReadWebPage rwp = new ReadWebPage("http://shakespeare.mit.edu/macbeth/full.html");
		rwp.scrapeSiteJSoup();
		
	}
}
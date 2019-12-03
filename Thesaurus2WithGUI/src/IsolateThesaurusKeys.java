/**
 * This isolates the theme user selects from the thesaurus
 * @author Kai
 *
 */
public class IsolateThesaurusKeys {

	
	/**
	 * Check if there is theme in thesaurus else return null
	 * will return the entry if there is theme
	 * @param theme
	 * @param tt
	 * @return
	 */
	public static SynAnt checkThemeInThesaurus(String theme, tempThes tt){
		if(tt.getThesaurus().size() > 0) {
		if(tt.getThesaurus().containsKey(theme)) {
			SynAnt entry = tt.getThesaurus().get(theme);
			System.out.println("it's in! " + entry);
			return entry;
		}
		}
		return null;
	}
	
	/**
	 * temp main for testing...Transfer this to GUI
	 * @param args
	 */
	public static void main(String[] args) {
		

	}
}

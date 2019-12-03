/**
 * This class identities each synAnt objects scoring
 * @author Kai K
 *
 */
public class ScoreWords {
 private static double keyWordScore = 0;
 private static double antonymScore = 0;
 private static double synonymScore = 0;
 private static double keyWordBonus = 0.05;
 
 /**
  * This method will determine the synonym score
  * @param numberEntries
  */
	public static void synScore(int numberEntries) {
		
		if(numberEntries > 0) {
			//numberEntries+=1;
double avg = 100/numberEntries;
double addedValueForKeyWord = avg*(keyWordBonus);
//System.out.println("ADDED VALUE " + addedValueForKeyWord);

keyWordScore = avg + addedValueForKeyWord;
//System.out.println("KEYWORD " + keyWordScore);

double takeAway = keyWordBonus/numberEntries;
//System.out.println("THE NEW PERCENT TOTAL " + newPercentTotal);
synonymScore = avg - takeAway;
		}
		else {
			System.out.println("no words found");
		}

	}
	
	/**
	 * This method will determine the antonym score
	 * @param numberEntries
	 */
public static void antScore(int numberEntries) {
		
		if(numberEntries > 0) {
 antonymScore = 100/numberEntries;

		}
}

	/**
	 * @return the keyWordScore
	 */
	public static double getKeyWordScore() {
		return keyWordScore;
	}

	/**
	 * @return the antonymScore
	 */
	public static double getAntonymScore() {
		return antonymScore;
	}

	/**
	 * @return the synonymScore
	 */
	public static double getSynonymScore() {
		return synonymScore;
	}


}

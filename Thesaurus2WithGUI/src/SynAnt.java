import java.util.ArrayList;


/**creates an antonym/synonym object containing list of antonyms
 * @author info
 *
 */
public class SynAnt {
private ArrayList<String> syn = new ArrayList<String>();
private ArrayList<String> ant = new ArrayList<String>();
private  int antNum = 0; //amount of antonyms is 0 since keyword does not count
private  int synNum = 0; //amount of synonyms is one since keyword always counts
private double antonymScore = 0.0;
private double synonymScore = 0.0;
private double keyWordScore = 0.0;


public SynAnt() {//String word) {
	//ant.add(word);
}

/**
 * add antonym to object
 * @param word
 */
public void addAnt(String word) {
	antNum++;
	ant.add(word);
}

/**
 * add synomym to object
 * @param word
 */
public void addSyn(String word) {
	synNum++;
	syn.add(word);
	if(synNum > 0)
	{
		ScoreWords.synScore(synNum);
		 synonymScore = ScoreWords.getSynonymScore();
		 keyWordScore = ScoreWords.getKeyWordScore();
	}
}

public void score() {
	if(antNum > 0) {
		 ScoreWords.antScore(antNum);
		 antonymScore = ScoreWords.getAntonymScore();
	}
	//keyWordScore = ScoreWords.getKeyWordScore();
	//synonymScore = ScoreWords.getAntonymScore();
}


/**
 * @return the antNum
 */
public  int getAntNum() {
	return antNum;
}

/**
 * @return the synNum
 */
public  int getSynNum() {
	return synNum;
}

/**
 * to String method
 */
@Override
public String toString() {
	return "SynAnt [syn=" + syn + ", ant=" + ant + "]";
}

/**
 * prints the synAnt arraylists
 */
public void print() {
	System.out.println("SynAnt [syn=" + syn + ", ant=" + ant + "]");
}


/**
 * @return the ant
 */
public ArrayList<String> getAnt() {
	return ant;
}

/**
 * @return the syn
 */
public ArrayList<String> getSyn() {
	return syn;
}

/**
 * @return the antonymScore
 */
public double getAntonymScore() {
	return antonymScore;
}

/**
 * @return the synonymScore
 */
public double getSynonymScore() {
	return synonymScore;
}

/**
 * @return the keyWordScore
 */
public double getKeyWordScore() {
	return keyWordScore;
}




}

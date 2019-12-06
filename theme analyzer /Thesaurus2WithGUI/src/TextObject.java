import java.util.ArrayList;

/**
 * When text is read, each lines unique characteristics are stores in this class
 * will record score, location, etc
 * @author info
 *
 */
public class TextObject implements Comparable<TextObject> {
//private double lineScore = 0;
private String line = "";
private int lineNum = 0;
private double score;
private int numberOfEntries;
ArrayList<String> SynOrAndFound = new ArrayList<String>();

/**
 * constructor to build TO
 * @param lineScore
 * @param line
 * @param lineNum
 */
TextObject(String line, int lineNum, ArrayList<String> SynOrAndFound){
	//this.lineScore = lineScore;
	this.line = line;
	this.lineNum = lineNum;
	this.SynOrAndFound = SynOrAndFound;
	if(line.length()<0) {
		numberOfEntries++;
	}
}


/**
 * @return the lineScore
 */
//public double getLineScore() {
//	return lineScore;
//}


/**
 * @return the line
 */
public String getLine() {
	return line;
}

/**
 * to string method to print it out
 */
@Override
public String toString() {
	return "TextObject [line=" + line + ", lineNum=" + lineNum + ", score=" + score + "]";
}


//public int compareTo(TextObject comparestu) {
//	double compareScore=((TextObject)comparestu).getScore();
//	return Double.compare(x, y);// this.score-compareScore;
//}

/**
 * @return the lineNum
 */
public int getLineNum() {
	return lineNum;
}


/**
 * @return the score
 */
public double getScore() {
	return score;
}


/**
 * @param score the score to set
 */
public void setScore(double score) {
	this.score = score;
}



/**
 * @return the numberOfEntries
 */
public int getNumberOfEntries() {
	return numberOfEntries;
}


@Override
public int compareTo(TextObject o) {
    return new Double(score).compareTo( o.score);
}






}

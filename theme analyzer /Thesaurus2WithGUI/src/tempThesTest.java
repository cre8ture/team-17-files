
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class tempThesTest {

    @Test
    void testGetThesaurusSize() {
	tempThes t = new tempThes("thesaurus.txt");
	int size = t.getThesaurus().size();
	
	assertEquals(size, 5959);

    }
    @Test
    void testGetKeyWordsSize() {
	tempThes t = new tempThes("thesaurus.txt");
	int size = t.getKeyWordsInFile();
	
	assertEquals(size, 6228);

    }
    @Test
    void testNonexistantKeyWordForNull() {
	tempThes t = new tempThes("thesaurus.txt");
	SynAnt synonyms = t.getThesaurus().get("zipper");
	
	assertEquals(synonyms, null);

    }
    
    @Test
    void testExistantKeyWordforValuesNotNull() {
	tempThes t = new tempThes("thesaurus.txt");
	assertTrue(t.getThesaurus().containsKey("love") && (t.getThesaurus().get("love") != null));
	
    }
    @Test
    void testExistantKeyWordForSynAnt() {
	tempThes t = new tempThes("thesaurus.txt");
	SynAnt sa = t.getThesaurus().get("love");
	
	assertEquals(sa, "love");

    }

}

package openNLP;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class trainingCategoryBayesTest {
private String toTest = "If we shadows have offended, Think but this, and all is mended: That you have but slumbered here, While these visions did appear; And this weak and idle theme, No more yielding but a dream, Gentles, do not reprehend. If you pardon, we will mend.";
private trainingCategoryBayes ctb;

trainingCategoryBayesTest(){
ctb = new trainingCategoryBayes(toTest);
}
   @Test
	void testTrainingCategoryBayes() {
		assertEquals(ctb.ranBayes, true);
		//fail("Not yet implemented");
	}

	@Test
	void testGetBestCategory() {
		assertEquals(ctb.getBestCategory(), "betrayal");
		//fail("Not yet implemented");
	}

	@Test
	void categories() {
		assertEquals(ctb.category1, "love");
		
	}

	@Test
	void testGetProbabilityForThree() {
		assertEquals(ctb.getProbabilityForThree(), 0.4415709938344992);
	}

	@Test
	void testGetProbabilityForBetrayal() {
		assertEquals(ctb.getProbabilityForBetrayal(), 0.5582603057391264);
	}

}

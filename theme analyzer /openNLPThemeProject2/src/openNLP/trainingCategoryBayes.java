package openNLP;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
import opennlp.tools.doccat.DoccatFactory;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizer;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.ml.AbstractTrainer;
import opennlp.tools.ml.naivebayes.NaiveBayesTrainer;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;
 
/**
* oepnnlp version 1.7.2
* Training of Document Categorizer using Naive Bayes Algorithm in OpenNLP for Document Classification
* @author www.tutorialkart.com
*/
public class trainingCategoryBayes {
	private String categories;
	private String bestCategory;
	private double probabilityForThree;
	private double probabilityForBetrayal;
	public boolean ranBayes = false;
	public String category1 = "";
 
	public trainingCategoryBayes(String test){

        try {
            // read the training data
            InputStreamFactory dataIn = new MarkableFileInputStreamFactory(new File("firstTrain.txt")); //"train"+File.separator+"categoryKAIS.train"));    //"en-movie-category.train"));
            ObjectStream lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
            ObjectStream sampleStream = new DocumentSampleStream(lineStream);
            StringBuilder sb = new StringBuilder();
            // define the training parameters
            TrainingParameters params = new TrainingParameters();
            params.put(TrainingParameters.ITERATIONS_PARAM, 10+"");
            params.put(TrainingParameters.CUTOFF_PARAM, 0+"");
            params.put(AbstractTrainer.ALGORITHM_PARAM, NaiveBayesTrainer.NAIVE_BAYES_VALUE);
 
            // create a model from traning data
            DoccatModel model = DocumentCategorizerME.train("en", sampleStream, params, new DoccatFactory());
           sb.append("\nModel is successfully trained.\n");
           // System.out.println("\nModel is successfully trained.");
 
            // save the model to local
            BufferedOutputStream modelOut = new BufferedOutputStream(new FileOutputStream("model"+File.separator+"en-movie-classifier-naive-bayes.bin"));
            model.serialize(modelOut);
            //System.out.println("\nTrained Model is saved locally at : "+"model"+File.separator+"en-movie-classifier-naive-bayes.bin");
            sb.append("\nTrained Model is saved locally at : "+"model"+File.separator+"en-movie-classifier-naive-bayes.bin");
 
            // test the model file by subjecting it to prediction
            DocumentCategorizer doccat = new DocumentCategorizerME(model);
            String[] docWords = test.replaceAll("[^A-Za-z]", " ").split(" ");//"He jumped over the car, flipped his gun out and shot into the air. Charlie was ready for anything to fight and blast!".replaceAll("[^A-Za-z]", " ").split(" ");      //"Afterwards Stuart and Charlie notice Kate in the photos Stuart took at Leopolds ball and realise that her destiny must be to go back and be with Leopold That night while Kate is accepting her promotion at a company banquet he and Charlie race to meet her and show her the pictures Kate initially rejects their overtures and goes on to give her acceptance speech but it is there that she sees Stuarts picture and realises that she truly wants to be with Leopold".replaceAll("[^A-Za-z]", " ").split(" ");
            double[] aProbs = doccat.categorize(docWords);
 
            // print the probabilities of the categories
           
           // System.out.println("\n---------------------------------\nCategory : Probability\n---------------------------------");
            sb.append("\n---------------------------------\nCategory : Probability\n---------------------------------");
            
            for(int i=0;i<doccat.getNumberOfCategories();i++){
                //System.out.println(doccat.getCategory(i)+" : "+aProbs[i]);
            	sb.append("\n" + doccat.getCategory(i)+" : "+aProbs[i]+"\n");
            }
            
            probabilityForThree = aProbs[2];
            probabilityForBetrayal = aProbs[4];  
            category1=doccat.getCategory(0);
           // System.out.println("---------------------------------");
            sb.append("\n---------------------------------\n");
            //System.out.println("\n"+doccat.getBestCategory(aProbs)+" : is the predicted category for the given sentence.");
       sb.append("\n"+doccat.getBestCategory(aProbs)+" : is the predicted category for the given sentence.");
       
       bestCategory = doccat.getBestCategory(aProbs);
       
       //prettyPrint(sb.toString());
       categories = sb.toString();
       
        }
        catch (IOException e) {
            System.out.println("An exception in reading the training file. Please check.");
            e.printStackTrace();
        }
        ranBayes = true;
    }
	
	
	
	
	/**
	 * @return the bestCategory
	 */
	public String getBestCategory() {
		return bestCategory;
	}



	/**
	 * @return the categories
	 */
	public String getCategories() {
		return categories;
	}



	/**
	 * @return the probabilityForThree
	 */
	public double getProbabilityForThree() {
		return probabilityForThree;
	}




	/**
	 * @return the probabiityForBetrayal
	 */
	public double getProbabilityForBetrayal() {
		return probabilityForBetrayal;
	}




	/**
	 * main method just for testing
	 * @param args
	 */
    public static void main(String[] args) {
 
        try {
            // read the training data
            InputStreamFactory dataIn = new MarkableFileInputStreamFactory(new File("firstTrain.txt")); //"train"+File.separator+"categoryKAIS.train"));    //"en-movie-category.train"));
            ObjectStream lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
            ObjectStream sampleStream = new DocumentSampleStream(lineStream);
 
            // define the training parameters
            TrainingParameters params = new TrainingParameters();
            params.put(TrainingParameters.ITERATIONS_PARAM, 10+"");
            params.put(TrainingParameters.CUTOFF_PARAM, 0+"");
            params.put(AbstractTrainer.ALGORITHM_PARAM, NaiveBayesTrainer.NAIVE_BAYES_VALUE);
 
            // create a model from traning data
            DoccatModel model = DocumentCategorizerME.train("en", sampleStream, params, new DoccatFactory());
            System.out.println("\nModel is successfully trained.");
 
            // save the model to local
            BufferedOutputStream modelOut = new BufferedOutputStream(new FileOutputStream("model"+File.separator+"en-movie-classifier-naive-bayes.bin"));
            model.serialize(modelOut);
            System.out.println("\nTrained Model is saved locally at : "+"model"+File.separator+"en-movie-classifier-naive-bayes.bin");
 
            // test the model file by subjecting it to prediction
            DocumentCategorizer doccat = new DocumentCategorizerME(model);
            String[] docWords = "He jumped over the car, flipped his gun out and shot into the air. Charlie was ready for anything to fight and blast!".replaceAll("[^A-Za-z]", " ").split(" ");      //"Afterwards Stuart and Charlie notice Kate in the photos Stuart took at Leopolds ball and realise that her destiny must be to go back and be with Leopold That night while Kate is accepting her promotion at a company banquet he and Charlie race to meet her and show her the pictures Kate initially rejects their overtures and goes on to give her acceptance speech but it is there that she sees Stuarts picture and realises that she truly wants to be with Leopold".replaceAll("[^A-Za-z]", " ").split(" ");
            double[] aProbs = doccat.categorize(docWords);
 
            // print the probabilities of the categories
            System.out.println("\n---------------------------------\nCategory : Probability\n---------------------------------");
            for(int i=0;i<doccat.getNumberOfCategories();i++){
                System.out.println(doccat.getCategory(i)+" : "+aProbs[i]);
            }
            System.out.println("---------------------------------");
 
            System.out.println("\n"+doccat.getBestCategory(aProbs)+" : is the predicted category for the given sentence.");
        }
        catch (IOException e) {
            System.out.println("An exception in reading the training file. Please check.");
            e.printStackTrace();
        }
        //If we shadows have offended, Think but this, and all is mended: That you have but slumbered here, While these visions did appear; And this weak and idle theme, No more yielding but a dream, Gentles, do not reprehend. If you pardon, we will mend.
        trainingCategoryBayes ncb = new trainingCategoryBayes("If we shadows have offended, Think but this, and all is mended: That you have but slumbered here, While these visions did appear; And this weak and idle theme, No more yielding but a dream, Gentles, do not reprehend. If you pardon, we will mend.");
       System.out.println("-------------");
        System.out.println(ncb.getCategories());
        System.out.println("------------");
       System.out.println(ncb.getBestCategory());
        
    }
}
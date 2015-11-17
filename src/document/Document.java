package document;

import java.math.BigDecimal;
/** 
 * A class that represents a text document
 * @author UC San Diego Intermediate Programming MOOC team
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Document {

	private String text;
	
	/** Create a new document from the given text.
	 * Because this class is abstract, this is used only from subclasses.
	 * @param text The text of the document.
	 */
	protected Document(String text)
	{
		this.text = text;
	}
	
	/** Returns the tokens that match the regex pattern from the document 
	 * text string.
	 * @param pattern A regular expression string specifying the 
	 *   token pattern desired
	 * @return A List of tokens from the document text that match the regex 
	 *   pattern
	 */
	protected List<String> getTokens(String pattern)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
	}
	protected List<String> getTokens(String pattern, String word)
	{
		ArrayList<String> tokens = new ArrayList<String>();
	//	Pattern tokSplitter = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(word);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
	}
	// This is a helper function that returns the number of syllables
	// in a word.  You should write this and use it in your 
	// BasicDocument class.
	// You will probably NOT need to add a countWords or a countSentences method
	// here.  The reason we put countSyllables here because we'll use it again
	// next week when we implement the EfficientDocument class.
	protected int countSyllables(String word)
	{
		// TODO: Implement this method so that you can call it from the 
	    // getNumSyllables method in BasicDocument (module 1) and 
	    // EfficientDocument (module 2).
/*		List<String> wordTokens = this.getTokens("(?i)a+|e+|i+|o+|u+|y+");
			//	"[aeiouyAEIOUYe\b]");
		//[aeiouy]+[^$e(,.:;!?)]
		return wordTokens.size();*/
		String regexp = "((a|i|o|u|y)(?!...)(a|i|o|u|y|e)|e?!(a|i|o|u|y|e|\\w)|\\w)";
				//+ "[bcdfghjklmnpqrstvwxz]*[aeiouy]+[bcdfghjklmnpqrstvwxz]*";
	    Pattern p = Pattern.compile(regexp);
	    Matcher m = p.matcher(word.toLowerCase());

	    int count = 0;

	    while (m.find()) {
	        count++;
	    }
	    return count;
		
//		int numSyllables = this.getTokens("(?i)a+|e+|i+|o+|u+|y+", word).size();
//	    return word.endsWith("e") ? numSyllables-1 : numSyllables;

		//	    String [] words = word.split("[^aeiouAEIOU]+");
//	    List<String> syllablesInWord = new ArrayList<String>(Arrays.asList(words));
//	    syllablesInWord.remove("");
//	    int count = syllablesInWord.size();
//	    if(count > 1 && syllablesInWord.lastIndexOf("e") == count-1){
//	    	count -=1;
//	    }
//	    return count;

	}
	


	/** A method for testing
	 * 
	 * @param doc The Document object to test
	 * @param syllables The expected number of syllables
	 * @param words The expected number of words
	 * @param sentences The expected number of sentences
	 * @return true if the test case passed.  False otherwise.
	 */
	public static boolean testCase(Document doc, int syllables, int words, int sentences)
	{
		System.out.println("Testing text: ");
		System.out.print(doc.getText() + "\n....");
		boolean passed = true;
		int syllFound = doc.getNumSyllables();
		int wordsFound = doc.getNumWords();
		int sentFound = doc.getNumSentences();
		if (syllFound != syllables) {
			System.out.println("\nIncorrect number of syllables.  Found " + syllFound 
					+ ", expected " + syllables);
			passed = false;
		}
		if (wordsFound != words) {
			System.out.println("\nIncorrect number of words.  Found " + wordsFound 
					+ ", expected " + words);
			passed = false;
		}
		if (sentFound != sentences) {
			System.out.println("\nIncorrect number of sentences.  Found " + sentFound 
					+ ", expected " + sentences);
			passed = false;
		}
		
		if (passed) {
			System.out.println("passed.\n");
		}
		else {
			System.out.println("FAILED.\n");
		}
		return passed;
	}
	
	
	/** Return the number of words in this document */
	public abstract int getNumWords();
	
	/** Return the number of sentences in this document */
	public abstract int getNumSentences();
	
	/** Return the number of syllables in this document */
	public abstract int getNumSyllables();
	
	/** Return the entire text of this document */
	public String getText()
	{
		return this.text;
	}
	
	/** return the Flesch readability score of this document */
	public double getFleschScore()
	{
	    // TODO: Implement this method
		double numWords = (double)getNumWords();
		double numSentesces = (double)getNumSentences();
		double numSyllables = (double)getNumSyllables();
		
		double fs = 206.835-1.015*(numWords/numSentesces)-84.6*(numSyllables/numWords);
		BigDecimal bd = new BigDecimal(Double.toString(fs));
		    bd = bd.setScale(1,BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
		
		
	}
	
	
	
}

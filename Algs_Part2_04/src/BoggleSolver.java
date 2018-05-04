import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class BoggleSolver 
{
	private HashSet<String> dictionary = new HashSet<String>();
	private HashMap<Integer, Integer> lengthScore = new HashMap<Integer, Integer>();
	private HashMap<String, Boolean> wordsFound = new HashMap<String, Boolean>();
	
	// Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	public BoggleSolver(String[] dictionary)
    {
    	defineScoring();
    	createDictionary(dictionary);
    }
    
    private void defineScoring()
    {
    	lengthScore.put(1, 0);
    	lengthScore.put(2, 0);
    	lengthScore.put(3, 1);
    	lengthScore.put(4, 1);
    	lengthScore.put(5, 2);
    	lengthScore.put(6, 3);
    	lengthScore.put(7, 5);
    	lengthScore.put(8, 11);
    }
    
    private void createDictionary(String[] d)
    {
    	// Setup HashSet for easy querying of words
    	for (int i = 0; i < d.length; i++)
    	{
    		dictionary.add(d[i]);
    	}
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board)
    {
    	return new ArrayList<String>();
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word)
    {
    	int wordLength = word.length();
    	
    	if (wordLength > 8)
    	{
    		wordLength = 8;
    	}
    	
    	return lengthScore.get(wordLength);
    }
    
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		String[] words = new String[10];
		words[0] = "To";
		words[1] = "And";
		words[2] = "Test";
		words[3] = "Large";
		words[4] = "Larger";
		words[5] = "Largest";
		words[6] = "Surprise";
		words[7] = "Surprises";
		words[8] = "Watermelon";
		words[9] = "Watermelons";
		
		BoggleSolver bg = new BoggleSolver(words);
		System.out.println(bg.scoreOf(words[0]));
		System.out.println(bg.scoreOf(words[1]));
		System.out.println(bg.scoreOf(words[2]));
		System.out.println(bg.scoreOf(words[3]));
		System.out.println(bg.scoreOf(words[4]));
		System.out.println(bg.scoreOf(words[5]));
		System.out.println(bg.scoreOf(words[6]));
		System.out.println(bg.scoreOf(words[7]));
		System.out.println(bg.scoreOf(words[8]));
		System.out.println(bg.scoreOf(words[9]));
		
		System.out.println(bg.dict.contains("Tos"));
	}
}

//import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TST;

public class BoggleSolver 
{
	private boolean solved = false;
	private ArrayList<String> wordsFound = new ArrayList<String>();
	private TST<Integer> dictionary = new TST<Integer>();
	
	private HashMap<Integer, Integer> lengthScore = new HashMap<Integer, Integer>();
	
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
    		dictionary.put(d[i], i);
    	}
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board)
    {
    	if (!solved)
    	{
    		solveBoard(board);
    	}

    	return wordsFound;
    }
    
    private int convertTo1D(BoggleBoard board, int x, int y)
	{
		return board.cols() * y + x;
	}
    
    // run recursive DFS
    private void solveBoard(BoggleBoard board)
    {
    	solved = true;
    	
    	for (int y = 0; y < board.rows(); y++)
    	{
    		for (int x = 0; x < board.cols(); x++)
        	{
    			HashSet<Integer> lettersUsed = new HashSet<Integer>();
    			appendNeighbor("", board, x, y, lettersUsed);
        	}
    	}
    }
    
    private void appendNeighbor(String wordSoFar, BoggleBoard board, int x, int y, HashSet<Integer> lettersUsed)
    {
    	if (lettersUsed.contains(convertTo1D(board, x, y)))
    		return;
    	
    	char nextLetter = board.getLetter(y, x);
    	wordSoFar += nextLetter;
    	if (nextLetter == 'Q')
    	{
    		wordSoFar += "U";
    	}
    	lettersUsed = new HashSet<Integer>(lettersUsed);
    	lettersUsed.add(convertTo1D(board, x, y));
    	
    	Iterable<String> it = dictionary.keysWithPrefix(wordSoFar);
    	if (!it.iterator().hasNext())
    	{
    		return;
    	}

		if (wordSoFar.length() > 2 && dictionary.contains(wordSoFar) && !wordsFound.contains(wordSoFar))
		{
			wordsFound.add(wordSoFar);
		}
    	
    	// Run recursion on neighboring positions
    	int upperY = board.rows() - 1;
    	int upperX = board.cols() - 1;
    	
    	// Up
    	if (y > 0)
    		appendNeighbor(wordSoFar, board, x, y - 1, lettersUsed);
    	
    	// Down
    	if (y < upperY)
    		appendNeighbor(wordSoFar, board, x, y + 1, lettersUsed);
    	
    	// Left
    	if (x > 0)
    		appendNeighbor(wordSoFar, board, x - 1, y, lettersUsed);
    	
    	// Right
    	if (x < upperX)
    		appendNeighbor(wordSoFar, board, x + 1, y, lettersUsed);
    	
    	// Up-Left
    	if (y > 0 && x > 0)
    		appendNeighbor(wordSoFar, board, x - 1, y - 1, lettersUsed);
    		
    	// Up-Right
    	if (y > 0 && x < upperX)
    		appendNeighbor(wordSoFar, board, x + 1, y - 1, lettersUsed);
    	
    	// Down-Left
    	if (y < upperY && x > 0)
    		appendNeighbor(wordSoFar, board, x - 1, y + 1, lettersUsed);
    	
    	// Down-Right
    	if (y < upperY && x < upperX)
    		appendNeighbor(wordSoFar, board, x + 1, y + 1, lettersUsed);
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    public int scoreOf(String word)
    {
    	if (dictionary.contains(word))
    	{
	    	int wordLength = word.length();
	    	
	    	if (wordLength > 8)
	    	{
	    		wordLength = 8;
	    	}
	    	
	    	return lengthScore.get(wordLength);
    	}
    	
    	return 0;
    }
    
    public static void main(String[] args) 
    {
    	
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        for (String word : solver.getAllValidWords(board)) 
        {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    	
    	
        /*
    	File dictF = new File("C:\\Users\\kpeterson\\Desktop\\VS_Projects\\Boggle\\dictionary-yawl.txt");
        In in = new In(dictF);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        
        String boardAsString = "C:\\Users\\kpeterson\\Desktop\\VS_Projects\\Boggle\\basic_board.txt";
        BoggleBoard board = new BoggleBoard(boardAsString);
        
        int score = 0;
        int count = 0;
        for (String word : solver.getAllValidWords(board)) 
        {
        	count++;
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
        System.out.println("Total count: " + count);
        */
    }
}

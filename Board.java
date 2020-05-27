import java.util.*;

public abstract class Board {
	
	private int rows;
	private int cols;
	private int boardSize;
	private int minSetSize;
	private List<String> minSet;
	
	public static final int DEFAULT_SIZE = 3;
	
	// Default constructor
	public Board() {
		this(DEFAULT_SIZE, DEFAULT_SIZE);
	}
	
	// Constructor
	public Board(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		minSet = new ArrayList<>();
		boardSize = rows*cols;
		minSetSize = findMinSetSize();
		StringBuilder emptyBoard = new StringBuilder();
	    for (int i = 0; i < boardSize; i++) {
	    	emptyBoard.append('0');
	    }
	    do {
	    	minSetSize += 1;
	    	permute(minSetSize, 0, 0, boardSize, emptyBoard);
	    } while (minSet.isEmpty());
	} 
	
	// Recursive method to generate permutations of binary string
	public void permute(int numberOfOnes, int first, int depth, int length, 
			StringBuilder base) {
		for (int i = first; i < length; i++) {
			StringBuilder combo = new StringBuilder(base.toString());
			combo.setCharAt(i, '1');
			if (numberOfOnes == depth + 1) {
				evaluate(combo.toString());
			} else {
				permute(numberOfOnes, i + 1, depth + 1, length, combo);
			}
		}
	}
	
	// Calls overridden method
	public void evaluate(String board) {
        evaluateBoard(board);
    	}
	
	// Overridden methods (abstract)
	abstract int findMinSetSize();

    	abstract void evaluateBoard(String board);
    
    	// Set methods
    	public void setRows(int rows) {
    		this.rows = rows;
    	}
    
    	public void setCols(int cols) {
    		this.cols = cols;
    	}
    
    	public void addMinSet(String board) {
    		minSet.add(board);
    	}
    
    	// Accessor methods
 	public int getRows() {
 		return rows;
 	}
 	
 	public int getCols() {
 		return cols;
 	}
 	
 	public int getBoardSize() {
 		return boardSize;
 	}
 	
 	public int getNumberOfMinSets() {
 		return minSet.size();
 	}
 	
 	public int getMinSetSize() {
 		return minSetSize;
 	}
 	
 	public List<String> getMinSet() {
 		return minSet;
 	}
}
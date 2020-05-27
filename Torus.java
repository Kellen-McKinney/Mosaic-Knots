public class Torus extends Board {
	
	// Default constructor
	public Torus() {
		super();
	}
	
	// Constructor
	public Torus(int i, int j) {
		super(i,j);
	}
	
	@Override
   	public void evaluateBoard(String board) {
		if (board.charAt(0) == '1') {	// Efficiency
		// Converts binary representation of board into array
		// for easier processing
		int[][] arr = new int[getRows()][getCols()];
		int i = 0;
		int j = 0;
		for (int x = 0; x < board.length(); x++) {
			if (j == getCols()) {
				j = 0;
				i++;
			} if (board.charAt(x) == '1') {
				arr[i][j] += 4;
				evaluateTile(i, j, arr);
			} else {
				arr[i][j] += 0;
			}
			j++;
		}
		// Evaluates whether or not the array is a determining set
		int detTiles = 0;
		for (int x = 0; x < arr.length; x++) {
			for (int y = 0; y < arr[0].length; y++) { 
				if (arr[x][y] >= 3) {
					detTiles++;
				}
			}
		}
		// If it's a determining set, we'll ass it to our ArrayList of
		// minimum determining sets which we can access using the get 
		// method found in the abstract superclass Board
		if (detTiles == getBoardSize()) {
			addMinSet(board.toString());
		}
	    }
	}

	// Recursive method to evaluate tiles
	public void evaluateTile(int i, int j, int[][] arr) {
		if (j <= getCols() - 2) {	// east
			arr[i][j + 1] += 1;
			if (arr[i][j + 1] == 3) {
				evaluateTile(i, j + 1, arr);
			}
		} else if (j + 1 == getCols()) {
			arr[i][0] += 1;
			if (arr[i][0] == 3) {
				evaluateTile(i, 0, arr);
			}
		} if (j > 0) {	// west
			arr[i][j - 1] += 1;
			if (arr[i][j - 1] == 3) {
				evaluateTile(i, j - 1, arr);
			}
		} else if (j == 0) {
			arr[i][getCols() - 1] += 1;
			if (arr[i][getCols() - 1] == 3) {
				evaluateTile(i, getCols() - 1, arr);
			}
		} if (i <= getRows() - 2) {	// south
			arr[i + 1][j] += 1;
			if (arr[i + 1][j] == 3) {
				evaluateTile(i + 1, j, arr);
			}
		} else if (i + 1 == getRows()) {
			arr[0][j] += 1;
			if (arr[0][j] == 3) {
				evaluateTile(0, j, arr);
			}
		} if (i > 0) {	// north
			arr[i - 1][j] += 1;
			if (arr[i - 1][j] == 3) {
				evaluateTile(i - 1, j, arr);
			}
		} else if (i == 0) {
			arr[getRows() -1][j] += 1;
			if (arr[getRows() - 1][j] == 3) {
				evaluateTile(getRows() - 1, j, arr);
			}
		}
	}
	
	@Override
	int findMinSetSize() {
		if (getRows() > getCols()) {
			return getRows() - 1;
		} else {
			return getCols() - 1;
		}
	}
}
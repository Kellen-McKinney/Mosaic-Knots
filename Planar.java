public class Planar extends Board {
	
	// Default constructor
	public Planar() {
		super();
	}
	
	// Constructor
	public Planar(int i, int j) {
		super(i,j);
	}
	
	@Override
    	public void evaluateBoard(String board) {
		int[][] arr = new int[getRows()][getCols()];
		int i = 0;
		int j = 0;
		for (int x = 1; x < arr[0].length - 1; x++) { 
			arr[0][x] += 1;
			arr[getRows() - 1][x] += 1;
		} 
		for (int x = 0; x < arr.length; x++) {
			arr[x][0] += 1;
			arr[x][getCols() - 1] += 1;
		} 
		arr[0][0] += 1;
		arr[0][getCols() - 1] += 1;
		arr[getRows() - 1][0] += 1;
		arr[getRows() - 1][getCols() - 1] += 1;
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
		int detTiles = 0;
		for (int x = 0; x < arr.length; x++) {
			for (int y = 0; y < arr[0].length; y++) { 
				if (arr[x][y] >= 3) {
					detTiles++;
				}
			}
		}
		if (detTiles == getBoardSize()) {
			addMinSet(board.toString());
		}
    	}

	// Recursive method to evaluate tiles
	public void evaluateTile(int i, int j, int[][] arr) {
		if (j <= getCols() - 2) {	// east
			arr[i][j + 1] += 1;
			if (arr[i][j + 1] == 3) {
				evaluateTile(i, j + 1, arr);
			}
		} if (j > 0) {	// west
			arr[i][j - 1] += 1;
			if (arr[i][j - 1] == 3) {
				evaluateTile(i, j - 1, arr);
			}
		} if (i <= getRows() - 2) {	// south
			arr[i + 1][j] += 1;
			if (arr[i + 1][j] == 3) {
				evaluateTile(i + 1, j, arr);
			}
		} if (i > 0) {	// north
			arr[i - 1][j] += 1;
			if (arr[i - 1][j] == 3) {
				evaluateTile(i -1, j, arr);
			}
		}
	}
	
	@Override
	int findMinSetSize() {
		return 0;
	}
}
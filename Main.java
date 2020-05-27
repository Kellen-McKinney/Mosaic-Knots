/*
 * Minimum Determining Set Algorithm for knot theory research
 * Eden Chmielewski, 4 May 2020
 * 
 */

public class Main {
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Board test = new Planar(5, 5);
		System.out.println("Size: " + test.getRows() + "*" 
				+ test.getCols());
		System.out.println("Min det set size: " 
				+ test.getMinSetSize());
		long elapsedTime = System.currentTimeMillis() - startTime;
		long elapsedSeconds = elapsedTime / 1000;
		long elapsedMinutes = elapsedSeconds / 60;
		System.out.println("Runtime: " + elapsedSeconds 
				+ " seconds");
		System.out.println("Runtime: " + elapsedMinutes 
				+ " minutes");
		
	}
	
	// These methods are modified from the Board class
	// They allow us to evaluate a board by passing a binary 
	// String representation of the board as a parameter
	// This is useful for determining whether or not a board
	// is a determining set
	public static void evaluatePlanarBoard(String board, 
			int rows, int cols) {
		int[][] arr = new int[rows][cols];
		int i = 0;
		int j = 0;
		for (int x = 1; x < arr[0].length - 1; x++) { 
			arr[0][x] += 1;
			arr[rows - 1][x] += 1;
		} 
		for (int x = 0; x < arr.length; x++) {
			arr[x][0] += 1;
			arr[x][cols - 1] += 1;
		} 
		arr[0][0] += 1;
		arr[0][cols - 1] += 1;
		arr[rows - 1][0] += 1;
		arr[rows - 1][cols - 1] += 1;
		for (int x = 0; x < board.length(); x++) {
			if (j == cols) {
				j = 0;
				i++;
			} if (board.charAt(x) == '1') {
				arr[i][j] += 4;
				evaluatePlanarTile(i, j, arr, rows, cols);
			} else {
				arr[i][j] += 0;
			}
			j++;
		} 
		for (int x = 0; x < arr.length; x++) {
			for (int y = 0; y < arr[0].length; y++) { 
				System.out.print(arr[x][y]);
			}
			System.out.println();
		}
	}
	
	
	public static void evaluateTorusBoard(String board, 
			int rows, int cols) {	
		if (board.charAt(0) == '1') {
			int[][] arr = new int[rows][cols];
			int i = 0;
			int j = 0;
			for (int x = 0; x < board.length(); x++) {
				if (j == cols) {
					j = 0;
					i++;
				} if (board.charAt(x) == '1') {
					arr[i][j] += 4;
					evaluateTorusTile(i, j, arr, rows, cols);
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
			for (int x = 0; x < arr.length; x++) {
				for (int y = 0; y < arr[0].length; y++) { 
					System.out.print(arr[x][y]);
				}
				System.out.println();
			}
			if (detTiles == rows*cols) {
				System.out.println(board.toString());
			}
		}
    }

	public static void evaluatePlanarTile(int i, int j, int[][] arr, 
			int rows, int cols) {
		if (j <= cols - 2) {	// east
			arr[i][j + 1] += 1;
			if (arr[i][j + 1] == 3) {
				evaluatePlanarTile(i, j + 1, arr, rows, cols);
			}
		} if (j > 0) {	// west
			arr[i][j - 1] += 1;
			if (arr[i][j - 1] == 3) {
				evaluatePlanarTile(i, j - 1, arr, rows, cols);
			}
		} if (i <= rows - 2) {	// south
			arr[i + 1][j] += 1;
			if (arr[i + 1][j] == 3) {
				evaluatePlanarTile(i + 1, j, arr, rows, cols);
			}
		} if (i > 0) {	// north
			arr[i - 1][j] += 1;
			if (arr[i - 1][j] == 3) {
				evaluatePlanarTile(i -1, j, arr, rows, cols);
			}
		}
	}
		
	public static void evaluateTorusTile(int i, int j, int[][] arr, 
			int rows, int cols) {
		if (j <= cols - 2) {	// east
			arr[i][j + 1] += 1;
			if (arr[i][j + 1] == 3) {
				evaluateTorusTile(i, j + 1, arr, rows, cols);
			}
		} else if (j + 1 == cols) {
			arr[i][0] += 1;
			if (arr[i][0] == 3) {
				evaluateTorusTile(i, 0, arr, rows, cols);
			}
		} if (j > 0) {	// west
			arr[i][j - 1] += 1;
			if (arr[i][j - 1] == 3) {
				evaluateTorusTile(i, j - 1, arr, rows, cols);
			}
		} else if (j == 0) {
			arr[i][cols - 1] += 1;
			if (arr[i][cols - 1] == 3) {
				evaluateTorusTile(i, cols - 1, arr, rows, cols);
			}
		} if (i <= rows - 2) {	// south
			arr[i + 1][j] += 1;
			if (arr[i + 1][j] == 3) {
				evaluateTorusTile(i + 1, j, arr, rows, cols);
			}
		} else if (i + 1 == rows) {
			arr[0][j] += 1;
			if (arr[0][j] == 3) {
				evaluateTorusTile(0, j, arr, rows, cols);
			}
		} if (i > 0) {	// north
			arr[i - 1][j] += 1;
			if (arr[i - 1][j] == 3) {
				evaluateTorusTile(i - 1, j, arr, rows, cols);
			}
		} else if (i == 0) {
			arr[rows -1][j] += 1;
			if (arr[rows - 1][j] == 3) {
				evaluateTorusTile(rows - 1, j, arr, rows, cols);
			}
		}
	}
}
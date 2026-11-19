/*
 * Class: CMSC203 CRN 20798
 * Instructor: Ahmed Tarek
 * Description: Utility class with static methods to calculate totals, averages,
 * and highest/lowest sales in a ragged 2D array.
 * Due: 11/18/2026
 * Platform/compiler: Eclipse IDE / macOS
 * I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * I have not given my code to any student.
 * Print your Name here: Chandul Somaweera
 */


public class SalesDataUtility {

	//Calc and returns sum of the 2d array
	public static double getTotal(double[][] data) {
		double total = 0.0;
		for(int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[row].length; col++) {
				total = total + data[row][col];
			}
		}
		return total;
	}
	
	//calc the average of the elements in the array
	public static double getAverage(double[][] data) {
		double total = 0.0;
		int count = 0;
		for(int row = 0; row < data.length; row++) {
			for(int col = 0; col < data[row].length; col++) {
				total = total + data[row][col];
				count++;
			}
		}
		
		if(count == 0) {
			return 0.0;
		}
		return total / count;
	}
	// Calc total of a row
	public static double getRowTotal(double[][] data, int row) {
		double total = 0.0;
		for (int col = 0; col < data[row].length; col++) {
			total = total + data[row][col];
		}
		return total;
	}
	
	//calc total of a column
	public static double getColumnTotal(double[][] data, int col) {
		double total = 0.0;
		for (int row = 0; row < data.length; row++) {
			if (col < data[row].length) {
				total = total + data[row][col];
			}
		}
		return total;
	}
	
	//finds the highest value in a row
	public static double getHighestInRow(double[][] data, int row) {
		double highest = data[row][0];
		for (int col = 1; col < data[row].length; col++) {
			if (data[row][col] > highest) {
				highest = data[row][col];
			}
		}
		return highest;
	}
	
	//finds the lowest value in a row
	public static double getLowestInRow(double[][] data, int row) {
		double lowest = data[row][0];
		for(int col = 1; col < data[row].length; col++) {
			if (data[row][col] < lowest) {
				lowest = data[row][col];
			}
		}
		return lowest;
	}
	
	// finds the highest value in an array
	public static double getHighestInArray(double[][] data) {
		double highest = data[0][0];
		for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[row].length; col++) {
				if (data[row][col] > highest ) {
					highest = data[row][col];
				}
			}
		}
		return highest;
	}
	
	// finds the lowest value in an array
	public static double getLowestInArray(double[][] data) {
		double lowest = data[0][0];
		for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[row].length; col++) {
				if (data[row][col] < lowest ) {
					lowest = data[row][col];
				}
			}
		}
		return lowest;
	}
	
	
}















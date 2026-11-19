/*
 * Class: CMSC203 CRN 20798
 * Instructor: Ahmed Tarek
 * Description: Helper class that reads sales data from a text file into a ragged 2D array
 * and writes the summary statistics to an output file.
 * Due: 11/18/2026
 * Platform/compiler: Eclipse IDE / macOS
 * I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * I have not given my code to any student.
 * Print your Name here: Chandul Somaweera
 */

//imports

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class SalesFileIO {
	
	
	public static double[][] readSalesData(String filename) throws FileNotFoundException {
		File file = new File(filename);
		
		
		// count nnon empty lines
		Scanner scan = new Scanner(file);
		int rowCount = 0;
		while (scan.hasNextLine()) {
			String line = scan.nextLine().trim();
			if(line.length() > 0) {
				rowCount++;
			}
		}
		scan.close();
		
		double[][] data = new double[rowCount][];
		
		//split each line and parse values
		Scanner dataScan = new Scanner(file);
		int currentRow = 0;
		while (dataScan.hasNextLine()) {
			String line = dataScan.nextLine().trim();
			if(line.length() > 0) {
				String[] parts = line.split(" ");
				data[currentRow] = new double[parts.length];
				
				
				for(int col = 0; col < parts.length; col++) {
					data[currentRow][col] = Double.parseDouble(parts[col]);
				}
				currentRow++;
			}
		}
		dataScan.close();
		
		return data;
	}
	
	// Search up videos and online sources for this method.
	
	// Writes the totals, averages, min/max, row totals, and column totals.
	
	public static void writeSummary(String filename, double[][] data) throws FileNotFoundException {
		
		PrintWriter outFile = new PrintWriter(filename);
		
		outFile.printf("Total sales: %.2f\n", SalesDataUtility.getTotal(data));
		outFile.printf("Average sale: %.2f\n", SalesDataUtility.getAverage(data));
		outFile.printf("Highest sale: %.2f\n", SalesDataUtility.getHighestInArray(data));
		outFile.printf("Lowest sale: %.2f\n", SalesDataUtility.getLowestInArray(data));
		outFile.println();
		
		for (int row = 0; row < data.length; row++) {
			outFile.printf("Row %d total: %.2f\n", row, SalesDataUtility.getRowTotal(data,  row));
		}
		outFile.println();
		
		
		int maxCols = 0;
		for (int row = 0; row < data.length; row++) {
			if (data[row].length > maxCols) {
				maxCols = data[row].length;
			}
		}
		
		for (int col = 0; col < maxCols; col++) {
			outFile.printf("Column %d total: %.2f\n,/", col, SalesDataUtility.getColumnTotal(data,  col));
		}
		
		outFile.close();
	}
	

}

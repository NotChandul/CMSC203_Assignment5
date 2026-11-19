
/*
 * Class: CMSC203 CRN 20798
 * Instructor: Ahmed Tarek
 * Description: Console driver that asks the user for input and output filenames,
 * calculates sales statistics, and saves the summary file.
 * Due: 11/18/2026
 * Platform/compiler: Eclipse IDE / macOS
 * I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * I have not given my code to any student.
 * Print your Name here: Chandul Somaweera
 */


import java.io.FileNotFoundException;
import java.util.Scanner;

public class SalesAppDriver {

	// primpts file names
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		//get input and output
		System.out.print("Enter input file name: ");
		String inputFile = scan.nextLine();
		System.out.print("Enter output file name: ");
		String outputFile = scan.nextLine();
		
		try {
			double[][] sales = SalesFileIO.readSalesData(inputFile);
			
			//displays the overall sales
			System.out.printf("Total sales: %.2f\n", SalesDataUtility.getTotal(sales));
			System.out.printf("Average sale: %.2f\n", SalesDataUtility.getAverage(sales));
			System.out.printf("Highest sale: %.2f\n", SalesDataUtility.getHighestInArray(sales));
			System.out.printf("Lowest sale: %.2f\n", SalesDataUtility.getLowestInArray(sales));
			System.out.println();
			
			//display totals
			for (int row = 0; row < sales.length; row++) {
				System.out.printf("Row %d total: %.2f\n", row, SalesDataUtility.getRowTotal(sales, row));
			}
			
			System.out.println();
			

			int maxCols = 0;
			for (int row = 0; row < sales.length; row++) {
				if (sales[row].length > maxCols) {
					maxCols = sales[row].length;
				}
			}
			
			for (int col = 0; col < maxCols; col++) {
				System.out.printf("Column %d total: %.2f\n,/", col, SalesDataUtility.getColumnTotal(sales,  col));
			}
			
			//save the results to an output file
			SalesFileIO.writeSummary(outputFile,  sales);
			
			System.out.println("\nSummary saved to " + outputFile);
			
		} 
		
		catch (FileNotFoundException e) {
			System.out.println("Error: File not found.");
		}
		
		System.out.println("\nProgrammer: Chandul Somaweera");
		scan.close();

	}

}

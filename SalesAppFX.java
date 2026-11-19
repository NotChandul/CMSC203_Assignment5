
/*
 * Class: CMSC203 CRN 20798
 * Instructor: Ahmed Tarek
 * Description: JavaFX GUI application for loading sales data, viewing statistics,
 *              and saving a formatted summary file.
 * Due: 11/18/2026
 * Platform/compiler: Eclipse IDE / macOS
 * I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * I have not given my code to any student.
 * Print your Name here: Chandul Somaweera
 */

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SalesAppFX extends Application {

	private TextField inputFileField;
	private TextField outputFileField;
	private TextArea displayArea;
	private double[][] salesData;
	
	@Override
	public void start(Stage primaryStage) {
		Label inputLabel = new Label("Input File:");
		inputFileField = new TextField("Salesdata.txt");
		
		Label outputLabel = new Label("Output File:");
		outputFileField = new TextField("sales_summary55.txt");
		
		
		GridPane topGrid = new GridPane();
		
        topGrid.setHgap(10);
        topGrid.setVgap(10);
        topGrid.add(inputLabel, 0, 0);
        topGrid.add(inputFileField, 1, 0);
        topGrid.add(outputLabel, 0, 1);
        topGrid.add(outputFileField, 1, 1);

        Button loadButton = new Button("Load & Process");
        Button saveButton = new Button("Save Summary");
        
        HBox buttonRow = new HBox(10, loadButton, saveButton);

        displayArea = new TextArea();
        displayArea.setEditable(false);
        displayArea.setPrefHeight(260);
        
        
        loadButton.setOnAction(new EventHandler<ActionEvent>() {
        	
        	@Override
        	public void handle(ActionEvent event) {
        		String filename = inputFileField.getText();
        		try {
        			salesData = SalesFileIO.readSalesData(filename);
        			
        			String text = "Data loaded from " + filename + "\n\n";
        			
        			text = text + String.format("Total sales: %.2f\n", SalesDataUtility.getTotal(salesData));
                    text = text + String.format("Average sale: %.2f\n", SalesDataUtility.getAverage(salesData));
                    text = text + String.format("Highest sale: %.2f\n", SalesDataUtility.getHighestInArray(salesData));
                    text = text + String.format("Lowest sale: %.2f\n\n", SalesDataUtility.getLowestInArray(salesData));
                    
                    for(int row = 0; row < salesData.length; row++) {
                    	text = text + String.format("Row %d total: %.2f\n", row, SalesDataUtility.getRowTotal(salesData, row));
                    	
                    }
                    text = text + "\n";
                    
                    int maxCols = 0;
                	for (int row = 0; row < salesData.length; row++) {
                		if (salesData[row].length > maxCols) {
                			maxCols = salesData[row].length;
           
                		}
                	
                	
                	}
                	for ( int col = 0; col < maxCols; col++) {
                		text = text + String.format("Column %d total: %.2f\n", col, SalesDataUtility.getColumnTotal(salesData, col));
                	}
                	
                	displayArea.setText(text);
                	
        		}
        		catch (FileNotFoundException e) {
        			displayArea.setText("Error: Could not find file " + filename);
        		}
        	}
        });
        
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
        		if(salesData == null) {
        			displayArea.setText("Click 'Load & Process' first.");
        			return;
        		}
        		
        		String outFilename = outputFileField.getText();
        		try {
        			SalesFileIO.writeSummary(outFilename, salesData);
        			displayArea.appendText("\nSummary saved to " + outFilename);
        		}
        		catch (FileNotFoundException e) {
        			displayArea.appendText("\nError: Could not save the file" + outFilename);

        		}
        	}
        });
        
        VBox mainBox = new VBox(12, topGrid, buttonRow, displayArea);
        	
        Scene scene = new Scene(mainBox, 550, 400);
        primaryStage.setTitle("Store Sales Data Processor");
        primaryStage.setScene(scene);
        primaryStage.show();
        
	}
	
	
	public static void main(String[] args) {

		launch(args);
	}

}

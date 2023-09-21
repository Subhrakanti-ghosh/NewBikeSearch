package Hackathon.NewBikeSearch.utils; // Import necessary modules

import java.io.File; // Import File class
import java.io.FileInputStream; // Import FileInputStream class
import java.io.FileOutputStream; // Import FileOutputStream class
import java.util.List; // Import List interface

import org.apache.poi.xssf.usermodel.XSSFRow; // Import XSSFRow class
import org.apache.poi.xssf.usermodel.XSSFSheet; // Import XSSFSheet class
import org.apache.poi.xssf.usermodel.XSSFWorkbook; // Import XSSFWorkbook class

public class WriteExcelData { // Define a class named "WriteExcelData"

	// Declare necessary static variables
	static File f;
	static FileInputStream fi;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static XSSFRow row;
	static String path = "./test-output/excel_output/OutputData.xlsx"; // Path for the Excel file
	static FileOutputStream fos;

	// Method to write top cell data for bikes
	public static void writeExcelTopCellBikes() throws Exception {
		// Create or open the workbook and sheet
		f = new File(path);
		if (!f.exists()) {
			workbook = new XSSFWorkbook();
			fos = new FileOutputStream(path);
			workbook.write(fos);
		}

		fi = new FileInputStream(path);

		workbook = new XSSFWorkbook(fi);
		if (workbook.getSheetIndex("Bikes") == -1) {
			workbook.createSheet("Bikes");
		}

		sheet = workbook.getSheet("Bikes");
		if (sheet.getRow(0) == null)
			sheet.createRow(0);
		row = sheet.getRow(0);

		// Write headers for the Excel sheet
		row.createCell(0).setCellValue("Bike's Name");
		row.createCell(1).setCellValue("Price");
		row.createCell(2).setCellValue("Launched Date");
		fos = new FileOutputStream(f);
		workbook.write(fos);
	}

	// Method to write top cell data for cars
	public static void writeExcelTopCellCars() throws Exception {
		// Create or open the workbook and sheet
		f = new File(path);

		if (!f.exists()) {
			workbook = new XSSFWorkbook();
			fos = new FileOutputStream(path);
			workbook.write(fos);
		}

		fi = new FileInputStream(path);

		workbook = new XSSFWorkbook(fi);
		if (workbook.getSheetIndex("Cars") == -1) {
			workbook.createSheet("Cars");
		}

		sheet = workbook.getSheet("Cars");
		if (sheet.getRow(0) == null)
			sheet.createRow(0);
		row = sheet.getRow(0);

		// Write headers for the Excel sheet
		row.createCell(0).setCellValue("Car's Name");
		fos = new FileOutputStream(f);
		workbook.write(fos);
	}

	// Method to write data to the Excel sheet based on an array
	public static void writeExcelData(String[] s, int rowNum, String sheetName) throws Exception {
		// Create or open the workbook and sheet
		f = new File(path);

		fi = new FileInputStream(f);

		workbook = new XSSFWorkbook(fi);

		sheet = workbook.getSheet(sheetName);
		Flush(); // Flush the output stream

		row = sheet.createRow(rowNum);

		// Write data to the Excel cells
//		row.createCell(0).setCellValue(s[0]);
//		row.createCell(1).setCellValue(s[1]);
//		row.createCell(2).setCellValue(s[2]);

		for (int i = 0; i < s.length; i++) {
			row.createCell(i).setCellValue(s[i]);
		}

		fos = new FileOutputStream(f);
		workbook.write(fos);
		fos.close();
	}

	// Method to write data to the Excel sheet based on a list of strings
	public static void writeExcelData(List<String> arr, String sheetName) throws Exception {
		// Create or open the workbook and sheet
		f = new File(path);

		fi = new FileInputStream(f);

		workbook = new XSSFWorkbook(fi);

		sheet = workbook.getSheet(sheetName);
		Flush(); // Flush the output stream

		int rowNum = 1;

		for (int i = 0; i < arr.size(); i++) {
			row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(arr.get(i));
		}

		fos = new FileOutputStream(f);
		workbook.write(fos);
		fos.close();
	}

	// Method to flush the output stream
	public static void Flush() throws Exception {
		fos = new FileOutputStream(path);
		fos.flush();
	}
}

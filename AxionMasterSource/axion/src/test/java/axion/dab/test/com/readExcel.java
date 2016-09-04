package axion.dab.test.com;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


	public class readExcel {
		
		 public int getNoRows(String fileName, String sheetName) throws Exception {

			 try {
			    FileInputStream fs = new FileInputStream(fileName);
			    Workbook wb = Workbook.getWorkbook(fs);
			    Sheet sh = wb.getSheet(sheetName);

		
			    int totalNoOfRows = sh.getRows();
			    return  totalNoOfRows;
		
			   } catch (FileNotFoundException e) {
				   throw new FileNotFoundException("Excel not found or corrupted " + fileName + "Sheet = " + sheetName); 
			   } catch (IOException e) {
			    e.printStackTrace();
			    e.printStackTrace();
			   } catch (BiffException e) {
			    e.printStackTrace();
			   }catch (Exception e ) {
				   throw new FileNotFoundException("Excel not found or corrupted " + fileName + "Sheet = " + sheetName); 
			   }
			   return 0;
			  }

		
		
		 public int getNoCoulmn(String fileName, String sheetName) throws Exception {

			 try {
			    FileInputStream fs = new FileInputStream(fileName);
			    Workbook wb = Workbook.getWorkbook(fs);
			    Sheet sh = wb.getSheet(sheetName);

			    int totalNoOfCols = sh.getColumns();
			    return  totalNoOfCols;
			    

			   } catch (FileNotFoundException e) {
				   throw new FileNotFoundException("Excel not found or corrupted " + fileName + "Sheet = " + sheetName); 
			   } catch (IOException e) {
			    e.printStackTrace();
			    e.printStackTrace();
			   } catch (BiffException e) {
			    e.printStackTrace();
			   }catch (Exception e ) {
				   throw new FileNotFoundException("Excel not found or corrupted " + fileName + "Sheet = " + sheetName); 
			   }
			   return 0;
			  }
	 
		 
	
		 public String[][] getExcelData(String fileName, String sheetName) throws Exception  {
			   String[][] arrayExcelData = null;   			   
			   try {
			    FileInputStream fs = new FileInputStream(fileName);
			    Workbook wb = Workbook.getWorkbook(fs);
			    Sheet sh = wb.getSheet(sheetName);

			    int totalNoOfCols = sh.getColumns();
			    int totalNoOfRows = sh.getRows();
			    arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];
			    
			    for (int i= 1 ; i < totalNoOfRows; i++) {

			     for (int j=0; j < totalNoOfCols; j++) {
			      arrayExcelData[i-1][j] = sh.getCell(j, i).getContents();
			     }

			    }
			   } catch (FileNotFoundException e) { 		
				   throw new FileNotFoundException("Excel not found  or corrupted " + fileName + "  Sheet  " + sheetName );    			   
			   } catch (IOException e) {
				    e.printStackTrace();
			   } catch (BiffException e) {
				    e.printStackTrace();
			   } catch (Exception e ) {
				   throw new FileNotFoundException("Excel not found or corrupted " + fileName + "  Sheet  " + sheetName); 
			   }
			   return arrayExcelData;
			  }

		 
		 
}

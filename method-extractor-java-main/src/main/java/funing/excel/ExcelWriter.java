package funing.excel;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.csvreader.CsvWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;



public class ExcelWriter {


    public ArrayList<id_code> get_id_path() throws EncryptedDocumentException, InvalidFormatException, IOException{
        String SAMPLE_XLSX_FILE_PATH = "./Eclipse_Platform_UI.xlsx";
        Workbook workbook_read = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

        // Retrieving the number of sheets in the Workbook
        //System.out.println("Workbook has " + workbook_read.getNumberOfSheets() + " Sheets : ");

        /*
           =============================================================
           Iterating over all the sheets in the workbook (Multiple ways)
           =============================================================
        */

        // Getting the Sheet at index zero
        Sheet sheet = workbook_read.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        // 2. Or you can use a for-each loop to iterate over the rows and columns
        ArrayList<id_code> id_path=new ArrayList();
        String bug_id="";
        String commit="";
        String source_code="";
        
        //System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
        for (Row row: sheet) {
        	if(row.getRowNum()!=0) {
                for(Cell cell: row) {
                	if(cell.getColumnIndex()==1) {
                		bug_id=(int)(cell.getNumericCellValue())+"";
                        //System.out.print(cellValue + "\t");            		
                	}   
                	if(cell.getColumnIndex()==7) {
                		commit=cell.getStringCellValue();
                        //System.out.print(cellValue + "\t");            		
                	} 
                	if(cell.getColumnIndex()==9) {
                		source_code=cell.getStringCellValue();
                        //System.out.print(cellValue + "\t");            		
                	}                       	
                }  
                id_path.add(new id_code(bug_id,commit,source_code));
        	}

            //System.out.println();
        }
        workbook_read.close();        
    	
		return id_path;
    	
    }

 
    public void Write_voca_fre(ArrayList voca_fre_arr) throws IOException { //ArrayList<Voca_freq> voca_fre_arr
    	

    	
        String writerCsvFilePath = "writer.csv";
        CsvWriter csvWriter = new CsvWriter(writerCsvFilePath, ',', Charset.forName("UTF-8"));    	


        for(int i=0;i<voca_fre_arr.size();i++) {      	

            
			ArrayList<Voca_freq> voca_fre = (ArrayList) voca_fre_arr.get(i);
			ArrayList<String> contents = new ArrayList();

			contents.add(voca_fre.get(0).getBug_id());
			
			for (Voca_freq voca : voca_fre) {
				contents.add(voca.getAST_Node());
				contents.add(voca.getFrequency());
			}   
			csvWriter.writeRecord(convertToStringArray(contents));
        }    

        // Write the output to a file
        csvWriter.close();
    }
    
    public static String[] convertToStringArray(ArrayList<String> list) {
    	String[] covertedArray = new String[list.size()];
        	
        covertedArray = (String[]) list.toArray(covertedArray);

    	return covertedArray;
    }
}


import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.Color;
import java.io.*;
import java.net.URL;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;


public class ExcelData{
	public List<Stock> stockList;
	public String[]columnNames={"Symbol","Company","Date","Time","Last","Open", "Close","High","Low","Volume","Percent Change", "Recomendation"};
	ExcelData(){
	
		try {
			stockList=new ArrayList<>();
			
     
			FileInputStream file = new FileInputStream(new File("OriginalData.xlsx"));
     
			//Get the workbook instance for XLS file 
			XSSFWorkbook workbook = new XSSFWorkbook(file);
 
			//Get first sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
			//Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				
				int rowNum=row.getRowNum();
				if(row.getRowNum()==0 || row.getRowNum()==1 || row.getRowNum()==2|| row.getRowNum()==3){
					continue; //just skip the rows if row number is 0 or 1
				}
				//For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();

				if (!isRowEmpty(row)){
					Stock s=new Stock();
						
		
					while(cellIterator.hasNext()) {
						
						Cell nextCell = cellIterator.next();
						int columnIndex = nextCell.getColumnIndex();
						switch (columnIndex) {
			        		case 0:
			        			
			        			s.setSymbol((String) getCellValue(nextCell));
			        			break;
			        		case 1:
			        			s.setCompany((String) getCellValue(nextCell));
			        			break;
			        		case 2:
			        			if (nextCell.getCellType()==Cell.CELL_TYPE_NUMERIC){
			        				if (DateUtil.isCellDateFormatted(nextCell)) {
			        					s.setDate(df.format(getCellValue(nextCell)));
			        				}
			        			}
			        			break;
			        		case 3:
			        			s.setTime((String) getCellValue(nextCell));
			        			break;
			        		case 4:
			        			s.setLastPrice((double) getCellValue(nextCell));
			        			break;
			        		case 5:
			        			s.setOpeningPrice((double) getCellValue(nextCell));
			        			break;
			        		case 6:
			        			s.setClosingPrice((double) getCellValue(nextCell));
			        			break;
			        		case 7:
			        			s.setHighPrice((double) getCellValue(nextCell));
			        			break;
			        		case 8:
			        			s.setLowPrice((double) getCellValue(nextCell));
			        			break;
			        		case 9:
			        			s.setVolume((double) getCellValue(nextCell));
			        			break;
			        			
			            }
			        }
					s.setPercentChange(5);
			        stockList.add(s);
				}
			 }
			 
			
			
			System.out.println(stockList);
			workbook.close();
			file.close();
			
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
/*	public int findColNum(String colHeading){
		for (int c=0;c<colNames.length; c++){
			String col=colNames[c];
			if (colNames[c].equals(colHeading)){
				return c;
			}
		}
		return -1;
	}
	*/

	
	private Object getCellValue(Cell cell) {
	    switch (cell.getCellType()) {
	    case Cell.CELL_TYPE_STRING:
	        return cell.getStringCellValue();
	 
	    case Cell.CELL_TYPE_BOOLEAN:
	        return cell.getBooleanCellValue();
	 
	    case Cell.CELL_TYPE_NUMERIC:
	        return cell.getNumericCellValue();
	    }
	 
	    return null;
	}
	private boolean isRowEmpty(Row row) {
	    if (row == null) {
	        return true;
	    }
	    if (row.getLastCellNum() <= 0) {
	        return true;
	    }
	    boolean isEmptyRow = true;
	    for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
	        Cell cell = row.getCell(cellNum);
	        if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK ) {
	            isEmptyRow = false;
	        }
	    }
	    return isEmptyRow;
	}
	public Object[][]getStockTable(){
		Object[][] table = new Object[stockList.size()][];
			for (int i = 0; i < stockList.size(); i++) {
				table[i] = stockList.get(i).toObjectArray();
			}
		return table;
	}
}


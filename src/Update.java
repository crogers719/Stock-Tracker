import java.util.Timer;
import java.util.TimerTask;
public class Update {

	public static void main(String[] args) {
		final ExcelData excel = new ExcelData();
	  // final stockBoard gui = new stockBoard(excel.getStockList(), excel.getColumnNames());
	    //gui.setVisible(true);
		GUI gui = new GUI(excel.getStockTable(), excel.columnNames);
		gui.setVisible(true);
		//refresh every minute
		int refreshPeriod = 60 * 1000; 

	    Timer timer = new Timer();
	    timer.scheduleAtFixedRate(new TimerTask()
	      {
	        public void run()
	        {
	        	//excel.update();
	        	//excel.sortArray();
	        	Object[][] stocklist = excel.getStockTable();
	            gui.refreshTable(stocklist);
	          
	         
	        }
	      }, 0, refreshPeriod);
	  
	}

}
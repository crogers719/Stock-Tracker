
public class Stock {
	private String symbol;
	private String company;
	private String date;
	private String time;
	private double lastPrice;
	private double openingPrice;
	private double closingPrice;
	private double highPrice;
	private double lowPrice;
	private double volume;
	private double percentChange;
	
	
	
	public String toString() {
	        return String.format("%s \t\t %s \t\t %s \t\t %s \t\t %.2f \t\t %.2f \t\t %.2f \t\t %.2f \t\t %.2f \t\t %.0f \t\t %.2f\n", symbol, company, date, time, lastPrice, openingPrice, closingPrice, highPrice, lowPrice, volume, percentChange);
	    }
	
	 public String getSymbol(){
		 return symbol;
	 }
	 public void setSymbol(String sym){
		 symbol=sym;
	 }
 
	 public String getCompany(){
		 return company;
	 }
	 public void setCompany(String companyName){
		 company=companyName;
	 }
	 public String getDate(){
		 return date;
	 }
	 public void setDate(String d){
		 date=d;
	 }
	 public String getTime(){
		 return time;
	 }
	 public void setTime(String t){
		 time=t;
	 }
	 public double getLastPrice(){
		 return lastPrice;
	 }
	 public void setLastPrice(double last){
		 lastPrice=last;
	 }
	 public double getOpeningPrice(){
		 return openingPrice;
	 }
	 public void setOpeningPrice(double open){
		 openingPrice=open;
	 }
	 public double getClosingPrice(){
		 return closingPrice;
	 }
	 public void setClosingPrice(double close){
		 closingPrice=close;
	 }
	 public double getHighPrice(){
		 return highPrice;
	 }
	 public void setHighPrice(double high){
		 highPrice=high;
	 }
	 public double getLowPrice(){
		 return lowPrice;
	 }
	 public void setLowPrice(double low){
		 lowPrice=low;
	 }
	 public double getVolume(){
		 return volume;
	 }
	 public void setVolume(double vol){
		 volume=vol;
	 }
	 public double getPercentChange(){
		 return percentChange;
	 }
	 public void setPercentChange(double percent){
		 percentChange=percent;
	 }
	 
	 public Object[] toObjectArray() {
		    return new Object[] {getSymbol(), getCompany(), getDate(), getTime(), getLastPrice(), getOpeningPrice(), getClosingPrice(), getHighPrice(), getLowPrice(), getVolume(), getPercentChange()};
		}
}

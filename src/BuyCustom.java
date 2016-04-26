public class BuyCustom implements Strategy {
	public String getRecommendation(int stockIndex) {
		// Buys if the volume traded is greater than 1 million
		// Holds otherwise
		float volumeTraded = 150000;
		
		if(volumeTraded > 1000000){
			return "Buy";
		}
		
		else{
			return "Hold";
		}
	}

}
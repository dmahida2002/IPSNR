import java.util.Arrays;

public class DetailedCompression {
	
	private static int set;
	
	private static String firstSet[];
	
	private static String secondSet[];
	
	private static String[] finalIPData = new String[5];
	
	private static Resources resources = new Resources();
	
	public DetailedCompression() {
		
		DetailedCompression.set = 0;
	}
	
	public static void toReceiver(String[] details) {
		
		if(set == 0) firstSet = details;
		if(set == 1) secondSet = details;
		
		if(set == 1) analize();
		
		set++;
	}
	
	public static void analize() {
		
		double scoreOne = 0;
		double similarityThreshold = 0.55;
		
		for(int i = 0; i < 4; i++) {
			
			scoreOne = resources.similarity(firstSet[i], secondSet[i]);
			
			if(scoreOne >= similarityThreshold) {
				
				finalIPData[i] = firstSet[i];
			}
			
			else {
				
				finalIPData[i] = "[" + firstSet[i] + " OR " + secondSet[i] + "]";
			}
		}
		
		if(firstSet.length == 5) {
			
			finalIPData[4] = firstSet[4];
		}
		
		if(secondSet.length == 5) {
			
			finalIPData[4] = secondSet[4];
		}
		
		if(!(finalIPData[4] == null)) {
			
			Command complete = new Command();
			
			complete.setIPDetails(getIPData());
		}
	}
	
	public static String[] getIPData() {
		
		return finalIPData;
	}
}

import java.util.Arrays;

public class DetailedCompression {
	
	private static int set;
	
	private static String firstSet[];
	
	private static String secondSet[];
	
	private static String thirdSet[];
	
	private static String[] finalIPData = new String[6];
	
	private static Resources resources = new Resources();
	
	public DetailedCompression() {
		
		DetailedCompression.set = 0;
	}
	
	public static void toReceiver(String[] details) {
		
		if(set == 0) firstSet = details;
		if(set == 1) secondSet = details;
		if(set == 2) thirdSet = details;
		
		System.out.println(set);
		System.out.println(Arrays.toString(firstSet));
		System.out.println(Arrays.toString(secondSet));
		System.out.println(Arrays.toString(thirdSet));
		
		if(set == 2) {
			
			analize();
		}
		
		set++;
	}
	
	public static void analize() {
		
		double scoreOne = 0;
		double scoreTwo = 0;
		double scoreThree = 0;
		
		double averageScore = 0;
		double similarityThreshold = 0.5;
		double highest = 0;
		
		for(int i = 0; i < 4; i++) {
			
			scoreOne = resources.similarity(firstSet[i], secondSet[i]);
			scoreTwo = resources.similarity(secondSet[i], thirdSet[i]);
			scoreThree = resources.similarity(firstSet[i], thirdSet[i]);
			
			averageScore = (scoreOne + scoreTwo + scoreThree) / 3;
			
			if(averageScore >= similarityThreshold) {
				
				highest = scoreOne;
				
				if (scoreTwo > highest) {
					
					highest = scoreTwo;
				}
				
				if (scoreThree > highest) {
					
					finalIPData[i] = firstSet[i];
				}
			}
			
			else {
				
				finalIPData[i] = "[" + firstSet[i] + " OR " + secondSet[i] + " OR " + thirdSet[i] + "]";
			}
		}
		
		if(firstSet.length == 5) {
			
			if(finalIPData[3] == null) finalIPData[4] = firstSet[3];
			
			else finalIPData[5] = firstSet[4];
		}
		
		if(secondSet.length == 5) {
			
			if(finalIPData[3] == null) finalIPData[4] = secondSet[3];
			
			else finalIPData[5] = secondSet[4];
		}
		
		if(thirdSet.length == 5) {
			
			if(finalIPData[3] == null) finalIPData[4] = thirdSet[3];
			
			else finalIPData[5] = thirdSet[4];
		}
		
		for(int i = 0; i < finalIPData.length; i++) {
			
			if(finalIPData[4] == null) {
				
				continue;
			}
			
			else {
				
				System.out.println(getIPData());
			}
		}
		
		System.out.println(getIPData());
	}
	
	public static String getIPData() {
		
		return Arrays.toString(finalIPData);
	}
}

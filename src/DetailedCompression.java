import java.util.Arrays;

/**
 * The DetailedCompression class is responsible for receiving, comparing and analyzing the results of the IP check from different websites.
 * It has several private static fields: set, firstSet, secondSet, finalIPData and resources.
 * The set field is an integer that keeps track of the number of sets of data that have been received.
 * The firstSet and secondSet are arrays of strings that hold the details of the IP check from different websites.
 * The finalIPData is an array of strings that holds the analyzed results of the IP check.
 * The resources is an instance of the Resources class.
 *
 * The class has several methods: the constructor, toReceiver, analize, and getIPData.
 * The constructor initializes the set field to 0.
 * The toReceiver method is used to receive the details of the IP check from different websites and assigns them to firstSet or secondSet.
 * The analize method is used to compare and analyze the results of the IP check from different websites and stores them in finalIPData.
 * The getIPData method is used to retrieve the final results of the IP check.
 *
 * @author Deeshan M
 * @version 1.0
 */

public class DetailedCompression {
	
	/**
	* A private static integer that keeps track of the number of sets of data that have been received.
	*/
	
	private static int set;
	
	/**
	* A private static array of strings that holds the details of the IP check from the first website.
	*/
	
	private static String firstSet[];
	
	/**
	* A private static array of strings that holds the details of the IP check from the second website.
	*/
	
	private static String secondSet[];
	
	/**
	* A private static array of strings that holds the analyzed results of the IP check.
	*/
	
	private static String[] finalIPData = new String[5];
	
	/**
	* A private static instance of the Resources class.
	*/
	
	private static Resources resources = new Resources();
	
	/**
	* A constructor that initializes the set field to 0.
	*/
	
	public DetailedCompression() {
		
		DetailedCompression.set = 0;
	}
	
	/**
	* A public static method that receives the details of the IP check from different websites and assigns them to firstSet or secondSet.
	* If set == 1 it calls the analize() method.
	* @param details is an array of strings that holds the details of the IP check from a website.
	*/
	
	public static void toReceiver(String[] details) {
		
		if(set == 0) firstSet = details;
		if(set == 1) secondSet = details;
		
		if(set == 1) analize();
		
		set++;
	}
	
	/**
	* The analize method is used to compare and analyze the results of the IP check from different websites and stores them in finalIPData.
	* The method first initializes a variable called scoreOne to 0, and a variable called similarityThreshold to 0.55.
	* The method then uses a for loop to iterate through the first 4 elements of the firstSet and secondSet arrays.
	* For each iteration, it calls the similarity method from the Resources class and passes in the current element from firstSet and secondSet.
	* The returned value from the similarity method is assigned to scoreOne.
	* If scoreOne is greater than or equal to the similarityThreshold, the current element from firstSet is assigned to the corresponding element in finalIPData.
	* If scoreOne is less than the similarityThreshold, the corresponding element in finalIPData is set to a string that combines the current element from firstSet and secondSet.
	* Next, the method checks if the firstSet and secondSet arrays have a length of 5, and assigns the last element of the array to finalIPData[4] if they do.
	* Finally, the method checks if finalIPData[4] is not null, and assigns the finalIPData to the Command class if it is not.
	*/
	
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
	
	/**
	* The getIPData method is used to return the final IP data stored in the finalIPData array.
	* @return the finalIPData array containing the analyzed and processed IP data.
	*/
	
	public static String[] getIPData() {
		
		return finalIPData;
	}
}

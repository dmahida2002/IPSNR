import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/*
* Resources class provides the utility methods for the IP checker program.
* getFullCountryName method is used to convert the country code to its full name by sending a request to a REST API. 
* nordContAbr method is used to extract the abbreviation of the country name from the output of NordVPN website.
* similarity method is used to calculate the Jaccard similarity between two given strings.
* @author Deeshan M
* @version 1.0
*/

public class Resources {
	
	/**
     * getFullCountryName method is used to convert the country code to its full name by sending a request to a REST API. 
     * @param countryCode the country code to be converted to full name.
     * @return the full country name.
     */
	
	public String getFullCountryName(String countryCode) {
		
        String apiUrl = "https://restcountries.com/v2/all";
        
        try {
        	
            URL url = new URL(apiUrl);
            
            InputStreamReader reader = new InputStreamReader(url.openStream());
            
            @SuppressWarnings("deprecation")
			JsonArray jsonArray = new JsonParser().parse(reader).getAsJsonArray();
            
            for (int i = 0; i < jsonArray.size(); i++) {
            	
                JsonObject country = jsonArray.get(i).getAsJsonObject();
                
                if (country.get("alpha2Code").getAsString().equalsIgnoreCase(countryCode)) {
                	
                	String pulledAPICont = country.get("name").getAsString();
                	
                	if(pulledAPICont.equalsIgnoreCase("United States of America")) {
                		
                		return pulledAPICont.substring(0, pulledAPICont.indexOf(" of"));
                	}
                	
                    return country.get("name").getAsString();
                }
            }
            
        } catch (Exception ex) {
        	
            System.out.println("ERROR: Country code was not converted.");
        }
        
        return countryCode;
    }
	
	/**
	 * Extracts the abbreviation of the country name from a string returned by the NordVPN IP checker.
	 * @param country String representation of the country name returned by the NordVPN IP checker.
	 * @return the full name of the country.
	*/
	
	public String nordContAbr(String country) {
		
		int firstCommaIndex = country.indexOf(',');
		
		return country.substring(0, firstCommaIndex);
	}
	
	/**
	 * This method calculates the Jaccard Similarity between two input strings.
	 * Jaccard Similarity is a measure of the similarity between two sets of data,
	 * in this case the two input strings. It is defined as the size of the intersection
	 * of the sets divided by the size of the union of the sets.
	 * @param with The first string to compare
	 * @param to The second string to compare
	 * @return a double value representing the Jaccard Similarity between the two input strings
	*/
	
	public double similarity(String with, String to) {
        
        Set<Character> set1 = new HashSet<>();
        
        Set<Character> set2 = new HashSet<>();
        
        for (char c : with.toUpperCase().toCharArray()) {
            
            set1.add(c);
        }
        
        for (char c : to.toUpperCase().toCharArray()) {
            
            set2.add(c);
        }
        
        Set<Character> intersection = new HashSet<>(set1);
        
        intersection.retainAll(set2);
        
        Set<Character> union = new HashSet<>(set1);
        
        union.addAll(set2);
        
        double similarity = (double) intersection.size() / (double) union.size();
        
        return similarity;
    }
}

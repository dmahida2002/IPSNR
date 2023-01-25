import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Resources {
	
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
	
	public String nordContAbr(String country) {
		
		int firstCommaIndex = country.indexOf(',');
		
		return country.substring(0, firstCommaIndex);
	}
	
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

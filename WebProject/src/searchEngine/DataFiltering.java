package searchEngine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DataFiltering {
	private DatabaseManager db;
	
	public DataFiltering(){
		db =  new DatabaseManager();
	}
	
	public String filterSearch(String text){
		String userInput = text;
		
		//controlla che la query sia una stringa, che non sia vuota, che non contenga caratteri speciali,,,
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(userInput);
	 
	    if(matcher.matches() & userInput.length()<=50 & userInput.length()>0){
	        return text=db.findRicerca(userInput);
	    }
	    
	    if(!matcher.matches()){
	    	ErrorResponse response = new ErrorResponse("error 1");
	    	return text = response.toJSON();	
	    }
	    
	    if(userInput.length()>50){
	    	ErrorResponse response = new ErrorResponse("error 2");
	    	return text = response.toJSON();	
	    }
	    
	    if(userInput.isEmpty()){
	    	ErrorResponse response = new ErrorResponse("error 3");
	    	return text = response.toJSON();	
	    }
	    return text;
	}		
}

package searchEngine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import provaServlet.SearchServlet;

public class DataFiltering {
	DatabaseManager db=new DatabaseManager();
	SearchServlet servlet=new SearchServlet();
	
	public String filterSearch(String text){
		String userInput = text; //stringa di appoggio per i controlli e la pulizia 
		
		//controlla che la query sia una stringa, che non sia vuota, che non contenga caratteri speciali
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(userInput);
	 
	    if(matcher.matches() & userInput.length()<=50 & userInput.length()>0){
	           db.findRicerca(userInput);
	    }else{
	    	servlet.log("string '"+userInput + "' contains special character, it's empty or it's too long");	         
	    }
	    return text;
	}		
}

package searchEngine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DataFiltering {
	private DatabaseManager db;
	
	public DataFiltering(){
		db =  new DatabaseManager();
	}
	
	public String filterSearch(String text){
		String userInput = text; //stringa di appoggio per i controlli e la pulizia 
		
		//controlla che la query sia una stringa, che non sia vuota, che non contenga caratteri speciali
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(userInput);
	 
	    if(matcher.matches() & userInput.length()<=50 & userInput.length()>0){
	        text=db.findRicerca(userInput);
	    }else{
	    	text=null;	         
	    }
	    return text;
	}		
}

package searchEngine;

import java.util.ArrayList;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.model.Filters;
import com.mongodb.util.JSON;

import org.bson.Document;

public class DatabaseManager{
	
	//Selecting a collection
	private MongoCollection<Document> collection =null;
	// Accessing the database 
	private MongoDatabase mDB = null;
	// Creating a Mongo client
	private static MongoClient mongoClient = null;
    private Document tags=new Document();
	private static int i=0;
	
	public DatabaseManager(){
		mongoClient=getInstance();
		mDB=mongoClient.getDatabase("test");
	}
	
	//mongoClient singleton: solo un'istanza per volta
	public static MongoClient getInstance(){
		if(mongoClient == null) {
			mongoClient = new MongoClient("localhost", 27017);
	      }
	      return mongoClient;
	}
	
	@SuppressWarnings("unchecked")
	public String findRicerca(String a) {
		ArrayList<String> links = new ArrayList<>();
		collection=mDB.getCollection("collection");
		Document result = new Document();
		for(Document document : collection.find(Filters.regex("concepts", a, "i"))) {
			result.append("immagine " + document.getString("id"), (ArrayList<String>)document.get("concepts"));
			links.add(document.getString("id"));
		}
		if(!result.isEmpty()) {
			return convertToJSON(links);
		}else{ 
			System.out.println(result.toString());
			return convertToJSON(links);}
	}
	
	public String convertToJSON(ArrayList<String> links){
        String json = JSON.serialize(links);
        System.out.println(json);
        return json;
    }
	
	public void saveInDB(String url, ArrayList<String> tag){
		tags.append("concepts", tag).append("id", url);
		collection.insertOne(tags);
	}
	
	public void insertTag(String tag) {
		collection.insertOne(new Document("_idTag", i).append("nome", tag));
		i++;
	}
	
	public void compareTag(String a) {
		if(!collection.find(Filters.eq("nome", a)).first().isEmpty()) {
			System.out.println("Tag già esistente!");
		}else tags.append("tag", a);
	}
	
	public void deleteDocument(String url){
		  // Deleting the documents 
	      collection.deleteOne(Filters.eq("id", url)); 
	      System.out.println("Document deleted successfully...");  
	}
	
}

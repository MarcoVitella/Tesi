package searchEngine;

import java.util.ArrayList;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.model.Filters;
import com.mongodb.util.JSON;

import org.bson.Document;


public class DatabaseManager{
	
	public DatabaseManager(){
		mongoClient=getInstance();
		mDB=mongoClient.getDatabase("test");
	}
	
	//Selecting a collection
	MongoCollection<Document> collection =null;
	// Accessing the database 
	MongoDatabase mDB = null;
	// Creating a Mongo client
	private static MongoClient mongoClient = null;
    
	Document tags=new Document();
	static int i=0;
	
	public static MongoClient getInstance(){
		if(mongoClient == null) {
			mongoClient = new MongoClient("localhost", 27017);
	      }
	      return mongoClient;
	}
	
	@SuppressWarnings("unchecked")
	public String findRicerca(String a) {
		collection=mDB.getCollection("collection");
		String doc="documento non trovato!";
		Document result = new Document();
		for(Document document : collection.find(Filters.regex("concepts", a))) {
			result.append("immagine " + document.getString("id"), (ArrayList<String>)document.get("concepts"));
		}
		if(!result.isEmpty()) {
			System.out.println(result.toString());
			return result.toString();
		}else{ 
			System.out.println(result.toString());
			return doc;}
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
	
	@SuppressWarnings("deprecation")
	public void convertToJSON(){
        DBCollection collection = mongoClient.getDB("test").getCollection("collection");
        DBCursor cursor = collection.find();
        String serialize = JSON.serialize(cursor);
        System.out.println(serialize);
    }
}

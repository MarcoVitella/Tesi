import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;


import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.TextSearchOptions;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import com.mongodb.DBCursor;


public class DatabaseManager {
	
	MongoClient mongoClient = new MongoClient();
	MongoDatabase mDB = mongoClient.getDatabase("test");
	MongoCollection<Document> collection = mDB.getCollection("collection");
	Document tags=new Document();
	static int i=0;
	
	/*Block<Document> printBlock = new Block<Document>() {
        @Override
        public void apply(final Document document) {
            System.out.println(document.toJson());
        }
    };
  
    public void fullTextSearch(String query, boolean caseSensitive, boolean diacriticSensitive) {
 
        try {
            MongoCursor<Document> cursor = null;
            cursor = collection.find(new Document("$text", new Document("$search", query).append("$caseSensitive", new Boolean(caseSensitive)).append("$diacriticSensitive", new Boolean(diacriticSensitive)))).iterator();
 
            while (cursor.hasNext()) {
                Document article = cursor.next();
                System.out.println(article);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
 
    }*/
    
	public String findRicerca(String a) {
		String doc="documento non trovato!";
		Document result = new Document();
		for(Document document : collection.find(Filters.regex("concepts", a))) {
			result.append("immagine " + document.getString("id"), (ArrayList<String>)document.get("concepts"));
		}
		if(!result.isEmpty()) {
		return result.toString();
		}else return doc;
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
}

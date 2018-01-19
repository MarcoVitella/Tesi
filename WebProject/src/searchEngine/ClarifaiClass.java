package searchEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.api.request.input.SearchClause;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;

public class ClarifaiClass {
	
	ClarifaiClient client = new ClarifaiBuilder("d25b6723689d4b7998f616789bbede2e")
		    .buildSync();
	MongoClient mongoClient = new MongoClient();
	MongoDatabase mDB = mongoClient.getDatabase("test");
	MongoCollection<Document> collection = mDB.getCollection("collection");
	ArrayList<String> tag = new ArrayList<>();
	Document docu=new Document();

	String url = "http://www.deifiori.it/Images/BgPage/spiaggia5.jpg";
	ClarifaiResponse<List<ClarifaiOutput<Concept>>> output = client.getDefaultModels().generalModel().predict()
	.withInputs(ClarifaiInput.forImage(url))
	.executeSync();
	
	public void imageRecog(String image){
		client.getDefaultModels().generalModel().predict()
		.withInputs(ClarifaiInput.forImage(new File(image)))
		.executeSync();
	}
	
	public void saveImage(String url) {
		List<ClarifaiOutput<Concept>> list = output.get();
		ClarifaiOutput<Concept> clarifaiOutput = list.get(0);
		List<Concept> conceptList = clarifaiOutput.data();
		for(Concept concept : conceptList) {
			tag.add(concept.name());
		}
		docu.append("concepts", tag).append("id", url);
		collection.insertOne(docu);
	}
	
	public void addImagetotheIndex(String url) {
		client.addInputs()
	    .plus(
	        ClarifaiInput.forImage(url)
	    )
	    .executeSync();
	}
	
	public void tagSearch(String tag) {
		client.searchInputs(SearchClause.matchConcept(Concept.forName(tag)))
	    .getPage(1)
	    .executeSync();
	}
}

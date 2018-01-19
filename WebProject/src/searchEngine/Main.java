package searchEngine;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		MongoClient mongoClient = new MongoClient();
		MongoDatabase mDB = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = mDB.getCollection("collection");
		ArrayList<String> tag = new ArrayList<>();
		Document docu=new Document();

		String url = "http://footage.framepool.com/shotimg/qf/581836362-literature-school-book-bookshelf-homework.jpg";
		ClarifaiClient client = new ClarifaiBuilder("d25b6723689d4b7998f616789bbede2e")
			    .buildSync();
		ClarifaiResponse<List<ClarifaiOutput<Concept>>> output = client.getDefaultModels().generalModel().predict()
		.withInputs(ClarifaiInput.forImage(url))
		.executeSync();
		
		List<ClarifaiOutput<Concept>> list = output.get();
		ClarifaiOutput<Concept> clarifaiOutput = list.get(0);
		List<Concept> conceptList = clarifaiOutput.data();
		for(Concept concept : conceptList) {
			System.out.println("nome: " + concept.name() + " probabilita: " + concept.value());
			tag.add(concept.name());
		}
		docu.append("concepts", tag).append("id", url);
		collection.insertOne(docu);
		System.out.println(docu.toString());
		mongoClient.close();
	}

}

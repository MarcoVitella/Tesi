package searchEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.api.request.input.SearchClause;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;

public class ClarifaiClass {
	
	//Collegamento all'API Clarifai
	private 
	ClarifaiClient client = new ClarifaiBuilder("d25b6723689d4b7998f616789bbede2e")
		    .buildSync();
	private DatabaseManager database= new DatabaseManager();
	
	//Assegna i tag all'immagine
	public ClarifaiResponse<List<ClarifaiOutput<Concept>>> imageRecog(String image){
		ClarifaiResponse<List<ClarifaiOutput<Concept>>> output=client.getDefaultModels().generalModel().predict()
		.withInputs(ClarifaiInput.forImage(new File(image)))
		.executeSync();
		return output;
	}
	
	//Salva l'url dell'immagine e i tag ad essa associati nel database 
	public void saveImage(String url) {
		ArrayList<String> tag=new ArrayList<>();
		List<ClarifaiOutput<Concept>> list = imageRecog(url).get();
		ClarifaiOutput<Concept> clarifaiOutput = list.get(0);
		List<Concept> conceptList = clarifaiOutput.data();
		for(Concept concept : conceptList) {
			tag.add(concept.name());
		}
		database.saveInDB(url, tag);
	}
	
	//Indicizza l'immagine, non utilizzato
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

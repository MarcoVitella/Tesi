package searchEngine;

import org.bson.Document;

import com.mongodb.util.JSON;

public class ErrorResponse {
	private String status;
	
	public ErrorResponse(String status){
		this.status = status;
	}
	
	public String toJSON(){
		Document document = new Document();
		document.append("status", getStatus());
		return JSON.serialize(document);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}

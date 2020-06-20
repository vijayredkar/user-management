package com.journaldev.bootifulmongodb.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserData {

	@Id
	private String userId;
	 private String type;
	 Attributes attributes;


	 // Getter Methods 

	 public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getType() {
	  return type;
	 }

	

	 // Setter Methods 

	 public void setType(String type) {
	  this.type = type;
	 }

	public Attributes getAttributes() {
		return attributes;
	}

	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

	
}

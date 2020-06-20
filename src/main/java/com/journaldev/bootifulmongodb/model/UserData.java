package com.journaldev.bootifulmongodb.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserData {

	@Id
	private String id;
	
	@Indexed
	private UUID userId;	
	
	private String type;
	 
	Attributes attributes;

	 // Getter Methods 

	 public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
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

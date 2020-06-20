package com.journaldev.bootifulmongodb.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Attributes {

	@Id
	private String attributesId;
	 private String email;
	 private String mobilePhone;
	 private String identityNumber;
	 private String address;
	 private String postCode;
	 private String state;
	 private String country;
	 private String document;


	 // Getter Methods 

	 public String getEmail() {
	  return email;
	 }

	 public String getMobilePhone() {
	  return mobilePhone;
	 }

	 public String getIdentityNumber() {
	  return identityNumber;
	 }

	 public String getAddress() {
	  return address;
	 }

	 public String getPostCode() {
	  return postCode;
	 }

	 public String getState() {
	  return state;
	 }

	 public String getCountry() {
	  return country;
	 }

	 public String getDocument() {
	  return document;
	 }

	 // Setter Methods 

	 public void setEmail(String email) {
	  this.email = email;
	 }

	 public void setMobilePhone(String mobilePhone) {
	  this.mobilePhone = mobilePhone;
	 }

	 public void setIdentityNumber(String identityNumber) {
	  this.identityNumber = identityNumber;
	 }

	 public void setAddress(String address) {
	  this.address = address;
	 }

	 public void setPostCode(String postCode) {
	  this.postCode = postCode;
	 }

	 public void setState(String state) {
	  this.state = state;
	 }

	 public void setCountry(String country) {
	  this.country = country;
	 }

	 public void setDocument(String document) {
	  this.document = document;
	 }
}

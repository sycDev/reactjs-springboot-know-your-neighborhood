package com.wou.kyn.payload.response;

import java.time.Instant;

public class StoreResponse {
	private Long storeId;
	private String storeName;
	private String contactNumber;
	private String openingHours;
	private String address;
	private Instant createdAt;
	private UserSummary createdBy;
	private Instant updatedAt;
	private UserSummary updatedBy;

	public StoreResponse() {
		
	}
	
	public StoreResponse(Long storeId, String storeName, String contactNumber, String openingHours, 
			String address, Instant createdAt, UserSummary createdBy) {

		this.storeId = storeId;
		this.storeName = storeName;
		this.contactNumber = contactNumber;
		this.openingHours = openingHours;
		this.address = address;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
	}
	
	public StoreResponse(Long storeId, String storeName, String contactNumber, String openingHours, 
			String address, Instant createdAt, UserSummary createdBy, Instant updatedAt, UserSummary updatedBy) {

		this.storeId = storeId;
		this.storeName = storeName;
		this.contactNumber = contactNumber;
		this.openingHours = openingHours;
		this.address = address;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.updatedAt = updatedAt;
		this.updatedBy = updatedBy;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public UserSummary getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserSummary createdBy) {
		this.createdBy = createdBy;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	public UserSummary getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(UserSummary updatedBy) {
		this.updatedBy = updatedBy;
	}
}

package com.wou.kyn.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class StoreRequest {
	@NotBlank(message = "Store name is required")
    @Size(min = 2, max = 50, message = "Store name must be between 2 and 30 characters")
	private String storeName;

	@Size(max = 20, message = "Contact number must not exceed 20 characters")
    private String contactNumber;

	@Size(max = 255, message = "Opening hours must not exceed 255 characters")
    private String openingHours;

	@NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;

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
}

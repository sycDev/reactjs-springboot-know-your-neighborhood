package com.wou.kyn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.wou.kyn.entity.audit.UserDateAudit;

@Entity
@Table(name = "store")
public class Store extends UserDateAudit {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id", nullable = false)
    private Long storeId;

    @NotBlank(message = "Store name is required")
    @Size(min = 2, max = 50, message = "Store name must be between 2 and 30 characters")
    @Column(name = "store_name", nullable = false)
    private String storeName;

    @Column(name = "contact_number")
    @Size(max = 20, message = "Contact number must not exceed 20 characters")
    private String contactNumber;

    @Column(name = "opening_hours")
    @Size(max = 255, message = "Opening hours must not exceed 255 characters")
    private String openingHours;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;

    public Store() {
    	
    }
    
    public Store(String storeName, String contactNumber, String openingHours, String address) {
    	this.storeName = storeName;
    	this.contactNumber = contactNumber;
    	this.openingHours = openingHours;
    	this.address = address;
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
}

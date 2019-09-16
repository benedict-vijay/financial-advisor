package com.xyz.fa.riskportfolio.dao.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class GenericEntity {

	@Id
	private Long id;

	private int active;
	private String createdBy;
	private String modifiedBy;
	private Date createdDate;
	private Date modifiedDate;

	public GenericEntity() {

	}
	
	public GenericEntity(Long id, int active, String createdBy) {
		this.id = id;
		this.active = active;
		this.createdBy = createdBy;
		this.createdDate = new Date();
	}
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}

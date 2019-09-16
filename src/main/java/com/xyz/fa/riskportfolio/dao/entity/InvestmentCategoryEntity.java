package com.xyz.fa.riskportfolio.dao.entity;

import javax.persistence.Entity;

@Entity
public class InvestmentCategoryEntity extends GenericEntity {
	
	private String name;
	
/*	@OneToOne(mappedBy = "id")
	private RiskPortfolioEntity riskPortfolioEntity;
*/	
	public InvestmentCategoryEntity() {

	}
	
	public InvestmentCategoryEntity(Long id, String name, int active, String createdBy) {
		super(id, active, createdBy);
		this.setName(name);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("investmentCategory=[").append("id=").append(getId()).append(", ")
				.append("name=").append(name).append("]").toString();
	}
}

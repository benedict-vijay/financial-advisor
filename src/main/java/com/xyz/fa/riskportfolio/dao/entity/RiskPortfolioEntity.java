package com.xyz.fa.riskportfolio.dao.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RiskPortfolioEntity extends GenericEntity {

	private Integer riskLevel;
	
	@ManyToOne(cascade=CascadeType.MERGE, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "InvestmentCategoryEntity.id"/*, insertable=false, updatable=false*/)
	private InvestmentCategoryEntity investmentCategoryEntity;
	
	private Integer percentage;

	public RiskPortfolioEntity() {

	}
	
	public RiskPortfolioEntity(Long id, int active, String createdBy, Integer riskLevel,
			InvestmentCategoryEntity investmentCategoryEntity, Integer percentage) {
		super(id, active, createdBy);
		this.riskLevel = riskLevel;
		this.investmentCategoryEntity = investmentCategoryEntity;
		this.percentage = percentage;
	}

	public Integer getRiskLevel() {
		return riskLevel;
	}
	
	public void setRiskLevel(Integer riskLevel) {
		this.riskLevel = riskLevel;
	}
	
	public Integer getPercentage() {
		return percentage;
	}
	
	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}

	public InvestmentCategoryEntity getInvestmentCategoryEntity() {
		return investmentCategoryEntity;
	}
	
	public void setInvestmentCategoryEntity(InvestmentCategoryEntity investmentCategoryEntity) {
		this.investmentCategoryEntity = investmentCategoryEntity;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("rp=[").append("id").append(getId()).append("riskLevel").append(riskLevel)
				.append("\n").append(investmentCategoryEntity).append("]").toString();
	}
}

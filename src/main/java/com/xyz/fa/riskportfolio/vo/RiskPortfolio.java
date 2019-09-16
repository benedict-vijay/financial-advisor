package com.xyz.fa.riskportfolio.vo;

import java.util.Map;

public class RiskPortfolio {

	private int riskLevel;
	private Map<Long, InvestmentCategory> investmentCategories;

	public RiskPortfolio() {
		// TODO Auto-generated constructor stub
	}
	
	public RiskPortfolio(int riskLevel) {
		this.riskLevel = riskLevel;
	}
	
	public int getRiskLevel() {
		return riskLevel;
	}
	
	public void setRiskLevel(int riskLevel) {
		this.riskLevel = riskLevel;
	}
	
	public Map<Long, InvestmentCategory> getInvestmentCategories() {
		return investmentCategories;
	}

	public void setInvestmentCategories(Map<Long, InvestmentCategory> investmentCategories) {
		this.investmentCategories = investmentCategories;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RiskPortfolio) {
			RiskPortfolio riskPortfolio = (RiskPortfolio) obj;
			return riskPortfolio.getRiskLevel() == this.getRiskLevel();
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return riskLevel * 31;
	}
	
}

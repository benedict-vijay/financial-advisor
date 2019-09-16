package com.xyz.fa.riskportfolio.vo;

import java.util.List;

public class RiskPortfolioFund {

	private int riskLevel;
	private List<Fund> funds;

	public int getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(int riskLevel) {
		this.riskLevel = riskLevel;
	}

	public List<Fund> getFunds() {
		return funds;
	}

	public void setFunds(List<Fund> funds) {
		this.funds = funds;
	}

}

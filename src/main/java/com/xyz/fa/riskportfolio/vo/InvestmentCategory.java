package com.xyz.fa.riskportfolio.vo;

public class InvestmentCategory {

	private Long id;
	private String name;
	private int percentage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof InvestmentCategory) {
			InvestmentCategory investmentCategory = (InvestmentCategory) obj;
			return investmentCategory.getId() == this.getId();
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return id != null ? id.intValue() * 31 : 0;
	}
	
}

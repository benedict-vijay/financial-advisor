package com.xyz.fa.riskportfolio.vo;

import java.math.BigDecimal;

public class Fund {

	private long investCatId;
	private BigDecimal money;

	public Fund() {

	}
	
	public Fund(long investCatId, BigDecimal money) {
		this.investCatId = investCatId;
		this.money = money;
	}
	
	public long getInvestCatId() {
		return investCatId;
	}

	public void setInvestCatId(long investCatId) {
		this.investCatId = investCatId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("fund=[").append("investCatId=").append(investCatId).append(", money=").append(money).append("]").toString();
	}

}

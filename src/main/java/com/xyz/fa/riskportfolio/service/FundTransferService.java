package com.xyz.fa.riskportfolio.service;

import java.util.List;

import com.xyz.fa.riskportfolio.vo.RiskPortfolioFund;

public interface FundTransferService {
	
	/**
 	 * This method calculates the minimum transactions needed to make the customer investment match the ideal investment strategy. 

	 * @param riskPortfolioFund
	 * @return
	 */
	List<String> calculateTransference(RiskPortfolioFund riskPortfolioFund) 
			throws RiskPortfolioServiceException;
}
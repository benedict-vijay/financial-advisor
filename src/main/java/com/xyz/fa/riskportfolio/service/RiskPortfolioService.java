package com.xyz.fa.riskportfolio.service;

import java.util.Map;

import com.xyz.fa.riskportfolio.vo.RiskPortfolio;

public interface RiskPortfolioService {
	
	/**
	 * Finds all the risk portfolios registered in the system 
	 * @return
	 */
	Map<Integer, RiskPortfolio> findAllRiskPortfolio() throws RiskPortfolioServiceException;
	
	RiskPortfolio getPortfolioByRiskLevel(int riskLevel) throws RiskPortfolioServiceException;
	
}

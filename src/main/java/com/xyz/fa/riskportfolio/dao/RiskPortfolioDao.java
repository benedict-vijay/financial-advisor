package com.xyz.fa.riskportfolio.dao;

import java.util.List;

import com.xyz.fa.riskportfolio.dao.entity.RiskPortfolioEntity;

public interface RiskPortfolioDao {
	RiskPortfolioEntity getRiskPortfolioById(Long riskPortfolioId) throws RiskPortfolioDaoException;
	List<RiskPortfolioEntity> findAllRiskPortfolio() throws RiskPortfolioDaoException;
}

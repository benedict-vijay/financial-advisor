package com.xyz.fa.riskportfolio.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.xyz.fa.riskportfolio.dao.entity.RiskPortfolioEntity;
import com.xyz.fa.riskportfolio.dao.repository.RiskPortfolioRepository;

@Repository
public class RiskPortfolioDaoImpl implements RiskPortfolioDao {

	@Resource
	private RiskPortfolioRepository riskPortfolioRepository;
	
	@Override
	public RiskPortfolioEntity getRiskPortfolioById(Long riskPortfolioId) throws RiskPortfolioDaoException {
		try {
			return riskPortfolioRepository.getOne(riskPortfolioId);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RiskPortfolioDaoException(e);
		}
	}

	@Override
	public List<RiskPortfolioEntity> findAllRiskPortfolio() throws RiskPortfolioDaoException {
		try {
			return riskPortfolioRepository.findAll();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RiskPortfolioDaoException(e);
		}
	}
	
}

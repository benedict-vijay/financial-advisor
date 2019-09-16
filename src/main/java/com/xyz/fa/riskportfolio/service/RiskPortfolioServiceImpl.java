package com.xyz.fa.riskportfolio.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xyz.fa.riskportfolio.dao.RiskPortfolioDao;
import com.xyz.fa.riskportfolio.dao.RiskPortfolioDaoException;
import com.xyz.fa.riskportfolio.dao.entity.RiskPortfolioEntity;
import com.xyz.fa.riskportfolio.vo.InvestmentCategory;
import com.xyz.fa.riskportfolio.vo.RiskPortfolio;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RiskPortfolioServiceImpl implements RiskPortfolioService {
	
	/**
	 * This is done for enabling the cross-cutting concerns to take effect even for local method calls.
	 */
	@Autowired
	private RiskPortfolioServiceImpl riskPortfolioService;
	
	@Autowired
	private RiskPortfolioDao riskPortfolioDaoDao;
	
	/**
	 * Finds all the risk portfolios registered in the system and put them in the cache (riskportfolio). The cache is scheduled to refresh periodically
	 * as configured in the spring application.properties. 
	 */
	@Override
	@Transactional
    @Cacheable(cacheNames = "riskportfolio")
	public Map<Integer, RiskPortfolio> findAllRiskPortfolio() throws RiskPortfolioServiceException {
		Map<Integer, RiskPortfolio> riskPortfolioMap = new HashMap<>();
		try {
			List<RiskPortfolioEntity> riskPortfolioEntities = riskPortfolioDaoDao.findAllRiskPortfolio();
			
			if (riskPortfolioEntities != null) {
				for (RiskPortfolioEntity riskPortfolioEntity : riskPortfolioEntities) {
					RiskPortfolio riskPortfolio = null;
					if (riskPortfolioMap.containsKey(riskPortfolioEntity.getRiskLevel())) {
						riskPortfolio = riskPortfolioMap.get(riskPortfolioEntity.getRiskLevel());
					} else {
						riskPortfolio = new RiskPortfolio();
						riskPortfolio.setRiskLevel(riskPortfolioEntity.getRiskLevel());
						riskPortfolio.setInvestmentCategories(new HashMap<>());
						riskPortfolioMap.put(riskPortfolioEntity.getRiskLevel(), riskPortfolio);
					}

					InvestmentCategory investmentCategory = new InvestmentCategory();
					investmentCategory.setId(riskPortfolioEntity.getInvestmentCategoryEntity().getId());
					investmentCategory.setName(riskPortfolioEntity.getInvestmentCategoryEntity().getName());
					investmentCategory.setPercentage(riskPortfolioEntity.getPercentage());
					riskPortfolio.getInvestmentCategories().put(investmentCategory.getId(), investmentCategory);
				}
			}
			
		} catch (RiskPortfolioDaoException e) {
			e.printStackTrace();
			throw new RiskPortfolioServiceException(e);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RiskPortfolioServiceException(e);
		}
		
		System.out.println("Number of Risk Portfolios fetched from DB: "  + riskPortfolioMap.size());
		return riskPortfolioMap;
	}

	/**
	 * In this method we fetch the RiskPortfolio mapped for a particular riskLevel.
	 * The time complexity for this will be O(1). 
	 */
	@Override
	@Transactional
	public RiskPortfolio getPortfolioByRiskLevel(int riskLevel) throws RiskPortfolioServiceException {
		try {
			Map<Integer, RiskPortfolio> riskPortfolios = riskPortfolioService.findAllRiskPortfolio();
			return riskPortfolios.get(riskLevel);
			
		} catch (RiskPortfolioServiceException e) {
			e.printStackTrace();
			throw new RiskPortfolioServiceException(e, "FA-101", String.valueOf(riskLevel));
		}
		
	}
}

package com.xyz.fa.riskportfolio.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xyz.fa.riskportfolio.dao.RiskPortfolioDao;
import com.xyz.fa.riskportfolio.dao.RiskPortfolioDaoImpl;
import com.xyz.fa.riskportfolio.dao.entity.InvestmentCategoryEntity;
import com.xyz.fa.riskportfolio.dao.entity.RiskPortfolioEntity;
import com.xyz.fa.riskportfolio.dao.repository.RiskPortfolioRepository;
import com.xyz.fa.riskportfolio.vo.Fund;
import com.xyz.fa.riskportfolio.vo.RiskPortfolioFund;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class FundTransferServiceTest {
	
    @MockBean
    static RiskPortfolioRepository riskPortfolioRepository;
	
	@Autowired
	private FundTransferService fundTransferService;
    
	@TestConfiguration
    static class FundTransferServiceImplTestContextConfiguration {
  
        @Bean
        public FundTransferService fundTransferService() {
            return new FundTransferServiceImpl();
        }
        
        @Bean
        public RiskPortfolioService riskPortfolioService() {
            return new RiskPortfolioServiceImpl();
        }

        @Bean
        public RiskPortfolioDao riskPortfolioDao() {
            return new RiskPortfolioDaoImpl();
        }
        
        @Bean
        public RiskPortfolioRepository riskPortfolioRepository() {
        	return riskPortfolioRepository;
        }

        
    }

	@Before
	public void init() {
		when(riskPortfolioRepository.findAll()).thenReturn(new RiskPortfolioMockData().getRiskPortfolioEntityList()); 
	}
	
	@After
	public void after() {
		riskPortfolioRepository = null;
	}
	
	
	@Test
	public void testCalculateTransference() {
		try {
			RiskPortfolioFund riskPortfolioFund = new RiskPortfolioFund();
			riskPortfolioFund.setRiskLevel(6);
			List<Fund> funds = new ArrayList<>();
			funds.add(new Fund(1, new BigDecimal(200)));
			funds.add(new Fund(2, new BigDecimal(200)));
			funds.add(new Fund(3, new BigDecimal(200)));
			funds.add(new Fund(4, new BigDecimal(200)));
			funds.add(new Fund(5, new BigDecimal(200)));
			
			riskPortfolioFund.setFunds(funds);
			List<String> transferenceList = fundTransferService.calculateTransference(riskPortfolioFund);
			assertEquals(3, transferenceList.size());
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testCalculateTransference_withEmptyInputIC() {
		try {
			RiskPortfolioFund riskPortfolioFund = new RiskPortfolioFund();
			riskPortfolioFund.setRiskLevel(6);
			List<Fund> funds = new ArrayList<>();
			funds.add(new Fund(1, new BigDecimal(200)));
			funds.add(new Fund(2, new BigDecimal(200)));
			funds.add(new Fund(3, new BigDecimal(200)));
			funds.add(new Fund(4, new BigDecimal(200)));
			funds.add(new Fund(5, new BigDecimal(200)));
			
			//riskPortfolioFund.setFunds(funds);
			fundTransferService.calculateTransference(riskPortfolioFund);
			fail();
		} catch (Exception e) {
			assertEquals("com.xyz.fa.riskportfolio.service.RiskPortfolioDataException: Risk Portfolio funds data not Present !!", e.getMessage());
		}
	}
	
}

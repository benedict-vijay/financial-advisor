package com.xyz.fa.riskportfolio.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.xyz.fa.riskportfolio.dao.repository.RiskPortfolioRepository;
import com.xyz.fa.riskportfolio.vo.Fund;
import com.xyz.fa.riskportfolio.vo.RiskPortfolio;
import com.xyz.fa.riskportfolio.vo.RiskPortfolioFund;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class RiskPortfolioServiceTest {
	
    @MockBean
    static RiskPortfolioRepository riskPortfolioRepository;
	
	@Autowired
	private RiskPortfolioService riskPortfolioService;
    
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
	public void testGetPortfolioByRiskLevel() {
		try {
			
			RiskPortfolio riskPortfolio = riskPortfolioService.getPortfolioByRiskLevel(6);
			assertNotNull(riskPortfolio);
			assertEquals(6, riskPortfolio.getRiskLevel());
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetPortfolioBy_invalid_RiskLevel() {
		try {
			
			RiskPortfolio riskPortfolio = riskPortfolioService.getPortfolioByRiskLevel(0);
			assertNull(riskPortfolio);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testAllRiskPortfolio() {
		try {
			
			Map<Integer, RiskPortfolio> riskPortfolioMap = riskPortfolioService.findAllRiskPortfolio();
			assertNotNull(riskPortfolioMap);
			assertEquals(10, riskPortfolioMap.size());
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
}

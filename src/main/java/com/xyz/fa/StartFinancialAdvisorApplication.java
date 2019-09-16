package com.xyz.fa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.xyz.fa.riskportfolio.dao.entity.InvestmentCategoryEntity;
import com.xyz.fa.riskportfolio.dao.entity.RiskPortfolioEntity;
import com.xyz.fa.riskportfolio.dao.repository.InvestmentCategoryRepository;
import com.xyz.fa.riskportfolio.dao.repository.RiskPortfolioRepository;

@SpringBootApplication
public class StartFinancialAdvisorApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartFinancialAdvisorApplication.class, args);
    }

    // run this only on profile 'demo', avoid run this in test
    @Profile("demo")
    @Bean
	CommandLineRunner initDatabase(InvestmentCategoryRepository investmentCategoryRepository, RiskPortfolioRepository riskPortfolioRepository) {
        return args -> {
        	
        	InvestmentCategoryEntity investmentCategory1 = getInvestmentCategory(investmentCategoryRepository, 1L, "Bonds");
        	InvestmentCategoryEntity investmentCategory2 = getInvestmentCategory(investmentCategoryRepository, 2L, "Large Cap");
        	InvestmentCategoryEntity investmentCategory3 = getInvestmentCategory(investmentCategoryRepository, 3L, "Mid Cap");
        	InvestmentCategoryEntity investmentCategory4 = getInvestmentCategory(investmentCategoryRepository, 4L, "Foreign");
        	InvestmentCategoryEntity investmentCategory5 = getInvestmentCategory(investmentCategoryRepository, 5L, "Small Cap");
        	
        	Map<Integer, List<Integer>> percentMap = new HashMap<>();
        	loadPercetageMap(percentMap);
        	
        	List<RiskPortfolioEntity> list = new ArrayList<>();
        	for (int i = 1, j = 1; i <= 50 && j <= 10; i++, j++) {
        		
        		list.add(riskPortfolioRepository.saveAndFlush(new RiskPortfolioEntity(new Long(i), 1, "ADMIN", j, investmentCategory1, percentMap.get(j).get(0))));
        		list.add(riskPortfolioRepository.saveAndFlush(new RiskPortfolioEntity(new Long(++i), 1, "ADMIN", j, investmentCategory2, percentMap.get(j).get(1))));
        		list.add(riskPortfolioRepository.saveAndFlush(new RiskPortfolioEntity(new Long(++i), 1, "ADMIN", j, investmentCategory3, percentMap.get(j).get(2))));
        		list.add(riskPortfolioRepository.saveAndFlush(new RiskPortfolioEntity(new Long(++i), 1, "ADMIN", j, investmentCategory4, percentMap.get(j).get(3))));
        		list.add(riskPortfolioRepository.saveAndFlush(new RiskPortfolioEntity(new Long(++i), 1, "ADMIN", j, investmentCategory5, percentMap.get(j).get(4))));
            	
        	}
        	
        	//System.out.println(list);
        	        	
        };
        
        
    }

	private void loadPercetageMap(Map<Integer, List<Integer>> percentMap) {
		percentMap.put(1, new ArrayList<>(Arrays.asList(80, 20, 0, 0, 0)));
		percentMap.put(2, new ArrayList<>(Arrays.asList(70, 15, 15, 0, 0)));
		percentMap.put(3, new ArrayList<>(Arrays.asList(60, 15, 15, 10, 0)));
		percentMap.put(4, new ArrayList<>(Arrays.asList(50, 20, 20, 10, 0)));
		percentMap.put(5, new ArrayList<>(Arrays.asList(40, 20, 20, 20, 0)));
		percentMap.put(6, new ArrayList<>(Arrays.asList(35, 25, 5, 30, 5)));
		percentMap.put(7, new ArrayList<>(Arrays.asList(20, 25, 25, 25, 5)));
		percentMap.put(8, new ArrayList<>(Arrays.asList(10, 20, 40, 20, 10)));
		percentMap.put(9, new ArrayList<>(Arrays.asList(5, 15, 40, 25, 15)));
		percentMap.put(10, new ArrayList<>(Arrays.asList(0, 5, 25, 30, 40)));
	}

	private InvestmentCategoryEntity getInvestmentCategory(InvestmentCategoryRepository investmentCategoryRepository, Long id, String investCatName) {
		InvestmentCategoryEntity investmentCategory = investmentCategoryRepository.saveAndFlush(new InvestmentCategoryEntity(id, investCatName, 1, "ADMIN"));
		return investmentCategory;
	}
	
}
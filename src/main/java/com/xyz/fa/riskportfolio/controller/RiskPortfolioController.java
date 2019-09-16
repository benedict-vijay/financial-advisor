package com.xyz.fa.riskportfolio.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.fa.riskportfolio.service.FundTransferService;
import com.xyz.fa.riskportfolio.service.RiskPortfolioService;
import com.xyz.fa.riskportfolio.service.RiskPortfolioServiceException;
import com.xyz.fa.riskportfolio.vo.FAConfig;
import com.xyz.fa.riskportfolio.vo.RiskPortfolio;
import com.xyz.fa.riskportfolio.vo.RiskPortfolioFund;

@RestController
@RequestMapping("/fa")
public class RiskPortfolioController {

	@Autowired
	private RiskPortfolioService riskPortfolioService;
	
	@Autowired
	private FundTransferService fundTransferService;
	
	/**
	 * This api returns all the risk portfolios by their risk level.
	 * @return
	 * @throws RiskPortfolioControllerException
	 */
    @GetMapping("/riskportfolios")
    public Map<Integer, RiskPortfolio> findAll() throws RiskPortfolioControllerException {
    	try {
            return riskPortfolioService.findAllRiskPortfolio();
            
    	} catch (RiskPortfolioServiceException e) {
    		e.printStackTrace();
    		throw new RiskPortfolioControllerException(e);
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new RiskPortfolioControllerException(e);
    	}
    }

    /**
     * This api give the corresponding portfolio for the give risklevel.
     * @param riskLevel
     * @return
     * @throws RiskPortfolioControllerException
     */
    @GetMapping("/getPortfolioByRiskLevel")
    public RiskPortfolio getPortfolioByRiskLevel(@RequestParam("risklevel") int riskLevel) throws RiskPortfolioControllerException {
    	
    	try {
        	return riskPortfolioService.getPortfolioByRiskLevel(riskLevel);
            
    	} catch (RiskPortfolioServiceException e) {
    		e.printStackTrace();
    		throw new RiskPortfolioControllerException(e);
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new RiskPortfolioControllerException(e);
    	}
    }

    /**
     * This api returns the transference list.
     * @param riskPortfolioFund
     * @return
     * @throws RiskPortfolioControllerException
     */
    @GetMapping("/getTransferenceList")
    public List<String> getTransferenceList(@RequestBody RiskPortfolioFund riskPortfolioFund) throws RiskPortfolioControllerException {
        
    	try {
            return fundTransferService.calculateTransference(riskPortfolioFund);
            
    	} catch (RiskPortfolioServiceException e) {
    		e.printStackTrace();
    		throw new RiskPortfolioControllerException(e);
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new RiskPortfolioControllerException(e);
    	}
        
    }
    
    /**
     * This api returns the Financia Advisor application configuration details.
     * @param riskPortfolioFund
     * @return
     * @throws RiskPortfolioControllerException
     */
    @GetMapping("/getFAConfigDetails")
    public FAConfig getFAConfigDetails(@RequestBody RiskPortfolioFund riskPortfolioFund) throws RiskPortfolioControllerException {
    	return new FAConfig();
    }
}

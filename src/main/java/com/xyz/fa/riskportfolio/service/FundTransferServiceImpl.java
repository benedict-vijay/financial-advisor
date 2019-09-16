package com.xyz.fa.riskportfolio.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xyz.fa.riskportfolio.vo.Fund;
import com.xyz.fa.riskportfolio.vo.RiskPortfolio;
import com.xyz.fa.riskportfolio.vo.RiskPortfolioFund;

@Service
public class FundTransferServiceImpl implements FundTransferService {

	private static final String PERIOD = ".";

	private static final String TO = " to ";

	private static final String FROM = " from ";

	private static final String TRANSFER = "Transfer $";

	@Autowired
	private RiskPortfolioService riskPortfolioService;
	
	/**
	 * This method calculates the minimum transactions needed to make the customer investment match the ideal investment strategy. 
	 * Below are the four major operations.
	 * 1. Calculate the total amount.
	 * 2. Populate the debit and the credit list.
	 * 3. Sort the debit and credit list.
	 * 4. Calculate the transference.
	 * 
	 * The overall time complexity is O(n log n) and the space complexity is O(n).
	 */
	@Override
	@Transactional
	public List<String> calculateTransference(RiskPortfolioFund riskPortfolioFund) throws RiskPortfolioServiceException {
		List<String> transference = new ArrayList<>();
		try {
			
			validate(riskPortfolioFund);

			Map<Integer, RiskPortfolio> riskPortfolios = riskPortfolioService.findAllRiskPortfolio();
			RiskPortfolio riskPortfolio = riskPortfolios.get(riskPortfolioFund.getRiskLevel());
			
			if (riskPortfolioFund.getFunds() != null) {
				List<Fund> customerAllotedFunds = riskPortfolioFund.getFunds();
				//1. Calculate the total amount.
				BigDecimal totalAmt = getTotalAmount(customerAllotedFunds);
				
				List<Fund> creditList = new ArrayList<>();
				List<Fund> debitList = new ArrayList<>();
				
				//2. Populate the debit and the credit list.
				populateCreditDebitList(riskPortfolio, totalAmt, new ArrayList<>(riskPortfolioFund.getFunds()), creditList, debitList);
				
				//3. Sort the debit and credit list.
				sortByDecendingOrder(creditList, debitList);
				
				//4. Calculate the transference.
				transference = calculateTransference(riskPortfolio, creditList, debitList);
				
			}
		} catch (RiskPortfolioDataException e) {
			e.printStackTrace();
			throw new RiskPortfolioServiceException(e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RiskPortfolioServiceException(e);
		}
		return transference;
	}

	/**
	 * Basic validations to check whether the risk portfolio data is present.
	 * 
	 * @param riskPortfolioFund
	 * @throws RiskPortfolioDataException
	 */
	private void validate(RiskPortfolioFund riskPortfolioFund) throws RiskPortfolioDataException {
		if (riskPortfolioFund == null || riskPortfolioFund.getFunds() == null
				|| riskPortfolioFund.getFunds().size() == 0 || riskPortfolioFund.getRiskLevel() == 0) {
			throw new RiskPortfolioDataException("Risk Portfolio funds data not Present !!");
		}
		
		if (riskPortfolioFund.getRiskLevel() == 0) {
			throw new RiskPortfolioDataException("Risk Portfolio funds data not Present !!");
		}
		
	}
	
	/**
	 * Here we populate the debit and the credit list. debitList will have the exact
	 * amount that must be debited from the respective Investment Category account.
	 * creditList will have the exact amount that must be credited to the respective
	 * Investment Category account.
	 * The time complexity for this is O(n) and space complexity is O(n).
	 * @param riskPortfolio
	 * @param totalAmt
	 * @param newAllotedFunds
	 * @param creditList
	 * @param debitList
	 */
	private void populateCreditDebitList(RiskPortfolio riskPortfolio, BigDecimal totalAmt, List<Fund> newAllotedFunds,
			List<Fund> creditList, List<Fund> debitList) {
		for (Fund fund : newAllotedFunds) {
			BigDecimal oldMoney = fund.getMoney();
			
			int percent = riskPortfolio.getInvestmentCategories().get(fund.getInvestCatId()).getPercentage();
			BigDecimal newMoney = totalAmt.multiply(new BigDecimal(percent).divide(new BigDecimal(100)));
			
			if (newMoney.compareTo(oldMoney) > 0) {
				// account to be credited
				fund.setMoney(newMoney.subtract(oldMoney));
				creditList.add(fund);
			} else if (newMoney.compareTo(oldMoney) < 0) {
				// account to be debited
				fund.setMoney(oldMoney.subtract(newMoney));
				debitList.add(fund);
			}
		}
		
	}
	
	/**
	 * Here we calculate the total amount. This will take O(n) time complexity and O(1) space complexity.
	 * @param customerAllotedFunds
	 * @return
	 */
	private BigDecimal getTotalAmount(List<Fund> customerAllotedFunds) {
		BigDecimal totalAmt = new BigDecimal(0);
		for (Fund fund : customerAllotedFunds) {
			fund.setMoney(fund.getMoney() == null ? BigDecimal.ZERO : fund.getMoney());
			totalAmt = totalAmt.add(fund.getMoney());
		}
		return totalAmt;
	}

	/**
	 * Sort the creditList and debitList in descending order. The time complexity is O(n log n) and O(n) space complexity.
	 * @param creditList
	 * @param debitList
	 */
	private void sortByDecendingOrder(List<Fund> creditList, List<Fund> debitList) {
		Comparator<Fund> fundComparator = (fund1, fund2) -> fund1.getMoney().compareTo(fund2.getMoney());
		Collections.sort(creditList, fundComparator.reversed());
		Collections.sort(debitList, fundComparator.reversed());
	}

	/**
	 * Here we prepare the suggestions by finding the minimum number of fund transfers to be made to reach the ideal Investment strategy.
	 * We iterate through the sorted credit and debit list in a greedy way. The idea here is to completely transfer the funds in the debitList one by one.
	 * The time complexity for this is O(n) and space complexity is O(1).
	 * @param riskPortfolio
	 * @param creditList
	 * @param debitList
	 * @return
	 */
	private List<String> calculateTransference(RiskPortfolio riskPortfolio, List<Fund> creditList, List<Fund> debitList) {
		List<String> transference = new ArrayList<>();
		int cCtr = 0, dCtr = 0;
		while (cCtr < creditList.size() && dCtr < debitList.size()) {
			if (creditList.get(cCtr).getMoney().compareTo(debitList.get(dCtr).getMoney()) == 0) {
				
				transference.add(new StringBuilder().append(TRANSFER).append(debitList.get(dCtr).getMoney())
						.append(FROM)
						.append(riskPortfolio.getInvestmentCategories().get(debitList.get(dCtr).getInvestCatId()).getName())
						.append(TO).append(riskPortfolio.getInvestmentCategories().get(creditList.get(cCtr).getInvestCatId()).getName())
						.append(PERIOD)
						.toString());
				
				debitList.get(dCtr).setMoney(BigDecimal.ZERO);
				creditList.get(cCtr).setMoney(BigDecimal.ZERO);
				dCtr++;
				cCtr++;
				
			} else if (creditList.get(cCtr).getMoney().compareTo(debitList.get(dCtr).getMoney()) < 0) {
				transference.add(new StringBuilder().append(TRANSFER).append(creditList.get(cCtr).getMoney())
						.append(FROM)
						.append(riskPortfolio.getInvestmentCategories().get(debitList.get(dCtr).getInvestCatId()).getName())
						.append(TO).append(riskPortfolio.getInvestmentCategories().get(creditList.get(cCtr).getInvestCatId()).getName())
						.append(PERIOD)
						.toString());
				debitList.get(dCtr).setMoney(debitList.get(dCtr).getMoney().subtract(creditList.get(cCtr).getMoney()));
				creditList.get(cCtr).setMoney(BigDecimal.ZERO);
				cCtr++;
				
			} else if (creditList.get(cCtr).getMoney().compareTo(debitList.get(dCtr).getMoney()) > 0) {
				transference.add(new StringBuilder().append(TRANSFER).append(debitList.get(dCtr).getMoney())
						.append(FROM)
						.append(riskPortfolio.getInvestmentCategories().get(debitList.get(dCtr).getInvestCatId()).getName())
						.append(TO).append(riskPortfolio.getInvestmentCategories().get(creditList.get(cCtr).getInvestCatId()).getName())
						.append(PERIOD)
						.toString());
				creditList.get(cCtr).setMoney(creditList.get(cCtr).getMoney().subtract(debitList.get(dCtr).getMoney()));
				debitList.get(dCtr).setMoney(BigDecimal.ZERO);
				dCtr++;
				
			}
		}
		
		return transference;
	}
}
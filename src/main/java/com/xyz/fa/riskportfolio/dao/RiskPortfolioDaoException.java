package com.xyz.fa.riskportfolio.dao;

@SuppressWarnings("serial")
public class RiskPortfolioDaoException extends Exception {

	private String errorMessage;
	private String errorCode;
	private String[] errorMessageParameters;
	
	public RiskPortfolioDaoException() {
		super();
	}
	
	public RiskPortfolioDaoException(Throwable cause) {
		super(cause);
	}
	
	public RiskPortfolioDaoException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
		this.errorMessage = errorMessage;
	}

	public RiskPortfolioDaoException(String errorCode, String[] errorMessageParameters, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.errorMessageParameters = errorMessageParameters;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public String[] getErrorMessageParameters() {
		return errorMessageParameters;
	}
}

package com.xyz.fa.riskportfolio.service;

@SuppressWarnings("serial")
public class RiskPortfolioServiceException extends Exception {

	private String errorMessage;
	private String errorCode;
	private String[] errorMessageParameters;
	
	public RiskPortfolioServiceException() {
		super();
	}
	
	public RiskPortfolioServiceException(Throwable cause) {
		super(cause);
	}
	
	public RiskPortfolioServiceException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
		this.errorMessage = errorMessage;
	}

	public RiskPortfolioServiceException(Throwable cause, String errorCode, String... errorMessageParameters) {
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

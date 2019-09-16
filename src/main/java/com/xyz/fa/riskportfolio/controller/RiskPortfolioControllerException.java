package com.xyz.fa.riskportfolio.controller;

@SuppressWarnings("serial")
public class RiskPortfolioControllerException extends Exception {

	private String errorMessage;
	private String errorCode;
	private String[] errorMessageParameters;
	
	public RiskPortfolioControllerException() {
		super();
	}
	
	public RiskPortfolioControllerException(Throwable cause) {
		super(cause);
	}
	
	public RiskPortfolioControllerException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
		this.errorMessage = errorMessage;
	}

	public RiskPortfolioControllerException(Throwable cause, String errorCode, String... errorMessageParameters) {
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

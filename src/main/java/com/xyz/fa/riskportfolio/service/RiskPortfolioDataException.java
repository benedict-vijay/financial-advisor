package com.xyz.fa.riskportfolio.service;

@SuppressWarnings("serial")
public class RiskPortfolioDataException extends Exception {

	private String errorMessage;
	private String errorCode;
	private String[] errorMessageParameters;
	
	public RiskPortfolioDataException() {
		super();
	}
	
	public RiskPortfolioDataException(Throwable cause) {
		super(cause);
	}

	public RiskPortfolioDataException(String message) {
		super(message);
		this.errorMessage = message;
	}
	
	public RiskPortfolioDataException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
		this.errorMessage = errorMessage;
	}

	public RiskPortfolioDataException(String errorCode, String[] errorMessageParameters, Throwable cause) {
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

package com.example.randomImage.errorJSON;

public class ErrorJSON {
	private String errorStatus;
	private String errorMessage;
	
	public ErrorJSON(String errorStatus, String errorMessage) {
		this.setErrorStatus(errorStatus);
		this.setErrorMessage(errorMessage);
	}

	public String getErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}

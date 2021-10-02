package net.slide.util;

@SuppressWarnings("serial")
public class TransException extends Exception {

	String errMsg;
	
	public TransException() {
		super();
		errMsg = "unknown";
	}

	public TransException(String message) {
		super(message);
		errMsg = message;

	}
	
	public String getError(){
		return errMsg;
	}

}

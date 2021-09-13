package tgl

import org.springframework.validation.Errors

class ErrorService extends RuntimeException {

	public ErrorService(String message) {
		super(message)
	}

	public ErrorService(String message, Throwable t) {
		super(message, t)
	}

	public ErrorService(){
		
	}
}
package br.com.hubfintech.projeto.exception;

public class ServiceException extends Exception{

	private static final long serialVersionUID = -3285686331008549906L;

	public ServiceException() {
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}

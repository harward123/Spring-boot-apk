package com.edwardpinzon.aplicacion.Exception;

public class UserNameOrldNotFound extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4537212905059000301L;
	public UserNameOrldNotFound() {
		super("usuario o id no encontrado");
	}
	public UserNameOrldNotFound(String message) {
		super (message);
		
	}

}

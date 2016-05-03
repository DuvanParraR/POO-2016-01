package Exception;

/**
 * NickException Clase que permite gestionar la excepcion de
 * nick unico entre usuarios registrados
 * 
 * @author Duvan Parra Y Santiago Botia
 * @version 1.0
 * @since 01/05/2016
 *
 */

public class NickException extends Exception{
	public NickException(String mensaje) {
		super(mensaje);
	}
}

package Exception;

/**
 * MinimumUserException Clase que permite gestionar la excepcion de
 * minimo de etiquetas de usuarios en una instancia de fotografia
 * 
 * @author Duvan Parra Y Santiago Botia
 * @version 1.0
 * @since 01/05/2016
 *
 */

public class MinimumUserException extends Exception{
	public MinimumUserException(String mensaje) {
		super(mensaje);
	}
}

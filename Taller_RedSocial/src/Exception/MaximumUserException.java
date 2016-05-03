package Exception;

/**
 * MaximumUserException Clase que permite gestionar la excepcion de
 * maximo de etiquetas en una instancia de fotografia
 * 
 * @author Duvan Parra Y Santiago Botia
 * @version 1.0
 * @since 01/05/2016
 *
 */

public class MaximumUserException extends Exception {
	public MaximumUserException(String mensaje) {
		super(mensaje);
	}

}

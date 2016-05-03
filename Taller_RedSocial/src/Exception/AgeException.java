package Exception;

/**
 * AgeException Clase que permite gestionar la excepcion de edad en la
 * aplicacion
 * 
 * @author Duvan Parra Y Santiago Botia
 * @version 1.0
 * @since 01/05/2016
 *
 */

public class AgeException extends Exception {
	public AgeException(String mensaje) {
		super(mensaje);
	}
}

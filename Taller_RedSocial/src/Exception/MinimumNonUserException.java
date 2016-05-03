package Exception;

/**
 * MinimumNonUserException Clase que permite gestionar la excepcion de
 * minimo de etiquetas de no usuarios en una instancia de fotografia
 * 
 * @author Duvan Parra Y Santiago Botia
 * @version 1.0
 * @since 01/05/2016
 *
 */

public class MinimumNonUserException extends Exception{
	public MinimumNonUserException(String mensaje) {
		super(mensaje);
	}
}

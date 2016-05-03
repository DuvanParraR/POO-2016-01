package Exception;

/**
 * NotFoundUserException Clase que permite gestionar la excepcion de
 * no encontrar usuario registrado durante una busqueda en la lista generada en
 * el Dao
 * 
 * @author Duvan Parra Y Santiago Botia
 * @version 1.0
 * @since 01/05/2016
 *
 */

public class NotFoundUserException extends Exception {
	public NotFoundUserException(String mensaje) {
		super(mensaje);
	}
}

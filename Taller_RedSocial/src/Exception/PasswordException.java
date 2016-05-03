package Exception;

/**
 * PasswordException Clase que permite gestionar la excepcion de
 * no permitir la contraseña especificada "123456"
 * 
 * @author Duvan Parra Y Santiago Botia
 * @version 1.0
 * @since 01/05/2016
 *
 */

public class PasswordException extends Exception{
	public PasswordException(String mensaje) {
		super(mensaje);
	}
}

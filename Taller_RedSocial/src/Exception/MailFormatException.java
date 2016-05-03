package Exception;

/**
 * MailFormatException Clase que permite gestionar la excepcion de
 * formato en la aplicacion
 * 
 * @author Duvan Parra Y Santiago Botia
 * @version 1.0
 * @since 01/05/2016
 *
 */

public class MailFormatException extends Exception {
	public MailFormatException(String mensaje) {
		super(mensaje);
	}
}

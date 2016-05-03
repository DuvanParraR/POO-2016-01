package Exception;

/**
 * OneHundredCharException Clase que permite gestionar la excepcion de
 * limitar el tamaño del nombre real de usuario a 100 caracteres
 * 
 * @author Duvan Parra Y Santiago Botia
 * @version 1.0
 * @since 01/05/2016
 *
 */

public class OneHundredCharException extends Exception {
	public OneHundredCharException(String mensaje) {
		super(mensaje);
	}

}

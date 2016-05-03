package Exception;

/**
 * TwoHundredCharException Clase que permite gestionar la excepcion de
 * limitar el tamaño de la descripcion personal de la fotografia o del texto de
 * comentario a 200 caracteres
 * 
 * @author Duvan Parra Y Santiago Botia
 * @version 1.0
 * @since 01/05/2016
 *
 */

public class TwoHundredCharException extends Exception {
	public TwoHundredCharException(String mensaje) {
		super(mensaje);
	}
}

package Data;

import java.io.*;
import java.util.*;

/**
 * Comentario Clase que permite almacenar el texto de un comentario y su fecha
 * de creacion.
 * 
 * @author Duvan Parra Y Santiago Botia
 * @version 1.0
 * @since 01/05/2016
 *
 */

public class Comentario implements Serializable {
	/**
	 * Atributos de la clase Comentario
	 */
	private String texto;
	private Date fecha;

	/**
	 * Constructor de la clase Comentario
	 * 
	 * @param texto
	 *            cuerpo del comentario. Tiene la restriccion de no tener comas
	 *            para que la lectura de datos desde archivos externos sea
	 *            efectiva ademas de una restriccion de 200 caracteres. El
	 *            atributo fecha se crea por defecto en la fecha que corra el
	 *            programa.
	 */

	public Comentario(String texto) {
		super();
		this.texto = texto;
		this.fecha = new Date();
	}

	/**
	 * metodo que permite obtener el atributo texto de la clase Comentario
	 * 
	 * @return String Retorna el contenido textual del comentario ingresado
	 */

	public String getTexto() {
		return texto;
	}

	/**
	 * Metodo que permite informar al usuario del estado de las instancias que
	 * se creen de esta clase Comentario
	 * 
	 */

	@Override
	public String toString() {
		return "" + texto + "\nFecha=" + fecha.toString();
	}

}

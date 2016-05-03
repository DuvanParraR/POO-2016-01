package Data;

import java.io.*;
import java.util.*;

/**
 * Fotografia Clase que permite almacenar un paquete de informacion referente a
 * una fotografia.
 * 
 * @author Duvan Parra Y Santiago Botia
 * @version 1.0
 * @since 01/05/2016
 *
 */

public class Fotografia implements Serializable {
	/**
	 * Atributos privados de la clase Fotografia. las etiquetas de la clase
	 * Fotografia se componen de una referencia a un usuario junto con un par de
	 * coordenadas enteras no negativas.
	 */

	private String nombreDeArchivo;
	private String descripcionPersonal;
	private HashMap<Usuario, int[]> tagUsuario;

	/**
	 * Constructor de la clase Fotografia. No se crean las etiquetas de la
	 * fotografia hasta que se lean las etiquetas y hayan pasado la excepcion de
	 * usuarios.
	 * 
	 * @param nombreDeArchivo
	 *            referencia o nombre que se le asigna a la fotografia. este
	 *            atributo no tiene restricciones de algun tipo
	 * @param descripcionPersonal
	 *            cuerpo textual que acompaña a la fotografia. Tiene restriccion
	 *            de 200 caracteres.
	 */

	public Fotografia(String nombreDeArchivo, String descripcionPersonal) {
		super();
		this.nombreDeArchivo = nombreDeArchivo;
		this.descripcionPersonal = descripcionPersonal;
		this.tagUsuario = new HashMap<Usuario, int[]>();
	}

	/**
	 * Metodo que permite establecer las etiquetas de la fotografia despues de
	 * haber creado una instancia de la clase.
	 * 
	 * @param tagUsuario
	 *            Pareja de usuario y coordenadas enteras que hacen referencia a
	 *            una posicion de referencia en la fotografia para ese usuario.
	 */

	public void setTagUsuario(HashMap<Usuario, int[]> tagUsuario) {
		this.tagUsuario = tagUsuario;
	}

	/**
	 * Metodo que devuelve las etiquetas que tiene una instancia de la clase
	 * Fotografia
	 * 
	 * @return Una lista de parejas de usuario-coordenadas enteras que
	 *         significan una posicion en la imagen asociada a ese usuario
	 */

	public HashMap<Usuario, int[]> getTagUsuario() {
		return tagUsuario;
	}

	/**
	 * Metodo que devuelve el nombre del archivo de una instancia de esta clase
	 * 
	 * @return
	 */

	public String getNombreDeArchivo() {
		return nombreDeArchivo;
	}

}

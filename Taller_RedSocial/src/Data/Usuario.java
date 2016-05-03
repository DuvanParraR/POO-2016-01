package Data;

import java.io.*;
import java.util.*;

/**
 * Fotografia Clase que permite almacenar un paquete de informacion referente a
 * un usuario de una red social.
 * 
 * @author Duvan Parra Y Santiago Botia
 * @version 1.0
 * @since 01/05/2016
 *
 */

public class Usuario implements Serializable {
	/**
	 * Atributos privados de la clase Usuario. En esta clase se contempla
	 * informacion del usuario como el nombre, nick, edad, clave, correo,
	 * isRegistered (Sujetos a restricciones y excepciones) e informacion que el
	 * usuario puede almacenar mediante acciones en la entrada de lectura de
	 * archivos como fotografias y comentarios
	 * 
	 */

	private String nombre;
	private String nick;
	private int edad;
	private String clave;
	private String correo;
	private boolean isRegistered;
	private ArrayList<Comentario> comentarios;
	private ArrayList<Fotografia> fotografias;

	/**
	 * Constructor de la clase usuario
	 * 
	 * @param nombre
	 *            String nombre real del usuario. Tiene restriccion de 100
	 *            caracteres
	 * @param nick
	 *            String nombre complementario al real, apodo. Tiene restriccion
	 *            de ser unico en la red social
	 * @param edad
	 *            int edad del usuario. Tiene restriccion de ser mayor a 18 años
	 * @param clave
	 *            String clave de la cuenta del usuario. Tiene restriccion de no
	 *            ser la clave "123456"
	 * @param correo
	 *            String correo de respaldo para la cuenta de la red social.
	 *            tiene restriccion de estar sujeta a un formato especifico en
	 *            el que tiene que llevar unicamente 1 simboloe "@". Ejemplo:
	 *            "cuenta@correo.terminal"
	 */

	public Usuario(String nombre, String nick, int edad, String clave, String correo) {
		super();
		this.nombre = nombre;
		this.nick = nick;
		this.edad = edad;
		this.clave = clave;
		this.correo = correo;
		this.isRegistered = true;
		this.comentarios = new ArrayList<Comentario>();
		this.fotografias = new ArrayList<Fotografia>();
	}

	/**
	 * Constructor de la clase de Usuario. Es un constructor especial para poder
	 * realizar etiquetas de personas que mno estan registradas en el sistema de
	 * la red social. Este nick no hace parte de los nicks unicos en el sistema
	 * de la red social por tanto puede ser repetido. Este constructor evita el
	 * hecho de realizar una nueva clase para personas que no estan registradas
	 * en la red social.
	 * 
	 * @param nick
	 *            String referente al apodo del usuario. Este nick no tiene
	 *            restriccion de ser unico porque es perteneciente a un usuario
	 *            no registrado
	 */

	public Usuario(String nick) {
		super();
		this.nombre = null;
		this.nick = nick;
		this.edad = 18;
		this.clave = null;
		this.correo = null;
		this.isRegistered = false;
		this.comentarios = null;
		this.fotografias = null;
	}

	/**
	 * Metodo que devuelve el nombre del usuario
	 * 
	 * @return
	 */

	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo que devuelve el nick del usuario
	 * 
	 * @return
	 */

	public String getNick() {
		return nick;
	}

	/**
	 * metodo que devuelve el estado del usuario respecto a la red social. si
	 * esta registrado puede acceder a todas las acciones de la red, si no esta
	 * registrado se limita a poder ser etiquetado en fotos de usuarios
	 * registrados enla red social
	 * 
	 * @return
	 */

	public boolean isRegistered() {
		return isRegistered;
	}

	/**
	 * Metodo que devuelve la lista de comentarios que ha publicado el usuario
	 * 
	 * @return
	 */

	public ArrayList<Comentario> getComentarios() {
		return comentarios;
	}

	/**
	 * Metodo que devuelve una lista de fotografias que ha publicado el usuario
	 * 
	 * @return
	 */

	public ArrayList<Fotografia> getFotografias() {
		return fotografias;
	}

	/**
	 * Metodo que devuelve el estado actual de la instancia de la clase Usuario
	 */

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", nick=" + nick + ", edad=" + edad + ", correo=" + correo + "]";
	}

}

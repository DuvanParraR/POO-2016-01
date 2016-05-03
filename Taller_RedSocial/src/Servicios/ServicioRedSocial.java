package Servicios;

import java.io.*;

import java.util.*;

import Dao.*;
import Data.*;
import Exception.*;

/**
 * ServicioRedSocial Clase que permite gestionar las acciones con un
 * registro de usuarios, de acuerdo a la opcion que se ingrese por consola en la
 * interfaz de usuario
 * 
 * @author Duvan Parra Y Santiago Botia
 * @version 1.0
 * @since 01/05/2016
 *
 */

public class ServicioRedSocial {
	/**
	 * Atributos privados de la clase de servicios. Aqui se crea una instancia
	 * de la clase Dao para la lectuera de archivos y una lista de usuarios para
	 * generar el hilo de informacion entre paquetes de clases
	 */

	private Dao dao;
	private ArrayList<Usuario> usuarios = null;

	/**
	 * Constructor de la clase de servicios. Aqui se instancia la clase Dao para
	 * poder leer un archivo de texto.
	 */

	public ServicioRedSocial() {
		super();
		this.dao = new Dao();
	}

	/**
	 * este metodo permite cargar los usuarios de un archivo de texto a una
	 * lista con la cual se puede operar y hacer acciones de filtro en la
	 * busqueda
	 * 
	 * @param archivo
	 *            Ruta del archivo de texto del cual se va a leer la informacion
	 *            referente a la instancia de clases
	 * @throws FileNotFoundException
	 *             Es la excepcion para el caso de no encontrar un usario
	 *             registrado
	 * @throws OneHundredCharException
	 *             Es la excepcion de longitud del nombre real de usuario
	 * @throws NickException
	 *             Es la excepcion de unicidad en el nick de cada usuario. Tiene
	 *             prioridad el usario que primero lo adopte como nick
	 * @throws AgeException
	 *             Es la excepcion de mayoria de edad en el usuario registrado
	 * @throws PasswordException
	 *             Es la excepcion de la clave que no admite la clave "1234556"
	 * @throws MailFormatException
	 *             Es la excepcion que no admite un formato de correo que no
	 *             tenga unicamente 1 simbolo "@"
	 * @throws TwoHundredCharException
	 *             Es la excepcion para limitar la descripcion personal de la
	 *             fotografia o el texto del comentario a no mas de 200
	 *             caracteres
	 * @throws NotFoundUserException
	 *             Es la excepcion para la busqueda de usuarios en el registro
	 * @throws MinimumUserException
	 *             Es la excepcion para el numero de etiqueta minimo de usuarios
	 * @throws MinimumNonUserException
	 *             Es la excepcion para el numero de etiqueta minimo de no
	 *             usuarios
	 * @throws MaximumUserException
	 *             Es la excepcion para la etiqueta maxima en una instancia de
	 *             fotografia
	 */
	public void cargarUsuarios(String archivo) throws FileNotFoundException, OneHundredCharException, NickException,
			AgeException, PasswordException, MailFormatException, TwoHundredCharException, NotFoundUserException,
			MinimumUserException, MinimumNonUserException, MaximumUserException {
		this.usuarios = this.dao.cargarUsuarios(archivo);
	}

	/**
	 * Metodo que devuelve la lista de usuarios a la interfaz de usuario
	 * 
	 * @return
	 */

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * Metodo que permite buscar un usuario registrado en la lista
	 * 
	 * @param nick
	 *            es el codigo para referenciar un usuario en el registro ya que
	 *            en esta aplicacion es unico
	 * @return Usuario un usuario registrado con un nick de argumento
	 * @throws NotFoundUserException
	 *             Es la excepcion de error al no encontrar un usuario
	 *             registrado con el nick name de argumento
	 */

	public Usuario buscarUsuario(String nick) throws NotFoundUserException {
		for (Usuario usuario : usuarios) {
			if (usuario.getNick().equals(nick))
				return usuario;
		}
		throw new NotFoundUserException("No se encontro un usuario con el nick " + nick);
	}

	/**
	 * Metodo que devuelve una lista de comentarios pertenecientes a un usuario
	 * registrado con el nick de argumento
	 * 
	 * @param nick
	 *            Argumento que permite identificar a un unico usuario
	 *            registrado con este argumento en el campo nick de la clase
	 *            Usuario
	 * @return Lista de comentarios
	 * @throws NotFoundUserException
	 *             Es la excepcion de no encontrar un usuario al realizar la
	 *             busqueda sobre el registro
	 */

	public ArrayList<Comentario> buscarComentariosUsuario(String nick) throws NotFoundUserException {
		for (Usuario usuario : usuarios) {
			if (usuario.getNick().equals(nick))
				return usuario.getComentarios();
		}
		throw new NotFoundUserException("No se encontro un usuario con el nick " + nick);
	}

	/**
	 * Metodo que devuelve una lista de fotografias donde aparece etiquetado un
	 * usuario reconocido con el nick de argumento
	 * 
	 * @param nick
	 *            Argumento para realizar la busqueda en el registro
	 * @return Lista de fotografias
	 * @throws NotFoundUserException
	 *             Es la excepcion de no encontrar un usuario al realizar la
	 *             busqueda sobre el registro
	 */

	public ArrayList<Fotografia> buscarTags(String nick) throws NotFoundUserException {
		ArrayList<Fotografia> tags = new ArrayList<Fotografia>();
		boolean isTagged = false;

		// Itera sobre la lista the usuarios, luego sobre cada fotografia de
		// cada usuario, luego sobre cada tag en cada fotografia de cada usuario
		// y compara los nicks de los tags con el argumento. Si encuentra un tag
		// igual al del argumento del metodo entonces lo añade a una lista de
		// fotogrfias, sin importar si sale repetidas
		for (Usuario usuario : usuarios) {
			for (Fotografia fotografia : usuario.getFotografias()) {
				for (Usuario tag : fotografia.getTagUsuario().keySet()) {
					if (tag.getNick().equals(nick)) {
						tags.add(fotografia);
						isTagged = true;
					}
				}
			}
		}

		// Aqui se emplean HashSet para eliminar fotos repetidas y se vuelven a
		// colocar en la lista de fotografias que es el valor de retorno
		HashSet<Fotografia> hashSet = new HashSet<Fotografia>();
		hashSet.addAll(tags);
		tags.clear();
		tags.addAll(hashSet);
		if (isTagged) {
			return tags;
		} else {
			throw new NotFoundUserException("No se encontro un usuario con el nick " + nick);
		}

	}

	/**
	 * Este metodo devuelve una lista de comentarios filtrados por palabra sin
	 * importar el usuario que las haya publicado
	 * 
	 * @param palabra
	 *            String parametro de busqueda en el texto de los comentarios
	 * 
	 * @return Lista de comentarios filtrados por la palabra de parametrizacion
	 * @throws OneHundredCharException
	 *             Esta excepcion se usa para el caso de que no se encuentr
	 *             ningun comentario con la palabra especificada
	 */

	public ArrayList<Comentario> buscarComentarioPorPalabra(String palabra) throws OneHundredCharException {
		ArrayList<Comentario> comments = new ArrayList<Comentario>();
		boolean isComment = false;
		// Itera sobre los usuarios, luego sobre los comentarios de los
		// usuarios, luego sobre los textos de los comentarios de los usuarios y
		// los divide por espacios en blanco. Itera sobre esa lista de palabras
		// y si encuentra la palabra en el comentario agrega el comentario a la
		// lista.
		for (Usuario usuario : usuarios) {
			for (Comentario comentario : usuario.getComentarios()) {
				String[] palabras = comentario.getTexto().split(" ");
				for (String word : palabras) {
					if (word.equals(palabra)) {
						comments.add(comentario);
						isComment = true;
					}
				}
			}
		}
		// Se realiza el filtro de repetidosusando el tipo de dato HashSet
		HashSet<Comentario> hashSet = new HashSet<Comentario>();
		hashSet.addAll(comments);
		comments.clear();
		comments.addAll(hashSet);
		if (isComment) {
			return comments;
		} else {
			throw new OneHundredCharException("No se encontro ningun comentario con la palabra " + palabra);
		}
	}

}

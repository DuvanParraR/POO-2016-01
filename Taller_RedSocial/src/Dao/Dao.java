package Dao;

import java.io.*;
import java.util.*;

import Data.*;
import Exception.*;

/**
 * Dao Clase que permite gestionar la lectura de archivos y almacenar la
 * inormacion leida en instancias de la clase pertinente que se encuentr en el
 * paquete Data
 * 
 * @author Duvan Parra Y Santiago Botia
 * @version 1.0
 * @since 01/05/2016
 *
 */

public class Dao {
	/**
	 * Atributo privado de la clase Dao. en esta lista se almacenan todos los
	 * nick usuarios que se registren desde el archivo de texto. Es en esencia
	 * la red social. Es una prueba. Ahora se pasara de leer datos a leer por
	 * consola los datos y añadirlos a un texto base como informacion
	 */

	private ArrayList<String> nicks;
	private Scanner sc;

	/**
	 * Constructor de la clase Dao. inicializa la lista en una lista vacia de
	 * usuarios para poder llenarla conforme se lee el archivo de texto
	 */

	public Dao() {
		this.nicks = new ArrayList<String>();
		this.sc = new Scanner(System.in);
	}

	/**
	 * Metodo que permite leer y crear una instancia de clase Usuario en la
	 * lista de la clase Dao.
	 * 
	 * @param lectura
	 *            Scanner que tiene como entrada de lectura de datos un archivo
	 *            de texto .txt
	 * @return Usuario. Retorna un usuario. el lugar desde donde se invoca es
	 *         preferiblemente el metodo carcgar usuarios, que se encarga de
	 *         crear instancias de usuarios.
	 * @throws OneHundredCharException
	 *             Es la excepcion para el tamaño del nombre. el nombre real del
	 *             usuario no puede ser mayor a 100 caracteres
	 * @throws NickException
	 *             Es la excepcion para el nick. El nick es de caracter unico
	 *             entre usuarios y no pueden haber un nick igual para dos
	 *             usuarios diferentes. Tiene prioridad el que primero se cree.
	 * @throws AgeException
	 *             Es la excepcion para la edad. En la red social no se admiten
	 *             personas registradas con edad menor a 18 años.
	 * @throws PasswordException
	 *             Es la excepcion para la clave del usuario. En la red social
	 *             se permite cualquier contraseña de cualquier tamaño o
	 *             repetida, pero no puede ser "123456".
	 * @throws MailFormatException
	 *             Es la excepcion para el formato del correo. El formato
	 *             correcto para esta aplicacion es tener un simbolo "@" entre
	 *             la direccion unicamente 1.
	 */

	public Usuario leerUsuario(Scanner lectura)
			throws OneHundredCharException, NickException, AgeException, PasswordException, MailFormatException {
		String nombre = lectura.next();
		if (nombre.length() > 100) {
			throw new OneHundredCharException("Nombre incorrecto " + nombre);
		}
		String nick = lectura.next();
		for (String nickname : nicks) {
			if (nick.equals(nickname)) {
				throw new NickException("Nick repetido " + nick);
			}
		}
		// Añade los nicks no repetidos a la lista de nicks
		nicks.add(nick);
		int edad = lectura.nextInt();
		if (edad < 18) {
			throw new AgeException("Edad invalida para crear cuenta de usuario, es menor de edad");
		}
		String clave = lectura.next();
		if (clave.equals("123456")) {
			throw new PasswordException("Contraseña invalida no puede ser 123456");
		}
		String correo = lectura.next();

		// Divide el correo por cada simbolo @ que contenga. si el arreglo
		// resultante no tiene la longitud de 2 significa que no lleva la
		// cantidad de simbolos permitidos

		String[] tramos = correo.split("@", 0);
		if (tramos.length != 2) {
			throw new MailFormatException(
					"Formato de correo invalido, debe ser de la forma 'direccion@compañia'. No debe tener mas de 2 simbolos @");
		}
		Usuario usuario = new Usuario(nombre, nick, edad, clave, correo);		
		return usuario;

	}

	/**
	 * Metodo que permite agregar un comentario a la lista de un usuario ya
	 * existente previamente en la red social
	 * 
	 * @param lectura
	 *            Scanner que tiene como entrada de lectura de datos un archivo
	 *            de texto .txt
	 * @param usuarios
	 *            Lista de usuarios registrados
	 * @return Lista de usuarios registrados actualizado con comentarios en sus
	 *         campos privados
	 * @throws TwoHundredCharException
	 *             Es la excepcion para el comentario. Esta restringido a no mas
	 *             de 200 caracteres.
	 * @throws NotFoundUserException
	 *             Es la excepcion de usuario, el usuario se identifica con un
	 *             unico nick name por lo tanto si no se encuentra el nick a
	 *             buscar en ninguno de los usuarios registrados, no esta
	 *             registrado y no se puede completar la accion
	 */

	public ArrayList<Usuario> leerComentario(Scanner lectura, ArrayList<Usuario> usuarios)
			throws TwoHundredCharException, NotFoundUserException {
		String nickPropietario = lectura.next();
		String texto = lectura.next();
		boolean foundUser = false;
		if (texto.length() > 200) {
			throw new TwoHundredCharException("El mensaje se excedio del numero de caracteres");
		}
		Comentario comentario = new Comentario(texto);

		// Itera sobre el registro de usuarios y luego compara sus nicks con el
		// que se ingresa por archivo de texto y verifica si el nick coincide
		// con algun registro
		for (Usuario usuario : usuarios) {
			if (usuario.getNick().equals(nickPropietario) && usuario.isRegistered()) {
				usuario.getComentarios().add(comentario);
				foundUser = true;
			}
		}
		if (foundUser == false) {
			throw new NotFoundUserException("No se encontro un usuario con este nick");
		}
		return usuarios;
	}

	/**
	 * 
	 * @param line
	 *            linea de argumentos en el archivo de texto.
	 * @param usuarios
	 *            registro de usuarios en la clase Dao
	 * @return lista de usuarios con las fotografias adicionadas al usuario
	 *         pertinente de acuerdo a la entrada del archivo de texto
	 * @throws TwoHundredCharException
	 *             Es la excepcion para la descripcion personal de la
	 *             fotografia. Esta descripcion esta restringida a no mas de 200
	 *             caracteres
	 * @throws MinimumUserException
	 *             Es la excepcion para la etiqueta de usuarios. En las
	 *             etiquetas deben haber al menos 2 usuarios registrados
	 * @throws MinimumNonUserException
	 *             Es la excepcion para la etiqueta de usuarios no registrados
	 *             en la red social. Minimo debe haber 1 no usuario registrado
	 * @throws MaximumUserException
	 *             Es la excepcion para la etiqueta total. No se pueden
	 *             etiquetar mas de 5 en total, sea no registrados o registrados
	 *             en la red social
	 * @throws NotFoundUserException
	 *             Es la excepcion para la busqueda de un usuario en la red
	 *             social, si no se encuentra en la red social sale el error
	 */

	public ArrayList<Usuario> leerFotografia(String line, ArrayList<Usuario> usuarios) throws TwoHundredCharException,
			MinimumUserException, MinimumNonUserException, MaximumUserException, NotFoundUserException {
		// Divide la linea por tokens de acuerdo a un delimitador y asigna a
		// cada token un valor, segun el formato en el que venga el archivo de
		// texto (predeterminado)
		String[] tokens = line.split(",");
		String nickPropietario = tokens[1];
		String nombreArchivo = tokens[2];
		String descripcionPersonal = tokens[3];
		if (descripcionPersonal.length() > 200) {
			throw new TwoHundredCharException("La descripcion se excedio del numero de caracteres");
		}
		Fotografia fotografia = new Fotografia(nombreArchivo, descripcionPersonal);
		HashMap<Usuario, int[]> tags = new HashMap<Usuario, int[]>();
		String tag = "";

		// Inicializa enteros para tener la cuenta de etiquetas de usuarios y no
		// usuarios
		int numUsuarios = 0;
		int numNoUsuarios = 0;
		boolean isRegistered = false;

		// La iteracion de etiquetas empieza a partir de las siguientes 4
		// posiciones: la primera es vacia, dada el use Delimiter en la linea y
		// las siguientes 3 son para la creacion de una instancia de la
		// fotografia
		for (int i = 4; i < tokens.length; i++) {
			tag = tokens[i];

			// Ignora etiquetas vacias
			if (tag.trim().equals("")) {
				continue;
			}

			// Genera coordenadas al azar para facilitar la realizacion del
			// codigo
			int[] coordenadas = { (int) (Math.random() * 100 + 1), (int) (Math.random() * 100 + 1) };

			// Para mantener la cuenta de etiquetas de usuarios y de no usuarios
			// se hace el registro de etiquetas de usuarios a parte del de no
			// usuarios y se incrementan los valores de control aisladamente
			for (Usuario usuario : usuarios) {
				if (usuario.getNick().equals(tag)) {
					tags.put(usuario, coordenadas);
					numUsuarios++;
					isRegistered = true;
				}
			}
			if (isRegistered == false) {
				Usuario unregistered = new Usuario(tag);
				tags.put(unregistered, coordenadas);
				numNoUsuarios++;
			}
			isRegistered = false;
		}

		if (numUsuarios < 2) {
			throw new MinimumUserException("Se etiquetaron menos numero de usuarios registrados en " + nombreArchivo);
		}
		if (numNoUsuarios < 1) {
			throw new MinimumNonUserException(
					"Se etiquetaron menos numero de no usuarios registrados en " + nombreArchivo);
		}
		if (tags.size() > 5) {
			throw new MaximumUserException("Se etiquetaron mas usuarios de los que se podian en " + nombreArchivo);
		}
		// Reiniciar los valores de control por seguridad
		numNoUsuarios = 0;
		numUsuarios = 0;

		fotografia.setTagUsuario(tags);

		// Verificacion de que hay un usuario al cual asignar la fotografia con
		// etiquetas
		boolean foundUser = false;
		for (Usuario usuario : usuarios) {
			if (usuario.getNick().equals(nickPropietario) && usuario.isRegistered()) {
				usuario.getFotografias().add(fotografia);
				foundUser = true;
			}
		}
		if (foundUser == false) {
			throw new NotFoundUserException("Error en la busqueda. No se encontro un usuario con este nick");
		}
		return usuarios;
	}

	/**
	 * Funcion que permite añadir usuarios o fotografias y comentarios a
	 * usuarios registrados
	 * 
	 * @param archivo
	 *            Es la ruta del archivo para que el Scanner lo tome como
	 *            entrada de datos
	 * @return Una lista de usuarios registrados con comentarios y fotografias
	 *         registradas en los usuarios pertinentes de acuerdo al archivo de
	 *         texto.
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

	public ArrayList<Usuario> cargarUsuarios(String archivo) throws FileNotFoundException, OneHundredCharException,
			NickException, AgeException, PasswordException, MailFormatException, TwoHundredCharException,
			NotFoundUserException, MinimumUserException, MinimumNonUserException, MaximumUserException {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		Scanner lectura = new Scanner(new File(archivo));
		lectura.useDelimiter(",");
		String type = "";

		// Mientras en el archivo de texto se encuentre una proxima linea el
		// ciclo continua
		while (lectura.hasNext()) {
			type = lectura.next().trim();

			// La primera palabra es el tipo de dato que se va a añadir al
			// registro. hay tres casos posibles para esta aplicacion

			if (type.equals("Usuario")) {
				usuarios.add(leerUsuario(lectura));

			} else if (type.equals("Comentario")) {
				usuarios = leerComentario(lectura, usuarios);

			} else if (type.equals("Fotografia")) {
				String line = lectura.nextLine();
				usuarios = leerFotografia(line, usuarios);

			} else {
				System.out.println("No hay concordancia con la estructura del proyecto Red Social");
				lectura.nextLine();

			}

		}
		return usuarios;
	}

}

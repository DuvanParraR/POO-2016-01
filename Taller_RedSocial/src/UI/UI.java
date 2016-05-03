package UI;

import java.io.FileNotFoundException;
import java.util.*;

import Exception.*;
import Servicios.ServicioRedSocial;
import Data.*;

/**
 * UI Clase que permite mostrar gestionar las acciones de la interfaz
 * de usuario, tiene un menu de usuario y una serie de metodos para cada opcion
 * que se permite en el menu
 * 
 * @author Duvan Parra Y Santiago Botia
 * @version 1.0
 * @since 01/05/2016
 *
 */

public class UI {

	/**
	 * Atributos privados de la clase UI. Se necesita un Scanner especifico para
	 * la lectura de informacion por teclado, consola y una instancia de
	 * servicios de red social para administrar los datos de la lista de
	 * registro y poder mostrarlos
	 */

	private Scanner lectura;
	private ServicioRedSocial servicio;

	/**
	 * Constructor de la clase UI. Se inicializa el Scanner para la entrada de
	 * datos por consola y se instancia la clase de servicios de red social
	 */

	public UI() {
		super();
		this.lectura = new Scanner(System.in);
		this.servicio = new ServicioRedSocial();
	}

	/**
	 * Metodo que muestra en la interfaz de usuario el menu de opciones.
	 */

	public void menu() {
		int opcion = 0;
		System.out.println("Bienvenido a la Red Social");
		System.out.println(
				"Opciones. 1. Cargar datos \n2.Buscar usuario \n3.Listar comentarios de un usuario \n4.Listar fotos donde se encuentra etiquetado un usuario \n5.Buscar comentarios con una palabra especifica en el");
		try {
			opcion = this.lectura.nextInt();
		} catch (InputMismatchException ex) {

		}
		// La opcion de cargar usuarios desde el archivo de texto contiene toda la gestion de excepciones para tratarlas en un solo lugar.
		if (opcion == 1) {
			//System.out.println("Nombre del archivo: ");
			//String rutaArchivo = lectura.next();
			String rutaArchivo = "datos.txt";
			try {
				this.servicio.cargarUsuarios(rutaArchivo);
			} catch (FileNotFoundException ex) {
				System.out.println("El archivo especificado no existe");
			} catch (OneHundredCharException ex) {
				System.out.println(ex.getMessage());
			} catch (NickException ex) {
				System.out.println(ex.getMessage());
			} catch (AgeException ex) {
				System.out.println(ex.getMessage());
			} catch (PasswordException ex) {
				System.out.println(ex.getMessage());
			} catch (MailFormatException ex) {
				System.out.println(ex.getMessage());
			} catch (TwoHundredCharException ex) {
				System.out.println(ex.getMessage());
			} catch (NotFoundUserException ex) {
				System.out.println(ex.getMessage());
			} catch (MinimumUserException ex) {
				System.out.println(ex.getMessage());
			} catch (MinimumNonUserException ex) {
				System.out.println(ex.getMessage());
			} catch (MaximumUserException ex) {
				System.out.println(ex.getMessage());
			}
				
		} else if (opcion == 2) {
			System.out.println("Ingrese el nickname del usuario");
			String nick = lectura.next();
			try {
				Usuario usuario = this.servicio.buscarUsuario(nick);
				System.out.println(usuario.toString());
			} catch (NotFoundUserException ex) {
				System.out.println(ex.getMessage());
			}

		} else if (opcion == 3) {
			System.out.println("Ingrese el nickname del usuario");
			String nick = lectura.next();
			try {
				Usuario usuario = this.servicio.buscarUsuario(nick);
				ArrayList<Comentario> comments = this.servicio.buscarComentariosUsuario(nick);
				System.out.println("Usuario propietario de los comentarios: \n" + usuario.toString());
				int iterador = 0;
				for (Comentario comment : comments) {
					System.out.println("Comentario " + ++iterador + ":\n" + comment.toString());
				}
			} catch (NotFoundUserException ex) {
				System.out.println(ex.getMessage());
			}

		} else if (opcion == 4) {
			System.out.println("Ingrese el nickname del usuario de la persona etiquetada");
			String nick = lectura.next();
			try {
				ArrayList<Fotografia> tags = servicio.buscarTags(nick);
				System.out.println("El usuario registrado con el nick " + nick
						+ " se encuentra etiquetado en las siguientes fotografias");
				int iterador_fotos = 0;
				for (Fotografia fotografia : tags) {
					System.out.println("Fotografia " + ++iterador_fotos);
					System.out.println("nombre del archivo: " + fotografia.getNombreDeArchivo());
					System.out.println("Otros Tags:");
					for (Usuario otro_tag : fotografia.getTagUsuario().keySet()) {
						if (otro_tag.getNick().equals(nick) == false)
							System.out.println(otro_tag.toString());
					}
				}
			} catch (NotFoundUserException ex) {
				System.out.println(ex.getMessage());
			}

		} else if (opcion == 5) {
			System.out.println("Ingrese la palabra para realizar la busqueda");
			String palabra = lectura.next();
			try {
				ArrayList<Comentario> comentarios = servicio.buscarComentarioPorPalabra(palabra);
				System.out.println("Estos son los resultados de la busqueda");
				int iterador = 0;
				for (Comentario comentario : comentarios) {
					System.out.println("Comentario " + ++iterador);
					System.out.println(comentario.toString());
				}
			} catch (OneHundredCharException ex) {
				System.out.println(ex.getMessage());
			}

		} else {
			System.out.println("Opcion invalida");
		}

	}

}

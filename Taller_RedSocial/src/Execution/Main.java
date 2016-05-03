package Execution;

import java.util.*;

import UI.UI;

/**
 * Main Clase que ejecuta la aplicacion inicializando una interfaz de
 * usuario y deplegando su menu. Hace un ciclo para determinar la opcion de
 * terminar con la aplicacion.
 * 
 * La modificacion de los datos que se leen en la Red Social se realizan en el archivo de texto datos.txt con el siguiente formato
 * Para registrar un usuario:
 * Usuario,nombre de usuario,nick,edad,clave,correo,
 * Para registrar comentario:
 * Comentario,nickdelpropietario,texto del comentario sin comas,
 * Para registrar una fotografia:
 * Fotografia,nickdelpropietario,nombredeArchivo,descripcionPersonal,nicktag1,nicktag2,...,nicktagN,
 * 
 * @author Duvan Parra Y Santiago Botia
 * @version 1.0
 * @since 01/05/2016
 *
 */

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UI vista = new UI();
		Scanner scanner = new Scanner(System.in);
		boolean indicador = false;
		int opcion = 0;
		while (indicador == false) {

			vista.menu();
			System.out.println("Desea continuar...1. Si 2. No");
			try {
				opcion = scanner.nextInt();
			} catch (InputMismatchException ex) {
				System.out.println("Opcion invalida. El sistema ha dejado de funcionar");
			}
			if (opcion != 1)
				indicador = true;

		}
	}

}

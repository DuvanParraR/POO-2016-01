package lab1_classes;

public class ProblemTwo {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		java.util.Scanner lectura = new java.util.Scanner(System.in);
		
		int[] posicion = new int[2];
		char clase, pref, opcionContMenu = 'y';
		String nombre = "";
		long id = 0;
		int opcionMenu = 0, anularReserva = 0;
		
		boolean[][] checkInSilla = new boolean[9][6];
		long[][] idInSilla = new long[9][6];
		String[][] nameInSilla = new String[9][6];
		
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 6; j++){
				checkInSilla[i][j] = true;
				idInSilla[i][j] = 0;
				nameInSilla[i][j] = "";
				if((i == 0 || i == 1) && (j == 1 || j == 4))
					continue;				
			}
		}
		
		displayArray(checkInSilla);
		while(opcionContMenu == 'y'){
			System.out.println("Este es el menu de opciones para administrar el vuelo"
					+ "\n1. Asignacion de silla a pasajeros"
					+ "\n2. Contar sillas ejecutivas ocupadas"
					+ "\n3. Localizar silla por numero de cedula"
					+ "\n4. Anular una reserva"
					+ "\n5. Contar numero de puestos disponibles en ventana en clase economica"
					+ "\n6. Identificar 2 puestos reservados con el mismo nombre en economica"
					+ "\nIngrese su opcion a continuacion");
			opcionMenu = lectura.nextInt();
			switch(opcionMenu){
			case 1:
				System.out.println("Clase que el pasajero prefiere (A para Ejecutiva, B para Economica):");
				clase = lectura.next().charAt(0);
				System.out.println("Puesto que el pasajero prefiere (V para Ventana, C para Centro, P para Pasillo):");
				pref = lectura.next().charAt(0);
				posicion = asignarSilla(checkInSilla, 'A', 'V');
				System.out.println("Nombre (Ejemplo: DuvanParra):");
				nombre = lectura.next();
				System.out.println("Identificacion(Cedula):");
				id = lectura.nextLong();
				checkInSilla[posicion[0]][posicion[1]] = false;
				idInSilla[posicion[0]][posicion[1]] = id;
				nameInSilla[posicion[0]][posicion[1]] = nombre;
				displayArray(checkInSilla);
				break;
			case 2:
				ocupEjecutiva(checkInSilla);
				break;
			case 3:
				System.out.println("Ingrese la cedula del pasajero:");
				id = lectura.nextLong();
				posicion = buscarId(idInSilla, id);
				if(posicion == null)
					System.out.println("No se encuentra en el avion ninguna persona identificada con esa id");
				else
					System.out.println(nameInSilla[posicion[0]][posicion[1]] + " se encuentra en el puesto " + buscarPos(posicion));
				break;
			case 4:
				System.out.println("Ingrese el numero del puesto que se muestra en consola para cancelar la reserva");
				anularReserva = lectura.nextInt();
				posicion = anularReserva(checkInSilla, anularReserva);
				checkInSilla[posicion[0]][posicion[1]] = true;
				idInSilla[posicion[0]][posicion[1]] = 0;
				nameInSilla[posicion[0]][posicion[1]] = "";
				displayArray(checkInSilla);
				break;
			case 5:
				dispVentEconomica(checkInSilla);
				break;
			case 6:
				isSameName(nameInSilla);
				break;
			default:
				break;
			}
			
			System.out.println("Desea realizar otra accion? (y/n)");
			opcionContMenu = lectura.next().charAt(0);
		}
	}
	
	public static void displayArray(boolean[][] checkInSilla){
		int contador = 0;
		for(int i = 0; i < 9; i++){
			System.out.print("|");
			
			for(int j = 0; j < 6; j++){				
				if((i == 0 || i == 1) && (j == 1 || j == 4)){
					System.out.print("   |");
					continue;
				}
				contador++;	
				if(contador / 10 == 0 && checkInSilla[i][j])
					System.out.print("0" + contador + "D|");
				else if(contador / 10 == 0 && checkInSilla[i][j] == false)
					System.out.print("0" + contador + "N|");
				else if(contador / 10 != 0 && checkInSilla[i][j])
					System.out.print(contador + "D|");
				else if(contador / 10 != 0 && checkInSilla[i][j] == false)
					System.out.print(contador + "N|");
				
				if(j % 2 == 0 && j % 4 != 0)
					System.out.print(" |");
			}
			System.out.println();
		}
	}
	
	public static int[] asignarSilla(boolean[][] checkInSilla, char clase, char pref){
		int[] posicion = new int[2];
		boolean sillaAsignada = false;
		
		if(isEmptySilla(checkInSilla)){
			if(clase == 'A' && pref == 'V')
				for(int i = 0; i < 2; i++){	
					if(sillaAsignada)
						return posicion;
					for(int j = 0; j < 6; j++){
						if(j == 1 || j == 2 || j == 3 || j == 4)
							continue;
						if(checkInSilla[i][j]){
							sillaAsignada = true;
							posicion[0] = i;
							posicion[1] = j;
							break;
						}
					}					
				}
			if(clase == 'A' && pref == 'P')
				for(int i = 0; i < 2; i++){	
					if(sillaAsignada)
						return posicion;
					for(int j = 0; j < 6; j++){
						if(j == 0 || j == 1 || j == 4 || j == 5)
							continue;
						if(checkInSilla[i][j]){
							sillaAsignada = true;
							posicion[0] = i;
							posicion[1] = j;
							break;
						}
					}					
				}
			if(clase == 'B' && pref == 'V')
				for(int i = 2; i < 9; i++){	
					if(sillaAsignada)
						return posicion;
					for(int j = 0; j < 6; j++){
						if(j == 1 || j == 2 || j == 3 || j == 4)
							continue;
						if(checkInSilla[i][j]){
							sillaAsignada = true;
							posicion[0] = i;
							posicion[1] = j;
							break;
						}
					}
				}
			if(clase == 'B' && pref == 'P')
				for(int i = 2; i < 9; i++){	
					if(sillaAsignada)
						return posicion;
					for(int j = 0; j < 6; j++){
						if(j == 0 || j == 1 || j == 4 || j == 5)
							continue;
						if(checkInSilla[i][j]){
							sillaAsignada = true;
							posicion[0] = i;
							posicion[1] = j;
							break;
						}
					}
				}
			if(clase == 'B' && pref == 'C')
				for(int i = 2; i < 9; i++){	
					if(sillaAsignada)
						return posicion;
					for(int j = 0; j < 6; j++){
						if(j == 0 || j == 2 || j == 3 || j == 5)
							continue;
						if(checkInSilla[i][j]){
							sillaAsignada = true;
							posicion[0] = i;
							posicion[1] = j;
							break;
						}
					}
				}
			if(sillaAsignada == false){
				for(int i = 0; i < 9; i++)
					for(int j = 0; j < 6; j++){
						if((i == 0 || i == 1) && (j == 1 || j == 4))
							continue;
						if(checkInSilla[i][j]){
							posicion[0] = i;
							posicion[1] = j;
						}				
					}				
			}
			return posicion;
		}
		else
			System.out.println("Asientos ocupados, no se puede asignar mas habria que anular una reserva.");
		return null;
	}
	
	public static boolean isEmptySilla(boolean[][] checkInSilla){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 6; j++){
				if((i == 0 || i == 1) && (j == 1 || j == 4))
					continue;
				if(checkInSilla[i][j])
					return true;
			}
		}
		return false;
	}
	
	public static int[] buscarId(long[][] idInSilla, long value){
		int[] posicion = new int[2];
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 6; j++){
				if((i == 0 || i == 1) && (j == 1 || j == 4))
					continue;		
				if(value == idInSilla[i][j]){
					posicion[0] = i;
					posicion[1] = j;
					return posicion;
				}
			}
		}
		return null;
	}
	
	public static int[] anularReserva(boolean[][] checkInSilla, int numAsiento){
		int contador = 0;
		int[] posicion = new int[2];
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 6; j++){
				if((i == 0 || i == 1) && (j == 1 || j == 4))
					continue;
				if(numAsiento == ++contador){
					posicion[0] = i;
					posicion[1] = j;
				}
			}
		}
		return posicion;
	}
	
	public static int buscarPos(int[] posicion){
		int contador = 0;
		boolean finder = false;
		for(int i = 0; i < 9; i++){			
			for(int j = 0; j < 6; j++){				
				if((i == 0 || i == 1) && (j == 1 || j == 4)){
					continue;
				}
				contador++;
				if(i == posicion[0] && j == posicion[1]){
					finder = true;
					break;
				}					
			}
			if(finder)
				break;
		}
		return contador;
	}
	
	public static void ocupEjecutiva(boolean[][] checkInSilla){
		int contador = 0;
		for(int i = 0; i < 2; i++)
			for(int j = 0; j < 6; j++){
				if(j == 1 || j == 4)
					continue;
				if(checkInSilla[i][j] == false)
					contador++;
			}
		System.out.println("El conteo arrojaun total de " + contador + " sillas ocupadas en clase Ejecutiva");
	}
	
	public static void dispVentEconomica(boolean[][] checkInSilla){
		int contador = 0;
		for(int i = 2; i < 9; i++)
			for(int j = 0; j < 6; j++){
				if(j == 1 || j == 2 || j == 3 || j == 4)
					continue;
				if(checkInSilla[i][j])
					contador++;
			}
		System.out.println("Hay " + contador + " puestos de ventana disponibles en clase Economica");
	}
	
	public static void isSameName(String[][] nameInSilla){
		boolean isSame = false;
		String name = "";
		for(int i = 2; i < 9; i++){
			for(int j = 0; j < 6; j++){
				name = nameInSilla[i][j];
				for(int k = i; k < 9; k++){
					for(int l = j; l < 6; l++){
						if(k == i && l == j)
							continue;
						if(name == nameInSilla[k][l] && nameInSilla[k][l] != ""){
							isSame = true;
							break;
						}
					}	
					if(isSame)
						break;
				}
				if(isSame)
					break;
			}
			if(isSame)
				break;
		}
		if(isSame)
			System.out.println("Se ha encontrado dos asientos en Economica que ocupan con el mismo nombre " + name);
		else
			System.out.println("No se han encontrado repeticiones en los nombres que ocupan asientos en Economica");
	}
}

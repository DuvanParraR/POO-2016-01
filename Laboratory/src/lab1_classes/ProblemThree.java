package lab1_classes;

public class ProblemThree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		java.util.Scanner lectura = new java.util.Scanner(System.in);
		boolean[][] checkParking = new boolean[3][29];
		String[][] placaParking = new String[3][29];
		int[][] horaParking = new int[3][29];
		int[] posicion = new int[2];
		String placa = "";
		int horaIngreso = 6, horaSalida = 6, opcionMenu = 0;
		double precioHora = 0.0, accumDinero = 0.0, cargo = 0.0;
		char contMenu = 'y';		
				
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 29; j++){
				checkParking[i][j] = true;
				placaParking[i][j] = "";
				horaParking[i][j] = 6;
			}
		
		System.out.println("Establezca el precio hora para este dia:");
		precioHora = lectura.nextDouble();
		
		while(contMenu == 'y'){
			printParking(checkParking);
			System.out.println("Este es el menu para elusuario de la aplicacion"
					+ "\n1. Asignar cupo a un carro que ingresa"
					+ "\n2. Asignar un cobro a un carro que sale"
					+ "\n3. Decir el monto total de cobro en el dia"
					+ "\n4. Hacer el conteo de espacios libres"
					+ "\nAhora ingrese una opcion");
			opcionMenu = lectura.nextInt();
			switch(opcionMenu){
			case 1:
				posicion = asignarParking(checkParking);
				if(posicion == null)
					System.out.println("Parqueadero lleno, no se puede asignar un espacio para ingresar");
				else{
					System.out.println("Ingrese la placa del carro:");
					placa = lectura.next();
					System.out.println("Ingrese la hora de ingreso (Hora entera redondeando a el valor minimo, en formato militar hasta las 19 horas es permitido):");
					horaIngreso = lectura.nextInt();
					checkParking[posicion[0]][posicion[1]] = false;
					placaParking[posicion[0]][posicion[1]] = placa;
					horaParking[posicion[0]][posicion[1]] = horaIngreso;
				}
				break;
			case 2:
				System.out.println("Ingrese la placa del carro que va a salir");
				placa = "";
				placa = lectura.next();				
				posicion = buscarPlaca(placaParking, placa);
				if(posicion == null)
					System.out.println("No se ha encontrado ningun carro identificado con esta placa");
				else{
					System.out.println("Ingrese la hora de salida (Hora entera redondeando a el valor maximo, en formato militar hasta las 19 horas es permitido):");
					horaSalida = lectura.nextInt();
					cargo = precioHora * (double)(horaSalida - horaParking[posicion[0]][posicion[1]]);
					horaParking[posicion[0]][posicion[1]] = 6;
					checkParking[posicion[0]][posicion[1]] = true;
					placaParking[posicion[0]][posicion[1]] = "";
					System.out.println("El cliente tiene que pagar un precio de $" + cargo);
					accumDinero += cargo;
					cargo = 0.0;
				}
				break;
			case 3:
				System.out.println("El monto actual de cobros es $" + accumDinero);
				break;
			case 4:
				emptySpaces(checkParking);
				break;
			default:
				break;					
			}
		}	
	}
	
	public static void printParking(boolean[][] checkParking){
		int contador = 0;
		for(int i = 0; i < 3; i++){
			System.out.print("|");
			for(int j = 0; j < 29; j++){
				if(++contador / 10 == 0 && checkParking[i][j] == true)
					System.out.print("0" + contador + "D|");
				else if(contador / 10 == 0 && checkParking[i][j] == false)
					System.out.print("0" + contador + "N|");
				else if(contador / 10 != 0 && checkParking[i][j] == true)
					System.out.print(contador + "D|");
				else if(contador / 10 != 0 && checkParking[i][j] == false)
					System.out.print(contador + "N|");
			}
			System.out.println();
		}
	}
	
	public static int[] asignarParking(boolean[][] checkParking){
		int[] posicion = new int[2];
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 29; j++)
				if(checkParking[i][j]){
					posicion[0] = i;
					posicion[1] = j;
					return posicion;
				}
		System.out.println("El parqueadero esta lleno no se pueden recibir mas ingresos");
		return null;
	}
	
	public static int[] buscarPlaca(String[][] placaParking, String placa){
		int[] posicion = new int[2];
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 29; j++)
				if(placaParking[i][j].equals(placa)){
					posicion[0] = i;
					posicion[1] = j;
					return posicion;
				}			
		return null;
	}
	
	public static void emptySpaces(boolean[][] checkParking){
		int contador = 0;
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 29; j++)
				if(checkParking[i][j])
					contador++;
		if(contador > 0)
			System.out.println("Hay " + contador + " espacios libres en el parqueadero");
		else
			System.out.println("No hay espacios libres en el parqueadero");
	}

}

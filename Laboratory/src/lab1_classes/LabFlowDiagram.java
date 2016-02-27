package lab1_classes;

public class LabFlowDiagram {
	static java.util.Scanner lectura = new java.util.Scanner(System.in);
	// In this class goes the first part of the Laboratory #1 of Decomposing The respective flow diagram into conventional words and into useful code.
	public static void main(String[] args){
		System.out.println("Here goes the flow diagram activity: each exercise will describe in conventional text what does it do and then will execute the code.");
		int opcion = 0;
		char nextFlowchart = 'y';
		while(nextFlowchart == 'y'){
			System.out.println("Este es el menu de diagramas de flujo: ");
			System.out.println("1 para Comparador()");
			System.out.println("2 para Arbol()");
			System.out.println("3 para Funciones()");
			System.out.println("4 para PlayGuessTheNumber()");
			System.out.print("Ingrese su opcion: ");
			opcion = lectura.nextInt();
			switch(opcion){
				case 1:
					Comparador();
					break;
				case 2:
					Arbol();
					break;
				case 3:
					Funciones();
					break;
				case 4:
					PlayGuessTheNumber();
					break;
				default:
					System.out.println("Opcion invalida");
					break;
			}
			System.out.println("Desea continuar (y/n):");
			nextFlowchart = lectura.next().charAt(0);
		}
	}
	
	public static void Comparador(){
		System.out.println();
		System.out.println("Ejercicio 1: Al iniciar pide que el usuario ingrese 3 valores numericos, luego los compara usando condicionales para determinar cual de"
				+ "\nlos 3 valores es el mayor, y la salida es el mayor de los 3 valores ingresados.");
		int a, b, c, mayor = 0;
		System.out.println("Ingrese el valor de a:");
		a = lectura.nextInt();
		System.out.println("Ingrese el valor de b:");
		b = lectura.nextInt();
		System.out.println("Ingrese el valor de c:");
		c = lectura.nextInt();
		
		if((a > b) && (a > c))
			mayor = a;			
		else if(b > c)
			mayor = b;
		else
			mayor = c;
		System.out.println("El mayor es: " + mayor);
	}
	
	public static void Arbol(){
		System.out.println();
		System.out.println("Ejercicio 2: El programa arroja un arreglo semejante a un arbol, en cada linea aumenta de hojas de manera impar. No toma valores de entrada"
				+ "\nsino que es por defecto 5 lineas. Las hojas estan representadas por un asterisco (*) y estan separadas por espacios en blanco ( ). Para lograr"
				+ "\nel arreglo itera sobre el numero de lineas y en funcion de las lineas itera de manera que se muestren los espacios y los asteriscos.Los espacios"
				+ "\nen blanco aparecen en funcion del numero total de lineas menos la posicion de la linea actual y luego los asteriscos aparecen en funcion del"
				+ "\ndoble de la linea actual. Asi por ejemplo en la linea 1 apareceran 4 espacios en blanco(5 lineas menos 1 linea (la actual es la primera)) y luego"
				+ "\naparecera un asterisco (hasta el doble de la linea actual es decir hasta 2)");
		int cuenta_as = 1, contador = 1, blan = 1, num = 5;
		char ast = '*', esp = ' ';
		while(contador <= num){
			blan = num - contador;
			while(blan > 0){
				System.out.print(esp);
				blan--;
			}
			cuenta_as = 1;
			while(cuenta_as < 2*contador){
				System.out.print(ast);
				cuenta_as++;
			}
			contador++;
			System.out.println();
		}
	}
	
	public static void Funciones(){
		System.out.println();
		System.out.println("Ejercicio 3: El programa hace uso de funciones para imprimir mensajes enumerandolas al main como primera y de ahi para lla seguiria la enumeracion."
				+ "\nComo no estamos en el main la primera funcion que se enumera seria la funcion Funciones, esto baste de aclaracion pero si se hiciera una clase"
				+ "\na parte con el main aislado si se podria hacer al pie de la letra, sin embargo se agrupan todos los ejercicios en funciones para facilidad de"
				+ "\nrevision.");
		System.out.println("Primera funcion en Funciones");
		func1();
		func2();
		System.out.println("Se termina Funciones");				
	}
	
	public static void func1(){
		System.out.println("Segunda Funcion");
	}
	
	public static void func2(){
		System.out.println("Tercera Funcion");
	}
	
	public static void PlayGuessTheNumber(){
		char playAgain = 'y';
		while(playAgain == 'y'){
			GuessingGame();
			System.out.println("Would you like to play again (y/n):");
			playAgain = lectura.next().charAt(0);
		}				
	}
	
	public static void GuessingGame(){
		int answer = (int) Math.floor((Math.random()*100)+1);
		int guess = 0, numGuesses = 0;		
		while(numGuesses < 7 && guess != answer){
			System.out.println("Guess a number");
			guess = lectura.nextInt();
			if(guess < answer)
				System.out.println("Higher...");
			else if(guess > answer)
				System.out.println("Lower...");
			else
				System.out.println("You Win!");
			numGuesses += 1;
		}
		if(numGuesses >= 7)
			System.out.println("You Loser!");
	}
}

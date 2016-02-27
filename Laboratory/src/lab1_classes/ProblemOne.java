package lab1_classes;

public class ProblemOne {
	
	public static void main(String[] args){
		java.util.Scanner lectura = new java.util.Scanner(System.in);
		
		String[] candList = new String[20];
		String[] muniList = new String[20];
		int[][] voteCount = new int[20][20];
		int candNum, muniNum;
		int mayor = 0, cont = 0, total = 0, value = 0;
		double partial = 0.0;
		String candName = "";
				
		for(int i = 0; i < 20; i++){
			candList[i] = "-";
			muniList[i] = "-";
			for(int j = 0; j < 20; j++)				
				voteCount[i][j] = 0;
		}
		
		System.out.println("Ingrese el numero de candidatos luego el numero de municipios, la capacidad de ambos datos es de 20, esta capacidad se usara para mostrar estadisticas");
		candNum = lectura.nextInt();
		muniNum = lectura.nextInt();
		
		int[] votesPerCand = new int[candNum];
		double[] percentage = new double[candNum];
		for(int i = 0; i < candNum; i++)
			votesPerCand[i] = 0;
		
		System.out.println("Ingrese los nombres de los candidatos");
		for(int i = 0; i < candNum; i++)
			candList[i] = lectura.next();
		System.out.println("Ingrese los nombres de los municipios");
		for(int i = 0; i < muniNum; i++){
			muniList[i] = lectura.next();
			if(muniList[i].length() > mayor)
				mayor = muniList[i].length();
		}
		System.out.println("Ingrese el conteo de votos para cada candidato en el respectivo municipio que se indique");
		for(int i = 0; i < muniNum; i++){
			System.out.println("Municipio: " + muniList[i]);
			for(int j = 0; j < candNum; j++){
				System.out.println("Candidato: " + candList[j]);
				voteCount[i][j] = lectura.nextInt();
			}
			System.out.println();
		}
		
		System.out.print(ContarSpaceGap(0, mayor) + " ");
		for(int i = 0; i < candNum; i ++)
			System.out.print(candList[i] + " ");
		System.out.println();
		for(int i = 0; i < muniNum; i++){
			System.out.print(muniList[i] + ContarSpaceGap(mayor, muniList[i].length())+ " ");
			for(int j = 0; j < candNum; j++){
				System.out.print(voteCount[i][j] + ContarSpaceGap(candList[j].length(), ContarDigitos(voteCount[i][j])) +" ");								
			}
			System.out.println();
		}
		for(int i = 0; i < candNum; i++){
			cont = 0;
			for(int j = 0; j < muniNum; j++)
				cont += voteCount[j][i];
			votesPerCand[i] = cont;
			total += votesPerCand[i];			
		}
		for(int i = 0; i < candNum; i++)
			percentage[i] = ((double)(votesPerCand[i])/total)*100;
			
		System.out.print("Total" + ContarSpaceGap(5, mayor) + " ");
		for(int i = 0; i < candNum; i++)
			System.out.print(votesPerCand[i] + ContarSpaceGap(candList[i].length(), ContarDigitos(votesPerCand[i]))+ " ");
		System.out.println();
		for(int i = 0; i < candNum; i++){
			value = votesPerCand[i];
			cont = i;
			for(int j = i; j < candNum; j++)
				if(votesPerCand[j] > value){
					cont = j;
					value = votesPerCand[j];
				}					
			
			votesPerCand[cont] = votesPerCand[i];
			votesPerCand[i] = value;
			
			candName = candList[cont];
			candList[cont] = candList[i];
			candList[i] = candName;
			
			partial = percentage[cont];
			percentage[cont] = percentage[i];
			percentage[i] = partial;
		}
		
		for(int i = 0; i < candNum; i++){
			System.out.println(candList[i] + " " + votesPerCand[i] + " " + percentage[i] + "%");			
		}
		
		if(percentage[0] >= 50.0)
			System.out.println(candList[0] + "gana las elecciones");
		else
			System.out.println(candList[0] + " y " + candList[1] + "Pasan a ronda de segunda de votaciones por ser los dos mas votados");
	}
	
	public static int ContarDigitos(int number){
		int cont = 0;
		if (number == 0)
			return 1;
		while(number > 0){
			number = number / 10;			
			cont++;
		}
		return cont;
	}
	
	public static String ContarSpaceGap(int lineLength, int number){		
		char espacio = ' ';
		String spaceGap = "";		
		number = Math.abs(lineLength - number);
		for(int i = 0; i < number; i++)
			spaceGap += espacio;
		return spaceGap;
	}
}


public class Organizacion {
	private String name;
	private Delegacion[] delegacion;

	public Organizacion(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Delegacion[] getDelegacion() {
		return delegacion;
	}

	public void setDelegacion(Delegacion[] delegacion) {
		this.delegacion = delegacion;
	}

	public void cambiarMedallas(int newNumMedallas, char typeOfMedalla, String nameOfCountry) {
		int counter = 0;
		for(int i = 0; i < this.delegacion.length; i++){
			if(this.delegacion[i].getNameOfCountry() == nameOfCountry)
				counter = i;
		}
		if(typeOfMedalla == 'O')
			this.delegacion[counter].setNumGoldMedals(newNumMedallas);
		if(typeOfMedalla == 'P')
			this.delegacion[counter].setNumSilverMedals(newNumMedallas);
		if(typeOfMedalla == 'B')
			this.delegacion[counter].setNumBronzaMedals(newNumMedallas);;
	}

	public Delegacion mayorNumMedallas() {
		int counter = 0;
		int mayor = 0;
		for(int i = 1; i < this.delegacion.length; i++){
			if(this.delegacion[i].sumMedals() > this.delegacion[i-1].sumMedals())
				counter = i;
		}
		return null;
	}

	public Delegacion nuevaDelegacion(Grupo groupArray) {
		// TODO Auto-generated method
		return null;
	}

	public Delegacion consultarDelegacion() {
		// TODO Auto-generated method
		return null;
	}

}
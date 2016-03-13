
public class Delegacion {

	private String nameOfCountry;
	private int numAthletes;
	private int numBronzaMedals;
	private Grupo[] grupo;
	private int numSilverMedals;
	private int numGoldMedals;
	
	public Delegacion(String nameOfCountry, Grupo[] grupo) {
		this.nameOfCountry = nameOfCountry;
		this.grupo = grupo;
	}

	public String getNameOfCountry() {
		return nameOfCountry;
	}

	public void setNameOfCountry(String nameOfCountry) {
		this.nameOfCountry = nameOfCountry;
	}

	public int getNumAthletes() {
		return numAthletes;
	}

	public void setNumAthletes(int numAthletes) {
		this.numAthletes = numAthletes;
	}

	public int getNumBronzaMedals() {
		return numBronzaMedals;
	}

	public void setNumBronzaMedals(int numBronzaMedals) {
		this.numBronzaMedals = numBronzaMedals;
	}

	public Grupo[] getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo[] grupo) {
		this.grupo = grupo;
	}

	public int getNumSilverMedals() {
		return numSilverMedals;
	}

	public void setNumSilverMedals(int numSilverMedals) {
		this.numSilverMedals = numSilverMedals;
	}

	public int getNumGoldMedals() {
		return numGoldMedals;
	}

	public void setNumGoldMedals(int numGoldMedals) {
		this.numGoldMedals = numGoldMedals;
	}
	
	public int sumMedals(){
		return this.numBronzaMedals + this.numGoldMedals + this.numSilverMedals;
	}

}
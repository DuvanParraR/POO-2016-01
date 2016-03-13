
public class Grupo {
	private String nameOfSport;
	private Integrante[] integrante;
		
	public Grupo(String nameOfSport, Integrante[] integrante) {
		this.nameOfSport = nameOfSport;
		this.integrante = integrante;
	}

	public String getNameOfSport() {
		return nameOfSport;
	}

	public void setNameOfSport(String nameOfSport) {
		this.nameOfSport = nameOfSport;
	}

	public Integrante[] getIntegrante() {
		return integrante;
	}

	public void setIntegrante(Integrante[] integrante) {
		this.integrante = integrante;
	}

}
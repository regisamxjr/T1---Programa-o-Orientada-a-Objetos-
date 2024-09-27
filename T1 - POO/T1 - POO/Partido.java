import java.util.ArrayList;

public class Partido {

	private int numero;

	private String nome;
	private String municipio;

	private ArrayList<Candidato> candidato = new ArrayList<>();


	public Partido(String nome, int numero) {
		this.numero = numero;
		this.nome = nome;
		this.municipio = municipio;
	}

	public void adicionaCandidato(Candidato c) {
		candidato.add(c);
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public ArrayList<Candidato> getCandidato() {
		return candidato;
	}

	@Override
	public String toString() {
		return "Numero: " + numero + ", Nome: " + nome + "\n";
	}

}



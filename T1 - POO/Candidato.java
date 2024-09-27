import java.util.ArrayList;

public class Candidato {

	private int numero;
	private String nome;
	private String municipio;
	private int votos;
	private ArrayList<Partido>partidos;



	public Candidato(int numero, String nome, String municipio) {
		this.numero = numero;
		this.nome = nome;
		this.municipio = municipio;
		this.votos = 0;
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

	public int getVotos(){
		return votos;
	}

	public void adicionarVoto(int voto){
		this.votos++;
	}
	public static ArrayList<Partido> getPartidos() {
		return getPartidos();
	}

}



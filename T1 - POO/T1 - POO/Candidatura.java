import java.util.ArrayList;

public class Candidatura {

	private static ArrayList<Candidato> candidato = new ArrayList<>();


	//cadastraCandidato(Candidato): recebe como parâmetro um novo candidato e
	//o cadastra no sistema. Não pode haver candidatos com o mesmo número no
	//mesmo município. Retorna true se o cadastro teve sucesso; ou false em caso
	//contrário.

	public boolean cadastraCandidato(Candidato c) {
		for (Candidato candidato : candidato) {
			if (candidato.getNumero() == c.getNumero() && candidato.getMunicipio().equals(c.getMunicipio())) {
				return false;
			}
		}
		candidato.add(c);
		return true;
	}

	public Candidato dadosDeUmCandidato(int numero, String municipio) {
		for (Candidato candidato : candidato) {
			if (candidato.getNumero() == numero && candidato.getMunicipio().equalsIgnoreCase(municipio)) {
				return candidato;
			}
		}
		return null;
	}

	public void adicionarVotos(int numero, int votos){
		for (Candidato candidato : candidato) {
			if (candidato.getNumero() == numero){
				candidato.adicionarVoto(votos);
				System.out.printf(candidato.getNumero() + candidato.getNome() +  candidato.getMunicipio() + votos);
				return;
			}
		}
		System.out.println("3:Nenhum candidato encontrado.");
	}

	public void dadosPrefeitoVereadorMaisVotados(){
		Candidato prefeitoMaisVotado = null;
		Candidato vereadorMaisVotado = null;
		for (Candidato candidato : candidato) {
			if(candidato.getNumero()<100) {
				if (prefeitoMaisVotado == null || candidato.getVotos() > prefeitoMaisVotado.getVotos()) {
					prefeitoMaisVotado = candidato;
				}
			}else if (vereadorMaisVotado == null || candidato.getVotos()>vereadorMaisVotado.getVotos()){
				vereadorMaisVotado = candidato;

			}

		}
		if (prefeitoMaisVotado != null) {
			System.out.printf("8:%d,%s,%s,%d%n", prefeitoMaisVotado.getNumero(), prefeitoMaisVotado.getNome(), prefeitoMaisVotado.getMunicipio(), prefeitoMaisVotado.getVotos());
		}

		if (vereadorMaisVotado != null) {
			System.out.printf("8:%d,%s,%s,%d%n", vereadorMaisVotado.getNumero(), vereadorMaisVotado.getNome(), vereadorMaisVotado.getMunicipio(), vereadorMaisVotado.getVotos());
		}
	}

}


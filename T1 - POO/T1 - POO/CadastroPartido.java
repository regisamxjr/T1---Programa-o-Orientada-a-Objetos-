import java.util.ArrayList;

public class CadastroPartido {
	private static ArrayList<Partido> partido = new ArrayList();

	//cadastraPartido(Partido): recebe como parâmetro um novo Partido e o
	//cadastra no sistema. Não pode haver partidos com o mesmo número. Retorna
	//true se o cadastro teve sucesso; ou false em caso contrário.

	public boolean cadastraPartido(Partido p, int numero, String nome) {
		for (int i = 0; i < partido.size(); i++) {
			if (p == null) {
				System.out.println("O partido não pode ser nulo");
			} else if (partido.get(i).getNumero() == (p.getNumero())) {
				System.out.println("Já existe um partido com esse número!");

			} else if (partido.get(i).getNumero() != p.getNumero()) {
				partido.add(p);
				System.out.println("O partido foi cadastrado com sucesso!");
				System.out.println("Numero: " + p.getNumero() + "Nome " + p.getNome());
				return true;
			}

		}
		return false;
	}
	public Partido partidoComMaisCandidatos(){
		Partido maior = null;
		int max =0;
		for(Partido p : partido){
			if(p.getCandidato().size() > max){
				maior = p;
			}
		}
		return maior;
	}

	//o consultaPartido(String): retorna o partido com o nome indicado. Se não houver
	//nenhum partido com este nome retorna null.

	public Partido consultaPartidoPorNome(String nome) {
		for (int i = 0; i < partido.size(); i++) {
			Partido p = partido.get(i);
			if (partido.get(i).getNome().equals(nome)) {
				return p;
			}
		}
		return null;
	}

	public Partido consultaPartidoPorNumero(int numero) {
		for (int i = 0; i < partido.size(); i++){
			Partido p = partido.get(i);
			if (p.getNumero() == numero) {
				return p;
			}
		}
		return null;
	}

	//o consultaPartido(int): retorna o partido com o número indicado. Se não houver
	//nenhum partido com este número retorna null.

	public Partido dadosDeUmPartidoPeloNumero(int numero, String nome) {
		for (int i = 0; i < partido.size(); i++) {
			Partido p = partido.get(i);
			if (partido.get(i).getNumero() == numero) {
				System.out.println("Nome: " + p.getNome() + "Numero: " + p.getNumero());
				return p;
			}
		}
		return null;
	}

	public Partido buscarPartido(String nome) {
		for (int i = 0; i < partido.size(); i++) {
			if (partido.get(i).getNome().equals(nome)) {
				System.out.println(nome);
			} else {
				System.out.println("Partido não encontrado");
			}
		}
		return null;

	}

	public void votosDePrefeitoDeUmPartido(String nomePartido) {
		Partido partido = consultaPartidoPorNome(nomePartido);
		if (partido == null) {
			System.out.println("6:Nenhum partido encontrado.");
			return;
		}
	}
	public void votosPrefeitoDeUmPartido(String nomePartido) {
		Partido partido = consultaPartidoPorNome(nomePartido);
		if (partido != null) {
			for (Candidato c : partido.getCandidato()) {
				if (c.getNumero() < 100) {
					System.out.println(partido.getNome() + c.getNumero() + c.getNome() + c.getMunicipio() + c.getVotos());
				} else {
					System.out.println("Nenhum partido encontrado");
				}
			}
		}
	}


}



import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Scanner;

public class ACMEVoting {
	private Scanner entrada;
	private PrintStream saidaPadrao = System.out;
	private final String nomeArquivoEntrada = "input.txt";
	private final String nomeArquivoSaida = "output.txt";
	private final CadastroPartido cadastroPartido = new CadastroPartido();
	private final Candidatura candidatura = new Candidatura();

	public ACMEVoting() {
		redirecionaEntrada();
		redirecionaSaida();
	}

	private void redirecionaEntrada() {
		try {
			entrada = new Scanner(new File(nomeArquivoEntrada), StandardCharsets.UTF_8.name());
			Locale.setDefault(Locale.ENGLISH);
			entrada.useLocale(Locale.ENGLISH);
		} catch (FileNotFoundException e) {
			System.err.println("Erro ao abrir o arquivo de entrada: " + e.getMessage());
		}
	}

	private void redirecionaSaida() {
		try {
			PrintStream streamSaida = new PrintStream(new FileOutputStream(nomeArquivoSaida), true, StandardCharsets.UTF_8.name());
			System.setOut(streamSaida);
		} catch (FileNotFoundException e) {
			System.err.println("Erro ao abrir o arquivo de saída: " + e.getMessage());
		} catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

	private void restauraEntrada() {
		entrada = new Scanner(System.in);
	}

	private void restauraSaida() {
		System.setOut(saidaPadrao);
	}

	public void executar() {
		cadastroPartido();
		cadastroCandidatos();
		cadastrarVotosdeCandidatos();
		dadosDeUmPartidoPeloNumero(); // ajeitar fazendo uma substring para pegar os 0 a 2 primeiros numeros
		dadosDeUmCandidato();
		votosDePrefeitoDeUmPartido();
		dadosPartidoMaisCandidato();
		dadosPartidoVereadorMaisVotados();
	}

	//Cadastrar partidos: lê todos os dados de cada partido e, se o número não for
	//repetido, cadastra-o no sistema. Para cada partido cadastrado com sucesso no
	//sistema, mostra os dados do partido no formato: 1:número,nome
	public void cadastroPartido() {
		while (entrada.hasNextLine()) {
			int numero = Integer.parseInt(entrada.nextLine());
			if (numero == -1) break;
			String nome = entrada.nextLine().trim();
			Partido p = new Partido(nome, numero);
			if (cadastroPartido.cadastraPartido(p, numero, nome)) {
				System.out.println("1." + numero + "," + nome);
			}
		}
	}

	//Cadastrar candidatos: lê todos os dados de cada candidato e, se o número não
	//for repetido no município e o partido existir, cadastra o candidato no sistema. Para
	//cada candidato cadastrado com sucesso no sistema, mostra os dados do candidato
	//no formato: 2:número,nome,município

	public void cadastroCandidatos() {
		while (entrada.hasNextLine()) {
			int numero = Integer.parseInt(entrada.nextLine());
			if (numero == -1) break;
			String nome = entrada.nextLine().trim();
			String municipio = entrada.nextLine().trim();
			Candidato c = new Candidato(numero, nome, municipio);
			if (candidatura.cadastraCandidato(c)) {
				System.out.println("2." + numero + "," + nome + "," + municipio);
			}
		}
	}

	//Cadastrar votos de candidatos: lê os votos de um determinado candidato. Se o
	//número do candidato for válido, adiciona os votos do candidato. Para cada
	//cadastramento com sucesso mostra os dados no formato:
	//3:número,município,votos

	public void cadastrarVotosdeCandidatos() {
		while (entrada.hasNextLine()) {
			int numero = Integer.parseInt(entrada.nextLine());
			if (numero == -1) break;
			String municipio = entrada.nextLine();
			int votos = Integer.parseInt(entrada.nextLine());
			candidatura.adicionarVotos(numero,votos,municipio);
			System.out.println("3:" + numero + "," + municipio + "," + votos);
		}
	}

//Mostrar os dados de um determinado partido pelo número: lê o número de um
//determinado partido. Se não existir um partido com o número indicado, mostra a
//mensagem de erro: “4:Nenhum partido encontrado.”. Se existir, mostra os
//dados do partido no formato: 4:numero,nome

	public void dadosDeUmPartidoPeloNumero() {
		while (entrada.hasNextLine()) {
			String linha = entrada.nextLine().trim();
			if (linha.matches("\\d+")) {
				int numero = Integer.parseInt(linha);
				Partido p = cadastroPartido.consultaPartidoPorNumero(numero);
				if (p != null) {
					System.out.println("4:" + p.getNumero() + "," + p.getNome());
				} else {
					System.out.println("4:Nenhum partido encontrado.");
				}
			} else {
				System.out.println("4: Nenhum partido encontrado.");
			}
		}
	}


	//Mostrar os dados de um determinado candidato: lê um número de candidato e
	//o município. Se não existir um candidato com o número indicado no município,
	//mostra a mensagem de erro: “5:Nenhum candidato encontrado.”. Se existir,
	//mostra os dados do candidato no formato: 5:numero,nome,município,votos

	public void dadosDeUmCandidato() {
		while (entrada.hasNextLine()) {

			int numero= Integer.parseInt(entrada.nextLine());;
			String municipio = entrada.nextLine().trim();
			Candidato c = candidatura.dadosDeUmCandidato(numero, municipio);
			if (c != null) {
				System.out.println("5:" + c.getNumero() + "," + c.getNome() + "," + c.getMunicipio() + "," + c.getVotos());
			} else {
				System.out.println("5:Nenhum candidato encontrado.");
			}

		}
	}

	//Mostrar os votos dos prefeitos de um determinado partido: lê o nome de um
	//partido. Se não houver nenhum partido com o número indicado, mostra a
	//mensagem de erro: “6:Nenhum partido encontrado.”, caso contrário, mostra
	//os dados de cada um de seus prefeitos no formato:
	//6:nomepartido,númeroprefeito,nomeprefeito,municipio,votos

	public void votosDePrefeitoDeUmPartido() {
		while (entrada.hasNextLine()) {
		if (entrada.hasNextLine()) {
			String nome = entrada.nextLine().trim();
			cadastroPartido.votosPrefeitoDeUmPartido(nome);
		}
			System.out.println("6: Nenhum partido encontrado.");
		}
	}

	//Mostrar os dados do partido com mais candidatos: localiza o partido com maior
	//quantidade de candidatos. Se não houver partidos com candidatos, mostra a
	//mensagem de erro: “7:Nenhum partido com candidatos.”. Caso contrário,
	//mostra os dados do partido e quantidade de candidatos correspondente no formato:
	//7:número,nome,quantidade

	public void dadosPartidoMaisCandidato() {
		while (entrada.hasNextLine()) {
			Partido p = cadastroPartido.partidoComMaisCandidatos();
			if (p != null) {
				System.out.printf("7: " + p.getNumero() + p.getNome() + p.getCandidato().size());
			} else {
				System.out.println("7:Nenhum partido com candidatos.");
			}
		}
	}


	//Mostrar os dados do prefeito e do vereador mais votados: se não houver
	//candidatos cadastrados no sistema, mostra a mensagem de erro: “8:Nenhum
	//candidato encontrado.” Senão, mostra os dados do prefeito e do vereador
	//mais votados no formato: 8:número,nome,município,votos

	public void dadosPartidoVereadorMaisVotados() {
		while (entrada.hasNextLine()) {
			String nome = entrada.nextLine().trim();
			candidatura.dadosPrefeitoVereadorMaisVotados();
		}
	}
}

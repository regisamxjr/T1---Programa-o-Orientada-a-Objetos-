import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ACMEVoting {
	private Scanner entrada = new Scanner(System.in);
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
			BufferedReader streamEntrada = new BufferedReader(new FileReader(nomeArquivoEntrada));
			entrada = new Scanner(streamEntrada);
		} catch (Exception e) {
			System.out.println(e);
		}
		Locale.setDefault(Locale.ENGLISH);
		entrada.useLocale(Locale.ENGLISH);
	}

	private void redirecionaSaida() {
		try {
			PrintStream streamSaida = new PrintStream(new File(nomeArquivoSaida), Charset.forName("UTF-8"));
			System.setOut(streamSaida);
		} catch (Exception e) {
			System.out.println(e);
		}
		Locale.setDefault(Locale.ENGLISH);
	}

	private void restauraEntrada() {
		entrada = new Scanner(System.in);
	}

	private void restauraSaida() {
		System.setOut(saidaPadrao);
	}

	public void executar() {
		System.out.println("Começando o programa... ");
		cadastroPartido();
		cadastroCandidatos();
		cadastrarVotosdeCandidatos();
		dadosDeUmPartidoPeloNumero();
		dadosDeUmCandidato();
		votosDePrefeitoDeUmPartido();
		dadosPartidoMaisCandidato();
		dadosPartidoVereadorMaisVotados();
	}

	public void cadastroPartido() {
		while (entrada.hasNext()) {
			int numero = entrada.nextInt();
			if (numero == -1) break;
			String nome = entrada.nextLine().trim();
			Partido p = new Partido(nome, numero);
			if (cadastroPartido.cadastraPartido(p,numero,nome)) {
				System.out.println("1." + numero + "," + nome);
			}
		}
	}

	public void cadastroCandidatos() {
		while (entrada.hasNext()) {
			int numero = entrada.nextInt();
			if (numero == -1) break;
			String nome = entrada.nextLine().trim();
			String municipio = entrada.nextLine().trim();
			Candidato c = new Candidato(numero, nome, municipio);
			if (candidatura.cadastraCandidato(c)) {
				System.out.println("2." + numero + "," + nome + "," + municipio);
			}
		}
	}

	public void cadastrarVotosdeCandidatos() {
		while (entrada.hasNext()) {
			int numero = entrada.nextInt();
			if (numero == -1) break;
			int votos = entrada.nextInt();
			candidatura.adicionarVotos(numero, votos);
		}
	}

	public void dadosDeUmPartidoPeloNumero() {
		int numero = entrada.nextInt();
		Partido p = cadastroPartido.consultaPartidoPorNumero(numero);
		if (p != null) {
			System.out.println("4:" + p.getNumero() + "," + p.getNome());
		} else {
			System.out.println("4:Nenhum partido encontrado.");
		}
	}

	public void dadosDeUmCandidato() {
		int numero = entrada.nextInt();
		String municipio = entrada.nextLine().trim();
		Candidato c = candidatura.dadosDeUmCandidato(numero, municipio);
		if (c != null) {
			System.out.println("5:" + c.getNumero() + "," + c.getNome() + "," + c.getMunicipio() + "," + c.getVotos());
		} else {
			System.out.println("5:Nenhum candidato encontrado.");
		}
	}

	public void votosDePrefeitoDeUmPartido() {
		String nomePartido = entrada.nextLine().trim();
		cadastroPartido.votosDePrefeitoDeUmPartido(nomePartido);
	}

	public void dadosPartidoMaisCandidato() {
		Partido p = cadastroPartido.partidoComMaisCandidatos();
		if (p != null) {
			System.out.printf("7:%d,%s,%d%n", p.getNumero(), p.getNome(), p.getCandidato().size());
		} else {
			System.out.println("7:Nenhum partido com candidatos.");
		}
	}

	public void dadosPartidoVereadorMaisVotados() {
		candidatura.dadosPrefeitoVereadorMaisVotados();
	}
}








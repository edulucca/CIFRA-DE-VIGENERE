package principal;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CifraDeVigenere {
	public static void main(String[] args) {
		//Decleração de variaveis
		BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
		String texto = "";
		String chave = "";
		String saida = "";
		String opcao = "";
		
		//Entrada de dados
		texto = lerTexto(leitor);
		chave = lerChave(leitor);
		
		try {
			System.out.println("Digite <E> para encriptar ou <D> para decriptar: ");
			opcao = leitor.readLine();
		}catch(Exception erro) {
			System.out.println(erro);
		}
		//Processamento
		if(opcao.equalsIgnoreCase("E")) {
			saida = criptografar(texto, chave);
		} else{
			saida = decriptografar(texto, chave);
		}
		
		//Saida
		System.out.println(saida);
	}

	private static String decriptografar(String textoCifrado, String chave) {
		String retorno = "";
		
		//For que passa de DUAS em DUAS letras que indica que cada letra corresponde a dois caracteres cifrados
		for(int i = 0; i < textoCifrado.length(); i+=2) {
			//Separa de 2 em 2 letras
			String letraTexto = textoCifrado.substring(i, i + 2);
			/*Captura cada letra da chave. A divisão 'i/2' é necessária para pegar a letra correta, invertendo o efeito
			 * do for que 'pula' de 2 em 2 */
			int letraChave = chave.charAt(i/2 % chave.length());
			
			//Realiza a relação do 'ou exclusivo' entre a chave e a cifra em int e é convertido para char
			char letraXOR = (char) (Integer.parseInt(letraTexto, 16) ^ letraChave);
			
			//Reúne as letras decriptografadas em um unico texto
			retorno += letraXOR;
		}
		return retorno;
	}

	private static String criptografar(String texto, String chave) {
		String retorno = "";
		for(int i = 0; i < texto.length(); i++) {
			//Captura letra do Texto por em decimal pela tabela ASCII
			int letraTexto = texto.charAt(i);
			//Captura letra da Chave por em decimal pela tabela ASCII e repete o indice da chave sempre que ela termina
			int letraChave = chave.charAt(i % chave.length());
			//Faz a realção do "Ou Exclusivo"
			int letraXOR = (letraTexto ^ letraChave);
			//Transforma em Hexadecimal como padrão de criptografia
			String letraHex = Integer.toHexString(letraXOR);
			
			//Quando a letra em hexadecimal tiver apenas 1 caractere, adiciona 0 + a letra hexadecimal
			retorno += ((letraHex.length() == 2 ? "" : "0") + letraHex);
		}
		return retorno;
	}

	private static String lerChave(BufferedReader leitor) {
		String retorno = "";
		try {
			System.out.println("Digite a chave: ");
			retorno = leitor.readLine();
		}
		catch(Exception erro) {
			System.out.println(erro);
		}
		
		return retorno;
	}

	private static String lerTexto(BufferedReader leitor) {
		String retorno = "";
		try {
			System.out.println("Digite um texto: ");
			retorno = leitor.readLine();
		}
		catch(Exception erro) {
			System.out.println(erro);
		}
		
		return retorno;
	}
}

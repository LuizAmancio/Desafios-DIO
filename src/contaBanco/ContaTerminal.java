package contaBanco;

import java.util.Scanner;

public class ContaTerminal {

	public static void main(String[] args) {
		
		double saldo = 234.67;
		
		Scanner scanner = new Scanner(System.in);
		
		
		System.out.println("Insira o nome do Cliente: ");
		String nome= scanner.nextLine();
		
		System.out.println("Insira o número da Agência: ");
		Integer agencia = scanner.nextInt();
		
		System.out.println("Insira o número da conta: ");
		Integer numero = scanner.nextInt();
		
		String msg = "Olá "+nome+", obrigado por criar uma conta em nosso banco!"
				+ "\r\nAgência: "+agencia
				+ "\r\nConta: "+numero
				+ "\r\nSaldo disponível: R$"+saldo;
		
		System.out.println(msg);
		scanner.close();
	}

}

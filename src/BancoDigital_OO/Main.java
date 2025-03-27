package BancoDigital_OO;

import java.util.InputMismatchException;
import java.util.Scanner;

import BancoDigital_OO.entidades.Cliente;
import BancoDigital_OO.entidades.Conta;
import BancoDigital_OO.entidades.ContaCorrente;
import BancoDigital_OO.entidades.ContaPoupanca;
import BancoDigital_OO.enums.ContaEnum;

public class Main {

	private final static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		
		Cliente francisco =  new Cliente("Francisco", "120.223.123-00", 42);
		
		Conta cc = new ContaCorrente(ContaEnum.AGENCIA_PADRAO.getCodigo(),120,francisco, 2000);
		
		Conta cp = new ContaPoupanca(ContaEnum.AGENCIA_PADRAO.getCodigo(), 250, francisco);
		
		executar(cc,cp);
		
		scanner.close();

	}
	
	public static void executar(Conta cc, Conta cp) {
		
		int conta = getIntValue("Selecione o tipo de conta que deseja utilizar:"
								+ "\r\n1 - Conta Poupança\r\n"
								+ "2 - Conta Corrente\r\n"
								+ "0 - Sair");
		
		switch (conta) {
			case 1 -> executarOperacao(cp);
			case 2 -> executarOperacao(cc);
			case 0 -> System.exit(0);
			default -> System.out.println("Selecione opção válida\r\n");
		}
		
		if(getIntValue("\r\nDeseja realizar nova operação?\r\n1- Sim\r\n2 - Não") == 1) {
			executar(cc, cp);
		}
	}
	
	public static void executarOperacao(Conta conta) {
		boolean continua = true;
		while(continua) {

			int op = getIntValue("------ Conta "+conta.getTipoConta()+" de "+conta.getCliente().getNome()+" ------"
								+ "\r\nSelecione a operação desejada:"
								+ "\r\n1 - Sacar"
								+ "\r\n2 - Depositar"
								+ "\r\n3 - Transferir"
								+ "\r\n0 - Sair");
			
			switch (op) {	
				case 1 -> {
					sacar(conta);
					continua = false;
					break; 
				}
				case 2 -> {
					depositar(conta);
					continua = false;
					break; 
				}
				case 3 -> {
					realizarTransferencia(conta);
					continua = false;
					break;
				}
				case 0 -> {
					System.exit(0); 
				}
			}
		}
	}
	
	public static void sacar(Conta conta) {
		double valor = getDoubleValue("------ Conta "+conta.getTipoConta()+" de "+conta.getCliente().getNome()+" ------"
										+ "\r\nQual valor que deseja sacar?");
		conta.sacar(valor);
	}
	
	public static void depositar(Conta conta) {
		double valor = getDoubleValue("------ Conta "+conta.getTipoConta()+" de "+conta.getCliente().getNome()+" ------"
										+ "\r\nQual valor que deseja depositar?");
		conta.depositar(valor);
	}
	
	public static void realizarTransferencia(Conta contaOrigem) {
		
		String nome = getStringValue("------ Conta de " + contaOrigem.getCliente().getNome() + " ------"
									+ "\r\nQual nome do titular da conta que deseja transferir?");
		
		String cpf = getCpfValue("------ Conta de " + contaOrigem.getCliente().getNome() + " ------"
									+ "\r\nQual CPF do titular da conta que deseja transferir?");
		
		Cliente clienteDestino = new Cliente(nome, cpf, 50);
		
		double valorTransferencia = getDoubleValue("------ Conta de " + contaOrigem.getCliente().getNome() + " ------"
													+ "\r\nQual valor deseja transferir para " + clienteDestino.getNome() + "?");
		
		int tipoConta = getIntValue("------ Conta de " + contaOrigem.getCliente().getNome() + " ------"
									+ "\r\nQual tipo de conta deseja transferir?\r\n1 - Corrente\r\n2 - Poupança");
		
		Conta contaDestino = null;
		
		switch (tipoConta) {
		case 1:
			contaDestino = new ContaCorrente(ContaEnum.AGENCIA_PADRAO.getCodigo(), 500, clienteDestino, 3500);
			break;
		case 2:
			contaDestino = new ContaPoupanca(ContaEnum.AGENCIA_PADRAO.getCodigo(), 500, clienteDestino);
			break;
		default:
			System.out.println("Tipo de conta inválido");
			return;
		}
		
		contaOrigem.transferir(valorTransferencia, contaDestino);
	}
	
	public static int getIntValue(String msg) {
		while (true) {
	        try {
	            System.out.println(msg);
	            int value = scanner.nextInt();
	            
	            return value;
	            
	        } catch (InputMismatchException e) {
	            System.out.println("Entrada inválida! Por favor, insira um número válido");
	            scanner.nextLine();
	        }
	    }
	}
	
	public static double getDoubleValue(String msg) {
		while (true) {
			try {
				System.out.println(msg);
				double value = scanner.nextDouble();
				
				return value;
				
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida! Por favor, insira um valor válido");
				 scanner.nextLine();
			}
		}
	}
	
	public static String getStringValue(String msg) {
		while (true) {
			try {
				System.out.println(msg);
				String value = scanner.next();
				
				if (value.matches("[^0-9]+")) {
				   return value;
				} 
				
				System.out.println("Entrada inválida! Por favor, insira um nome válido");
				
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida! Por favor, insira um nome válido");
				 scanner.nextLine();
			}
		}
	}
	
	public static String getCpfValue(String msg) {
		while (true) {
			try {
				System.out.println(msg);
				String cpf = scanner.next();
				
				if (cpf.matches("[0-9.-]+")
						&& cpf.replace(".", "").replace("-", "").length() == 11) {
					return cpf;
				} 
				
				System.out.println("Entrada inválida! Por favor, insira um CPF válido");
				
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida! Por favor, insira um CPF válido");
				scanner.nextLine();
			}
		}
	}

}

package BancoDigital_OO.entidades;

import java.util.Scanner;

import BancoDigital_OO.enums.ContaEnum;

public class Main {

	public static void main(String[] args) {
		
		Cliente francisco =  new Cliente("Francisco", "120.223.123-00", 42);
		
		Conta cc = new ContaCorrente(ContaEnum.AGENCIA_PADRAO.getCodigo(),120,francisco, 2000);
		
		Conta cp = new ContaPoupanca(ContaEnum.AGENCIA_PADRAO.getCodigo(), 250, francisco);
		
		Scanner scanner = new Scanner(System.in);
		
		executar(scanner,cc,cp);
		
		scanner.close();

	}
	
	public static void executar(Scanner scanner, Conta cc, Conta cp) {
		System.out.println("Selecione o tipo de conta que deseja utilizar:"
				+ "\r\n1 - Conta Poupança\r\n"
				+ "2 - Conta Corrente\r\n"
				+ "0 - Sair");
		
		int conta = scanner.nextInt();
		
		switch (conta) {
		case 1: {
			executarCP(scanner, cp);
			break;
		}
		case 2: {
			executarCC(scanner, cc);
			break;
		}
		case 0: {
			System.exit(0);
		}
		default:
			System.out.println("Selecione opção válida\r\n");
			executar(scanner, cc, cp);
		}
		
		System.out.println("\r\nDeseja realizar nova operação?\r\n1- Sim\r\n2 - Não");
		int repetir = scanner.nextInt();
		if(repetir == 1) {
			executar(scanner, cc, cp);
		}
		
	}
	
	public static void executarCC(Scanner scanner, Conta cc) {
		boolean continuar = true;
		
		while(continuar) {
			System.out.println("------ Conta Corrente de "+cc.getCliente().getNome()+" ------"
					+ "\r\nSelecione a operação desejada:"
					+ "\r\n1 - Sacar"
					+ "\r\n2 - Depositar"
					+ "\r\n3 - Transferir"
					+ "\r\n0 - Sair");
			
			int op = scanner.nextInt();
			
			switch (op) {
			case 1: {
				
				System.out.println("------ Conta Corrente de "+cc.getCliente().getNome()+" ------"
						+ "\r\nQual valor que deseja sacar?");
				double valor = scanner.nextDouble();
				cc.sacar(valor);
				continuar = false;
				break;
			}
			case 2: {
				System.out.println("------ Conta Corrente de "+cc.getCliente().getNome()+" ------"
						+ "\r\nQual valor que deseja depositar?");
				double valor = scanner.nextDouble();
				cc.depositar(valor);
				continuar = false;
				break;
			}
			case 3: {
				
				System.out.println("------ Conta Corrente de "+cc.getCliente().getNome()+" ------"
						+ "\r\nQual nome do titular da conta que deseja transferir?");
				String nome = scanner.next();
				
				System.out.println("------ Conta Corrente de "+cc.getCliente().getNome()+" ------"
						+ "\r\nQual CPF do titular da conta que deseja transferir?");
				String cpf = scanner.next();
				
				Cliente cliente = new Cliente(nome, cpf, 50);
				
				System.out.println("------ Conta Corrente de "+cc.getCliente().getNome()+" ------"
						+ "\r\nQual valor que deseja transferir para "+cliente.getNome()+"?");
				double valor = scanner.nextDouble();
				
				System.out.println("------ Conta Corrente de "+cc.getCliente().getNome()+" ------"
						+ "\r\nQual tipo de conta que deseja transferir?\r\n1 - Corrente\r\n2 - poupança");
				int tpConta = scanner.nextInt();
				
				Conta conta = null;
				
				switch (tpConta) {
				case 1: {
					conta = new ContaCorrente(ContaEnum.AGENCIA_PADRAO.getCodigo(), 500, cliente, 3500);
					break;
				}
				case 2: {
					conta = new ContaPoupanca(ContaEnum.AGENCIA_PADRAO.getCodigo(), 500, cliente);
					break;
				}
				default:
					continuar = false;
				}
				
				cc.transferir(valor, conta);
				continuar = false;
				break;
			}
			case 0: {
				continuar = false;
				break;
			}
			default:
				System.out.println("Selecione opção válida\r\n");
			}
		}
	}
	
	public static void executarCP(Scanner scanner, Conta cp) {
boolean continuar = true;
		
		while(continuar) {
			System.out.println("------ Conta Poupança de "+cp.getCliente().getNome()+" ------"
					+ "\r\nSelecione a operação desejada:"
					+ "\r\n1 - Sacar"
					+ "\r\n2 - Depositar"
					+ "\r\n3 - Transferir"
					+ "0 - Sair");
			
			int op = scanner.nextInt();
			
			switch (op) {
			case 1: {
				
				System.out.println("------ Conta Poupança de "+cp.getCliente().getNome()+" ------"
						+ "\r\nQual valor que deseja sacar?");
				double valor = scanner.nextDouble();
				cp.sacar(valor);
				break;
			}
			case 2: {
				System.out.println("------ Conta Poupança de "+cp.getCliente().getNome()+" ------"
						+ "\r\nQual valor que deseja depositar?");
				double valor = scanner.nextDouble();
				cp.depositar(valor);
				break;
			}
			case 3: {
				
				System.out.println("------ Conta Poupança de "+cp.getCliente().getNome()+" ------"
						+ "\r\nQual nome do titular da conta que deseja transferir?");
				String nome = scanner.next();
				
				System.out.println("------ Conta Poupança de "+cp.getCliente().getNome()+" ------"
						+ "\r\nQual CPF do titular da conta que deseja transferir?");
				String cpf = scanner.next();
				
				Cliente cliente = new Cliente(nome, cpf, 50);
				
				System.out.println("------ Conta Poupança de "+cp.getCliente().getNome()+" ------"
						+ "\r\nQual valor que deseja transferir para "+cliente.getNome()+"?");
				double valor = scanner.nextDouble();
				
				System.out.println("------ Conta Poupança de "+cp.getCliente().getNome()+" ------"
						+ "\r\nQual tipo de conta que deseja transferir?\r\n1 - Corrente\r\n2 - poupança");
				int tpConta = scanner.nextInt();
				
				Conta conta = null;
				
				switch (tpConta) {
				case 1: {
					conta = new ContaCorrente(ContaEnum.AGENCIA_PADRAO.getCodigo(), 500, cliente, 3500);
					break;
				}
				case 2: {
					conta = new ContaPoupanca(ContaEnum.AGENCIA_PADRAO.getCodigo(), 500, cliente);
					break;
				}
				default:
					continuar = false;
				}
				
				cp.transferir(valor, conta);
			}
			case 0: {
				continuar = false;
			}
			default:
				System.out.println("Selecione opção válida\r\n");
			}
		}
	}

}

package BancoDigital_OO.entidades;

public class ContaPoupanca extends Conta {
	
	
	public ContaPoupanca(int agencia, double saldo, Cliente cliente) {
		super(agencia, saldo, cliente);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean sacar(double valor) {
		if(super.saldo >= valor) {
			super.saldo -= valor;
			System.out.println("Saque realizado!!"
					+ "\r\n"
					+ "Valor Sacado: R$ "+valor
					+"\r\n"
					+ "Saldo: R$"+super.saldo+"\r\n");
			return true;
		}
		
		System.out.println("SALDO INSUFICIENTE!!"
				+ "\r\n"
				+ "Valor Solicitado: R$ "+valor
				+"\r\n"
				+ "Saldo: R$ "+super.saldo+"\r\n");
		
		return false;
		
	}

	@Override
	public void depositar(double valor) {
		super.saldo += valor;
		
		System.out.println("Deposito realizado!!"
				+ "\r\n"
				+ "Valor Depositado: R$ "+valor
				+"\r\n"
				+ "Saldo: R$"+super.saldo
				+"\r\n");
	}

	@Override
	public boolean transferir(double valor, Conta contaDestino) {
		if(this.sacar(valor)) {
			contaDestino.depositar(valor);
			System.out.println("Transferência Efetuada!!"
					+ "\r\n"
					+ "Valor Transferido: R$ "+valor
					+"\r\n"
					+ "Saldo: R$ "+super.saldo+"\r\n");
			return true;
		}
		
		System.out.println("Transferência Não Realizada !!\r\nLIMITE INSUFICIENTE!!"
				+ "\r\n"
				+ "Valor Solicitado: R$ "+valor
				+"\r\n"
				+ "Saldo: R$ "+super.saldo+"\r\n");
		
		return false;
	}
	
	@Override
	public String getTipoConta() {
		return "Poupança";
	}

}

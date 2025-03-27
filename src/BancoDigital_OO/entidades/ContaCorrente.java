package BancoDigital_OO.entidades;

public class ContaCorrente extends Conta {
	
	private double limite;
	private double limiteUtilizado = 0;
	
	
	public ContaCorrente(int agencia,double saldo, Cliente cliente, double limite) {
		super(agencia, saldo, cliente);
		this.limite = limite;
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
			
		}else if(getLimiteDisponivel() >= valor ) {
			super.saldo -= valor;
			limiteUtilizado += valor;
			System.out.println("Saque realizado!!"
					+ "\r\n"
					+ "Valor Sacado: R$ "+valor
					+"\r\n"
					+ "Saldo: R$"+super.saldo
					+"\r\n"
					+ "Limite Disponivel: R$"+getLimiteDisponivel()+"\r\n");
			return true;
		}
		
			System.out.println("LIMITE INSUFICIENTE!!"
					+ "\r\n"
					+ "Valor Solicitado: R$ "+valor
					+"\r\n"
					+ "Saldo: R$ "+super.saldo
					+"\r\n"
					+ "Limite Total: R$ "+getLimite()
					+"\r\n"
					+ "Limite Disponivel: R$ "+getLimiteDisponivel()+"\r\n");
			return false;
	}

	@Override
	public void depositar(double valor) {
		super.saldo += valor;
		if(limiteUtilizado > 0) {
			limiteUtilizado -= valor;
			if(limiteUtilizado > limite) {
				limiteUtilizado =0;
			}
		}
		
		System.out.println("Deposito realizado!!"
				+ "\r\n"
				+ "Valor Depositado: R$ "+valor
				+"\r\n"
				+ "Saldo: R$"+super.saldo
				+"\r\n"
				+ "Limite Disponivel: R$"+getLimiteDisponivel()+"\r\n");
	}

	@Override
	public boolean transferir(double valor, Conta contaDestino) {
		if(this.sacar(valor)) {
			contaDestino.depositar(valor);
			System.out.println("Transferência Efetuada!!"
					+ "\r\n"
					+ "Valor Transferido: R$ "+valor
					+"\r\n"
					+ "Saldo: R$ "+super.saldo
					+"\r\n"
					+ "Limite Disponivel: R$ "+getLimiteDisponivel()+"\r\n");
			return true;
		}
		
		System.out.println("Transferência Não Realizada !!\r\nLIMITE INSUFICIENTE!!"
				+ "\r\n"
				+ "Valor Solicitado: R$ "+valor
				+"\r\n"
				+ "Saldo: R$ "+super.saldo
				+"\r\n"
				+ "Limite Total: R$ "+getLimite()
				+"\r\n"
				+ "Limite Disponivel: R$ "+getLimiteDisponivel()+"\r\n");
		
		return false;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public double getLimiteUtilizado() {
		return limiteUtilizado;
	}

	public double getLimiteDisponivel() {
		return limite - limiteUtilizado;
	}

	@Override
	public String getTipoConta() {
		return "Corrente";
	}
}

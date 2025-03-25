package BancoDigital_OO.entidades;

public abstract class Conta {
	
	private static int SEQUENCIAL = 1;
	
	private int agencia;
	private int numero;
	protected double saldo;
	
	private Cliente cliente;
	
	public abstract boolean sacar(double valor);
	
	public abstract void depositar(double valor) ;
	
	public abstract boolean transferir(double valor, Conta contaDestino) ;
	
	public Conta(int agencia, double saldo, Cliente cliente) {
		super();
		this.agencia = agencia;
		this.saldo = saldo;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
	}
	
	public int getAgencia() {
		return agencia;
	}
	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}

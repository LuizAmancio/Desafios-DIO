package BancoDigital_OO.enums;

public enum ContaEnum {
	
	AGENCIA_PADRAO(1),
	CODIGO_PADRAO(1302);
	
	private int codigo;

	private ContaEnum(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	
}

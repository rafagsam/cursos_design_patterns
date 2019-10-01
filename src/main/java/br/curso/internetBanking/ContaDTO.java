package br.curso.internetBanking;

import java.math.BigDecimal;

public class ContaDTO {
	private Long id;
	private ClienteDTO cliente;
	private BigDecimal saldo;
	private TipoConta tipoConta;
	private TipoAplicacao tipoAplicacao;
	
	public ContaDTO() {
		super();
	}
	public ContaDTO(Conta conta) {
		super();
		this.id = conta.getId();
		this.cliente = new ClienteDTO(conta.getCliente());
		this.saldo = conta.getSaldo();
		if (conta instanceof ContaCorrente) {
			this.tipoConta = TipoConta.CORRENTE;	
		} else {
			this.tipoConta = TipoConta.INVESTIMENTO;
			this.tipoAplicacao = ((ContaInvestimento)conta).getTipoAplicacao();
		}
		
	}
	public Conta asConta() {
		switch (this.tipoConta) {
		case CORRENTE:
			ContaCorrente conta = new ContaCorrente();
			conta.setCliente(this.cliente.asCliente());
			return conta;
		default:
			ContaInvestimento invest = new ContaInvestimento(this.tipoAplicacao);
			invest.setCliente(this.cliente.asCliente());
			return invest;
		}
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public TipoConta getTipoConta() {
		return tipoConta;
	}
	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}
	public TipoAplicacao getTipoAplicacao() {
		return tipoAplicacao;
	}
	public void setTipoAplicacao(TipoAplicacao tipoAplicacao) {
		this.tipoAplicacao = tipoAplicacao;
	}
}

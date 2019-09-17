package br.curso.internetBanking;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ContaInvestimento extends Conta {
	
	private TipoAplicacao tipoAplicacao;

	public ContaInvestimento(TipoAplicacao tipoAplicacao) {
		super();
		this.tipoAplicacao = tipoAplicacao;
	}

	public TipoAplicacao getTipoAplicacao() {
		return tipoAplicacao;
	}

	public void setTipoAplicacao(TipoAplicacao tipoAplicacao) {
		this.tipoAplicacao = tipoAplicacao;
		
	}

	@Override
	public void accept(TaxaContaVisitor visitor) {
		visitor.visitContaInvestimento(this);
	}
	
	public void acrescentaRentabilidade(BigDecimal valor) {
		this.credito(valor);
		Movimentacao movimentacao = new Movimentacao.Builder().set(m -> {
			m.conta = this;
			m.tipoMovimentacao = TipoMovimentacao.RENDIMENTO;
			m.dataMovimentacao = LocalDateTime.now();
			m.valor = valor;
		}).build();
		this.movimentaConta(movimentacao);
		
	}
	
	@Override
	public void calculaRentabilidade() {
		tipoAplicacao.getCalculoRentabilidadeStrategy().executaCalculaRentabilidade(this);
	}
	
}

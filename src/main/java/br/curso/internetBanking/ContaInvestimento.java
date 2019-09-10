package br.curso.internetBanking;

public class ContaInvestimento extends Conta {
	private TipoAplicacao tipoAplicacao;

	public TipoAplicacao getTipoAplicacao() {
		return tipoAplicacao;
	}

	public void setTipoAplicacao(TipoAplicacao tipoAplicacao) {
		this.tipoAplicacao = tipoAplicacao;
	}
}

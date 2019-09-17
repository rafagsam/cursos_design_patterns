package br.curso.internetBanking;

public interface TaxaConta {
	public void accept(TaxaContaVisitor visitor);
}

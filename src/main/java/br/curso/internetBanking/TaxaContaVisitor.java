package br.curso.internetBanking;

public interface TaxaContaVisitor {
	public void visitContaCorrente(ContaCorrente contaCorrente);
	public void visitContaInvestimento(ContaInvestimento contaInvestimento);

}

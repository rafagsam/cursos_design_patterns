package br.curso.internetBanking;

public class COAF implements MovimentacaoObserver{
	private COAF() {};
	private static COAF INSTANCE = new COAF();
	
	public static COAF getInstance() {
		return INSTANCE;
	}
	
	public void notificarMovimentacao(Movimentacao movimentacao) {
		System.out.println("Movimentação notificada: ");
		System.out.println(movimentacao);
	}
	
	
	
}

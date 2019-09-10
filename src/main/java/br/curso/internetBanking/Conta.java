package br.curso.internetBanking;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Conta {
	private int LIMITE_HORA_SAQUE_INI = 6;
	private int LIMITE_HORA_SAQUE_FIM = 22;
	private BigDecimal LIMITE_SAQUE_HORARIO = new BigDecimal(1000);
	
	private BigDecimal LIMITE_NOTIFICACAO = new BigDecimal(50000);
	private List<Movimentacao> movimentacoes= new ArrayList<>();
	private List<MovimentacaoObserver> observadoresMovivemtacao = new ArrayList<>();
	
	private Long id;
	private BigDecimal saldo = BigDecimal.ZERO;
	
	protected void credito(BigDecimal valor) {
		this.saldo = this.saldo.add(valor);
	}
	private void debito(BigDecimal valor) {
		this.saldo = this.saldo.subtract(valor);
	}
	
	public void deposito(BigDecimal valor) {
		this.credito(valor);
		Movimentacao movimentacao = new Movimentacao.Builder().set(m -> {
			m.conta = this;
			m.tipoMovimentacao = TipoMovimentacao.DEPOSITO;
			m.dataMovimentacao = LocalDate.now();
			m.valor = valor;
		}).build();
		
		this.movimentacoes.add(movimentacao);
		if (valor.compareTo(LIMITE_NOTIFICACAO) > 0 ) {
			this.notificarMovimentacao(movimentacao);
		}
	
	}
	public void saque(BigDecimal valor) {		
		if (verificaPodeSacar(valor)) {
			this.debito(valor);
			Movimentacao movimentacao = new Movimentacao.Builder().set(m -> {
				m.conta = this;
				m.tipoMovimentacao = TipoMovimentacao.SAQUE;
				m.dataMovimentacao = LocalDate.now();
				m.valor = valor;
			}).build();
			this.movimentacoes.add(movimentacao);
		}
	}

	private boolean verificaPodeSacar(BigDecimal valor) {
		if (LIMITE_SAQUE_HORARIO.compareTo(valor) < 0 &&
				(LocalDate.now().getLong(ChronoField.HOUR_OF_DAY) < LIMITE_HORA_SAQUE_INI ||
				LocalDate.now().getLong(ChronoField.HOUR_OF_DAY) > LIMITE_HORA_SAQUE_FIM)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "Conta [id=" + id + ", saldo=" + saldo + "]";
	}
	
	public void adicionarObservador(MovimentacaoObserver observador) {
		this.observadoresMovivemtacao.add(observador);
	}
	
	private void notificarMovimentacao(Movimentacao movimentacao) {		
		this.observadoresMovivemtacao.forEach((obs) -> obs.notificarMovimentacao(movimentacao));
	}
	
	public void imprimeExtratoCompleto() {
		this.movimentacoes.stream()
			.sorted(Comparator.comparing(Movimentacao::getDataMovimentacao))
			.forEach(System.out::println);
	}
	
	
}

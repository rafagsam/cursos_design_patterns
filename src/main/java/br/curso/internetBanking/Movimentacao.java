package br.curso.internetBanking;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

public class Movimentacao {
	private Cliente cliente ;
	private Conta conta;
	private TipoMovimentacao tipoMovimentacao;
	private LocalDateTime dataMovimentacao;
	private BigDecimal valor;
	
	private Movimentacao(Builder builder) {
		this.cliente = builder.cliente;
		this.conta = builder.conta;
		this.dataMovimentacao = builder.dataMovimentacao;
		this.tipoMovimentacao = builder.tipoMovimentacao;
		this.valor = builder.valor;
	}


	public static class Builder {
		public Cliente cliente ;
		public  Conta conta;
		public  TipoMovimentacao tipoMovimentacao;
		public LocalDateTime dataMovimentacao;
		public BigDecimal valor;
		private Consumer<Builder> consumer;
		
		public Builder set(Consumer<Builder> consumer) {
			this.consumer = consumer;
			return this;
		}
		
		
		public Builder() {
			super();
		}

		public Movimentacao build() {
			this.consumer.accept(this);
			return new Movimentacao(this);
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public LocalDateTime getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Movimentacao [cliente=" + cliente + ", conta=" + conta.toString() + ", tipoMovimentacao=" + tipoMovimentacao
				+ ", dataMovimentacao=" + dataMovimentacao.format(DateTimeFormatter.ISO_DATE_TIME) + ", valor=" + valor.toPlainString() +"]";
	}

}

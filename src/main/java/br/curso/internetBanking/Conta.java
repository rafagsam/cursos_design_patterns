package br.curso.internetBanking;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipoConta", discriminatorType=DiscriminatorType.STRING, length=100)
public abstract class Conta implements TaxaConta {
	@Transient
	private int LIMITE_HORA_SAQUE_INI = 6;
	@Transient
	private int LIMITE_HORA_SAQUE_FIM = 22;
	@Transient
	private BigDecimal LIMITE_SAQUE_HORARIO = new BigDecimal(1000);
	@Transient
	private BigDecimal LIMITE_NOTIFICACAO = new BigDecimal(50000);
	@Transient
	private List<MovimentacaoObserver> observadoresMovivmentacao = new ArrayList<>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private BigDecimal saldo = BigDecimal.ZERO;
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	@JsonBackReference
	private Cliente cliente;

	@OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
	protected List<Movimentacao> movimentacoes= new ArrayList<>();

	protected void credito(BigDecimal valor) {
		this.saldo = this.saldo.add(valor);
	}
	protected void debito(BigDecimal valor) {
		this.saldo = this.saldo.subtract(valor);
	}
	
	public void deposito(BigDecimal valor) {
		this.credito(valor);
		Movimentacao movimentacao = new Movimentacao.Builder().set(m -> {
			m.conta = this;
			m.tipoMovimentacao = TipoMovimentacao.DEPOSITO;
			m.dataMovimentacao = LocalDateTime.now();
			m.valor = valor;
		}).build();
		
		movimentaConta(movimentacao);
	
	}
	protected void movimentaConta(Movimentacao movimentacao) {
		this.movimentacoes.add(movimentacao);
		if (movimentacao.getValor().compareTo(LIMITE_NOTIFICACAO) > 0 ) {
			this.notificarMovimentacao(movimentacao);
		}
	}
	public void saque(BigDecimal valor) {		
		if (verificaPodeSacar(valor)) {
			this.debito(valor);
			Movimentacao movimentacao = new Movimentacao.Builder().set(m -> {
				m.conta = this;
				m.tipoMovimentacao = TipoMovimentacao.SAQUE;
				m.dataMovimentacao = LocalDateTime.now();
				m.valor = valor;
			}).build();
			this. movimentaConta(movimentacao);
		}
	}
	public void taxar(BigDecimal valor) {		
		if (verificaPodeSacar(valor)) {
			this.debito(valor);
			Movimentacao movimentacao = new Movimentacao.Builder().set(m -> {
				m.conta = this;
				m.tipoMovimentacao = TipoMovimentacao.COBRANCA;
				m.dataMovimentacao = LocalDateTime.now();
				m.valor = valor;
			}).build();
			this.movimentaConta(movimentacao);
		}
	}

	private boolean verificaPodeSacar(BigDecimal valor) {
		if (LIMITE_SAQUE_HORARIO.compareTo(valor) < 0 &&
				(LocalDateTime.now().getLong(ChronoField.HOUR_OF_DAY) < LIMITE_HORA_SAQUE_INI ||
				 LocalDateTime.now().getLong(ChronoField.HOUR_OF_DAY) > LIMITE_HORA_SAQUE_FIM)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "Conta [id=" + id + ", saldo=" + saldo + "]";
	}
	
	public void adicionarObservador(MovimentacaoObserver observador) {
		this.observadoresMovivmentacao.add(observador);
	}
	
	private void notificarMovimentacao(Movimentacao movimentacao) {		
		this.observadoresMovivmentacao.forEach((obs) -> obs.notificarMovimentacao(movimentacao));
	}
	
	public void imprimeExtratoCompleto() {
		this.movimentacoes.stream()
			.sorted(Comparator.comparing(Movimentacao::getDataMovimentacao))
			.forEach(System.out::println);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	public void calculaRentabilidade() {
		//Do nothing
	}
	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}
	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}

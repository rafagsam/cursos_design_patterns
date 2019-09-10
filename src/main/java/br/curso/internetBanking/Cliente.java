package br.curso.internetBanking;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Cliente {	
	private String nome;
	private String sobrenome;
	private String endereco;
	private String telefone;
	
	private List<Conta> contas;
	
	private Cliente(Builder builder) {
		this.nome = builder.nome;
		this.sobrenome = builder.sobrenome;
		this.endereco = builder.endereco;
		this.telefone = builder.telefone;
		this.contas = builder.contas;
		
	}
	
	public static class Builder {
		public String nome;
		public String sobrenome;
		public String endereco;
		public String telefone;
		public List<Conta> contas = new ArrayList<>();
		
		private Consumer<Builder> consumer;
		
		public Builder set(Consumer<Builder> consumer) {
			this.consumer = consumer;
			return this;
		}
		
		
		public Builder() {
			super();
		}


		public Cliente build() {
			this.consumer.accept(this);
			return new Cliente(this);
		}
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", sobrenome=" + sobrenome + ", endereco=" + endereco + ", telefone="
				+ telefone + ", contas=" + contas + "]";
	}

}

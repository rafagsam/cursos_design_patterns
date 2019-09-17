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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

}

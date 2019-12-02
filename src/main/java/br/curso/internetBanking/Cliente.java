package br.curso.internetBanking;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nome;
	@Column
	private String sobrenome;
	@Column
	private String endereco;
	@Column
	private String telefone;
	
	public Cliente() {
		super();
	}

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	@JsonManagedReference
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}

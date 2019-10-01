package br.curso.internetBanking;

public class ClienteDTO {
	private Long id;
	private String nome;
	private String sobrenome;
	private String endereco;
	private String telefone;
	
	public ClienteDTO() {
		super();
	}
	public ClienteDTO(Cliente cliente) {
		super();
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.sobrenome = cliente.getSobrenome();
		this.endereco = cliente.getEndereco();
		this.telefone = cliente.getTelefone();
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
	
	public Cliente asCliente() {
		Cliente cliente = new Cliente.Builder().set((b) ->{
			b.endereco = this.getEndereco();
			b.nome = this.getNome();
			b.sobrenome = this.getSobrenome();
			b.telefone = this.getTelefone();
		}).build();
		cliente.setId(this.getId());
		return cliente;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}

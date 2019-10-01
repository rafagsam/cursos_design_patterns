package br.curso.internetBanking;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("v1")
public class ContaEndpoint {
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping(path="conta")
    @ApiOperation(value="Inclui uma conta.", response = ContaDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Informa que foi criado o cliente com sucesso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
	public ResponseEntity<?> adicionarConta(@RequestBody ContaDTO conta) {
        contaService.adicionarConta(conta.asConta());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
	
	@ApiOperation(value="Lista Contas de um cliente .", response = ContaDTO.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de Contas do cliente", response = ContaDTO.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
     })
	@ApiParam(value = "Identificador do cliente que se quer as contas cadastradas", name =  "idCliente")
	@GetMapping(path="conta")
    public ResponseEntity<?> pesquisarContaPorCliente(@RequestParam Long idCliente) {
        Cliente e = new Cliente();
        e.setId(idCliente);
		return new ResponseEntity<>(contaService.pesquisarContasCliente(e).stream().
        		map(ContaDTO::new)
        		.collect(Collectors.toList()),HttpStatus.OK);
    }
	
	@GetMapping(path="conta/{id}")
    public ResponseEntity<?> recuperaConta(@PathVariable("id") Long id) {
		var conta = contaService.recuperarporIdConta(id);
		if (conta.isPresent()) {
			return new ResponseEntity<>(new ContaDTO(conta.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
				
    }
	
	@DeleteMapping(path="conta/{id}/cliente/{clienteId}")
    public ResponseEntity<?> adicionarCliente(@PathVariable("id") Long id, 
    		@PathVariable("clienteId") Long idCliente) {
        var conta = contaService.recuperarporIdConta(id);
        var cliente = clienteService.recuperarporIdCliente(idCliente);
		if (cliente.isPresent() && conta.isPresent()) {
			contaService.removerConta(conta.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	@PutMapping(path="conta/{id}")
    public ResponseEntity<?> atualizarConta(@PathVariable("id") Long idConta,
    		@RequestBody ContaDTO conta) {
        var contaDB = contaService.recuperarporIdConta(idConta);
        if (contaDB.isPresent()) {
        	var contaAtualizado = conta.asConta();
        	contaAtualizado.setId(idConta);
        	contaService.atualizarConta(contaAtualizado);
        }
        return new ResponseEntity<>(HttpStatus.OK);
	}  		
	
	
	@PostMapping(path="conta/{id}/deposito")
    public ResponseEntity<?> deposito(@PathVariable("id") Long idConta,
    		BigDecimal valor) {
        
        	contaService.deposito(idConta, valor);        	
        return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(path="conta/{id}/saque")
    public ResponseEntity<?> saque(@PathVariable("id") Long idConta,
    		BigDecimal valor) {        
        
        	contaService.saque(idConta, valor);        	
        
        return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(path="conta/{id}/extrato")
    public ResponseEntity<?> saque(@PathVariable("id") Long idConta) {
        return new ResponseEntity<>(contaService.recuperarExtratoPeloIdConta(idConta),HttpStatus.OK);
	}

}

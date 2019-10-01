package br.curso.internetBanking;

import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

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



@RestController
@RequestMapping("v1")
public class ClienteEndpoint {
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping(path="cliente")
    public ResponseEntity<?> adicionarCliente(@RequestBody ClienteDTO cliente) {
        clienteService.adicionarCliente(cliente.asCliente());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
	
	@GetMapping(path="cliente")
    public ResponseEntity<?> pesquisarCliente(@RequestParam String nome) {
        return new ResponseEntity<>(clienteService.pesquisarCliente(nome).stream().
        		map(ClienteDTO::new)
        		.collect(Collectors.toList()),HttpStatus.OK);
    }
	
	@GetMapping(path="cliente/{id}")
    public ResponseEntity<?> pesquisarCliente(@PathVariable Long id) {
		var cliente = clienteService.recuperarporIdCliente(id);
		if (cliente.isPresent()) {
			return new ResponseEntity<>(new ClienteDTO(cliente.get()),HttpStatus.OK);	
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	@DeleteMapping(path="cliente/{id}")
    public ResponseEntity<?> adicionarCliente(@PathParam("id") Long idCliente) {
        var cliente = clienteService.recuperarporIdCliente(idCliente);
		if (cliente.isPresent()) {
			clienteService.removerCliente(cliente.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	@PutMapping(path="cliente/{id}")
    public ResponseEntity<?> atualizarCliente( @PathParam("id") Long idCliente,
    		@RequestBody ClienteDTO cliente) {
        var clienteDb = clienteService.recuperarporIdCliente(idCliente);
        if (clienteDb.isPresent()) {
        	var clienteAtualizado = cliente.asCliente();
        	clienteAtualizado.setId(idCliente);
        	clienteService.atualizarCliente(clienteAtualizado);
        }
        
        return new ResponseEntity<>(HttpStatus.OK);
	}  		
	

}

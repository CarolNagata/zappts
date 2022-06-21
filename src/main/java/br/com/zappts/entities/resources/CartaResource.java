package br.com.zappts.entities.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.zappts.entities.Carta;
import br.com.zappts.entities.Jogador;
import br.com.zappts.repositories.CartaRepository;
import br.com.zappts.services.CartaService;

@RestController
@RequestMapping(value = "/carta")
public class CartaResource {

	@Autowired
	private CartaService service;

	@Autowired
	private CartaRepository cartaRepository;

	@GetMapping
	public ResponseEntity<List<Carta>> findAll() {
		List<Carta> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/buscarNomeCarta")
	public ResponseEntity<List<Jogador>> findByNomeCartaContaining(@PathVariable String nomeCarta) {
		List<Jogador> list = service.findByNomeCartaContaining(nomeCarta);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<Carta> insert(@RequestBody Carta obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCarta())
				.toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Carta> update(@PathVariable Integer id, @RequestBody Carta obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
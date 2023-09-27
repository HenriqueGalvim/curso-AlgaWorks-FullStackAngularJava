package com.example.algamoney.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.event.ResourceCreateEvent;
import com.example.algamoney.model.Categoria;
import com.example.algamoney.repository.CategoriaRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")

public class CategoriaResource {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Categoria> listar(){
		return categoriaRepository.findAll();
		// ResponseEntity<?>
		// List<Categoria> categorias = categoriaRepository.findAll();
		// return !categorias.isEmpty() ? ResponseEntity.ok(categorias): ResponseEntity.noContent().build();
		// Se a minha lista não estiver vazia, eu respondo com ok, passando as categorias, se não, responde not found, o 404, e o build é padrão
		// o noContent ele da o 204, quer dizer que deu certo a requisição porem não tem nada para mostrar
	}
	
	@PostMapping
	// @ResponseStatus(HttpStatus.CREATED), foi tirada pois já estamos definindo no response entity e no return
	// Ao terminar a execução deste metodo, eu quero que ele responda um response status created
	 
	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		
		publisher.publishEvent(new ResourceCreateEvent(categoriaSalva, response, categoriaSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
		
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Categoria> categoria = this.categoriaRepository.findById(codigo);
		
		return categoria.isPresent() ? 
		        ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();
		}
	// Neste caso utilizamos o método isPresent, que nada mais é que uma comparação “obj != null”, e finalizamos com um ternário
	
}

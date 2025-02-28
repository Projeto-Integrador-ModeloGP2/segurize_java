package com.generation.crm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.crm.model.Plano;
import com.generation.crm.repository.PlanoRepository;
import com.generation.crm.repository.SeguradoraRepository;
import com.generation.crm.service.PlanoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/planos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PlanoController {
	@Autowired
	private PlanoRepository planoRepository;
	
	@Autowired
    private PlanoService planoService;
	
	@Autowired
	private SeguradoraRepository seguradoraRepository;
	
	@GetMapping
	public ResponseEntity<List<Plano>> getAll() {
		return ResponseEntity.ok(planoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Plano> getById(@PathVariable Long id) {
		return planoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse (ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Plano>> getByTitulo(@PathVariable String nome) {
		return ResponseEntity.ok(planoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Plano> post(@Valid @RequestBody Plano plano) {
		if(seguradoraRepository.existsById(plano.getSeguradora().getId()))
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(planoRepository.save(plano));
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seguradora não cadastrada.", null);
	}
	
	@PutMapping
	public ResponseEntity<Plano> put(@Valid @RequestBody Plano plano) {
		if(planoRepository.existsById(plano.getId())) {
			
			if (seguradoraRepository.existsById(plano.getSeguradora().getId()))
				return ResponseEntity.status(HttpStatus.OK)
						.body(planoRepository.save(plano));
						
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seguradora não cadastrada.", null);
			
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Plano> plano = planoRepository.findById(id);
		
		if(plano.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		planoRepository.deleteById(id);
	}
	
	// Endpoint para ativar um plano
    @PutMapping("/{id}/ativar")
    public ResponseEntity<Plano> ativarPlano(@PathVariable Long id) {
        Plano plano = planoService.ativarPlano(id);
        return ResponseEntity.ok(plano);
    }

    // Endpoint para desativar um plano
    @PutMapping("/{id}/desativar")
    public ResponseEntity<Plano> desativarPlano(@PathVariable Long id) {
        Plano plano = planoService.desativarPlano(id);
        return ResponseEntity.ok(plano);
    }
	
}

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

import com.generation.crm.model.Seguradora;
import com.generation.crm.repository.SeguradoraRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/seguradoras")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SeguradoraController {
	
	@Autowired
    private SeguradoraRepository seguradoraRepository;
    
    @GetMapping
    public ResponseEntity<List<Seguradora>> getAll(){
        return ResponseEntity.ok(seguradoraRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Seguradora> getById(@PathVariable Long id){
        return seguradoraRepository.findById(id)
            .map(resposta -> ResponseEntity.ok(resposta))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Seguradora>> getByTitle(@PathVariable 
    String nome){
        return ResponseEntity.ok(seguradoraRepository
            .findAllByNomeContainingIgnoreCase(nome));
    }
    
    @PostMapping
    public ResponseEntity<Seguradora> post(@Valid @RequestBody Seguradora seguradora){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(seguradoraRepository.save(seguradora));
    }
    
    @PutMapping
    public ResponseEntity<Seguradora> put(@Valid @RequestBody Seguradora seguradora){
        return seguradoraRepository.findById(seguradora.getId())
            .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
            .body(seguradoraRepository.save(seguradora)))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Seguradora> seguradora = seguradoraRepository.findById(id);
        
        if(seguradora.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        
        seguradoraRepository.deleteById(id);              
    }

}

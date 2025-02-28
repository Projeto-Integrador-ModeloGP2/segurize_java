package com.generation.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.crm.model.Plano;
import com.generation.crm.repository.PlanoRepository;

@Service
public class PlanoService {

    @Autowired
    private PlanoRepository planoRepository;

    // Método para ativar o plano
    public Plano ativarPlano(Long id) {
        Plano plano = planoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));
        plano.ativarStatus(); // Método da entidade para ativar o plano
        return planoRepository.save(plano); // Salva a alteração no banco de dados
    }

    // Método para desativar o plano
    public Plano desativarPlano(Long id) {
        Plano plano = planoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));
        plano.desativarStatus(); // Método da entidade para desativar o plano
        return planoRepository.save(plano); // Salva a alteração no banco de dados
    }
}
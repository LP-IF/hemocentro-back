package com.example.hemocentroapi.service;

import com.example.hemocentroapi.exception.RegraNegocioException;
import com.example.hemocentroapi.model.entity.Endereco;
import com.example.hemocentroapi.model.repository.EnderecoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class EnderecoService {
    
    private EnderecoRepository repository;
    
    public EnderecoService(EnderecoRepository repository) {this.repository = repository;}

    @Transactional
    public Endereco salvar(Endereco endereco) {
        return repository.save(endereco);
    }

    @Transactional
    public void excluir(Endereco endereco) {
        Objects.requireNonNull(endereco.getId());
        repository.delete(endereco);
    }

}

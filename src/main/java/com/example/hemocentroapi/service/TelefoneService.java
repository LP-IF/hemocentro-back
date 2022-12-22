package com.example.hemocentroapi.service;

import com.example.hemocentroapi.model.entity.Pessoa;
import com.example.hemocentroapi.model.entity.Telefone;
import com.example.hemocentroapi.model.repository.TelefoneRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TelefoneService {
    private TelefoneRepository repository;
    private Pessoa pessoa;
    private Telefone telefone;

    public TelefoneService(TelefoneRepository repository){this.repository = repository;}

    public Optional<Telefone> getTelefoneByPessoaId(Long id){
        return repository.findByPessoaId(telefone.getPessoa().getId(id));//nao sei fazer
    }
}

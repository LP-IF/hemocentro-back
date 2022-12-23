package com.example.hemocentroapi.service;

import com.example.hemocentroapi.exception.RegraNegocioException;
import com.example.hemocentroapi.model.entity.Administrador;
import com.example.hemocentroapi.model.entity.Telefone;
import com.example.hemocentroapi.model.repository.TelefoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Objects;


@Service
public class TelefoneService {
    private TelefoneRepository repository;


    public TelefoneService(TelefoneRepository repository){this.repository = repository;}

    public List<Telefone> getTelefonesByPessoaId(Long idPessoa) {
        return repository.getTelefonesByPessoaId(idPessoa);
    }

    @Transactional
    public Telefone salvar(Telefone telefone) {
        validar(telefone);
        return repository.save(telefone);
    }

    @Transactional
    public void excluir(Telefone telefone) {
        Objects.requireNonNull(telefone.getId());
        repository.delete(telefone);
    }

    public void validar(Telefone telefone) {
        if (telefone.getNumero() == null || telefone.getNumero().trim().equals("")) {
            throw new RegraNegocioException("Número inválido");
        }
    }


}

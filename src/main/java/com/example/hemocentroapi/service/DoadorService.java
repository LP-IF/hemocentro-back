package com.example.hemocentroapi.service;

import com.example.hemocentroapi.exception.RegraNegocioException;
import com.example.hemocentroapi.model.entity.Doador;
import com.example.hemocentroapi.model.repository.DoadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DoadorService {

    private DoadorRepository repository;

    public DoadorService(DoadorRepository repository){
        this.repository = repository;
    }

    public List<Doador> getDoadores(){
        return repository.findAll();
    }

    public Optional<Doador> getDoadorByCpf(String cpf) {
        return Optional.ofNullable(repository.findByCpf(cpf));
    }

    public Optional<Doador> getDoadorById(Long id) { return repository.findById(id);}
    @Transactional
    public Doador salvar(Doador doador) {
        validar(doador);
        return repository.save(doador);
    }

    @Transactional
    public void excluir(Doador doador) {
        Objects.requireNonNull(doador.getId());
        repository.delete(doador);
    }

    public void validar(Doador doador) {
        if (doador.getCpf() == null || doador.getCpf().trim().equals("")) {
            throw new RegraNegocioException("CPF inválido");
        }
    }

}

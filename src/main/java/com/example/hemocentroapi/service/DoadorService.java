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
        System.out.println("f1");
        System.out.println(doador);
        validar(doador);
        System.out.println("f2");
        return repository.save(doador);
    }
    @Transactional
    public Doador atualizar(Doador doador) {
        Optional<Doador> optionalDoador = Optional.ofNullable(repository.findByCpf(doador.getCpf()));
        if (optionalDoador.isPresent() && !optionalDoador.get().getId().equals(doador.getId())) {
            throw new RegraNegocioException("Já existe um doador com esse CPF");
        }
        return repository.save(doador);
    }

    @Transactional
    public void excluir(Doador doador) {
        Objects.requireNonNull(doador.getId());
        repository.delete(doador);
    }

    public void validar(Doador doador) {
        System.out.println("a1");
        if (doador.getCpf() == null || doador.getCpf().trim().equals("")){
            System.out.println("Q");
            throw new RegraNegocioException("CPF inválido");
        }
        System.out.println("a2");
        if(repository.existsByCpf(doador.getCpf())){
            System.out.println("Q1");
            throw new IllegalArgumentException("Já existe um doador com este CPF");
        }
    }

}

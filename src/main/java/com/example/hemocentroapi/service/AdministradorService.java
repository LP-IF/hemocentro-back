package com.example.hemocentroapi.service;

import com.example.hemocentroapi.model.entity.Administrador;
import com.example.hemocentroapi.model.repository.AdministradorRepository;
import com.example.hemocentroapi.exception.RegraNegocioException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdministradorService {

    private AdministradorRepository repository;

    public AdministradorService(AdministradorRepository repository) {
        this.repository = repository;
    }

    public Optional<Administrador> getAdministradorByEmail(String email) {
        return Optional.ofNullable(repository.findByEmail(email));
    }

    public Optional<Administrador> getAdministradorById(Long id){
        return repository.findById(id);
    }

//    public Optional<Administrador> getHemocentroByAdministrador

    public List<Administrador> getAdministradores(){
       return repository.findAll();
    }

//    public Optional<Administrador> verificaSeJáExiste

    @Transactional
    public Administrador salvar(Administrador administrador) {
        validar(administrador);
        return repository.save(administrador);
    }

    @Transactional
    public void excluir(Administrador administrador) {
        Objects.requireNonNull(administrador.getId());
        repository.delete(administrador);
    }

    public void validar(Administrador administrador) {
        if (administrador.getEmail() == null || administrador.getEmail().trim().equals("")) {
            throw new RegraNegocioException("Email inválido");
        }
    }
}

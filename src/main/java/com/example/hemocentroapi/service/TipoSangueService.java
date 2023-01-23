package com.example.hemocentroapi.service;

import com.example.hemocentroapi.exception.RegraNegocioException;
import com.example.hemocentroapi.model.entity.TipoSangue;
import com.example.hemocentroapi.model.repository.TipoSangueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TipoSangueService {

    private TipoSangueRepository repository;

    public TipoSangueService(TipoSangueRepository repository) {this.repository = repository;}

    public List<TipoSangue> getTiposSangue(){
        return repository.findAll();
    }

    public Optional<TipoSangue> getTipoSangueById(Long id){
        return repository.findById(id);
    }


    //fazer metodo e logica para retornar a quantidade de cada tipo de sangue no banco de sangue
    //FAZER METODO PARA RETORNAR QUAIS TIPOS O TIPO SANGUINEO PODE DOAR E DE QUEM PODE RECEBER

    @Transactional
    public TipoSangue salvar(TipoSangue tipoSangue) {
        validar(tipoSangue);
        return repository.save(tipoSangue);
    }

    @Transactional
    public void excluir(TipoSangue tipoSangue) {
        Objects.requireNonNull(tipoSangue.getId());
        repository.delete(tipoSangue);
    }

    public void validar(TipoSangue tipoSangue) {
        if (tipoSangue.getTipo() == null || tipoSangue.getTipo().trim().equals("")) {
            throw new RegraNegocioException("Tipo inválido");
        }
        if (tipoSangue.getFatorRh() == null || tipoSangue.getFatorRh().trim().equals("")) {
            throw new RegraNegocioException("Fator RH inválido");
        }
    }

}

package com.example.hemocentroapi.model.repository;

import com.example.hemocentroapi.model.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
}

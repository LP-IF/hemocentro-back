package com.example.hemocentroapi.model.repository;

import com.example.hemocentroapi.model.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TelefoneRepository extends JpaRepository<Telefone, Long>{

    @Query(value = "SELECT * FROM telefone INNER JOIN pessoa ON telefone.id_pessoa = pessoa.id WHERE pessoa.id = :fieldValue", nativeQuery = true)
    List<Telefone> getTelefonesByPessoaId(@Param("fieldValue") Long fieldValue);


}

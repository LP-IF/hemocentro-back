package com.example.hemocentroapi.model.repository;

import com.example.hemocentroapi.model.entity.TipoSangue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TipoSangueRepository extends JpaRepository<TipoSangue, Long>{

    @Query(value = "SELECT tipo, positivo, quantidade FROM tipoSangue WHERE tipoSangue.id = :fieldValue", nativeQuery = true)
    List<TipoSangue> getQuantidadeById(@Param("fieldValue") Long fieldValue);

}

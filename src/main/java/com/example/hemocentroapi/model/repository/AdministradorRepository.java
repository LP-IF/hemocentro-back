package com.example.hemocentroapi.model.repository;

import com.example.hemocentroapi.model.entity.Administrador;
import com.example.hemocentroapi.model.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdministradorRepository extends JpaRepository<Administrador, Long>{
    Administrador findByEmail(String email);
//    @Query(value = "SELECT email FROM pessoa WHERE email = :fieldValue", nativeQuery = true)
//    List<Administrador> getTelefonesByPessoaId(@Param("fieldValue") String fieldValue);
//    Hemocentro findByAdministrador(Administrador administrador);
}
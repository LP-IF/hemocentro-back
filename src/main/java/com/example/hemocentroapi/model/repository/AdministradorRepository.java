package com.example.hemocentroapi.model.repository;

import com.example.hemocentroapi.model.entity.Administrador;
import com.example.hemocentroapi.model.entity.Hemocentro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Administrador, Long>{
    Administrador findByEmail(String email);

//    Hemocentro findByAdministrador(Administrador administrador);
}
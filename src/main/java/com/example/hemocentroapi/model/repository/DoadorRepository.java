package com.example.hemocentroapi.model.repository;

import com.example.hemocentroapi.model.entity.Doador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoadorRepository extends JpaRepository<Doador, Long>{
}

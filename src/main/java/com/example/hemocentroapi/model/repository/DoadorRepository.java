package com.example.hemocentroapi.model.repository;

import com.example.hemocentroapi.model.entity.Doador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoadorRepository extends JpaRepository<Doador, Long>{
    Doador findByCpf(String cpf);

    //    @Query(value = "SELECT email FROM pessoa WHERE email = :fieldValue", nativeQuery = true)
//    List<Administrador> getTelefonesByPessoaId(@Param("fieldValue") String fieldValue);
//    Hemocentro findByAdministrador(Administrador administrador);
}

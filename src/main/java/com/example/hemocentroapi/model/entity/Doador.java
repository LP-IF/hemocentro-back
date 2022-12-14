package com.example.hemocentroapi.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doador extends Pessoa{
    private String dataNascimento;
    private String cpf;

    @ManyToOne
    private TipoSangue tipoSangue;
}

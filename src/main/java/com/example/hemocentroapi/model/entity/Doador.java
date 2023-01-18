package com.example.hemocentroapi.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
    private Integer quantidadeDoacoes = 0;

    @OneToOne
    private TipoSangue tipoSangue;

    @ManyToOne
    private Hemocentro hemocentro;
}

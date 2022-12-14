package com.example.hemocentroapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoSangue {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String tipo;
    private Boolean positivo;

    @OneToMany
    private TipoSangue tipoSangueDeQuemRecebe;

    @OneToMany
    private TipoSangue tipoSangueParaQuemDoa;
}

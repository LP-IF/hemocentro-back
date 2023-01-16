package com.example.hemocentroapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private String fatorRh;
    private Integer quantidade;

    @ManyToMany
    @JoinTable(name = "de_quem_recebe",
            joinColumns = @JoinColumn(name = "tipo_sangue_id"),
            inverseJoinColumns = @JoinColumn(name = "recebe_id"))
    private List<TipoSangue> deQuemRecebe;

    @ManyToMany
    @JoinTable(name = "para_quem_doa",
            joinColumns = @JoinColumn(name = "tipo_sangue_id"),
            inverseJoinColumns = @JoinColumn(name = "doa_id"))
    private List<TipoSangue> paraQuemDoa;
}

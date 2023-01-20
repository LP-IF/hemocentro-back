package com.example.hemocentroapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"deQuemRecebe", "paraQuemDoa"})
public class TipoSangue {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String tipo;
    private String fatorRh;
    private Integer quantidade;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "de_quem_recebe",
            joinColumns = @JoinColumn(name = "tipo_sangue_id"),
            inverseJoinColumns = @JoinColumn(name = "recebe_id"))
    private List<TipoSangue> deQuemRecebe;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "para_quem_doa",
            joinColumns = @JoinColumn(name = "tipo_sangue_id"),
            inverseJoinColumns = @JoinColumn(name = "doa_id"))
    private List<TipoSangue> paraQuemDoa;
}

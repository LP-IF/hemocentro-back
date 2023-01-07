package com.example.hemocentroapi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Administrador extends Pessoa{

    @Column(unique = true)
    private String email;

    @ManyToOne
    private Hemocentro hemocentro;
}

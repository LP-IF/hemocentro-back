package com.example.hemocentroapi.model.entity;

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
    private String email;

    @ManyToOne
    private Hemocentro hemocentro;
}

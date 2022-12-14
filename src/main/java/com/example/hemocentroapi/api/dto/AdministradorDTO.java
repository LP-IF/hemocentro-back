package com.example.hemocentroapi.api.dto;

import com.example.hemocentroapi.model.entity.Administrador;
import org.modelmapper.ModelMapper;

public class AdministradorDTO {

    private Long id;
    private String email;
    private String nome;

    public static AdministradorDTO create(Administrador administrador){
        ModelMapper modelMapper = new ModelMapper();
        AdministradorDTO dto = modelMapper.map(administrador, AdministradorDTO.class);
        dto.id = administrador.getId();
        dto.nome = administrador.getNome();
        return dto;
    }
}

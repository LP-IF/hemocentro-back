package com.example.hemocentroapi.api.dto;

import com.example.hemocentroapi.model.entity.Administrador;
import org.modelmapper.ModelMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdministradorDTO {

    private Long id;
    private String nome;
    private String email;

    public static AdministradorDTO create(Administrador administrador){
        ModelMapper modelMapper = new ModelMapper();
        AdministradorDTO dto = modelMapper.map(administrador, AdministradorDTO.class);
        return dto;
    }
}

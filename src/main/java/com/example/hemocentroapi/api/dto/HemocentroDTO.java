package com.example.hemocentroapi.api.dto;

import com.example.hemocentroapi.model.entity.Administrador;
import com.example.hemocentroapi.model.entity.Doador;
import com.example.hemocentroapi.model.entity.Hemocentro;
import org.modelmapper.ModelMapper;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HemocentroDTO {
    
    private Long id;
    private List<Administrador> administradores;
    private List<Doador> doadores;

    public static HemocentroDTO create(Hemocentro hemocentro){
        ModelMapper modelMapper = new ModelMapper();
        HemocentroDTO dto = modelMapper.map(hemocentro, HemocentroDTO.class);
//      dto.administradores = hemocentro.getAdministrador().getNome(); ver com o prof como faz
//      dto.doadores = hemocentro.getDoador().getNome(); ver com o prof como faz
        return dto;
    }
}

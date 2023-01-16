package com.example.hemocentroapi.api.dto;


import com.example.hemocentroapi.model.entity.TipoSangue;
import org.modelmapper.ModelMapper;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoSangueDTO {
    private Long id;
    private String tipo;
    private Boolean positivo;
    private Integer quantidade;

    public static TipoSangueDTO create(TipoSangue TipoSangue){
        ModelMapper modelMapper = new ModelMapper();
        TipoSangueDTO dto = modelMapper.map(TipoSangue, TipoSangueDTO.class);
//        dto.deQuemRecebe = TipoSangue.getTipoSangueDeQuemRecebe(); //perguntar ao prof como faz
//        dto.paraQuemDoa = TipoSangue.getTipoSangueParaQuemDoa(); //perguntar ao prof como faz
        return dto;
    }
}

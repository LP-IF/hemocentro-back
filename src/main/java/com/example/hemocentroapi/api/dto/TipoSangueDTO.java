package com.example.hemocentroapi.api.dto;


import com.example.hemocentroapi.model.entity.TipoSangue;
import org.modelmapper.ModelMapper;

import java.util.List;

public class TipoSangueDTO {
    private Long id;
    private String tipo;
    private Boolean positivo;
    private Integer quantidade;
    private List<TipoSangue> deQuemRecebe;
    private List<TipoSangue> paraQuemDoa;

    public static TipoSangueDTO create(TipoSangue TipoSangue){
        ModelMapper modelMapper = new ModelMapper();
        TipoSangueDTO dto = modelMapper.map(TipoSangue, TipoSangueDTO.class);
//        dto.deQuemRecebe = TipoSangue.getTipoSangueDeQuemRecebe(); //perguntar ao prof como faz
//        dto.paraQuemDoa = TipoSangue.getTipoSangueParaQuemDoa(); //perguntar ao prof como faz
        return dto;
    }
}

package com.example.hemocentroapi.api.dto;

import com.example.hemocentroapi.model.entity.Endereco;
import org.modelmapper.ModelMapper;

public class EnderecoDTO {
    private Long id;
    private String logradouro;
    private String numero;
    private String cep;

    public static EnderecoDTO create(Endereco endereco){
        ModelMapper modelMapper = new ModelMapper();
        EnderecoDTO dto = modelMapper.map(endereco, EnderecoDTO.class);
        return dto;
    }

}

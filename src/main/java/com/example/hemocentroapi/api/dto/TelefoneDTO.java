package com.example.hemocentroapi.api.dto;

import com.example.hemocentroapi.model.entity.Telefone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneDTO {

    private Long Id;
    private String numero;
    private String tipo;

    public static TelefoneDTO create(Telefone telefone){
        ModelMapper modelMapper = new ModelMapper();
        TelefoneDTO dto = modelMapper.map(telefone, TelefoneDTO.class);
        return dto;
    }
}

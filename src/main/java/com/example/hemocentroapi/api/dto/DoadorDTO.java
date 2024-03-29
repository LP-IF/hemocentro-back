package com.example.hemocentroapi.api.dto;


import com.example.hemocentroapi.model.entity.Doador;
import com.example.hemocentroapi.model.entity.Endereco;
import com.example.hemocentroapi.model.entity.Hemocentro;
import com.example.hemocentroapi.model.entity.TipoSangue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class DoadorDTO {
    private Long id;
    private String dataNascimento;
    private String cpf;
    private String nome;
    private Integer quantidadeDoacoes;
    private Long tipoSangueId;
    private Endereco endereco;

    public static DoadorDTO create(Doador doador){
        ModelMapper modelMapper = new ModelMapper();
        DoadorDTO dto = modelMapper.map(doador, DoadorDTO.class);
        dto.tipoSangueId = doador.getTipoSangue().getId();
        dto.endereco = doador.getEndereco();
        return dto;
    }
}

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
    private TipoSangue tipoSanguineo;
    private Endereco endereco;
    private Hemocentro hemocentro;

    public static DoadorDTO create(Doador doador){
        ModelMapper modelMapper = new ModelMapper();
        DoadorDTO dto = modelMapper.map(doador, DoadorDTO.class);
        dto.tipoSanguineo = doador.getTipoSangue();
        dto.endereco = doador.getEndereco();
        dto.hemocentro = doador.getHemocentro();
        return dto;
    }
}

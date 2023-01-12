package com.example.hemocentroapi.api.controller;

import com.example.hemocentroapi.api.dto.AdministradorDTO;
import com.example.hemocentroapi.api.dto.EnderecoDTO;
import com.example.hemocentroapi.exception.RegraNegocioException;
import com.example.hemocentroapi.model.entity.Administrador;
import com.example.hemocentroapi.model.entity.Endereco;
import com.example.hemocentroapi.service.AdministradorService;
import com.example.hemocentroapi.service.EnderecoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/enderecos")
@RequiredArgsConstructor
public class EnderecoController {
    private final EnderecoService service;


    @PostMapping()
    @ApiOperation("Salvar um endereço")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Endereço criado com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao salvar endereço")})
    public ResponseEntity post(EnderecoDTO dto) {
        try {
            Endereco endereco = converter(dto);
            endereco = service.salvar(endereco);
            return new ResponseEntity(endereco, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    @ApiOperation("Alterar um endereço")
    @ApiResponses({
            @ApiResponse(code = 202, message = "Endereço alterado com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao alterar endereço")})
    public ResponseEntity atualizar(@PathVariable("id") @ApiParam("id do Endereço") Long id, EnderecoDTO dto) {
            return new ResponseEntity("Endereço não encontrado", HttpStatus.NOT_FOUND);
        }

    @DeleteMapping("{id}")
    @ApiOperation("Apagar um endereço")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endereço excluido com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao excluir endereço"),
            @ApiResponse(code = 404, message = "Endereço não encontrado")})
    public ResponseEntity excluir(@PathVariable("id") @ApiParam("id do Endereço") Long id) {
            return new ResponseEntity("Endereço não encontrado", HttpStatus.NOT_FOUND);
        }


    public Endereco converter(EnderecoDTO dto) {
        Endereco endereco = new Endereco();
        endereco.setId(dto.getId());
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setNumero(dto.getNumero());
        endereco.setCep(dto.getCep());
        return endereco;
    }
}

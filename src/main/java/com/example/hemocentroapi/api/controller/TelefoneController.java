package com.example.hemocentroapi.api.controller;

import com.example.hemocentroapi.api.dto.AdministradorDTO;
import com.example.hemocentroapi.api.dto.EnderecoDTO;
import com.example.hemocentroapi.api.dto.TelefoneDTO;
import com.example.hemocentroapi.exception.RegraNegocioException;
import com.example.hemocentroapi.model.entity.Administrador;
import com.example.hemocentroapi.model.entity.Endereco;
import com.example.hemocentroapi.model.entity.Telefone;
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
    @ApiOperation("Salvar um telefone")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Telefone criado com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao salvar telefone")})
    public ResponseEntity post(TelefoneDTO dto) {
        try {
            Telefone telefone = converter(dto);
            telefone = service.salvar(telefone);
            return new ResponseEntity(telefone, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    @ApiOperation("Alterar um telefone")
    @ApiResponses({
            @ApiResponse(code = 202, message = "Telefone alterado com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao alterar telefone")})
    public ResponseEntity atualizar(@PathVariable("id") @ApiParam("id do Telefone") Long id, TelefoneDTO dto) {
        return new ResponseEntity("Telefone não encontrado", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Apagar um telefone")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Telefone excluido com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao excluir telefone"),
            @ApiResponse(code = 404, message = "Telefone não encontrado")})
    public ResponseEntity excluir(@PathVariable("id") @ApiParam("id do Telefone") Long id) {
        return new ResponseEntity("Telefonbe não encontrado", HttpStatus.NOT_FOUND);
    }

    public Telefone converter(TelefoneDTO dto) {
        Telefone telefone = new Telefone();
        telefone.setId(dto.getId());
        telefone.setNumero(dto.getNumero());
        telefone.setTipo(dto.getTipo());
        return telefone;
    }
}

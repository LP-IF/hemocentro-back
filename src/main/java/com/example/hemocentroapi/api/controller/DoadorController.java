package com.example.hemocentroapi.api.controller;

import com.example.hemocentroapi.api.dto.DoadorDTO;
import com.example.hemocentroapi.exception.RegraNegocioException;
import com.example.hemocentroapi.model.entity.Doador;
import com.example.hemocentroapi.model.entity.TipoSangue;
import com.example.hemocentroapi.service.DoadorService;
import com.example.hemocentroapi.service.TipoSangueService;
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
@RequestMapping("/api/doadores")
@RequiredArgsConstructor
public class DoadorController {
    private final DoadorService service;
    private final TipoSangueService tipoSangueService;

    @GetMapping()
    @ApiOperation("Obter todos os doadores")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Doador encontrado")})
    public ResponseEntity get() {
        List<Doador> doadores = service.getDoadores();
        return ResponseEntity.ok(doadores.stream().map(DoadorDTO::create).collect(Collectors.toList()));
    }

    

    @GetMapping("/{cpf}")
    @ApiOperation("Obter detalhes de um doador")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Doador encontrado"),
            @ApiResponse(code = 404, message = "Doador não encontrado")})
    public ResponseEntity get(@PathVariable("cpf") @ApiParam("cpf do Doador") String cpf) {
        Optional<Doador> doador = service.getDoadorByCpf(cpf);
        if (!doador.isPresent()) {
            return new ResponseEntity("Doador não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(doador.map(DoadorDTO::create));
    }


    @PatchMapping("{cpf}")
    @ApiOperation("Incrementar em 1 a quantidade de um tipoSangue")
    @ApiResponses({
            @ApiResponse(code = 200, message = "quantidade de tipoSangue alterada com sucesso"),
            @ApiResponse(code = 404, message = "Tipo de Sangue não encontrado com id fornecido"),
            @ApiResponse(code = 400, message = "Erro ao alterar a quantidade de tipoSangue")
    })
    public ResponseEntity incrementarQuantidade(@PathVariable String cpf) {
        try {
            Optional<Doador> optionalDoador = service.getDoadorByCpf(cpf);
            if (!optionalDoador.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            Doador doador = optionalDoador.get();
            doador.setQuantidadeDoacoes(doador.getQuantidadeDoacoes() + 1);
            Doador atualizado = service.salvar(doador);
            return ResponseEntity.ok(atualizado);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping()
    @ApiOperation("Salvar um doador")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Doador criado com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao salvar doador")})
    public ResponseEntity post(@RequestBody DoadorDTO dto) {
        try {
            Doador doador = converter(dto);
            doador = service.salvar(doador);
            return new ResponseEntity(doador, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    @ApiOperation("Alterar um doador")
    @ApiResponses({
            @ApiResponse(code = 202, message = "Doador alterado com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao alterar doador")})
    public ResponseEntity atualizar(@PathVariable("id") @ApiParam("id do Doador") Long id, DoadorDTO dto) {
        if (!service.getDoadorById(id).isPresent()) {
            return new ResponseEntity("Doador não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Doador doador = converter(dto);
            doador.setId(doador.getId());
            service.salvar(doador);
            return ResponseEntity.ok(doador);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ApiOperation("Apagar um doador")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Doador excluido com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao excluir doador"),
            @ApiResponse(code = 404, message = "Doador não encontrado")})
    public ResponseEntity excluir(@PathVariable("id") @ApiParam("id do Doador") Long id) {
        Optional<Doador> doador = service.getDoadorById(id);
        if (!doador.isPresent()) {
            return new ResponseEntity("Doador não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(doador.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Doador converter(DoadorDTO dto) {
        Doador doador = new Doador();
        doador.setId(dto.getId());
        doador.setNome(dto.getNome());
        doador.setEndereco(dto.getEndereco());
        doador.setDataNascimento(dto.getDataNascimento());
        doador.setCpf(dto.getCpf());
        if (dto.getTipoSangueId() != null) {
            Optional<TipoSangue> tipoSangue = tipoSangueService.getTipoSangueById((dto.getTipoSangueId()));
            if (!tipoSangue.isPresent()) {
                doador.setTipoSangue(null);
            } else {
                doador.setTipoSangue(tipoSangue.get());
            }
        }
        return doador;
    }
}

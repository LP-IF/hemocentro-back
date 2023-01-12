package com.example.hemocentroapi.api.controller;

import com.example.hemocentroapi.api.dto.DoadorDTO;
import com.example.hemocentroapi.exception.RegraNegocioException;
import com.example.hemocentroapi.model.entity.Doador;
import com.example.hemocentroapi.service.DoadorService;
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

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/doadores")
@RequiredArgsConstructor
public class DoadorController {
    private final DoadorService service;

    @GetMapping()
    @ApiOperation("Obter todos os doadores")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Doador encontrado")})
    public ResponseEntity get() {
        List<Doador> doadores = service.getDoadores();
        return ResponseEntity.ok(doadores.stream().map(DoadorDTO::create).collect(Collectors.toList()));
    }

//    @GetMapping("/{id}")
//    @ApiOperation("Obter detalhes de um doador")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Doador encontrado"),
//            @ApiResponse(code = 404, message = "Doador não encontrado")})
//    public ResponseEntity get(@PathVariable("id") @ApiParam("id do Doador") Long id) {
//        Optional<Doador> doador = service.getDoadorById(id);
//        if (!doador.isPresent()) {
//            return new ResponseEntity("Doador não encontrado", HttpStatus.NOT_FOUND);
//        }
//        return ResponseEntity.ok(doador.map(DoadorDTO::create));
//    }

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


    @PostMapping()
    @ApiOperation("Salvar um doador")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Doador criado com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao salvar doador")})
    public ResponseEntity post(DoadorDTO dto) {
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
        doador.setDataNascimento(dto.getDataNascimento());
        doador.setCpf(dto.getCpf());
        doador.setTipoSangue(dto.getTipoSanguineo());
        return doador;
    }
}

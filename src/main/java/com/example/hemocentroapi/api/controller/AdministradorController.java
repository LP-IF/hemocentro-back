package com.example.hemocentroapi.api.controller;

import com.example.hemocentroapi.api.dto.AdministradorDTO;
import com.example.hemocentroapi.exception.RegraNegocioException;
import com.example.hemocentroapi.model.entity.Administrador;
import com.example.hemocentroapi.service.AdministradorService;
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
@RequestMapping("/api/administradores")
@RequiredArgsConstructor
public class AdministradorController {
    private final AdministradorService service;

    @GetMapping()
    @ApiOperation("Obter todos os administradores")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Administrador encontrado")})
    public ResponseEntity get() {
        List<Administrador> administradores = service.getAdministradores();
        return ResponseEntity.ok(administradores.stream().map(AdministradorDTO::create).collect(Collectors.toList()));
    }

//    @GetMapping("/{id}")
//    @ApiOperation("Obter detalhes de um administrador")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Administrador encontrado"),
//            @ApiResponse(code = 404, message = "Administrador não encontrado")})
//    public ResponseEntity get(@PathVariable("id") @ApiParam("id do Administrador") Long id) {
//        Optional<Administrador> administrador = service.getAdministradorById(id);
//        if (!administrador.isPresent()) {
//            return new ResponseEntity("Administrador não encontrado", HttpStatus.NOT_FOUND);
//        }
//        return ResponseEntity.ok(administrador.map(AdministradorDTO::create));
//    }

    @GetMapping("/{email}")
    @ApiOperation("Obter detalhes de um administrador")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Administrador encontrado"),
            @ApiResponse(code = 404, message = "Administrador não encontrado")})
    public ResponseEntity get(@PathVariable("email") @ApiParam("email do Administrador") String email) {
        Optional<Administrador> administrador = service.getAdministradorByEmail(email);
        if (!administrador.isPresent()) {
            return new ResponseEntity("Administrador não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(administrador.map(AdministradorDTO::create));
    }

    @PostMapping()
    @ApiOperation("Salvar um administrador")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Administrador criado com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao salvar administrador")})
    public ResponseEntity post(AdministradorDTO dto) {
        try {
            Administrador administrador = converter(dto);
            administrador = service.salvar(administrador);
            return new ResponseEntity(administrador, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    @ApiOperation("Alterar um administrador")
    @ApiResponses({
            @ApiResponse(code = 202, message = "Administrador alterado com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao alterar administrador")})
    public ResponseEntity atualizar(@PathVariable("id") @ApiParam("id do Administrador") Long id, AdministradorDTO dto) {
        if (!service.getAdministradorById(id).isPresent()) {
            return new ResponseEntity("Administrador não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Administrador administrador = converter(dto);
            administrador.setId(administrador.getId());
            service.salvar(administrador);
            return ResponseEntity.ok(administrador);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ApiOperation("Apagar um administrador")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Administrador excluido com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao excluir administrador"),
            @ApiResponse(code = 404, message = "Administrador não encontrado")})
    public ResponseEntity excluir(@PathVariable("id") @ApiParam("id do Administrador") Long id) {
        Optional<Administrador> administrador = service.getAdministradorById(id);
        if (!administrador.isPresent()) {
            return new ResponseEntity("Administrador não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(administrador.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Administrador converter(AdministradorDTO dto) {
        Administrador administrador = new Administrador();
        administrador.setId(dto.getId());
        administrador.setNome(dto.getNome());
        administrador.setEmail(dto.getEmail());
        return administrador;
    }
}

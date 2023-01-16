package com.example.hemocentroapi.api.controller;

import com.example.hemocentroapi.api.dto.TipoSangueDTO;
import com.example.hemocentroapi.exception.RegraNegocioException;
import com.example.hemocentroapi.model.entity.TipoSangue;
import com.example.hemocentroapi.service.TipoSangueService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/api/tiposSangue")
@RequiredArgsConstructor
public class TipoSangueController {
    private final TipoSangueService service;

    @GetMapping()
    @ApiOperation("Obter todos os tiposSangue")
    @ApiResponses({
            @ApiResponse(code = 200, message = "TipoSangue encontrado")})
    public ResponseEntity get() {
        List<TipoSangue> tiposSangue = service.getTiposSangue();
        return ResponseEntity.ok(tiposSangue.stream().map(TipoSangueDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de um tipoSangue")
    @ApiResponses({
            @ApiResponse(code = 200, message = "TipoSangue encontrado"),
            @ApiResponse(code = 404, message = "TipoSangue não encontrado")})
    public ResponseEntity get(@PathVariable("id") @ApiParam("id do TipoSangue") Long id) {
        Optional<TipoSangue> tipoSangue = service.getTipoSangueById(id);
        if (!tipoSangue.isPresent()) {
            return new ResponseEntity("TipoSangue não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(tipoSangue.map(TipoSangueDTO::create));
    }

    @PostMapping()
    @ApiOperation("Salvar um tipoSangue")
    @ApiResponses({
            @ApiResponse(code = 201, message = "TipoSangue criado com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao salvar tipoSangue")})
    public ResponseEntity post(TipoSangueDTO dto) {
        try {
            TipoSangue tipoSangue = converter(dto);
            tipoSangue = service.salvar(tipoSangue);
            return new ResponseEntity(tipoSangue, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
//    @PatchMapping("{id}")
//    @ApiOperation("Incremendar em 1 a quantidade de um tipoSangue")
//    @ApiResponses({
//            @ApiResponse(code = 201, message = "quantidade de tipoSangue alterada com sucesso"),
//            @ApiResponse(code = 400, message = "Erro ao alterar a quantidade de tipoSangue")})
//    public ResponseEntity<TipoSangue> incrementarQuantidade(@PathVariable Long id) {
//        TipoSangue tipoSangue = service.getTipoSangueById(id).orElseThrow(() -> new RegraNegocioException("Tipo de Sangue não encontrado com id " + id));
//        tipoSangue.setQuantidade(tipoSangue.getQuantidade() + 1);
//        TipoSangue atualizado = service.salvar(tipoSangue);
//        return ResponseEntity.ok(atualizado);
//    }

    @PatchMapping("{id}")
    @ApiOperation("Incrementar em 1 a quantidade de um tipoSangue")
    @ApiResponses({
            @ApiResponse(code = 200, message = "quantidade de tipoSangue alterada com sucesso"),
            @ApiResponse(code = 404, message = "Tipo de Sangue não encontrado com id fornecido"),
            @ApiResponse(code = 400, message = "Erro ao alterar a quantidade de tipoSangue")
    })
    public ResponseEntity<TipoSangue> incrementarQuantidade(@PathVariable Long id) {
        Optional<TipoSangue> optionalTipoSangue = service.getTipoSangueById(id);
        if (!optionalTipoSangue.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        TipoSangue tipoSangue = optionalTipoSangue.get();
        tipoSangue.setQuantidade(tipoSangue.getQuantidade() + 1);
        try {
            TipoSangue atualizado = service.salvar(tipoSangue);
            return ResponseEntity.ok(atualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("{id}")
    @ApiOperation("Alterar um tipoSangue")
    @ApiResponses({
            @ApiResponse(code = 202, message = "TipoSangue alterado com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao alterar tipoSangue")})
    public ResponseEntity atualizar(@PathVariable("id") @ApiParam("id do TipoSangue") Long id, TipoSangueDTO dto) {
        if (!service.getTipoSangueById(id).isPresent()) {
            return new ResponseEntity("TipoSangue não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            TipoSangue tipoSangue = converter(dto);
            tipoSangue.setId(tipoSangue.getId());
            service.salvar(tipoSangue);
            return ResponseEntity.ok(tipoSangue);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ApiOperation("Apagar um tipoSangue")
    @ApiResponses({
            @ApiResponse(code = 204, message = "TipoSangue excluido com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao excluir tipoSangue"),
            @ApiResponse(code = 404, message = "TipoSangue não encontrado")})
    public ResponseEntity excluir(@PathVariable("id") @ApiParam("id do TipoSangue") Long id) {
        Optional<TipoSangue> tipoSangue = service.getTipoSangueById(id);
        if (!tipoSangue.isPresent()) {
            return new ResponseEntity("TipoSangue não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(tipoSangue.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @GetMapping("/categorias/{id}")
//    public ResponseEntity<Categoria> getCategoria(@PathVariable Long id) {
//        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com id " + id));
//        return ResponseEntity.ok().body(categoria);
//    }


    public TipoSangue converter(TipoSangueDTO dto) {
        TipoSangue tipoSangue = new TipoSangue();
        tipoSangue.setId(dto.getId());
//        tipoSangue.setTipo(dto.getTipo());
//        tipoSangue.setPositivo(dto.getPositivo());
//        tipoSangue.setQuantidade(dto.getQuantidade());
//        //tipoSangue.setTipoSangueParaQuemDoa(dto.getParaQuemDoa()); //COMO FAZER COM AUTO RELACIONAMENTOS
        return tipoSangue;
    }
}

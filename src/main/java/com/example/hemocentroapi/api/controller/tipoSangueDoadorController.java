package com.example.hemocentroapi.api.controller;

import com.example.hemocentroapi.api.dto.AdministradorDTO;
import com.example.hemocentroapi.api.dto.EnderecoDTO;
import com.example.hemocentroapi.exception.RegraNegocioException;
import com.example.hemocentroapi.model.entity.Administrador;
import com.example.hemocentroapi.model.entity.Doador;
import com.example.hemocentroapi.model.entity.Endereco;
import com.example.hemocentroapi.service.AdministradorService;
import com.example.hemocentroapi.service.DoadorService;
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
@RequestMapping("/api/tipoSangueDoador")
@RequiredArgsConstructor
public class tipoSangueDoadorController {

    private final DoadorService service;

    @GetMapping("/{cpf}")
    @ApiOperation("Obter detalhes de um doador")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Doador encontrado"),
            @ApiResponse(code = 404, message = "Doador não encontrado")})
    public ResponseEntity getTipoSangue(@PathVariable("cpf") @ApiParam("cpf do Doador") String cpf) {
        Optional<Doador> doador = service.getDoadorByCpf(cpf);
        if (!doador.isPresent()) {
            return new ResponseEntity("Doador não encontrado", HttpStatus.NOT_FOUND);
        }
        //TipoSangue tipoSangue = doador.get().getTipoSangue();
        return new ResponseEntity<>(doador.get().getTipoSangue(), HttpStatus.OK);
    }

}

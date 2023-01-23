package com.example.hemocentroapi;

import com.example.hemocentroapi.exception.RegraNegocioException;
import com.example.hemocentroapi.model.entity.Administrador;
import com.example.hemocentroapi.model.repository.AdministradorRepository;
import com.example.hemocentroapi.service.AdministradorService;

import org.junit.jupiter.api.Test;


import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

public class AdministradorServiceTest {
    @Test
    public void testarEmailInvalido(){

        AdministradorRepository administradorRepository = createMock(AdministradorRepository.class);
        AdministradorService administradorService = new AdministradorService(administradorRepository);
        Administrador administrador = new Administrador();

        try{
            administradorService.validar(administrador);
            fail();
        } catch(RegraNegocioException e){
            assertEquals("Email inválido", e.getMessage());
        }
    }

}

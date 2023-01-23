package com.example.hemocentroapi;

import com.example.hemocentroapi.exception.RegraNegocioException;
import com.example.hemocentroapi.model.entity.Administrador;
import com.example.hemocentroapi.model.repository.AdministradorRepository;
import com.example.hemocentroapi.service.AdministradorService;

import org.aspectj.lang.annotation.Before;
import org.easymock.EasyMock.*;
import org.easymock.Mock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Optional;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AdministradorServiceTest {

//    @Test
//    public void testarEmailInvalido() {
//        AdministradorService administradorService = createMock(AdministradorService.class);
//        Administrador administrador = new Administrador();
//
//        Throwable exception = assertThrows(RegraNegocioException.class, () -> {
//            administradorService.validar(administrador);
//        });
//
//        System.out.println(exception.getMessage());
//        assertEquals("Email inválido", exception.getMessage());
//
//    }
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

package com.example.hemocentroapi;

import com.example.hemocentroapi.exception.RegraNegocioException;
import com.example.hemocentroapi.model.entity.Administrador;
import com.example.hemocentroapi.service.AdministradorService;

import org.easymock.EasyMock.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AdministradorServiceTest {

    @Test
    public void testeEmailInvalido(){

        Administrador administradorMock = createMock(Administrador.class);
        administradorMock.setEmail("");

        AdministradorService administradorServiceMock = createMock(AdministradorService.class);

        doThrow(RegraNegocioException.class).when(administradorServiceMock).validar(administradorMock);






    }
}

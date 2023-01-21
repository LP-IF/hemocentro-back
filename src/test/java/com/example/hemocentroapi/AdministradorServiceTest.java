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
    public void testarEmailInvalido() {
        AdministradorService administradorService = createMock(AdministradorService.class);
        Administrador administrador = new Administrador();

        Throwable exception = assertThrows(RegraNegocioException.class, () -> {
            administradorService.validar(administrador);
        });

        assertEquals("Email inválido", exception.getMessage());

    }
}

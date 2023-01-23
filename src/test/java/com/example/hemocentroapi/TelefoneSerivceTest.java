package com.example.hemocentroapi;

import com.example.hemocentroapi.exception.RegraNegocioException;
import com.example.hemocentroapi.model.entity.Telefone;
import com.example.hemocentroapi.model.repository.TelefoneRepository;
import com.example.hemocentroapi.service.TelefoneService;

import org.junit.jupiter.api.Test;
import static org.easymock.EasyMock.createMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TelefoneSerivceTest {
    @Test
    public void testarTelefoneInvalido(){
        TelefoneRepository telefoneRepository = createMock(TelefoneRepository.class);
        TelefoneService telefoneService = new TelefoneService(telefoneRepository);
        Telefone telefone = new Telefone();

        try{
            telefoneService.validar(telefone);
            fail();
        } catch(RegraNegocioException e){
            assertEquals("Número inválido", e.getMessage());
        }

    }
}

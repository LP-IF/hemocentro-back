package com.example.hemocentroapi;

import com.example.hemocentroapi.exception.RegraNegocioException;
import com.example.hemocentroapi.model.entity.Doador;
import com.example.hemocentroapi.model.repository.DoadorRepository;
import com.example.hemocentroapi.service.DoadorService;
import org.junit.jupiter.api.Test;
import static org.easymock.EasyMock.createMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class DoadorServiceTest {
    @Test
    public void testarCpfInvalido(){
        DoadorRepository doadorRepository = createMock(DoadorRepository.class);
        DoadorService doadorService = new DoadorService(doadorRepository);
        Doador doador = new Doador();

        try{
            doadorService.validar(doador);
            fail();
        } catch(RegraNegocioException e){
            assertEquals("CPF inválido", e.getMessage());
        }

    }


}

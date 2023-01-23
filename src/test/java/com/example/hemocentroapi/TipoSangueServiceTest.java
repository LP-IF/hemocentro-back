package com.example.hemocentroapi;

import com.example.hemocentroapi.exception.RegraNegocioException;
import com.example.hemocentroapi.model.entity.TipoSangue;
import com.example.hemocentroapi.model.repository.TipoSangueRepository;
import com.example.hemocentroapi.service.TipoSangueService;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class TipoSangueServiceTest {
    @Test
    public void testarTipoSangueInvalido(){
        TipoSangueRepository tipoSangueRepository = createMock(TipoSangueRepository.class);
        TipoSangueService tipoSangueService = new TipoSangueService(tipoSangueRepository);
        TipoSangue tipoSangue = new TipoSangue();

        try{
            tipoSangueService.validar(tipoSangue);
            fail();
        } catch(RegraNegocioException e){
            assertEquals("Tipo inválido", e.getMessage());
        }
    }

    @Test
    public void testarFatorRhInvalido(){
        TipoSangueRepository tipoSangueRepository = createMock(TipoSangueRepository.class);
        TipoSangueService tipoSangueService = new TipoSangueService(tipoSangueRepository);
        TipoSangue tipoSangue = new TipoSangue();
        tipoSangue.setTipo("O");

        try{
            tipoSangueService.validar(tipoSangue);
            fail();
        } catch(RegraNegocioException e){
            assertEquals("Fator RH inválido", e.getMessage());
        }
    }



}

package com.samsung.evaluation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class CotacaoServiceTest {

    @Autowired
    private CotacaoService service;


    @Test
    public void testCotacao() {
        assertEquals(service.consulta().get(0).getToCurrencyCode().toString(), "PEN");
        assertEquals(service.consulta().get(1).getToCurrencyCode().toString(), "BRL");
        assertEquals(service.consulta().get(2).getToCurrencyCode().toString(), "USD");
    }
}
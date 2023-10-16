package com.samsung.evaluation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MoedaServiceTest {

    @Autowired
    private MoedaService service;


    @Test
    public void testMoeda() {
        assertEquals(service.consulta().get(0).getCurrencyCode().toString(), "USD");
        assertEquals(service.consulta().get(1).getCurrencyCode().toString(), "PEN");
        assertEquals(service.consulta().get(2).getCurrencyCode().toString(), "BRL");
    }
}
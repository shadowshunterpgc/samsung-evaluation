package com.samsung.evaluation.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samsung.evaluation.dto.CotacaoDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CotacaoService {
    public List<CotacaoDto> consulta() {
        String url = "https://sdshealthcheck.cellologistics.com.br/sds-devs-evaluation/evaluation/quotation";
        RestTemplate rt = new RestTemplate();
        String response = rt.getForObject(url, String.class);
        return converteJsonToCotacaoDto(response);
    }

    private List<CotacaoDto> converteJsonToCotacaoDto(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException("Falha na desserialização do JSON para MoedaDto", e);
        }
    }
}

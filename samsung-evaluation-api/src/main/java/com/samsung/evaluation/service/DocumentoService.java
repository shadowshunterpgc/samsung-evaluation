package com.samsung.evaluation.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samsung.evaluation.dto.CotacaoRequestDto;
import com.samsung.evaluation.dto.DocumentoDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.print.Doc;
import java.util.List;

@Service
public class DocumentoService {
    public List<DocumentoDto> consulta() {
        String url = "https://sdshealthcheck.cellologistics.com.br/sds-devs-evaluation/evaluation/docs";
        RestTemplate rt = new RestTemplate();
        String response = rt.getForObject(url, String.class);
        return converteJsonToDocumentoDto(response);
    }

    public DocumentoDto consulta(DocumentoDto documentoDto) {
        String url = "https://sdshealthcheck.cellologistics.com.br/sds-devs-evaluation/evaluation/docs";
        RestTemplate rt = new RestTemplate();
        String response = rt.getForObject(url, String.class);

        List<DocumentoDto> documentos = converteJsonToDocumentoDto(response);

        for (DocumentoDto documento : documentos) {
            if (documento.getDocumentNumber().equals(documentoDto.getDocumentNumber())) {
                return documento;
            }
        }
        return null;
    }

    private List<DocumentoDto> converteJsonToDocumentoDto(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException("Falha na desserialização do JSON para MoedaDto", e);
        }
    }
}

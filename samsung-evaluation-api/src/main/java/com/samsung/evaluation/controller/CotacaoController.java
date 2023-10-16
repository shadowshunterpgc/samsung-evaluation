package com.samsung.evaluation.controller;

import com.samsung.evaluation.dto.*;
import com.samsung.evaluation.service.CotacaoPrincipalService;
import com.samsung.evaluation.service.DocumentoService;
import com.samsung.evaluation.service.MoedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api")
@CrossOrigin(origins = "http://localhost:4200")
public class CotacaoController {

    @Autowired
    private MoedaService moedaService;

    @Autowired
    private CotacaoPrincipalService cotacaoPrincipalService;

    @Autowired
    private DocumentoService documentoService;

    @PostMapping("/cotacao/consulta")
    public ResponseEntity<List<CotacaoPrincipalDto>> consultaCotacaoPrincipal(@RequestBody CotacaoRequestDto request,
                                                                              @RequestHeader HttpHeaders header) {
        List<CotacaoPrincipalDto> response = cotacaoPrincipalService.consulta(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/documento/consulta")
    public ResponseEntity<DocumentoDto> consultaDocumento(@RequestBody DocumentoDto request,
                                                          @RequestHeader HttpHeaders header) {
        DocumentoDto response = documentoService.consulta(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/moeda/consulta")
    public ResponseEntity<List<MoedaDto>> consultaMoeda() {
        return new ResponseEntity<>(moedaService.consulta(), HttpStatus.OK);
    }
}
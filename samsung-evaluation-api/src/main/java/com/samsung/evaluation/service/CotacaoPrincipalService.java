package com.samsung.evaluation.service;

import com.samsung.evaluation.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CotacaoPrincipalService {

    @Autowired
    DocumentoService documentoService;

    @Autowired
    CotacaoService cotacaoService;

    @Autowired
    MoedaService moedaService;

    public static final String CODE_USD = "USD";
    public static final String CODE_PEN = "PEN";
    public static final String CODE_BRL = "BRL";

    public List<CotacaoPrincipalDto> consulta(CotacaoRequestDto cotacaoRequest) {
        List<CotacaoDto> listaCotacao = cotacaoService.consulta();
        List<DocumentoDto> listaDocumentos = documentoService.consulta();
        List<MoedaDto> listaMoeda = moedaService.consulta();
        List<CotacaoPrincipalDto> listaCotacaoPrincipal = new ArrayList<>();

        if (cotacaoRequest.getCurrencyDesc().equals("Dolar")) {
            String moedaCode = CODE_USD;
            preencheCotacao(listaDocumentos, listaCotacaoPrincipal, listaMoeda, listaCotacao, cotacaoRequest, moedaCode);

        } else if (cotacaoRequest.getCurrencyDesc().equals("Soles Peruano")) {
            String moedaCode = CODE_PEN;
            preencheCotacao(listaDocumentos, listaCotacaoPrincipal, listaMoeda, listaCotacao, cotacaoRequest, moedaCode);

        } else if (cotacaoRequest.getCurrencyDesc().equals("Real")) {
            String moedaCode = CODE_BRL;
            preencheCotacao(listaDocumentos, listaCotacaoPrincipal, listaMoeda, listaCotacao, cotacaoRequest, moedaCode);

        } else {
            preencheCotacao(listaDocumentos, listaCotacaoPrincipal, listaMoeda, listaCotacao, cotacaoRequest, "");
        }
        return listaCotacaoPrincipal;
    }

    public List<CotacaoPrincipalDto> preencheCotacao(List<DocumentoDto> listaDocumentos,
                                                     List<CotacaoPrincipalDto> listaCotacaoPrincipal,
                                                     List<MoedaDto> listaMoeda,
                                                     List<CotacaoDto> listaCotacao,
                                                     CotacaoRequestDto cotacaoRequest, String moedaCode) {

        for (int i = 0; i < listaDocumentos.size(); i++) {

            if ((listaDocumentos.get(i).getCurrencyCode().equals(moedaCode))
//                    || listaDocumentos.get(i).getDocumentDate().equals(cotacaoRequest.getDocumentDate())
                    || listaDocumentos.get(i).getDocumentNumber().equals(cotacaoRequest.getDocumentNumber())
            ) {

                CotacaoPrincipalDto cotacao = new CotacaoPrincipalDto();
                cotacao.setCurrencyCode(listaDocumentos.get(i).getCurrencyCode());
                cotacao.setDocumentDate(listaDocumentos.get(i).getDocumentDate());
                cotacao.setDocumentNumber(listaDocumentos.get(i).getDocumentNumber());
                cotacao.setDocumentValue(listaDocumentos.get(i).getDocumentValue());
                cotacao.setDocumentId(listaDocumentos.get(i).getDocumentId());


//                for (int j = 0; j < listaMoeda.size(); j++) {
                    if (cotacao.getCurrencyCode().equals(CODE_USD)) {
                        cotacao.setCurrencyDesc("Dolar");
                    } else if (cotacao.getCurrencyCode().equals(CODE_PEN)) {
                        cotacao.setCurrencyDesc("Soles Peruano");
                    } else if (cotacao.getCurrencyCode().equals(CODE_BRL)) {
                        cotacao.setCurrencyDesc("Real");
                    }

                    // Regra de negócio de conversão de moedas
                    for (int l = 0; l < listaCotacao.size(); l++) {
                        if (moedaCode.equals(listaCotacao.get(l).getToCurrencyCode())
                            || cotacaoRequest.getCurrencyCode().equals(listaCotacao.get(l).getToCurrencyCode())) {

                            if (listaCotacao.get(l).getToCurrencyCode().equals(CODE_USD)) {
                                cotacao.setValueUSD(cotacao.getDocumentValue());
                                cotacao.setValuePEN(converterMoeda(listaCotacao, listaDocumentos.get(i), CODE_USD, CODE_PEN, cotacaoRequest));
                                cotacao.setValueBRL(converterMoeda(listaCotacao, listaDocumentos.get(i), CODE_USD, CODE_BRL, cotacaoRequest));
                            }

                            if (listaCotacao.get(l).getToCurrencyCode().equals(CODE_PEN)) {
                                cotacao.setValuePEN(cotacao.getDocumentValue());
                                cotacao.setValueUSD(converterMoeda(listaCotacao, listaDocumentos.get(i), CODE_PEN, CODE_USD, cotacaoRequest));
                                cotacao.setValueBRL(converterMoeda(listaCotacao, listaDocumentos.get(i), CODE_PEN, CODE_BRL, cotacaoRequest));
                            }

                            if (listaCotacao.get(l).getToCurrencyCode().equals(CODE_BRL)) {
                                cotacao.setValueBRL(cotacao.getDocumentValue());
                                cotacao.setValuePEN(converterMoeda(listaCotacao, listaDocumentos.get(i), CODE_BRL, CODE_PEN, cotacaoRequest));
                                cotacao.setValueUSD(converterMoeda(listaCotacao, listaDocumentos.get(i), CODE_BRL, CODE_USD, cotacaoRequest));
                            }

                            if (listaCotacao.get(l).getToCurrencyCode().isBlank()) {
                                cotacao.setValueBRL(cotacao.getDocumentValue());
                                cotacao.setValuePEN(converterMoeda(listaCotacao, listaDocumentos.get(i), CODE_USD, CODE_PEN, cotacaoRequest));
                                cotacao.setValueBRL(converterMoeda(listaCotacao, listaDocumentos.get(i), CODE_USD, CODE_BRL, cotacaoRequest));
                                cotacao.setValueUSD(converterMoeda(listaCotacao, listaDocumentos.get(i), CODE_PEN, CODE_USD, cotacaoRequest));
                                cotacao.setValueBRL(converterMoeda(listaCotacao, listaDocumentos.get(i), CODE_PEN, CODE_USD, cotacaoRequest));
                                cotacao.setValuePEN(converterMoeda(listaCotacao, listaDocumentos.get(i), CODE_BRL, CODE_PEN, cotacaoRequest));
                                cotacao.setValueUSD(converterMoeda(listaCotacao, listaDocumentos.get(i), CODE_BRL, CODE_USD, cotacaoRequest));
                            }
                        }
//                    }
                }

                if (cotacao.getDocumentId() != null) {
                    listaCotacaoPrincipal.add(cotacao);
                }
            }
        }
        return listaCotacaoPrincipal;
    }

    private String converterMoeda(List<CotacaoDto> cotacoes, DocumentoDto documento, String moedaOrigem, String moedaDestino, CotacaoRequestDto cotacaoRequest) {
        Optional<CotacaoDto> cotacaoCorrespondente = cotacoes.stream()
                .filter(cotacaoDto -> cotacaoDto.getFromCurrencyCode().equals(moedaOrigem)
                        && cotacaoDto.getToCurrencyCode().equals(moedaDestino)
                        && cotacaoDto.getDataHoraCotacao().equals(cotacaoRequest.getDataHoraCotacao()))
                .findFirst();

        return cotacaoCorrespondente.map(cotacaoDto -> {
            double valor = Double.parseDouble(documento.getDocumentValue()) * Double.parseDouble(cotacaoDto.getCotacao());
            return String.format("%.2f", valor);
        }).orElse("0.00");
    }
}

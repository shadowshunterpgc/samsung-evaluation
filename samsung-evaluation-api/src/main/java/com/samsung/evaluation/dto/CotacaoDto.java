package com.samsung.evaluation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CotacaoDto {

    @JsonProperty("fromCurrencyCode")
    private String fromCurrencyCode;
    @JsonProperty("toCurrencyCode")
    private String toCurrencyCode;
    @JsonProperty("cotacao")
    private String cotacao;
    @JsonProperty("dataHoraCotacao")
    private String dataHoraCotacao;

    public String getFromCurrencyCode() {
        return fromCurrencyCode;
    }

    public void setFromCurrencyCode(String fromCurrencyCode) {
        this.fromCurrencyCode = fromCurrencyCode;
    }

    public String getToCurrencyCode() {
        return toCurrencyCode;
    }

    public void setToCurrencyCode(String toCurrencyCode) {
        this.toCurrencyCode = toCurrencyCode;
    }

    public String getCotacao() {
        return cotacao;
    }

    public void setCotacao(String cotacao) {
        this.cotacao = cotacao;
    }

    public String getDataHoraCotacao() {
        return dataHoraCotacao;
    }

    public void setDataHoraCotacao(String dataHoraCotacao) {
        this.dataHoraCotacao = dataHoraCotacao;
    }
}

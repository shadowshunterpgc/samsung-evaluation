package com.samsung.evaluation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CotacaoPrincipalDto {

    // Moeda

    @JsonProperty("currencyCode")
    private String currencyCode;

    @JsonProperty("currencyDesc")
    private String currencyDesc;

    // Documento
    @JsonProperty("documentId")
    private Long documentId;
    @JsonProperty("documentNumber")
    private String documentNumber;
    @JsonProperty("notaFiscal")
    private String notaFiscal;
    @JsonProperty("documentDate")
    private String documentDate;
    @JsonProperty("documentValue")
    private String documentValue;

    // Cotação
    @JsonProperty("cotacao")
    private String cotacao;
    @JsonProperty("dataHoraCotacao")
    private String dataHoraCotacao;
    @JsonProperty("valueUSD")
    private String valueUSD;
    @JsonProperty("valuePEN")
    private String valuePEN;
    @JsonProperty("valueBRL")
    private String valueBRL;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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

    public String getCurrencyDesc() {
        return currencyDesc;
    }

    public void setCurrencyDesc(String currencyDesc) {
        this.currencyDesc = currencyDesc;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(String notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public String getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(String documentDate) {
        this.documentDate = documentDate;
    }

    public String getDocumentValue() {
        return documentValue;
    }

    public void setDocumentValue(String documentValue) {
        this.documentValue = documentValue;
    }

    public String getValueUSD() {
        return valueUSD;
    }

    public void setValueUSD(String valueUSD) {
        this.valueUSD = valueUSD;
    }

    public String getValuePEN() {
        return valuePEN;
    }

    public void setValuePEN(String valuePEN) {
        this.valuePEN = valuePEN;
    }

    public String getValueBRL() {
        return valueBRL;
    }

    public void setValueBRL(String valueBRL) {
        this.valueBRL = valueBRL;
    }
}

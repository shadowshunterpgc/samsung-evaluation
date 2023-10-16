package com.samsung.evaluation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentoDto {

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
    @JsonProperty("currencyCode")
    private String currencyCode;

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

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}

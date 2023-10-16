package com.samsung.evaluation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MoedaDto {

    @JsonProperty("currencyId")
    private Long currencyId;
    @JsonProperty("currencyCode")
    private String currencyCode;
    @JsonProperty("currencyDesc")
    private String currencyDesc;

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyDesc() {
        return currencyDesc;
    }

    public void setCurrencyDesc(String currencyDesc) {
        this.currencyDesc = currencyDesc;
    }
}

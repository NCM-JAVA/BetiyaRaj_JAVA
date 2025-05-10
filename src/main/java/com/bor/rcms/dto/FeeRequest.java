package com.bor.rcms.dto;

public class FeeRequest {
    private int amount;

    public FeeRequest() {}

    public FeeRequest(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
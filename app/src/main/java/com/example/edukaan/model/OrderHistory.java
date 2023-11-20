package com.example.edukaan.model;

public class OrderHistory {
    public String code,buyer,status,userId;
    public int total_fees,tax;

    public OrderHistory(String code, String buyer, String status,String userId, int total_fees, int tax) {
        this.code = code;
        this.buyer = buyer;
        this.status = status;
        this.total_fees = total_fees;
        this.tax = tax;
        this.userId=userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotal_fees() {
        return total_fees;
    }

    public void setTotal_fees(int total_fees) {
        this.total_fees = total_fees;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }
}

package com.example.ordered_food.model;

public class PaymentDetails {

    private String paymentMethod;

    private String status;
    private  String paymentId;
    private  String vnPayPaymentLinkid;
    private  String vnPaymentLinkReferencedId;

    private  String vnPaymentLinkStatus;

    private  String vnPaymentId;

    public PaymentDetails() {
    }

    public PaymentDetails(String paymentMethod, String status, String paymentId, String vnPayPaymentLinkid, String vnPaymentLinkReferencedId, String vnPaymentLinkStatus, String vnPaymentId) {
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.paymentId = paymentId;
        this.vnPayPaymentLinkid = vnPayPaymentLinkid;
        this.vnPaymentLinkReferencedId = vnPaymentLinkReferencedId;
        this.vnPaymentLinkStatus = vnPaymentLinkStatus;
        this.vnPaymentId = vnPaymentId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getVnPayPaymentLinkid() {
        return vnPayPaymentLinkid;
    }

    public void setVnPayPaymentLinkid(String vnPayPaymentLinkid) {
        this.vnPayPaymentLinkid = vnPayPaymentLinkid;
    }

    public String getVnPaymentLinkReferencedId() {
        return vnPaymentLinkReferencedId;
    }

    public void setVnPaymentLinkReferencedId(String vnPaymentLinkReferencedId) {
        this.vnPaymentLinkReferencedId = vnPaymentLinkReferencedId;
    }

    public String getVnPaymentLinkStatus() {
        return vnPaymentLinkStatus;
    }

    public void setVnPaymentLinkStatus(String vnPaymentLinkStatus) {
        this.vnPaymentLinkStatus = vnPaymentLinkStatus;
    }

    public String getVnPaymentId() {
        return vnPaymentId;
    }

    public void setVnPaymentId(String vnPaymentId) {
        this.vnPaymentId = vnPaymentId;
    }
}

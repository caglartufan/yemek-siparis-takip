package com.caglartufan.yemek_siparis_takip.exception;

public class OrderListNotFoundException extends ResourceNotFoundException {
    public OrderListNotFoundException(Integer id) {
        super("Verilen kimlik bilgisiyle (" + id + ") uyuşan bir sipariş listesi bulunamadı.");
    }
}

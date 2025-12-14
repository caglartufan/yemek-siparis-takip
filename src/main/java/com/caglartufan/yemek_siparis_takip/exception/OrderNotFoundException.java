package com.caglartufan.yemek_siparis_takip.exception;

public class OrderNotFoundException extends ResourceNotFoundException {
    public OrderNotFoundException(Integer id) {
        super("Verilen kimlik bilgisiyle (" + id + ") uyuşan bir sipariş bulunamadı.");
    }
}

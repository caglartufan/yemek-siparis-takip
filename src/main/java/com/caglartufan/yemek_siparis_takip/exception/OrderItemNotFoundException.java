package com.caglartufan.yemek_siparis_takip.exception;

public class OrderItemNotFoundException extends ResourceNotFoundException {
    public OrderItemNotFoundException(Integer id) {
        super("Verilen kimlik bilgisiyle (" + id + ") uyuşan bir sipariş öğesi bulunamadı.");
    }
}

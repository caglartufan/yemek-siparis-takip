package com.caglartufan.yemek_siparis_takip.exception;

public class ProductNotFoundException extends ResourceNotFoundException {
    public ProductNotFoundException(Integer productId) {
        super("Verilen kimlik bilgisiyle (" + productId + ") uyuşan bir ürün bulunamadı.");
    }
}

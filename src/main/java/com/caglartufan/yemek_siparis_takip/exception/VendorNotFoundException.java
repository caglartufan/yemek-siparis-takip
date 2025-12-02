package com.caglartufan.yemek_siparis_takip.exception;

public class VendorNotFoundException extends ResourceNotFoundException {
    public VendorNotFoundException(Integer id) {
        super("Verilen kimlik bilgisiyle (" + id + ") uyuşan bir satıcı bulunamadı.");
    }
}

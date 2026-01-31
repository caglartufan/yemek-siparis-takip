package com.caglartufan.yemek_siparis_takip.service;

import com.caglartufan.yemek_siparis_takip.entity.Product;
import com.caglartufan.yemek_siparis_takip.exception.ProductNotFoundException;
import com.caglartufan.yemek_siparis_takip.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;

    public Product findProductOrElseThrow(Integer vendorId, Integer productId) {
        return productRepository
                .findByIdAndVendorId(productId, vendorId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }
}

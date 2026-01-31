package com.caglartufan.yemek_siparis_takip.service;

import com.caglartufan.yemek_siparis_takip.entity.Product;

public interface IProductService {
    Product findProductOrElseThrow(Integer vendorId, Integer productId);
}

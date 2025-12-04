package com.caglartufan.yemek_siparis_takip.service;

import com.caglartufan.yemek_siparis_takip.entity.Vendor;

public interface IVendorService {
    Vendor findVendorOrElseThrow(Integer id);
}

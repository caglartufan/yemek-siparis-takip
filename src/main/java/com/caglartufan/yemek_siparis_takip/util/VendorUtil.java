package com.caglartufan.yemek_siparis_takip.util;

import com.caglartufan.yemek_siparis_takip.entity.Vendor;
import com.caglartufan.yemek_siparis_takip.exception.VendorNotFoundException;
import com.caglartufan.yemek_siparis_takip.repository.VendorRepository;

public class VendorUtil {
    public static Vendor findVendorOrElseThrow(Integer id, VendorRepository repository) {
        // Find the vendor or fail
        return repository
                .findById(id)
                .orElseThrow(() -> new VendorNotFoundException(id));
    }
}

package com.caglartufan.yemek_siparis_takip.service;

import com.caglartufan.yemek_siparis_takip.entity.Vendor;
import com.caglartufan.yemek_siparis_takip.exception.VendorNotFoundException;
import com.caglartufan.yemek_siparis_takip.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VendorService implements IVendorService {
    private final VendorRepository vendorRepository;

    @Override
    public Vendor findVendorOrElseThrow(Integer id) {
        // Find the vendor or fail
        return vendorRepository
                .findById(id)
                .orElseThrow(() -> new VendorNotFoundException(id));
    }
}

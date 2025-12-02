package com.caglartufan.yemek_siparis_takip.repository;

import com.caglartufan.yemek_siparis_takip.entity.Vendor;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<@NonNull Vendor, @NonNull Integer> {
}

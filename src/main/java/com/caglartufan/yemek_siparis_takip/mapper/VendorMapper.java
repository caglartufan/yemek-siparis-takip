package com.caglartufan.yemek_siparis_takip.mapper;

import com.caglartufan.yemek_siparis_takip.dto.VendorDTO;
import com.caglartufan.yemek_siparis_takip.entity.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VendorMapper {
    @Mapping(target = "orderLists", ignore = true)
    @Mapping(target = "products", ignore = true)
    VendorDTO toVendorDTO(Vendor vendor);

    @Mapping(target = "orderLists", ignore = true)
    @Mapping(target = "products", ignore = true)
    Vendor fromVendorDTO(VendorDTO vendorDTO);
}


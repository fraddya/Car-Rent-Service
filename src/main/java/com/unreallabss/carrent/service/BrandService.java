package com.unreallabss.carrent.service;


import com.unreallabss.carrent.domain.Brand;

import java.util.List;

public interface BrandService {
    Brand createBrand(Brand brand);

    List<Brand> getAllBrands();

    Brand delete(Long id);
}

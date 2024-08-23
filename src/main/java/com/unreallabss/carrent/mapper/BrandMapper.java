package com.unreallabss.carrent.mapper;


import com.unreallabss.carrent.domain.Brand;
import com.unreallabss.carrent.dto.request.brand.BrandCreateRequest;
import com.unreallabss.carrent.dto.response.brand.BrandSearchResponse;
import com.unreallabss.carrent.enums.Status;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring",imports = { Status.class})
public interface BrandMapper {
    Brand mapToCreate(BrandCreateRequest request);

    BrandSearchResponse mapToViewResponse(Brand persistedBrand);

    List<BrandSearchResponse> mapToSuggestion(List<Brand> results);

    //GeneralCreateResponse mapToDeleteResponse(Brand brand);
}

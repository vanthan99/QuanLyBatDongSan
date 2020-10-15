package com.vanthan99.batdongsan.webservices.payload.request;

import lombok.Data;

@Data
public class PostSearchRequest {
    private Long categoryId;
    private Long regionId;
    private Long postTypeId;
    private Boolean condition;
    private Double area;
    private Long price;
    private Boolean deal;
    private Long directionId;
    private String keyword;
    private Boolean customerType;
    private String year;
    private Boolean gender;
}

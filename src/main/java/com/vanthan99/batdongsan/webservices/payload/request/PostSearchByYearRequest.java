package com.vanthan99.batdongsan.webservices.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostSearchByYearRequest {
    private String year;
    private Boolean gender;
    
}

package com.example.demo.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class RateDTO {

    @SerializedName("rate")
    private Double rate;

    @SerializedName("date")
    private String date;
}

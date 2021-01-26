package com.example.demo.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class BuyAndSellDTO {
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("bid")
    @Expose
    private Double buy;
    @SerializedName("ask")
    @Expose
    private Double sell;
}

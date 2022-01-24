package com.sachin.stock;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Builder
@ToString
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockDetailsEvent {

    @JsonProperty("Id")
    private String id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Datetime")
    private String datetime;
    @JsonProperty("Open")
    private BigDecimal open;
    @JsonProperty("High")
    private BigDecimal high;
    @JsonProperty("Low")
    private BigDecimal low;
    @JsonProperty("Close")
    private BigDecimal close;
    @JsonProperty("AdjClose")
    private BigDecimal adjClose;
    @JsonProperty("Volume")
    private long volume;
    @JsonProperty("Price")
    private BigDecimal price;
}

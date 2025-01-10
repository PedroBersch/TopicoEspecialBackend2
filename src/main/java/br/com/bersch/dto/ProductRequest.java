package br.com.bersch.dto;

import lombok.Data;

@Data

public class ProductRequest {
    private String name;
    private Double price;
    private Integer amount;
}

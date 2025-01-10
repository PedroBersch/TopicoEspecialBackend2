package br.com.bersch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "orders")
public class Order {

    @Id
    private String orderId;
    private List<Product> products = new ArrayList<>(); ;
    private Double price;

    public void calculatePrice() {
        this.price = products.stream()
                .mapToDouble(product -> product.getPrice() * product.getAmount())
                .sum();
    }
}
package br.com.bersch.service;

import br.com.bersch.dto.ProductRequest;
import br.com.bersch.model.Product;
import br.com.bersch.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String productId) {
        return productRepository.findById(productId);
    }

    public Product createProduct(ProductRequest productRequest) {
        return productRepository.save(Product.builder()
                .productId(UUID.randomUUID().toString())
                .name(productRequest.getName())
                .amount(productRequest.getAmount())
                .price(productRequest.getPrice())
                .build());
    }

    public Product updateProduct(String productId, ProductRequest productRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setAmount(productRequest.getAmount());

        return productRepository.save(product);
    }

    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }
}
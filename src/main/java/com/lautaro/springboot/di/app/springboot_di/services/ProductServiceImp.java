package com.lautaro.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.lautaro.springboot.di.app.springboot_di.models.Product;
import com.lautaro.springboot.di.app.springboot_di.repositories.ProductRepository;

@Service
public class ProductServiceImp implements ProductService {

    @Value("${prod.tax}")
    private Double tax;

    private Environment environment;
    private ProductRepository repository;

    public ProductServiceImp(@Qualifier("productJson") ProductRepository repository, Environment environment) {
        this.environment = environment;
        this.repository = repository;
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream().map(p -> {
            Double priceTax = p.getPrice() * environment.getProperty("prod.tax", Double.class);
            // Double priceTax = p.getPrice() * tax;

            Product newProduct = (Product) p.clone();
            newProduct.setPrice(priceTax.longValue());
            return newProduct;

            // p.setPrice(priceTax.longValue());
            // return p;
        }).collect(Collectors.toList());
    }

    

    @Override
    public Product findById(Long id) {
                return repository.findById(id);

    }



}

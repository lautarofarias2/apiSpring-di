package com.lautaro.springboot.di.app.springboot_di.repositories;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.lautaro.springboot.di.app.springboot_di.models.Product;

// @RequestScope
@Primary
@Repository("productList")
public class ProductRepositoryImp implements ProductRepository {

    private List<Product> data;

    public ProductRepositoryImp() {
        this.data = Arrays.asList(
            new Product(1L, "Memoria ram 32", 300L),
            new Product(2L, "Cpu Amd Rizen 70", 850L),
            new Product(3L, "Teclado razer mini", 850L),
            new Product(4L, "Motherboard Gigabyte", 850L)
        );
    }

    @Override 
    public List<Product> findAll(){
        return data;
    }

    @Override
    public Product findById(Long id){
        return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
    
}

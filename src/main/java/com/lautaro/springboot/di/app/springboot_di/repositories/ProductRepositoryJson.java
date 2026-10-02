package com.lautaro.springboot.di.app.springboot_di.repositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.lautaro.springboot.di.app.springboot_di.models.Product;

import tools.jackson.databind.ObjectMapper;

public class ProductRepositoryJson implements ProductRepository{

    private List<Product> list;

    public ProductRepositoryJson() {
        Resource resource = new ClassPathResource("json/product.json");
        ReadValueJson(resource);
    }

    public ProductRepositoryJson(Resource resource) {
        ReadValueJson(resource);
    }

    private void ReadValueJson(Resource resource) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            list = Arrays.asList(objectMapper.readValue(resource.getFile(), Product[].class)); //otra opcion resouce.getInputStream()
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        return list;
    }

    

    @Override
    public Product findById(Long id) {
        return list.stream().filter(
            p ->  p.getId().equals(id)
        ).findAny().orElseThrow();
    }

}

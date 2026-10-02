package com.lautaro.springboot.di.app.springboot_di;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import com.lautaro.springboot.di.app.springboot_di.repositories.ProductRepository;
import com.lautaro.springboot.di.app.springboot_di.repositories.ProductRepositoryJson;


@Configuration
@PropertySource(value="classpath:values.properties")
public class ValuesConfig {


    @Value("classpath:json/product.json")
    private Resource resource;

    @Bean("productJson")
    ProductRepository productRepositoryJson(){
        return new ProductRepositoryJson(resource);
    }
}

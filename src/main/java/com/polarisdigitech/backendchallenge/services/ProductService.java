package com.polarisdigitech.backendchallenge.services;

import com.polarisdigitech.backendchallenge.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
public interface ProductService {

    public <T> T saveProductEntity(T type);

    public <T> T findOne(Class<T> tClass,Long id);

}

package rikkei.academy.service;

import rikkei.academy.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    Product findById(long id);

    void save(Product product);

    void deleteById(long id);
}

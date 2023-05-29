package rikkei.academy.service;

import rikkei.academy.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceIMPL implements IProductService {
    public static List<Product> productList = new ArrayList<>();

    static {
        productList.add(new Product(1, "Quan Bo", "Anhdep.com", 10));
        productList.add(new Product(2, "Ao Thun", "Anhdep.com", 20));
        productList.add(new Product(3, "Mu Noi", "Anhdep.com", 40));
        productList.add(new Product(4, "Giay Da", "Anhdep.com", 60));
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public Product findById(long id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                return productList.get(i);
            }
        }
        return null;
    }

    @Override
    public void save(Product product) {
        if (findById(product.getId()) != null) {
            productList.set(productList.indexOf(findById(product.getId())), product);
        } else {
            productList.add(product);
        }
    }

    @Override
    public void deleteById(long id) {
        productList.remove(findById(id));
    }
}

package ken.tar.Product.service;


import ken.tar.Product.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts();

    Product saveProduct(Product product);

    Product getProduct(Long id);

    void deleteProduct(Long id);
}

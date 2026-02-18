package ken.tar.Product.service;

import ken.tar.Product.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Product> findAllProducts();

    Product saveProduct(Product product);

    Product getProduct(Long id);

    void deleteProduct(Long id);

    Product patch(Map<String, Object> patchData, Product existingProduct);
}

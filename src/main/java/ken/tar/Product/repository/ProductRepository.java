package ken.tar.Product.repository;

import ken.tar.Product.entity.Product;

import java.util.List;

public interface ProductRepository {
    Product findById(long id);
    Product save(Product product);
    List<Product> findAll();
    Void deleteById(long id);

    List<Product> findByNameContainingLike(String name);
}

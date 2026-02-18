package ken.tar.Product.repository;

import ken.tar.Product.entity.Product;

import java.util.List;

public interface ProductRepository {
    Product findById(long id);
    Product save(Product product);
    Product patch(Product product);
    Product update(Product product);

    List<Product> findAll();
    List<Product> findByName(String name);
    List<Product> findByNameLike(String name);
    List<Product> findByNameContainingLike(String name);

    List<Product> findByPrice(double price);
    List<Product> findByPriceGreaterThan(double price);
    List<Product> findByPriceLessThan(double price);
    List<Product> findByPriceBetween(double price1, double price2);
    
    Void deleteById(long id);
}

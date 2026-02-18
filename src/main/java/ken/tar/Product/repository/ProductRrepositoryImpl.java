package ken.tar.Product.repository;

import ken.tar.Product.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductRrepositoryImpl implements ProductRepository {

    @Override
    public Product findById(long id) {
        return null;
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public Product patch(Product product) {
        return null;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public List<Product> findByName(String name) {
        return List.of();
    }

    @Override
    public List<Product> findByNameLike(String name) {
        return List.of();
    }

    @Override
    public List<Product> findByNameContainingLike(String name) {
        return List.of();
    }

    @Override
    public List<Product> findByPrice(double price) {
        return List.of();
    }

    @Override
    public List<Product> findByPriceGreaterThan(double price) {
        return List.of();
    }

    @Override
    public List<Product> findByPriceLessThan(double price) {
        return List.of();
    }

    @Override
    public List<Product> findByPriceBetween(double price1, double price2) {
        return List.of();
    }

    @Override
    public Void deleteById(long id) {
        return null;
    }
}

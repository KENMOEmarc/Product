package ken.tar.Product.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ken.tar.Product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProductRrepositoryImpl implements ProductRepository {
    private final EntityManager entityManager;

    @Autowired
    public ProductRrepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Product findById(long id) {
        Product theProduct = entityManager.find(Product.class, id);
        return theProduct;
    }

    @Transactional
    @Override
    public Product save(Product product) {
        Product dbProduct = entityManager.merge(product);

        return dbProduct;
    }

    @Override
    public List<Product> findAll() {
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p", Product.class);
        List<Product> products = query.getResultList();

        return  products;
    }

    @Transactional
    @Override
    public Void deleteById(long id) {
        Product product = findById(id);
        if (product != null) {
            entityManager.remove(product);
        }
        return null;
    }

    @Override
    public List<Product> findByName(String name) {
        TypedQuery query = entityManager
                .createQuery("SELECT p FROM Product p WHERE p.name = :name", Product.class);
        query.setParameter("name", name);

        List<Product> theProducts = query.getResultList();
        return theProducts;
    }

    @Override
    public List<Product> findByNameLike(String name) {
        TypedQuery query = entityManager
                .createQuery("SELECT p FROM Product p WHERE p.name LIKE :name", Product.class);
        query.setParameter("name", name);

        List<Product> theProducts = query.getResultList();
        return theProducts;
    }

    @Override
    public List<Product> findByNameContainingLike(String name) {
        TypedQuery query = entityManager
                .createQuery("SELECT p FROM Product p WHERE p.name LIKE :name", Product.class);
        query.setParameter("name", "%" + name + "%");

        List<Product> theProducts = query.getResultList();
        return theProducts;
    }

    @Override
    public List<Product> findByPrice(double price) {
        TypedQuery query = entityManager
                .createQuery("SELECT p FROM Product p WHERE p.price = :price", Product.class);
        query.setParameter("price", price);

        List<Product> theProducts = query.getResultList();
        return theProducts;
    }

    @Override
    public List<Product> findByPriceGreaterThan(double price) {
        TypedQuery query = entityManager
                .createQuery("SELECT p FROM Product p WHERE p.price > :price", Product.class);
        query.setParameter("price", price);

        List<Product> theProducts = query.getResultList();
        return theProducts;
    }

    @Override
    public List<Product> findByPriceLessThan(double price) {
        TypedQuery query = entityManager
                .createQuery("SELECT p FROM Product p WHERE p.price < :price", Product.class);
        query.setParameter("price", price);

        return query.getResultList();
    }

    @Override
    public List<Product> findByPriceBetween(double price1, double price2) {
        TypedQuery query = entityManager
                .createQuery("SELECT p FROM Product p WHERE p.price BETWEEN :price1 AND :price2", Product.class);
        query.setParameter("price1", price1);
        query.setParameter("price2", price2);

        return query.getResultList();
    }

}

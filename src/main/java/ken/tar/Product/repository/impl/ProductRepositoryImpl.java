package ken.tar.Product.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ken.tar.Product.entity.Product;
import ken.tar.Product.repository.ProductRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final EntityManager theEntityManager;


    public ProductRepositoryImpl(EntityManager theEntityManager) {
        this.theEntityManager = theEntityManager;
    }

    @Override
    public Product findById(long id) {
        Product theProduct = theEntityManager.find(Product.class, id);
        return theProduct;
    }

    @Transactional
    @Override
    public Product save(Product product) {
        Product dbProduct = theEntityManager.merge(product);
        return dbProduct;
    }

    @Override
    public List<Product> findAll() {
        TypedQuery<Product> query = theEntityManager.createQuery("SELECT p FROM Product p", Product.class);
        List<Product> products = query.getResultList();

        return  products;
    }

    @Transactional
    @Override
    public Void deleteById(long id) {
        Product product = findById(id);
        if (product != null) {
            theEntityManager.remove(product);
        }
        return null;
    }

    @Override
    public List<Product> findByNameContainingLike(String name) {
        TypedQuery<Product> query = theEntityManager
                .createQuery("SELECT p FROM Product p WHERE LOWER(p.name) LIKE :name", Product.class);

        query.setParameter("name", "%" + name.toLowerCase() + "%");
        return query.getResultList();
    }
}

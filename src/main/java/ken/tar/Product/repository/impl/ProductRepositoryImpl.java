package ken.tar.Product.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ken.tar.Product.configuration.LoggerFactory;
import ken.tar.Product.entity.Product;
import ken.tar.Product.repository.ProductRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final Logger logger;
    private final EntityManager theEntityManager;



    public ProductRepositoryImpl(LoggerFactory loggerFactory, EntityManager entityManager) {
        this.logger = loggerFactory.getLogger(ProductRepositoryImpl.class);
        theEntityManager = entityManager;
    }

    @Override
    public Product findById(long id) {
        logger.info("Finding the product with id {} ", id);
        Product theProduct = theEntityManager.find(Product.class, id);
        return theProduct;
    }

    @Transactional
    @Override
    public Product save(Product product) {
        logger.info("Saving the product {} ", product);
        Product dbProduct = theEntityManager.merge(product);
        return dbProduct;
    }

    @Override
    public List<Product> findAll() {
        logger.info("Finding all products");
        TypedQuery<Product> query = theEntityManager.createQuery("SELECT p FROM Product p", Product.class);
        List<Product> products = query.getResultList();
        return  products;
    }

    @Transactional
    @Override
    public Void deleteById(long id) {
        logger.info("Verifying if the product with id {} exists in data base before deleting.", id);
        Product product = findById(id);
        if (product != null) {
            logger.warn("Deleting the product {} ", product);
            theEntityManager.remove(product);
        }
        logger.info("Unexisting product with id {} ", id);
        return null;
    }

    @Override
    public List<Product> findByNameContainingLike(String name) {
        logger.info("Finding product with the name {} ", name);
        TypedQuery<Product> query = theEntityManager
                .createQuery("SELECT p FROM Product p WHERE LOWER(p.name) LIKE :name", Product.class);

        query.setParameter("name", "%" + name.toLowerCase() + "%");
        return query.getResultList();
    }
}

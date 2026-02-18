package ken.tar.Product.service;

import ken.tar.Product.entity.Product;
import ken.tar.Product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository theProductRepository ;

    @Autowired
    public ProductServiceImpl(ProductRepository theProductRepository) {
        this.theProductRepository = theProductRepository;
    }

    public List<Product> findAllProducts() {
        return theProductRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return theProductRepository.save(product);
    }

    @Override
    public Product getProduct(Long id) {
        Product theProduct = theProductRepository.findById(id);
        if (theProduct == null) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        return theProduct;
    }

    @Override
    public void deleteProduct(Long id) {
        Product theProduct = theProductRepository.findById(id);
        if (theProduct == null) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        theProductRepository.deleteById(id);
    }
}

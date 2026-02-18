package ken.tar.Product.service;

import ken.tar.Product.entity.Product;
import ken.tar.Product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository theProductRepository ;

    @Autowired
    public ProductService(ProductRepository theProductRepository) {
        this.theProductRepository = theProductRepository;
    }


    public List<Product> findAllProducts() {
        return theProductRepository.findAll();
    }
}

package ken.tar.Product.service.impl;

import ken.tar.Product.entity.Product;
import ken.tar.Product.exception.ResourceNotFoundException;
import ken.tar.Product.repository.ProductRepository;
import ken.tar.Product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    private final ObjectMapper theObjectMapper;
    private final ProductRepository theProductRepository ;

    @Autowired
    public ProductServiceImpl(ObjectMapper theObjectMapper, ProductRepository theProductRepository) {
        this.theObjectMapper = theObjectMapper;
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
            throw new ResourceNotFoundException("Product id not found - " + id);
        }
        return theProduct;
    }

    @Override
    public Product patch(Map<String, Object> patchData, Product existingProduct) {
        // Convert Product object to a JSON object node
        ObjectNode productNode = theObjectMapper.convertValue(existingProduct, ObjectNode.class);

        // Convert the patchPayload map to a JSON object node
        ObjectNode patchNode = theObjectMapper.convertValue(patchData, ObjectNode.class);

        // Merge the patch updates into the product node
        productNode.setAll(patchNode);

        return theObjectMapper.convertValue(productNode, Product.class);
    }

    @Override
    public List<Product> findByNameContainingLike(String name) {
        return theProductRepository.findByNameContainingLike(name);
    }


    @Override
    public Product updateProduct(Long id, Product product) {
        Product dbProduct = getProduct(id);
        product.setName(dbProduct.getName());
        product.setPrice(dbProduct.getPrice());
        return theProductRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product theProduct = theProductRepository.findById(id);

        theProductRepository.deleteById(theProduct.getId());
    }
}

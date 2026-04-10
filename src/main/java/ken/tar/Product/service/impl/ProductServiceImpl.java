package ken.tar.Product.service.impl;

import ken.tar.Product.configuration.LoggerFactory;
import ken.tar.Product.entity.Product;
import ken.tar.Product.exception.ResourceNotFoundException;
import ken.tar.Product.repository.ProductRepository;
import ken.tar.Product.service.ProductService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    private final Logger logger;
    private final ObjectMapper theObjectMapper;
    private final ProductRepository theProductRepository ;

    @Autowired
    public ProductServiceImpl(LoggerFactory loggerFactory, ObjectMapper theObjectMapper, ProductRepository theProductRepository) {
        this.logger = loggerFactory.getLogger(ProductServiceImpl.class);
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
            logger.warn("Product with id {} not found", id);
            throw new ResourceNotFoundException("Product id not found - " + id);
        }
        logger.info("Product {} retieved !", theProduct);
        return theProduct;
    }

    @Override
    public Product patch(Map<String, Object> patchData, Product existingProduct) {

        logger.info("Converting Product {} to a JSON object node", existingProduct);
        ObjectNode productNode = theObjectMapper.convertValue(existingProduct, ObjectNode.class);

        logger.info("Converting the patchPayload map to a JSON object node");
        ObjectNode patchNode = theObjectMapper.convertValue(patchData, ObjectNode.class);

        logger.info("Merging the patch updates into the product node");
        productNode.setAll(patchNode);

        return theObjectMapper.convertValue(productNode, Product.class);
    }

    @Override
    public List<Product> findByNameContainingLike(String name) {
        return theProductRepository.findByNameContainingLike(name);
    }


    @Override
    public Product updateProduct(Long id, Product product) {
        logger.info("Verifying if the product with id {} exists in data base before updating.", id);
        Product dbProduct = getProduct(id);
        logger.info("Updating the product {} ", dbProduct);
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

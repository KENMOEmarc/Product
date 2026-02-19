package ken.tar.Product.service;

import ken.tar.Product.entity.Product;
import ken.tar.Product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    private final ObjectMapper objectMapper;
    private final ProductRepository theProductRepository ;

    @Autowired
    public ProductServiceImpl(ObjectMapper objectMapper, ProductRepository theProductRepository) {
        this.objectMapper = objectMapper;
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
        return theProductRepository.findById(id);
    }

    @Override
    public Product patch(Map<String, Object> patchData, Product existingProduct) {
        // Convert Product object to a JSON object node
        ObjectNode productNode = objectMapper.convertValue(existingProduct, ObjectNode.class);

        // Convert the patchPayload map to a JSON object node
        ObjectNode patchNode = objectMapper.convertValue(patchData, ObjectNode.class);

        // Merge the patch updates into the product node
        productNode.setAll(patchNode);

        return objectMapper.convertValue(productNode, Product.class);
    }

    @Override
    public void deleteProduct(Long id) {
        Product theProduct = theProductRepository.findById(id);

        theProductRepository.deleteById(id);
    }
}

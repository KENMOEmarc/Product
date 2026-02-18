package ken.tar.Product.controller;

import ken.tar.Product.entity.Product;
import ken.tar.Product.service.ProductService;
import ken.tar.Product.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService theProductService;

    @Autowired
    public ProductController(ProductServiceImpl theProductService) {
        this.theProductService = theProductService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping
    public List<Product> getAllProducts(){
        return theProductService.findAllProducts();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){
        Product theProduct = theProductService.getProduct(id);
        return theProduct;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Product saveProduct(Product product){
        return theProductService.saveProduct(product);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existingProduct = theProductService.getProduct(id);
        if (existingProduct == null) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        return theProductService.saveProduct(existingProduct);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{id}")
    public Product patchProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existingProduct = theProductService.getProduct(id);
        if (existingProduct == null) {
            throw new RuntimeException("Product not found with id: " + id);
        }

        return theProductService.saveProduct(existingProduct);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        theProductService.deleteProduct(id);
    }
}

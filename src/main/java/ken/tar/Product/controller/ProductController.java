package ken.tar.Product.controller;

import ken.tar.Product.entity.Product;
import ken.tar.Product.service.ProductService;
import ken.tar.Product.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        return theProductService.getProduct(id);
    }

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String name) {
        return theProductService.findByNameContainingLike(name);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Product saveProduct(Product product){
        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        product.setId(0);
        return theProductService.saveProduct(product);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return theProductService.updateProduct(id , product);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{id}")
    public Product patchProduct(@PathVariable Long id, @RequestBody Map<String, Object> patchData) {
        Product existingProduct = theProductService.getProduct(id);

        if (patchData.containsKey("id")) {
            throw  new RuntimeException("Product id is not allowed in patch request");
        }

        Product patchedProduct = theProductService.patch(patchData, existingProduct);

        return theProductService.saveProduct(patchedProduct);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        Product theProduct = theProductService.getProduct(id);
        theProductService.deleteProduct(id);
    }
}

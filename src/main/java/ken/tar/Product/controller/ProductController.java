package ken.tar.Product.controller;

import ken.tar.Product.entity.Product;
import ken.tar.Product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService theProductService;

    @Autowired
    public ProductController(ProductService theProductService) {
        this.theProductService = theProductService;
    }

    @GetMapping
    public List<Product> findAllProducts(){
        return theProductService.findAllProducts();
    }
}

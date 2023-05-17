package mk.ukim.finki.emt.productcatalog.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.productcatalog.domain.models.Product;
import mk.ukim.finki.emt.productcatalog.domain.models.ProductId;
import mk.ukim.finki.emt.productcatalog.services.ProductService;
import mk.ukim.finki.emt.productcatalog.services.form.ProductForm;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductResource {
    private final ProductService productService;

    @GetMapping
    public List<Product> findAll() {
        return productService.getAll();
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody ProductForm productForm) {
        return productService.createProduct(productForm);
    }



    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable ProductId id) {
        productService.deleteProduct(id);
    }
}

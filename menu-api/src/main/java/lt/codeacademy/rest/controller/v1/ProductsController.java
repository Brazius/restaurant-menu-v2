package lt.codeacademy.rest.controller.v1;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.math.BigDecimal;
import java.util.List;
import lt.codeacademy.rest.entities.Product;
import lt.codeacademy.rest.services.ProductsServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.multipart.MultipartFile;

@Deprecated
@RestController("ProductsController.v1")
@RequestMapping("/v1/products")
public class ProductsController {

    private final ProductsServiceImpl productsServiceImpl;

    public ProductsController(ProductsServiceImpl productsServiceImpl) {
        this.productsServiceImpl = productsServiceImpl;
    }

    @ApiResponses({
            @ApiResponse(code = 500, message = "Somethings wrong")
    })
    @GetMapping
    public List<Product> getProducts() {
        return productsServiceImpl.getAllProducts();
    }

    @GetMapping("/paginated")
    public Page<Product> getProductsPaginated(
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize") int pageSize
    ) {
        return productsServiceImpl.getProductsPaginated(pageNumber, pageSize);
    }


    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productsServiceImpl.getProductById(id);
    }

    @PostMapping("/product")
    public Product createProduct(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "price") BigDecimal price
    ) {
        Product product = Product.builder()
                .title(title)
                .description(description)
                .price(price)
                .build();

        return productsServiceImpl.createProduct(product, file);
    }

    @GetMapping("/fail")
    public Product getFailure() {
        throw new RuntimeException("This is an error");
    }
}

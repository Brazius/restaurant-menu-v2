package lt.codeacademy.rest.services;

import lt.codeacademy.rest.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface ProductsService {

    List<Product> getAllProducts();

    Product createProduct(Product product, MultipartFile file);

    Product getProductById(Long id);

    Product deleteProduct(Product product, Long id);

    Page<Product> getProductsPaginated(int pageNumber, int pageSize);
}

package lt.codeacademy.rest.repositories;

import lt.codeacademy.rest.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

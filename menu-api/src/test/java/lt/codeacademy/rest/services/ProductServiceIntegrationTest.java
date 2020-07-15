package lt.codeacademy.rest.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import lt.codeacademy.rest.entities.Product;
import lt.codeacademy.rest.repositories.ProductRepository;
import lt.codeacademy.rest.services.exceptions.ProductNotFoundException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProductServiceIntegrationTest {

    @Autowired
    private ProductsServiceImpl productsServiceImpl;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        Product product = Product.builder()
                .id(1L)
                .title("ABC")
                .price(BigDecimal.TEN)
                .build();

        productRepository.saveAndFlush(product);
    }

    @AfterEach
    public void cleanUp() {
        productRepository.deleteAll();
    }

    @Test
    public void shouldReturnOneProduct() {
        Product product = productsServiceImpl.getProductById(1L);

        Assertions.assertEquals("ABC", product.getTitle());
        Assertions.assertEquals(null, product.getDescription());
        assertThat(BigDecimal.TEN, Matchers.comparesEqualTo(product.getPrice()));
    }

    @Test
    public void shouldThrowExceptionWhenProductNotFound() {
        Assertions.assertThrows(ProductNotFoundException.class, () -> productsServiceImpl.getProductById(2L));
    }

    @Test
    public void shouldCreateProduct() {
        Product product = Product.builder()
                .title("DEF")
                .description("GHI")
                .price(BigDecimal.ONE)
                .build();

        productsServiceImpl.createProduct(product, null);

        Assertions.assertEquals(2L, productRepository.count());
        Assertions.assertEquals(
                Arrays.asList(
                    Product.builder()
                            .id(1L)
                            .title("ABC")
                            .price(BigDecimal.valueOf(10).setScale(2, RoundingMode.CEILING))
                            .build(),
                    Product.builder()
                            .id(2L)
                            .title("DEF")
                            .description("GHI")
                            .price(BigDecimal.valueOf(1).setScale(2, RoundingMode.CEILING))
                            .build()
                ), productRepository.findAll());
    }

}

package lt.codeacademy.rest.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
@Entity
@Table(name = "Products")
@ApiModel(value = "Product", description = "A product in this eshop")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    @ApiModelProperty(hidden = true)
    private Long id;

    @Column(name = "title")
    @NotEmpty
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    @DecimalMin("0.01")
    @NotNull
    private BigDecimal price;

    @Column(name = "file_name")
    private String fileName;

    @Tolerate
    public Product(){}
}

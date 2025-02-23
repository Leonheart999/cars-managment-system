package ge.levanchitiashvili.carsmanagementsystem.models.cars;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;

@Data
@Entity
@FieldNameConstants
@Table(name = "cars")
@SequenceGenerator(name = "carIdSeq", sequenceName = "cars_id_seq", allocationSize = 1)
public class Car {
    @Id
    @GeneratedValue(generator = "carIdSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "color")
    private String color;
    @Column(name = "model")
    private String model;
    @Column(name = "brand")
    private String brand;
    @Column(name = "production_year")
    private Integer productionYear;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "active")
    private Boolean active;
}
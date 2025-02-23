package ge.levanchitiashvili.carsmanagementsystem.requests.cars;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewCarRequest {
    private BigDecimal price;
    private Integer productionYear;
    private String color;
    private String model;
    private String brand;

}

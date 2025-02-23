package ge.levanchitiashvili.carsmanagementsystem.controllers.cars;


import ge.levanchitiashvili.carsmanagementsystem.models.cars.Car;
import ge.levanchitiashvili.carsmanagementsystem.requests.cars.CarEditRequest;
import ge.levanchitiashvili.carsmanagementsystem.requests.cars.NewCarRequest;
import ge.levanchitiashvili.carsmanagementsystem.services.cars.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;


    @QueryMapping
    public List<Car> getAllCars() {
        return carService.getAll();
    }

    @QueryMapping
    public Car getCarById(@Argument Long id) {
        return carService.get(id);
    }

    @MutationMapping
    public Car addCar(@Argument String brand, @Argument String model, @Argument String color,
                      @Argument Integer productionYear, @Argument BigDecimal price) {
        NewCarRequest newCar = NewCarRequest.builder().brand(brand).model(model).color(color).productionYear(productionYear).price(price).build();
        return carService.addNew(newCar);
    }

    @MutationMapping
    public Car updateCar(@Argument Long id, @Argument String brand, @Argument String model,
                         @Argument String color, @Argument Integer productionYear, @Argument BigDecimal price) {
        CarEditRequest carEdit = CarEditRequest.builder().brand(brand).model(model).color(color).productionYear(productionYear != null ? productionYear : 0)
                .price(price != null ? price : BigDecimal.ZERO).build();
        return carService.edit(id, carEdit);
    }

    @MutationMapping
    public void deleteCar(@Argument Long id) {
         carService.delete(id);
    }

    @SubscriptionMapping
    public Flux<Car> carUpdates() {
        return carService.getCarSink().asFlux();
    }


}

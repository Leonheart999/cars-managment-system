package ge.levanchitiashvili.carsmanagementsystem.services.cars;

import ge.levanchitiashvili.carsmanagementsystem.repositories.jpa.cars.CarJPARepository;
import ge.levanchitiashvili.carsmanagementsystem.models.cars.Car;
import ge.levanchitiashvili.carsmanagementsystem.requests.cars.CarEditRequest;
import ge.levanchitiashvili.carsmanagementsystem.requests.cars.NewCarRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Sinks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl  implements CarService {
    private final CarJPARepository repository;
    @Getter
    private final Sinks.Many<Car> carSink = Sinks.many().replay().limit(1000);

    @Override
    public List<Car> getAll() {
        return repository.findAllByActiveTrue();
    }

    @Override
    public Car get(long id) {
        Optional<Car> Car = repository.findByIdAndActiveTrue(id);
        if (Car.isEmpty()) {
            throw new RuntimeException(String.format("Car with with id %s", id));
        }
        return Car.get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Car addNew(NewCarRequest newCarRequest) {
        Car car = new Car();
        car.setBrand(newCarRequest.getBrand());
        car.setModel(newCarRequest.getModel());
        car.setColor(newCarRequest.getColor());
        car.setPrice(newCarRequest.getPrice());
        car.setProductionYear(newCarRequest.getProductionYear());
        car.setActive(true);
        validateCar(car);
        return save(car);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Car save(Car car) {
        car=repository.save(car);
        carSink.tryEmitNext(car);
        return car;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Car edit(long id, CarEditRequest carEditRequest) {
        Car car = get(id);
        if (carEditRequest.getBrand() != null && !carEditRequest.getBrand().isEmpty()) {
            car.setBrand(carEditRequest.getBrand());

        }
        if (carEditRequest.getModel() != null && !carEditRequest.getModel().isEmpty()) {
            car.setModel(carEditRequest.getModel());
        }
        if (carEditRequest.getColor() != null && !carEditRequest.getColor().isEmpty()) {
            car.setColor(carEditRequest.getColor());
        }
        if (car.getPrice()!=null && !car.getPrice().equals(BigDecimal.ZERO)) {
            car.setPrice(carEditRequest.getPrice());
        }

        if (car.getProductionYear()==null ) {
            car.setProductionYear(carEditRequest.getProductionYear());
        }
        validateCar(car);
        return save(car);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(long id) {
        Car car = get(id);
        car.setActive(false);
        save(car);
    }


    private void validateCar(Car car) {
        StringBuilder errors = new StringBuilder();
        if (car.getPrice()==null || car.getPrice().equals(BigDecimal.ZERO)) {
            errors.append("Car price is mandatory ");
        }

        if (car.getProductionYear()==null ) {
            errors.append("Car production year is mandatory ");
        }

        if (car.getBrand()==null || car.getBrand().isEmpty() ) {
            errors.append("Car Brand is mandatory ");
        }

        if (car.getModel()==null || car.getModel().isEmpty() ) {
            errors.append("Car Model is mandatory ");
        }

        if (!errors.isEmpty()) {
            throw new RuntimeException(errors.toString());
        }
    }

}

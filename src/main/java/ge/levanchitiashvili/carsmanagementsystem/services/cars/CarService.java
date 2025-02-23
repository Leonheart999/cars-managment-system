package ge.levanchitiashvili.carsmanagementsystem.services.cars;

import ge.levanchitiashvili.carsmanagementsystem.models.cars.Car;
import ge.levanchitiashvili.carsmanagementsystem.requests.cars.CarEditRequest;
import ge.levanchitiashvili.carsmanagementsystem.requests.cars.NewCarRequest;
import reactor.core.publisher.Sinks;
import java.util.List;

public interface CarService {

    List<Car> getAll();

    Car get(long id);

    Car addNew(NewCarRequest newCarRequest);

    Car save(Car car);

    Car edit(long id, CarEditRequest carEditRequest);

    void delete(long id);


    Sinks.Many<Car> getCarSink();
}

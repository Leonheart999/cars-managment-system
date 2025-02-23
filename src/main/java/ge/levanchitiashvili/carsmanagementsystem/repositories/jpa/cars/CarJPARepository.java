package ge.levanchitiashvili.carsmanagementsystem.repositories.jpa.cars;



import ge.levanchitiashvili.carsmanagementsystem.config.BaseRepository;
import ge.levanchitiashvili.carsmanagementsystem.models.cars.Car;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarJPARepository extends BaseRepository<Car, Long> {

    List<Car> findAllByActiveTrue();

    Optional<Car> findByIdAndActiveTrue(long id);

}

package ge.levanchitiashvili.carsmanagementsystem;

import graphql.scalars.ExtendedScalars;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EntityScan("ge.levanchitiashvili.carsmanagementsystem.models")
@EnableJpaRepositories("ge.levanchitiashvili.carsmanagementsystem.repositories.jpa")
@SpringBootApplication(exclude = {JpaRepositoriesAutoConfiguration.class})
public class CarsManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarsManagementSystemApplication.class, args);
    }


    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder.scalar(ExtendedScalars.GraphQLBigDecimal);
    }
}

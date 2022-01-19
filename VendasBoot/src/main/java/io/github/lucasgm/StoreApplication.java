package io.github.lucasgm;

import io.github.lucasgm.domain.entity.Client;
import io.github.lucasgm.domain.entity.Order;
import io.github.lucasgm.domain.repository.IClientsRepository;
import io.github.lucasgm.domain.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RestController
public class StoreApplication {

    @Bean
    public CommandLineRunner init(@Autowired IClientsRepository clientsRepository,
                                  @Autowired IOrderRepository orderRepository) {
        return args -> {
            Client lucas = new Client("Lucas");
            clientsRepository.save(lucas);
            clientsRepository.save(new Client("Outro cliente"));

            Order order = new Order();
            order.setClient(lucas);
            order.setOrderDate(LocalDate.now());
            order.setTotal(BigDecimal.valueOf(100));
            orderRepository.save(order);

            Client client = clientsRepository.findClientFetchOrders(lucas.getId());
            System.out.println(client);
            System.out.println(client.getOrders());


        };
    }

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }
}

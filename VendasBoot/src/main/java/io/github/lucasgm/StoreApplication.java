package io.github.lucasgm;

import io.github.lucasgm.domain.entity.Client;
import io.github.lucasgm.domain.repository.IClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class StoreApplication {

    @Bean
    public CommandLineRunner init(@Autowired IClientsRepository clients) {
        return args -> {
            clients.save(new Client("Lucas"));
            clients.save(new Client("Outro cliente"));

            List<Client> allClients = clients.findAll();
            allClients.forEach(System.out::println);

            List<Client> byName = clients.findByNameContaining("Outro");
            byName.forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }
}

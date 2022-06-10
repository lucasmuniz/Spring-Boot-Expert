package io.github.lucasgm;

import io.github.lucasgm.domain.entity.Client;
import io.github.lucasgm.domain.repository.IClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StoreApplication {

    @Bean
    public CommandLineRunner commandLineRunner(@Autowired IClientsRepository clientsRepository){
        return args -> {
            Client client = new Client("Fulano");
            clientsRepository.save(client);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }
}

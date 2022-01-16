package io.github.lucasgm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@DevelopmentProfile
public class SelfConfiguration {

    @Bean
    public CommandLineRunner execute() {
        return args -> System.out.println("Rodando a configuração de desenvolvimento");
    }

}

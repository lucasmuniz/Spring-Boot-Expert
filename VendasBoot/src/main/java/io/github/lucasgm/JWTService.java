package io.github.lucasgm;

import io.github.lucasgm.domain.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JWTService {

    @Value("${security.jwt.expiration}")
    private String expiration;

    @Value("${security.jwt.signKey}")
    private String signKey;

    public String tokenGen(User user) {
        long expString = Long.valueOf(expiration);
        LocalDateTime expirationDate = LocalDateTime.now().plusMinutes(expString);
        Date data = Date.from(expirationDate.atZone(ZoneId.systemDefault()).toInstant());
        return Jwts
                .builder()
                .setSubject(user.getLogin())
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, signKey)
                .compact();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(StoreApplication.class);
        JWTService service = context.getBean(JWTService.class);
        User user = new User("lucas", "123", false);
        String token = service.tokenGen(user);
        System.out.println(token);

    }

}

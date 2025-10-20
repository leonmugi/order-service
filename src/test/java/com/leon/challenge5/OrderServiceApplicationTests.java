package com.leon.challenge5;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = OrderServiceApplication.class)
@ActiveProfiles("test")
class OrderServiceApplicationTests {

    @Test
    void contextLoads() {
        // Verifica que el contexto arranque con el perfil test
    }
}

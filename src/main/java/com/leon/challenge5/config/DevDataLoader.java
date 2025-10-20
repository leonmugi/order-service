package com.leon.challenge5.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevDataLoader implements CommandLineRunner {

    @Override
    public void run(String... args) {
        // Aquí puedes sembrar datos de ejemplo si lo necesitas.
        // Por ahora dejémoslo como indicador de que el perfil dev está activo.
        System.out.println("✅ Dev profile activo: puedes sembrar datos aquí.");
    }
}
